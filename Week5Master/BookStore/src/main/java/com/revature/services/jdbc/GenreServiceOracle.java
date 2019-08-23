package com.revature.services.jdbc;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Genre;
import com.revature.data.GenreDAO;
import com.revature.data.oracle.GenreOracle;
import com.revature.services.GenreService;

public class GenreServiceOracle implements GenreService {
	private Logger log = Logger.getLogger(GenreServiceOracle.class);
	private GenreDAO gd = new GenreOracle();
	
	@Override
	public Set<Genre> getGenres() {
		return gd.getGenres();
	}
	@Override
	public Genre getGenreById(int i) {
		return gd.getGenre(i);
	}
	@Override
	public void updateGenre(Genre g) {
		gd.updateGenre(g);
	}
	@Override
	public void deleteGenre(Genre g) {
		gd.deleteGenre(g);
	}
	@Override
	public void addGenre(Genre g) {
		gd.addGenre(g);
	}
}
