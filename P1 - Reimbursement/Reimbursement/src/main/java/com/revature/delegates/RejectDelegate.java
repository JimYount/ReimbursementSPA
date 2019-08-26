package com.revature.delegates;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.services.SubmissionService;
import com.revature.services.SubmissionServiceOracle;

public class RejectDelegate implements FrontControllerDelegate{
	private Logger log = Logger.getLogger(RejectDelegate.class);
	SubmissionService subserv = new SubmissionServiceOracle();

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		log.trace(req.getMethod() + " received by reject delegate");
		
		switch (req.getMethod()) {
		case "POST":
			subserv.rejectSubmission(Integer.parseInt(req.getParameter("subID")), req.getParameter("commentReq"));
			break;
		default:
			break;
		}
	}
}
