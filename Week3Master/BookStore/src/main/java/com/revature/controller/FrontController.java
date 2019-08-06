package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

import com.revature.delegates.FrontControllerDelegate;

public class FrontController extends DefaultServlet {
	private Logger log = Logger.getLogger(FrontController.class);
	private RequestDispatcher rd = new RequestDispatcher();

	private void process(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		log.trace(req.getRequestURI());
		//log.trace(req.getRequestURL());
		//log.trace(req.getContextPath());
		String uriSansContext = req.getRequestURI().substring(req.getContextPath().length());
		//log.trace(uriSansContext);
		//log.trace(uriSansContext.startsWith("/static"));
		if(uriSansContext.startsWith("/static")) {
			log.trace("This is static content!");
			super.doGet(req, resp);
		} else {
			log.trace("Not static");
			// What we want to do now is determine what object
			// should handle these requests
			FrontControllerDelegate fd = rd.dispatch(req, resp);
			if(fd != null) {
				fd.process(req, resp);
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		process(req, resp);
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		process(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
}
