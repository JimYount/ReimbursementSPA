package com.revature.delegates;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.services.SubmissionService;
import com.revature.services.SubmissionServiceOracle;

public class AcceptDelegate implements FrontControllerDelegate {
	SubmissionService subserv = new SubmissionServiceOracle();
	private Logger log = Logger.getLogger(AcceptDelegate.class);

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		log.trace(req.getMethod() + " received by accept delegate");
		
		switch (req.getMethod()) {
		case "POST":
			subserv.acceptSubmission(Integer.parseInt(req.getParameter("subID")));
			break;
		default:
			break;
		}
	}

}
