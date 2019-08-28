package com.revature.data.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.beans.User;
import com.revature.data.UserDAO;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

@Component
public class UserHibernate implements UserDAO {
	@Autowired
	private HibernateUtil hu;
	@Override
	public int addUser(User user) {
		Session s = hu.getSession();
		Transaction t = null;
		Integer i = 0;
		try {
			t = s.beginTransaction();
			i = (Integer) s.save(user);
			t.commit();
		} catch(HibernateException e) {
			t.rollback();
			LogUtil.logException(e, UserHibernate.class);
		} finally {
			s.close();
		}
		return i;
	}

	@Override
	public User getUser(String username, String password) {
		Session s = hu.getSession();
		String query = "from User u where u.username=:username and u.password=:password";
		Query<User> q = s.createQuery(query, User.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		User u = q.uniqueResult();
		s.close();
		return u;
	}

	@Override
	public User getUser(User u) {
		Session s = hu.getSession();
		User ret = s.get(User.class, u.getId());
		if(ret==null) {
			String query = "from User u where u.username=:username and u.password=:password";
			Query<User> q = s.createQuery(query, User.class);
			q.setParameter("username", u.getUsername());
			q.setParameter("password", u.getPassword());
			ret = q.getSingleResult();
		}
		s.close();
		return ret;
	}

	@Override
	public User getUserById(User u) {
		Session s = hu.getSession();
		User ret = s.get(User.class, u.getId());
		s.close();
		return ret;
	}

	@Override
	public void deleteUser(User user) {
		Session s = hu.getSession();
		Transaction t = null;
		try{
			t = s.beginTransaction();
			s.delete(user.getId());
			t.commit();
		} catch(Exception e) {
			if(t != null)
				t.rollback();
			LogUtil.logException(e, UserHibernate.class);
		} finally {
			s.close();
		}
	}

	@Override
	public void updateUser(User user) {
		Session s = hu.getSession();
		Transaction t = null;
		try{
			t = s.beginTransaction();
			s.update(user.getId());
			t.commit();
		} catch(Exception e) {
			if(t != null)
				t.rollback();
			LogUtil.logException(e, UserHibernate.class);
		} finally {
			s.close();
		}
	}
}
