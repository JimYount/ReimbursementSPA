package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Customer;
import com.revature.beans.User;
import com.revature.data.AddressDAO;
import com.revature.data.AddressOracle;
import com.revature.data.CustomerDAO;
import com.revature.data.CustomerOracle;
import com.revature.data.UserDAO;
import com.revature.data.UserOracle;

public class CustomerServiceOracle implements CustomerService {
	private Logger log = Logger.getLogger(CustomerServiceOracle.class);
	private UserDAO ud = new UserOracle();
	private CustomerDAO cd = new CustomerOracle();
	private AddressDAO addrdao = new AddressOracle();
	private BookService bs = new BookServiceOracle();
	
	@Override
	public Customer getCustomer(String username, String password) {
		Customer cust = new Customer(0, username, password);
		cust = (Customer) ud.getUser(cust);
		if(cust.getId()==0) {
			log.warn("No customer found");
			return null;
		}
		cust = cd.getCustomer(cust);
		cust.setAddress(addrdao.getAddress(cust.getAddress().getId()));
		cust.setReadingList(bs.getBooksForReadingList(cust));
		return cust;
	}

	@Override
	public Customer getCustomerById(int i) {
		Customer cust = new Customer(i);
		cust = (Customer) ud.getUserById(cust);
		if(cust.getId()==0) {
			log.trace("No customer found");
			return null;
		}
		cust = cd.getCustomer(cust);
		cust.setAddress(addrdao.getAddress(cust.getAddress().getId()));
		cust.setReadingList(bs.getBooksForReadingList(cust));
		return cust;
	}

	@Override
	public Set<Customer> getCustomers() {
		Set<Customer> custList = cd.getCustomers();
		for(Customer c : custList) {
			ud.getUserById(c);
			c.setAddress(addrdao.getAddress(c.getAddress().getId()));
			c.setReadingList(bs.getBooksForReadingList(c));
		}
		return custList;
	}

	@Override
	public void deleteCustomer(Customer cust) {
		cd.deleteCustomer(cust);
	}

	@Override
	public void updateCustomer(Customer cust) {
		ud.updateUser(cust);
		addrdao.updateAddress(cust.getAddress());
		cd.updateCustomer(cust);
	}

	@Override
	public void addCustomer(Customer cust) {
		User u = ud.getUser(cust.getUsername(), cust.getPassword());
		if(u == null) {
			ud.addUser(cust);
		}
		cust.getAddress().setId(addrdao.addAddress(cust.getAddress()));
		cd.addCustomer(cust);
	}

}
