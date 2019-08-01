package com.revature.services;

import java.util.Set;

import com.revature.beans.Genre;

public interface GenreService {
	public Set<Genre> getGenres();
	public Genre getGenreById(int i);
	public void updateGenre(Genre g);
	public void deleteGenre(Genre g);
	public void addGenre(Genre g);
}
