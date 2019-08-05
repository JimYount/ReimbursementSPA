package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.services.UserService;

public class LoginServlet extends HttpServlet {
	private static UserService us = new UserService();
	private static Logger log = Logger.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// we want to use get to retrieve the login page.
		log.trace("login servlet received a get request");
		// if a loginservlet received a get request, we know
		// that we are trying to get the login page, because
		// we will not accept a login attempt via a get.
		// but we should check to see if we're logged in first?
		if(req.getSession().getAttribute("user")==null) {
			// We are not logged in.
			RequestDispatcher rd = req.getRequestDispatcher("login.html");
			rd.forward(req, resp);
		} else {
			// We are logged in.
			resp.sendRedirect("home");
		}			 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// req represents the httpRequest sent by the client.
		// The request then holds any information from our form.
		// We can assume therefore, that a username and a password
		// are being sent to us in our login method.
		// We will use our post for a login
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		log.trace("Logging in with user/pass: " + username + "/" + password);
		User u = us.login(username, password);
		log.trace(u);

		// The Session: an object that persists between requests
		// from the same client (identified by some id)

		if (u == null) {
			resp.sendRedirect("login");
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("user", u);
			resp.sendRedirect("home");
		}
	}
}
