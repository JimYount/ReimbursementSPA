package com.revature.data;

import java.util.Set;

import com.revature.beans.Book;
import com.revature.beans.Genre;

public interface GenreDAO {
	// Usually a DAO is going to be composed primarily of CRUD operations
	// CREATE - Insert
	Integer addGenre(Genre g);
	// READ - select
	Genre getGenre(Integer id);
	Genre getGenreByGenre(String genre);
	Set<Genre> getGenres();
	Set<Genre> getGenresByBook(Book b);
	// Update - Update
	void updateGenre(Genre g);
	// Delete - Delete
	void deleteGenre(Genre g);
}
