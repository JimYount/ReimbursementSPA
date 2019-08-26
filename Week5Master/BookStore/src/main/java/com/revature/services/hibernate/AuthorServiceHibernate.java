package com.revature.services.hibernate;

import java.util.Set;

import com.revature.beans.Author;
import com.revature.data.AuthorDAO;
import com.revature.data.hibernate.AuthorHibernate;
import com.revature.services.AuthorService;

public class AuthorServiceHibernate implements AuthorService {
	private AuthorDAO ad = new AuthorHibernate();
	
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
