package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.beans.InvoiceLine;
import com.revature.beans.Purchase;
import com.revature.data.PurchaseDAO;
import com.revature.data.oracle.PurchaseOracle;

public class PurchaseServiceOracle implements PurchaseService {
	private PurchaseDAO pd = new PurchaseOracle();
	private BookService bs = new BookServiceOracle();
	private CustomerService cs = new CustomerServiceOracle();
	private Logger log = Logger.getLogger(PurchaseServiceOracle.class);

	@Override
	public Purchase addBookToCart(Purchase p, Book b) {
		pd.addBookToCart(p, b);
		
		return this.getPurchase(p.getId());
	}
	@Override
	public Purchase removeBookFromCart(Purchase p, Book b) {
		pd.removeBookFromCart(p, b);
		return this.getPurchase(p.getId());
	}
	@Override
	public void emptyCart(Purchase p) {
		pd.emptyCart(p);
	}
	@Override
	public Purchase getPurchase(int i) {
		Purchase p = pd.getPurchase(i);
		if(p==null)
			return null;
		//eagerly fetch book information
		for(InvoiceLine invoice : p.getInvoiceLines())
		{
			invoice.setBook(bs.getBookById(invoice.getBook().getId()));
		}
		
		//eagerly fetch customer information
		p.setCust(cs.getCustomerById(p.getCust().getId()));
		log.debug(p);
		return p;
	}
	@Override
	public Purchase getOpenPurchase(Customer c) {
		Purchase p = pd.getPurchaseByStatus(c, "OPEN");
		log.trace(c);
		log.trace(p);
		if(p==null)
			return p;
		//eagerly fetch book information
		for(InvoiceLine invoice : p.getInvoiceLines())
		{
			invoice.setBook(bs.getBookById(invoice.getBook().getId()));
		}
				
		//eagerly fetch customer information
		p.setCust(cs.getCustomerById(p.getCust().getId()));
		log.debug(p);
		return p;
	}
	@Override
	public Set<Purchase> getPurchases() {
		Set<Purchase> purchases = pd.getPurchases();
		for(Purchase p : purchases)
		{
			p.setCust(cs.getCustomerById(p.getCust().getId()));
			for(InvoiceLine i : p.getInvoiceLines())
			{
				//eager fetching, haha
				i.setBook(bs.getBookById(i.getBook().getId()));
			}
		}
		return purchases;
	}

	@Override
	public Set<Purchase> getPurchasesByCustomer(Customer cust) {
		Set<Purchase> purchases = pd.getPurchasesByCustomer(cust);
		for(Purchase p : purchases)
		{
			for(InvoiceLine i : p.getInvoiceLines())
			{
				//eager fetching, haha
				i.setBook(bs.getBookById(i.getBook().getId()));
			}
		}
		return purchases;
	}

	@Override
	public void addPurchase(Purchase p) {
		pd.addPurchase(p);
	}

	@Override
	public void deletePurchase(Purchase p) {
		pd.deletePurchase(p);
	}
	@Override
	public void updatePurchase(Purchase p) {
		pd.updatePurchase(p);
	}

}
