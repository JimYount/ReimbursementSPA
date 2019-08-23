package com.revature.services.hibernate;

import java.util.Set;

import com.revature.beans.Author;
import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.beans.Genre;
import com.revature.data.AuthorDAO;
import com.revature.data.BookDAO;
import com.revature.data.GenreDAO;
import com.revature.data.hibernate.AuthorHibernate;
import com.revature.data.hibernate.BookHibernate;
import com.revature.data.hibernate.GenreHibernate;
import com.revature.services.BookService;

public class BookServiceHibernate implements BookService {
	private static BookDAO bd = new BookHibernate();
	private static AuthorDAO ad = new AuthorHibernate();
	private static GenreDAO gd = new GenreHibernate();

	@Override
	public Set<Book> getBooksForReadingList(Customer cust) {
		return cust.getReadingList();
	}

	@Override
	public Book getBookById(int i) {
		return bd.getBook(i);
	}

	@Override
	public void updateBook(Book b) {
		// make sure the authors are in db
		Set<Author> authors = b.getAuthors();
		for (Author a : authors) {
			if (ad.getAuthor(a.getId()) != null)
				ad.updateAuthor(a);
			else
				ad.addAuthor(a);
		}
		// make sure the genres are in db
		Set<Genre> genres = b.getGenres();
		for (Genre g : genres) {
			if (gd.getGenre(g.getId()) != null)
				gd.updateGenre(g);
			else
				gd.addGenre(g);
		}
		// update the book
		bd.updateBook(b);
	}

	@Override
	public void deleteBook(Book b) {
		bd.deleteBook(b);
	}

	@Override
	public void addBook(Book b) {
		// make sure the authors are in db
		Set<Author> authors = b.getAuthors();
		for (Author a : authors) {
			if (ad.getAuthor(a.getId()) != null)
				ad.updateAuthor(a);
			else
				ad.addAuthor(a);
		}
		// make sure the genres are in db
		Set<Genre> genres = b.getGenres();
		for (Genre g : genres) {
			if (gd.getGenre(g.getId()) != null)
				gd.updateGenre(g);
			else
				gd.addGenre(g);
		}
		// add the book
		bd.addBook(b);
	}

	@Override
	public Set<Book> getBooks() {
		return bd.getBooks();
	}

}
