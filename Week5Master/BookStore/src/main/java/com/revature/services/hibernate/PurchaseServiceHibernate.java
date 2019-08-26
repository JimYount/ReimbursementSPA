package com.revature.services.hibernate;

import java.util.Set;

import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.beans.Purchase;
import com.revature.data.PurchaseDAO;
import com.revature.data.hibernate.PurchaseHibernate;
import com.revature.services.PurchaseService;

public class PurchaseServiceHibernate implements PurchaseService {
	private PurchaseDAO pd = new PurchaseHibernate();

	@Override
	public Purchase addBookToCart(Purchase p, Book b) {
		pd.addBookToCart(p, b);
		
		return this.getPurchase(p.getId());
	}
	@Override
	public Purchase removeBookFromCart(Purchase p, Book b) {
		pd.removeBookFromCart(p, b);
		return this.getPurchase(p.getId());
	}
	@Override
	public void emptyCart(Purchase p) {
		pd.emptyCart(p);
	}
	@Override
	public Purchase getPurchase(int i) {
		return pd.getPurchase(i);
	}
	@Override
	public Purchase getOpenPurchase(Customer c) {
		Purchase p = pd.getPurchaseByStatus(c, "OPEN");

		return p;
	}
	@Override
	public Set<Purchase> getPurchases() {
		Set<Purchase> purchases = pd.getPurchases();

		return purchases;
	}

	@Override
	public Set<Purchase> getPurchasesByCustomer(Customer cust) {
		Set<Purchase> purchases = pd.getPurchasesByCustomer(cust);

		return purchases;
	}

	@Override
	public void addPurchase(Purchase p) {
		pd.addPurchase(p);
	}

	@Override
	public void deletePurchase(Purchase p) {
		pd.deletePurchase(p);
	}
	@Override
	public void updatePurchase(Purchase p) {
		pd.updatePurchase(p);
	}
}
