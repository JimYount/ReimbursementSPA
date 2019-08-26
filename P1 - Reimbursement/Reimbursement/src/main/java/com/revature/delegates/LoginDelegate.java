package com.revature.delegates;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Employee;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceOracle;

public class LoginDelegate implements FrontControllerDelegate {
	private EmployeeService empServ = new EmployeeServiceOracle();
	private ObjectMapper om = new ObjectMapper();
	private Logger log = Logger.getLogger(LoginDelegate.class);

	// @Override
	public void process(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		log.trace(req.getMethod() + " received by login delegate");
		HttpSession session = req.getSession();

		switch (req.getMethod()) {
		case "POST":
			Employee emp = (Employee) session.getAttribute("loggedEmployee");
			if (emp != null)
				respondWithUser(resp, emp);
			else
				checkLogin(req, resp);
			break;
		case "DELETE":
			session.invalidate();
			log.trace("User logged out");
			break;
		default:
			break;
		}
	}

	public void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.trace("Logging in!");
		HttpSession session = req.getSession();

		Employee emp = (Employee) session.getAttribute("loggedEmployee");
		if (emp != null) {
			respondWithUser(resp, emp);
		} else {
			String username = req.getParameter("user");
			String password = req.getParameter("pass");

			emp = empServ.getEmployee(username, password);

			if (emp != null) {
				log.trace("customer being added to session");
				session.setAttribute("loggedEmployee", emp);
			}
			
			if (emp == null) {
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No user found with those credentials");
			} else {
				respondWithUser(resp, emp);
			}
		}
	}

	private void respondWithUser(HttpServletResponse resp, Employee emp) throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		String e = om.writeValueAsString(emp);
		StringBuilder sb = new StringBuilder("{\"employee\":");
		sb.append(e);
		sb.append("}");
		resp.getWriter().write(sb.toString());
	}
}
