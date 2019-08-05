package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.User;

public class HomeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Check to see if we're logged in?
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		if(u==null) {
			resp.sendRedirect("login");
		} else {
			PrintWriter pw = resp.getWriter();
			pw.write("<html><head><title>Welcome, "+u.getUsername()+"</title></head><body>");
			pw.write("<div style=\"background-color:"+u.getFavoriteColor()+"\">"
					+ "<h1>Welcome back, "+u.getFirstName()+" "+u.getLastName()+"</h1>"
					+ "<form action=\"logout\" method=\"post\">"
					+ "<input type=\"submit\" value=\"Logout\"/>"
					+ "</form></div>"
					+ "</body></html>");
		}
	}
}
