package com.revature.data;

import java.util.Set;

import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.beans.Purchase;

public interface PurchaseDAO
{
	public double addBookToCart(Purchase purchase, Book book);
	public double removeBookFromCart(Purchase purchase, Book book);
	public void emptyCart(Purchase purchase);
	public int addPurchase(Purchase p);
	public Purchase getPurchase(int i);
	public Set<Purchase> getPurchases();
	public Set<Purchase> getPurchasesByCustomer(Customer c);
	public void deletePurchase(Purchase p);
	public void updatePurchase(Purchase p);
	public Purchase getPurchaseByStatus(Customer c, String status);
}
