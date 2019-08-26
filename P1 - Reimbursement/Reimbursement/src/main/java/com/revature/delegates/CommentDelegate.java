package com.revature.delegates;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.beans.Comment;
import com.revature.services.CommentService;
import com.revature.services.CommentServiceOracle;

public class CommentDelegate implements FrontControllerDelegate {
	CommentService comserv = new CommentServiceOracle();
	private Logger log = Logger.getLogger(RejectDelegate.class);

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		log.trace(req.getMethod() + " received by reject delegate");

		switch (req.getMethod()) {
		case "POST":
			
			break;
		default:
			break;
		}
	}

	public void createComment(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		Comment comment = new Comment();
		
		comment.setCommenterID(Integer.parseInt(req.getParameter("employeeID")));
		comment.setComments(req.getParameter("commentReq"));
		comment.setSubmissionID(Integer.parseInt(req.getParameter("subID")));
		
		comserv.requestComment(comment);
	}
}