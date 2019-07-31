package com.revature.data;

import java.util.Set;

import com.revature.beans.Genre;

public interface GenreDAO {
	// Usually a DAO has CRUD methods
	// CREATE = INSERT
	Integer addGenre(Genre g);
	// READ = SELECT
	Genre getGenre(Integer id);
	Genre getGenreByGenre(String genre);
	Set<Genre> getGenres();
	//Set<Genre> getGenresByBook(Book b);
	// UPDATE = UPDATE
	void updateGenre(Genre g);
	// DELETE = DELETE
	void deleteGenre(Genre g);
}
