package com.revature.delegates;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Submission;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceOracle;


public class ViewDelegate implements FrontControllerDelegate {
	EmployeeService empserv = new EmployeeServiceOracle();
	private ObjectMapper om = new ObjectMapper();
	private Logger log = Logger.getLogger(ViewDelegate.class);

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		
		log.trace(req.getMethod() + " received by view delegate");

		switch (req.getMethod()) {
		case "POST":
			respondWithSubmissions(resp, empserv.getSubmissions(Integer.parseInt(req.getParameter("employeeID"))));
			break;
		default:
			break;
		}
	}
	
	private void respondWithSubmissions(HttpServletResponse resp, Set<Submission> sub) throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		
		String submit = om.writeValueAsString(sub);
		StringBuilder sb = new StringBuilder("");
		sb.append(submit);
		
		resp.getWriter().write(sb.toString());
	}
}
