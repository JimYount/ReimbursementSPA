package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class HelloServlet extends HttpServlet {
	private Logger log = Logger.getLogger(HelloServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		log.trace("Handling a GET request!");
		PrintWriter pw = resp.getWriter();
		// PrintWriter: An object (retrieved from the Response object)
		// that allows me to write directly to the body of the
		// http response.
		pw.write("<html><head><title>Hello World</title></head><body>");
		pw.write("<a href=\"index.html\">request a color</a></body></html>");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		log.trace("Handling a POST request!");
		String color = req.getParameter("color");
		log.trace(color);
		
		PrintWriter pw = resp.getWriter();
		pw.write("<html><head><title>Hello World</title></head><body>");
		pw.write("<div style=\"background-color:"+color+"\">Requested color was: "+color+"</div>");
		pw.write("<a href=\"index.html\">request a color</a></body></html>");
	}
	
	@Override
	public void init() {
		// This method is  a convenience (safety) method
		// This method is called by the REAL init(ServletConfig) method
		// You are not supposed to override init(ServletConfig)
		// It is important and may break your code.
		log.trace("HelloServlet init()");
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		// This is the real init method. Do not override.
		log.trace("HelloServlet init(ServletConfig)");
		super.init(config);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		// called every time a request is sent to the server
		log.trace("Service!");
		// HTTPServlet Service calls our doGet/Post/Put/Delete methods
		super.service(req, resp);
	}
	@Override
	public void destroy() {
		log.trace("Destroy!");
		super.destroy();
	}
}
