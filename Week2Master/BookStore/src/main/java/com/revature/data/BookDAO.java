package com.revature.data;

import java.util.Set;

import com.revature.beans.Author;
import com.revature.beans.Book;
import com.revature.beans.Customer;

public interface BookDAO {
	// create
	public int addBook(Book b);
	// read
	public Book getBook(int i);
	public Book getBookByIsbn(String isbn);
	public Set<Book> getBooks();
	public Set<Book> getBooksByAuthor(Author a);
	public Set<Book> getReadingList(Customer c);
	// update
	public void updateBook(Book b);
	// delete
	public void deleteBook(Book b);
}
