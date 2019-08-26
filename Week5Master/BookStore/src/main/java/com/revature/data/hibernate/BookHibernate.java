package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Author;
import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.data.BookDAO;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class BookHibernate implements BookDAO {
	private HibernateUtil hu = HibernateUtil.getInstance();
	
	@Override
	public int addBook(Book b) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(b);
			tx.commit();
		} catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			LogUtil.logException(e, BookHibernate.class);
		} finally {
			s.close();
		}
		return b.getId();
	}

	@Override
	public Book getBook(int i) {
		Book b;
		Session s = hu.getSession();
		b = s.get(Book.class, i);
		s.close();
		return b;
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		Book b;
		Session s = hu.getSession();
		String query = "from Book where isbn10=:isbn or isbn13=:isbn";
		Query<Book> q = s.createQuery(query, Book.class);
		q.setParameter("isbn", isbn);
		b = q.uniqueResult();
		s.close();
		return b;
	}

	@Override
	public Set<Book> getBooks() {
		Session s = hu.getSession();
		String query = "FROM Book";
		Query<Book> q = s.createQuery(query, Book.class);
		List<Book> bookList = q.getResultList();
		Set<Book> bookSet = new HashSet<Book>();
		bookSet.addAll(bookList);
		s.close();
		return bookSet;
	}

	@Override
	public Set<Book> getBooksByAuthor(Author a) {
		Session s = hu.getSession();
		String query = "FROM Book b where :author = some elements(b.authors)";
		Query<Book> q = s.createQuery(query, Book.class);
		q.setParameter("author", a);
		List<Book> bookList = q.getResultList();
		Set<Book> bookSet = new HashSet<Book>();
		bookSet.addAll(bookList);
		s.close();
		return bookSet;
	}

	@Override
	@Deprecated
	public Set<Book> getReadingList(Customer c) {
		return c.getReadingList();
	}

	@Override
	public void updateBook(Book b) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(b);
			tx.commit();
		} catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			LogUtil.logException(e, BookHibernate.class);
		} finally {
			s.close();
		}
	}

	@Override
	public void deleteBook(Book b) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(b);
			tx.commit();
		} catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			LogUtil.logException(e, BookHibernate.class);
		} finally {
			s.close();
		}
	}

}
