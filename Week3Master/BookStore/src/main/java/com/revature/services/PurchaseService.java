package com.revature.services;

import java.util.Set;

import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.beans.Purchase;

public interface PurchaseService {
	public Purchase addBookToCart(Purchase p, Book b);
	public Purchase removeBookFromCart(Purchase p, Book b);
	public void emptyCart(Purchase p);
	public Purchase getPurchase(int i);
	public Purchase getOpenPurchase(Customer c);
	public Set<Purchase> getPurchasesByCustomer(Customer cust);
	public Set<Purchase> getPurchases();
	public void addPurchase(Purchase p);
	public void deletePurchase(Purchase p);
	public void updatePurchase(Purchase p);
}
