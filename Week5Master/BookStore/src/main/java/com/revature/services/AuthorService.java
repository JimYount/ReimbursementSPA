package com.revature.services;

import java.util.Set;

import com.revature.beans.Author;

public interface AuthorService {
	public Set<Author> getAuthors();
	public Author getAuthorById(int i);
	public void updateAuthor(Author a);
	public void deleteAuthor(Author a);
	public void addAuthor(Author a);
}
