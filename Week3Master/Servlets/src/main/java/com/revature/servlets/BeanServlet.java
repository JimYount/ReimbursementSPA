package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Bean;

public class BeanServlet extends HttpServlet {
	private Logger log = Logger.getLogger(BeanServlet.class);
	private ArrayList<Bean> beans = new ArrayList<Bean>();
	{
		beans.add(new Bean("Richard", "pinto", 1,1.0,false));
		beans.add(new Bean("Adam", "green", 1, 2.0, true));
		beans.add(new Bean("Ryan", "black eye", 2, 1.4, true));
		beans.add(new Bean("Shelby", "lima", 5, 1.2, true));
	}
	
	// Jackson is a library for marshalling Java Objects as  JSON
	// and unmarshalling JSON into Java Objects
	// Jackson performs marshalling using the ObjectMapper object
	private ObjectMapper om = new ObjectMapper();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Bean b = beans.get(new Random().nextInt(beans.size()));
		Collections.shuffle(beans);
		Bean b = beans.get(0);
		String respBean = om.writeValueAsString(b);
		resp.getWriter().write(respBean);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Bean b = om.readValue(req.getInputStream(), Bean.class);
		log.trace(b);
		beans.add(b);
		resp.sendRedirect("bean");
	}
	
	
}
