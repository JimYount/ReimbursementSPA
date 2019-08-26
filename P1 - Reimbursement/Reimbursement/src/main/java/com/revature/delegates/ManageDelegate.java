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
import com.revature.services.SubmissionService;
import com.revature.services.SubmissionServiceOracle;

public class ManageDelegate implements FrontControllerDelegate {
	SubmissionService subserv = new SubmissionServiceOracle();
	private ObjectMapper om = new ObjectMapper();
	private Logger log = Logger.getLogger(ManageDelegate.class);

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		log.trace(req.getMethod() + " received by manage delegate");

		switch (req.getMethod()) {
		case "POST":
			respondWithSubmissions(resp, subserv.getSubmissions(Integer.parseInt(req.getParameter("employeeID"))));
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
