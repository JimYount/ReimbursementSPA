package com.revature.services.hibernate;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.data.GenreDAO;
import com.revature.data.hibernate.GenreHibernate;
import com.revature.services.GenreService;

public class GenreServiceHibernate implements GenreService {
	private GenreDAO gd = new GenreHibernate();
	
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
