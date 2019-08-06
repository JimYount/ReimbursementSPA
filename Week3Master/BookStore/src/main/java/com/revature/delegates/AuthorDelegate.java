package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Author;
import com.revature.services.AuthorService;
import com.revature.services.AuthorServiceOracle;
import com.revature.utils.JsonParseUtil;

public class AuthorDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(AuthorDelegate.class);
	private AuthorService as = new AuthorServiceOracle();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null||"".equals(path)) {
			// Collections here
			collectionOperations(req, resp);
		} else {
			try {
				authorTimes(req,resp, Integer.parseInt(path));
			} catch(NumberFormatException e) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
	}

	private void authorTimes(HttpServletRequest req, HttpServletResponse resp, int authorId) throws IOException {
		log.trace("Operating on a specific book with id: "+authorId);
		PrintWriter writer = resp.getWriter();
		Author a = as.getAuthorById(authorId);
		switch(req.getMethod()) {
		case "GET": 
			resp.getWriter().write(om.writeValueAsString(a));
			return;
		case "PUT":
			// update the object in the db
			a = JsonParseUtil.parseJsonInput(req.getInputStream(), Author.class, resp);
			if(a==null) {
				return;
			}
			as.updateAuthor(a);
			writer.write(om.writeValueAsString(a));
			return;
		case "DELETE":
			as.deleteAuthor(a);
			resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void collectionOperations(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		switch(req.getMethod()) {
		case "GET":
			log.trace("Retrieving a list of all authors");
			Set<Author> authors = as.getAuthors();
			resp.getWriter().write(om.writeValueAsString(authors));
			break;
		case "POST":
			log.trace("Posting an author to db.");

			Author a;
			a = JsonParseUtil.parseJsonInput(req.getInputStream(), Author.class, resp);
			if(a==null) return;
			as.addAuthor(a);
			resp.setStatus(HttpServletResponse.SC_CREATED);
			resp.getWriter().write(om.writeValueAsString(a));
		}
	}

}
