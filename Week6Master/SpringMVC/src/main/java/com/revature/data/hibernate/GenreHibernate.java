package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.beans.Book;
import com.revature.beans.Genre;
import com.revature.data.GenreDAO;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

@Component
public class GenreHibernate implements GenreDAO{
	@Autowired
	private HibernateUtil hu;

	@Override
	public Integer addGenre(Genre g) {
		Session s = hu.getSession();
		Transaction t = null;
		Integer id = 0;
		try {
			t = s.beginTransaction();
			id = (Integer) s.save(g);
			t.commit();
		} catch(HibernateException e) {
			if(t != null)
				t.rollback();
			LogUtil.logException(e, GenreHibernate.class);
		} finally {
			s.close();
		}
		return id;
	}

	@Override
	public Genre getGenre(Integer id) {
		Session s = hu.getSession();
		Genre g = s.get(Genre.class, id);
		s.close();
		return g;
	}

	@Override
	public Genre getGenreByGenre(String genre) {
		// I'm still learning the new Criteria. It's more complicated than it used to be.
		Session s = hu.getSession();
		CriteriaBuilder critBuilder = s.getCriteriaBuilder();
		CriteriaQuery<Genre> query = critBuilder.createQuery(Genre.class);
		Root<Genre> root = query.from(Genre.class);
		query.select(root).where(critBuilder.equal(root.get("genre"), genre));
		Query<Genre> q = s.createQuery(query);
		s.close();
		return q.getSingleResult();
	}

	@Override
	public Set<Genre> getGenres() {
		Session s = hu.getSession();
		String query = "FROM Genre";
		Query<Genre> q = s.createQuery(query, Genre.class);
		List<Genre> genreList = q.getResultList();
		Set<Genre> genreSet = new HashSet<Genre>();
		genreSet.addAll(genreList);
		s.close();
		return genreSet;
	}

	@Override
	public void updateGenre(Genre g) {
		Session s = hu.getSession();
		Transaction t = null;
		try {
			t = s.beginTransaction();
			s.update(g);
			t.commit();
		} catch(HibernateException e) {
			if(t != null)
				t.rollback();
			LogUtil.logException(e, GenreHibernate.class);
		} finally {
			s.close();
		}
	}

	@Override
	public void deleteGenre(Genre g) {
		Session s = hu.getSession();
		Transaction t = null;
		try {
			t = s.beginTransaction();
			s.delete(g);
			t.commit();
		} catch(HibernateException e) {
			if(t != null)
				t.rollback();
			LogUtil.logException(e, GenreHibernate.class);
		} finally {
			s.close();
		}
	}

	@Override
	@Deprecated
	public Set<Genre> getGenresByBook(Book b) {
		return b.getGenres();
	}
}
