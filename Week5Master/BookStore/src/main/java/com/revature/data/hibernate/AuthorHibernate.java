package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Author;
import com.revature.beans.Book;
import com.revature.data.AuthorDAO;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class AuthorHibernate implements AuthorDAO {
private HibernateUtil hu = HibernateUtil.getInstance();
	
	@Override
	public int addAuthor(Author a) {
		Session s = hu.getSession();
		Transaction t = null;
		Integer id = 0;
		try {
			t = s.beginTransaction();
			id = (Integer) s.save(a);
			t.commit();
		} catch (HibernateException e) {
			if(t!=null)
				t.rollback();
			LogUtil.logException(e, AuthorHibernate.class);
		} finally {
			s.close();
		}
		return id;
	}

	@Override
	public Author getAuthor(int id) {
		Session s = hu.getSession();
		Author a = s.get(Author.class, id);
		s.close();
		return a;
	}

	@Override
	public Author getAuthorByName(String firstname, String lastname) {
		Session s = hu.getSession();
		Query<Author> q = s.createQuery("FROM Author where first =:first and last=:last", Author.class);
		q.setParameter("first", firstname);
		q.setParameter("last",  lastname);
		Author a = q.uniqueResult();
		s.close();
		return a;
	}

	@Override
	public Set<Author> getAuthors() {
		Session s = hu.getSession();
		Query<Author> q = s.createQuery("FROM Author", Author.class);
		List<Author> authorList = q.getResultList();
		Set<Author> authorSet = new HashSet<Author>(authorList);
		s.close();
		return authorSet;
	}

	@Override
	@Deprecated
	public Set<Author> getAuthorsByBook(Book b) {
		return b.getAuthors();
	}

	@Override
	public void updateAuthor(Author a) {
		Session s = hu.getSession();
		Transaction t = null;
		try{
			t = s.beginTransaction();
			s.update(a);
			t.commit();
		} catch(Exception e) {
			if(t != null) {
				t.rollback();
			}
			LogUtil.logException(e, AuthorHibernate.class);
		} finally {
			s.close();
		}
	}

	@Override
	public void deleteAuthor(Author a) {
		Session s = hu.getSession();
		Transaction t = null;
		try{
			t = s.beginTransaction();
			s.delete(a);
			t.commit();
		} catch(Exception e) {
			if(t != null) {
				t.rollback();
			}
			LogUtil.logException(e, AuthorHibernate.class);
		} finally {
			s.close();
		}
	}
}
