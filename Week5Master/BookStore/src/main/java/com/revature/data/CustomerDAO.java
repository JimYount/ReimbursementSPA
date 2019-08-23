package com.revature.data;

import java.util.Set;

import com.revature.beans.Customer;

public interface CustomerDAO {
	/**
	 * Inserts a customer into the database.
	 * 
	 * @param Customer the Customer object to be inserted
	 */
	public void addCustomer(Customer customer);
	
	/**
	 * returns a login object from the database
	 * 
	 * @param cust the Customer object created by our service to hold customer data
	 * @return the Customer from the database that matches the id,
	 * an empty customer object if no customer exists with that id.
	 */
	public Customer getCustomer(Customer cust);
	
	/**
	 * returns a list of all customers in database
	 * 
	 * @return list of customers in database
	 */
	public Set<Customer> getCustomers();
	
	/**
	 * deletes a Customer from the database
	 * 
	 * @param Customer the Customer to be deleted
	 */
	public void deleteCustomer(Customer customer);
	
	public void updateCustomer(Customer customer);
}
