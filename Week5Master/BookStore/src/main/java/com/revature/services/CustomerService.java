package com.revature.services;

import java.util.Set;

import com.revature.beans.Customer;

public interface CustomerService {
	public Customer getCustomer(String username, String password);
	public Customer getCustomerById(int i);
	public Set<Customer> getCustomers();
	public void deleteCustomer(Customer cust);
	public void updateCustomer(Customer cust);
	public void addCustomer(Customer cust);
}
