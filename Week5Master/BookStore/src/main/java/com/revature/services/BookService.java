package com.revature.services;

import java.util.Set;

import com.revature.beans.Book;
import com.revature.beans.Customer;

public interface BookService {
	public Set<Book> getBooksForReadingList(Customer cust);
	public Set<Book> getBooks();
	public Book getBookById(int i);
	public void updateBook(Book b);
	public void deleteBook(Book b);
	public void addBook(Book b);
}
