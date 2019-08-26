package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Customer;
import com.revature.data.CustomerDAO;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class CustomerHibernate implements CustomerDAO {
	private HibernateUtil hu = HibernateUtil.getInstance();
	private Logger log = Logger.getLogger(CustomerHibernate.class);
	
	@Override
	public void addCustomer(Customer customer) {
		Session s = hu.getSession();
		Transaction t = null;
		try {
			t = s.beginTransaction();
			s.save(customer);
			t.commit();
		} catch (HibernateException e) {
			if(t != null)
				t.rollback();
			LogUtil.logException(e, EmployeeHibernate.class);
		} finally {
			s.close();
		}
	}

	@Override
	public Customer getCustomer(Customer cust) {
		Session s = hu.getSession();
		Customer c;
		log.trace(cust);
		if(cust.getId()!=0) {
			//this means we're going to retrieve by id
			c = s.get(Customer.class, cust.getId());
		} else {
			//this means we're getting by user/pass
			String query = "from Customer c where c.username=:username and c.password=:password";
			Query<Customer> q = s.createQuery(query, Customer.class);
			q.setParameter("username", cust.getUsername());
			q.setParameter("password", cust.getPassword());
			c = q.getSingleResult();
		}
		s.close();
		return c;
	}

	@Override
	public Set<Customer> getCustomers() {
		Session s = hu.getSession();
		String query = "FROM Customer";
		Query<Customer> q = s.createQuery(query, Customer.class);
		List<Customer> custList = q.getResultList();
		Set<Customer> custSet = new HashSet<Customer>();
		custSet.addAll(custList);
		s.close();
		return custSet;
	}

	@Override
	public void deleteCustomer(Customer customer) {
		Session s = hu.getSession();
		Transaction t = null;
		try {
			t = s.beginTransaction();
			s.delete(customer);
			t.commit();
		} catch (HibernateException e) {
			if(t != null)
				t.rollback();
			LogUtil.logException(e, EmployeeHibernate.class);
		} finally {
			s.close();
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		Session s = hu.getSession();
		Transaction t = null;
		try {
			t = s.beginTransaction();
			s.update(customer);
			t.commit();
		} catch (HibernateException e) {
			if(t != null)
				t.rollback();
			LogUtil.logException(e, EmployeeHibernate.class);
		} finally {
			s.close();
		}
	}
}
