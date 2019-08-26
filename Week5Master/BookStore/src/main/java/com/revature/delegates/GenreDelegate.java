package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Genre;
import com.revature.services.GenreService;
import com.revature.services.hibernate.GenreServiceHibernate;
import com.revature.utils.JsonParseUtil;

public class GenreDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(GenreDelegate.class);
	private GenreService gs = new GenreServiceHibernate();
	private ObjectMapper om = new ObjectMapper();
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		// We need to determine what to do based on that path.
		if(path==null || "".equals(path)) {
			// We are operating on the collection of genres as a whole
			collectionOperations(req,resp);
		} else {
			// we are operating on a single genre
			singleGenre(req, resp, Integer.parseInt(path));
		}
	}
	private void singleGenre(HttpServletRequest req, HttpServletResponse resp, int genreId) 
			throws IOException {
		// GET, PUT, DELETE
		Genre g = gs.getGenreById(genreId);
		switch(req.getMethod()) {
		case "GET":
			resp.getWriter().write(om.writeValueAsString(g));
			break;
		case "PUT":
			g = JsonParseUtil.parseJsonInput(req.getInputStream(), Genre.class, resp);
			gs.updateGenre(g);
			break;
		case "DELETE":
			gs.deleteGenre(g);
			break;
		default: resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}
	private void collectionOperations(HttpServletRequest req, HttpServletResponse resp) 
		throws IOException, ServletException {
		// Only GETS and POSTS should happen on our collections
		switch(req.getMethod()) {
		case "GET":
			resp.getWriter().write(om.writeValueAsString(gs.getGenres()));
			break;
		case "POST":
			Genre g = JsonParseUtil.parseJsonInput(req.getInputStream(), Genre.class, resp);
			gs.addGenre(g);
			if(g.getId()==0) {
				resp.sendError(HttpServletResponse.SC_CONFLICT);
				return;
			}
			resp.setStatus(HttpServletResponse.SC_CREATED);
			resp.getWriter().write(om.writeValueAsString(g));
			break;
		default: resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}

}
