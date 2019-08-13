package com.revature.cc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfigContext extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		// The Servlet Config belongs to the specific servlet
		String config = this.getServletConfig().getInitParameter("cheese");
		// The Servlet Context belongs to all servlets
		String context = this.getServletContext().getInitParameter("cheese");
		
		resp.getWriter().write(config+"/"+context);
	}
}
