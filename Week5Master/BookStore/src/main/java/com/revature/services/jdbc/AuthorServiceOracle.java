package com.revature.services.jdbc;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Author;
import com.revature.data.AuthorDAO;
import com.revature.data.oracle.AuthorOracle;
import com.revature.services.AuthorService;

public class AuthorServiceOracle implements AuthorService {
	private Logger log = Logger.getLogger(AuthorServiceOracle.class);
	private AuthorDAO ad = new AuthorOracle();
	
	@Override
	public Set<Author> getAuthors() {
		return ad.getAuthors();
	}

	@Override
	public Author getAuthorById(int i) {
		return ad.getAuthor(i);
	}

	@Override
	public void updateAuthor(Author a) {
		ad.updateAuthor(a);
	}

	@Override
	public void deleteAuthor(Author a) {
		ad.deleteAuthor(a);
	}

	@Override
	public void addAuthor(Author a) {
		ad.addAuthor(a);
	}

}
