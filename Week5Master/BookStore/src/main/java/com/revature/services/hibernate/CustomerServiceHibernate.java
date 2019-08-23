package com.revature.services.hibernate;

import java.util.Set;

import com.revature.beans.Customer;
import com.revature.data.CustomerDAO;
import com.revature.data.UserDAO;
import com.revature.data.hibernate.CustomerHibernate;
import com.revature.data.hibernate.UserHibernate;
import com.revature.services.CustomerService;

public class CustomerServiceHibernate implements CustomerService {
	private UserDAO ud = new UserHibernate();
	private CustomerDAO cd = new CustomerHibernate();
	
	@Override
	public Customer getCustomer(String username, String password) {
		Customer cust = new Customer(null, username, password);
		cust = (Customer) ud.getUser(cust);
		return cust;
	}

	@Override
	public Customer getCustomerById(int i) {
		Customer cust = new Customer(i);
		cust = (Customer) ud.getUser(cust);
		return cust;
	}

	@Override
	public Set<Customer> getCustomers() {
		Set<Customer> custList = cd.getCustomers();
		return custList;
	}

	@Override
	public void deleteCustomer(Customer cust) {
		cd.deleteCustomer(cust);
	}

	@Override
	public void updateCustomer(Customer cust) {
		cd.updateCustomer(cust);
	}

	@Override
	public void addCustomer(Customer cust) {
		cd.addCustomer(cust);
	}

}
