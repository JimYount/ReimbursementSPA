package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Bear;
import com.revature.util.HibernateUtil;

public class BearDaoImpl implements BearDao {
	private Logger log = Logger.getLogger(BearDaoImpl.class);
	private HibernateUtil hu = HibernateUtil.getInstance();

	@Override
	public Bear addBear(Bear b) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(b);
			tx.commit();
		} catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			log.error(e.getMessage());
		} finally {
			s.close();
		}
		return b;
	}

	@Override
	public Bear getBearById(int i) {
		Session s = hu.getSession();
		Bear b = s.get(Bear.class, i);
		s.close();
		return b;
	}

	@Override
	public Set<Bear> getBears() {
		Session s = hu.getSession();
		String query = "from com.revature.beans.Bear"; // Select * from bear
		Query<Bear> q = s.createQuery(query, Bear.class);
		List<Bear> bearList = q.getResultList();
		s.close();
		return new HashSet<Bear>(bearList);
	}

	@Override
	public Bear updateBear(Bear b) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(b);
			tx.commit();
		} catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			log.error(e.getMessage());
		} finally {
			s.close();
		}
		return b;
	}

	@Override
	public void deleteBear(Bear b) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(b);
			tx.commit();
		} catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			log.error(e.getMessage());
		} finally {
			s.close();
		}
	}

}
