package com.revature.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.beans.Employee;
import com.revature.beans.InvoiceLine;
import com.revature.beans.Purchase;
import com.revature.services.BookService;
import com.revature.services.PurchaseService;
import com.revature.services.hibernate.BookServiceHibernate;
import com.revature.services.hibernate.PurchaseServiceHibernate;

public class PurchaseDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(PurchaseDelegate.class);
	private BookService bs = new BookServiceHibernate();
	private PurchaseService ps = new PurchaseServiceHibernate();
	private ObjectMapper om = new ObjectMapper();

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		Employee emp = (Employee) session.getAttribute("loggedEmployee");
		Customer cust = (Customer) session.getAttribute("loggedCustomer");

		String path = (String) req.getAttribute("path");
		log.trace(path);

		if (path == null || "".equals(path)) {

			// then we know we're operating on the entire collection. Only get (retrieval)
			// and post (creation) are allowed
			modifyCollection(req, resp);
		} else {
			// Either we're operating on a specific purchase or we're wrong
			// first, lets do what we would do if we're operating on a single purchase
			if (path.indexOf("/") == -1) {
				// If there is no /, then we must be getting or deleting a purchase
				// employees can operate on any purchase
				log.trace("modifying a single purchase");
				modifyPurchase(req, resp, cust, emp, Integer.parseInt(path.toString()));
				return;
			} else {
				// first, get the id of the purchase
				int purchId = Integer.parseInt(path.substring(0, path.indexOf("/")));
				log.trace("purchase Id: " + purchId);

				// second, get the bookId
				path = path.substring(path.indexOf("/") + 1);
				if (path.indexOf("/") == -1) {
					// if there is no bookId, the request is bad
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}

				int bookId = Integer.parseInt(path.substring(path.indexOf("/") + 1));
				log.trace("The book id is " + bookId);
				// third, ensure that the path is correct
				path = path.substring(0, path.indexOf("/"));
				if (!"book".equals(path)) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
				modifyCart(req, resp, purchId, bookId);
			}
		}
	}

	private void modifyCart(HttpServletRequest req, HttpServletResponse resp, int purchId, int bookId)
			throws IOException {
		Purchase p = ps.getPurchase(purchId);
		Book b = bs.getBookById(bookId);
		log.trace("book id: " + bookId + " purch id: " + purchId + "\n" + p + "\n" + b);
		if (p == null || b == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		switch (req.getMethod()) {
		case "PUT":
			p = ps.addBookToCart(p, b);
			resp.getWriter().write(om.writeValueAsString(p));
			break;
		case "DELETE":
			p = ps.removeBookFromCart(p, b);
			resp.getWriter().write(om.writeValueAsString(p));
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void modifyCollection(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Employee emp = (Employee) session.getAttribute("loggedEmployee");
		Customer cust = (Customer) session.getAttribute("loggedCustomer");
		log.trace(req.getMethod()+" sent to modifyCollection()");
		switch (req.getMethod()) {
		case "GET":
			if (emp == null) {
				log.trace("A non-employee is attempting to get all of their purchases");
				if (cust == null) {
					log.trace("There is no logged-in user. Not authorized to make request");
					resp.sendError(HttpServletResponse.SC_FORBIDDEN);
					return;
				}
				getCustomerPurchases(req, resp, cust);
			} else {
				getAllPurchases(req, resp);
			}
			break;
		case "POST":
			log.trace("post called on purchases");
			if (cust == null) {
				log.trace("a non customer tried to open a purchase");
				resp.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
			Purchase p = ps.getOpenPurchase(cust);
			if (p == null) {
				p = new Purchase();
				p.setCust(cust);
				p.setStatus("OPEN");
				p.setInvoiceLines(new HashSet<InvoiceLine>());
				try {
					ps.addPurchase(p);
					resp.setStatus(HttpServletResponse.SC_CREATED);
				} catch (Exception e) {
					resp.sendError(HttpServletResponse.SC_CONFLICT);
				}
			}
			resp.getWriter().write(om.writeValueAsString(p));
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getCustomerPurchases(HttpServletRequest req, HttpServletResponse resp, Customer cust)
			throws JsonProcessingException, IOException {
		log.info("getting customer purchases");
		Set<Purchase> purchases = ps.getPurchasesByCustomer(cust);
		log.trace(purchases);
		resp.getWriter().write(om.writeValueAsString(purchases));
	}

	private void modifyPurchase(HttpServletRequest req, HttpServletResponse resp, Customer cust, Employee emp, int id)
			throws IOException {
		Purchase purch = ps.getPurchase(id);
		if (authorizePurchase(cust, emp, purch)) {
			switch (req.getMethod()) {
			case "GET":
				resp.getWriter().write(om.writeValueAsString(purch));
				break;
			case "PUT":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				Purchase p = om.readValue(sb.toString(), Purchase.class);
				log.trace(p);
				ps.updatePurchase(p);
				resp.getWriter().write(om.writeValueAsString(p));
				break;
			case "DELETE":
				ps.emptyCart(purch);
				break;
			default:
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private boolean authorizePurchase(Customer cust, Employee emp, Purchase purch) {
		//short circuit on whether there is an employee or not
		//log.trace(cust+" "+emp);
		log.trace("Authorization is "+(emp!=null || cust != null && cust.getId() == purch.getCust().getId() ));
		return ((emp != null || cust != null && cust.getId() == purch.getCust().getId()) );
	}

	private void getAllPurchases(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
		log.info("Retrieving all purchases");
		Set<Purchase> purchases = ps.getPurchases();
		resp.getWriter().write(om.writeValueAsString(purchases));
	}

}
