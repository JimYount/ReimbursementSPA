package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.StoredProcedureQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.beans.Purchase;
import com.revature.data.PurchaseDAO;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class PurchaseHibernate implements PurchaseDAO {
private HibernateUtil hu = HibernateUtil.getInstance();
	
	@Override
	public double addBookToCart(Purchase purchase, Book book) {
		Session s = hu.getSession();
		StoredProcedureQuery q = s.createNamedStoredProcedureQuery("addBook");
		q.setParameter(1, purchase.getId());
		q.setParameter(2, book.getId());
		q.execute();
		Double total = (Double) q.getOutputParameterValue(3);
		s.close();
		return total;
	}

	@Override
	public double removeBookFromCart(Purchase purchase, Book book) {
		Session s = hu.getSession();
		StoredProcedureQuery q = s.createNamedStoredProcedureQuery("removeBook");
		q.setParameter(1, purchase.getId());
		q.setParameter(2, book.getId());
		q.execute();
		Double total = (Double) q.getOutputParameterValue(3);
		s.close();
		return total==null? 0 : total;
	}

	@Override
	public void emptyCart(Purchase purchase) {
		Session s = hu.getSession();
		StoredProcedureQuery q = s.createNamedStoredProcedureQuery("emptyCart");
		q.setParameter(1, purchase.getId());
		q.execute();
		s.close();
	}

	@Override
	public int addPurchase(Purchase p) {
		Session s = hu.getSession();
		Transaction t = null;
		Integer id = 0;
		try {
			t = s.beginTransaction();
			id = (Integer) s.save(p);
			t.commit();
		} catch(HibernateException e) {
			t.rollback();
		} finally {
			s.close();
		}
		return id;
	}

	@Override
	public Purchase getPurchase(int i) {
		Session s = hu.getSession();
		Purchase p = s.get(Purchase.class, i);
		s.close();
		return p;
	}

	@Override
	public Set<Purchase> getPurchases() {
		Session s = hu.getSession();
		String query = "FROM Purchase";
		Query<Purchase> q = s.createQuery(query, Purchase.class);
		List<Purchase> purchaseList = q.getResultList();
		Set<Purchase> purchaseSet = new HashSet<Purchase>();
		purchaseSet.addAll(purchaseList);
		s.close();
		return purchaseSet;
	}

	@Override
	public Set<Purchase> getPurchasesByCustomer(Customer c) {
		Session s = hu.getSession();
		String query = "FROM Purchase p where p.cust = :cust";
		Query<Purchase> q = s.createQuery(query, Purchase.class);
		q.setParameter("cust", c);
		List<Purchase> purchaseList = q.getResultList();
		Set<Purchase> purchaseSet = new HashSet<Purchase>();
		purchaseSet.addAll(purchaseList);
		s.close();
		return purchaseSet;
	}

	@Override
	public void deletePurchase(Purchase p) {
		Session s = hu.getSession();
		Transaction t = null;
		try {
			t = s.beginTransaction();
			s.delete(p);
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
	public void updatePurchase(Purchase p) {
		Session s = hu.getSession();
		Transaction t = null;
		try {
			t = s.beginTransaction();
			s.update(p);
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
	public Purchase getPurchaseByStatus(Customer c, String status) {
		Session s = hu.getSession();
		String query = "FROM Purchase p where p.cust = :cust and p.status = :status";
		Query<Purchase> q = s.createQuery(query, Purchase.class);
		q.setParameter("cust", c);
		q.setParameter("status", status);
		Purchase p = q.uniqueResult();
		s.close();
		return p;
	}
}
