package com.revature.data;

import com.revature.beans.Address;

public interface AddressDAO {
	/**
	 * Inserts a Address into the database.
	 * 
	 * @param Address the Address object to be inserted
	 * @return id of created address
	 */
	public int addAddress(Address address);
	
	/**
	 * returns a login object from the database
	 * 
	 * @param id the id of the User
	 * @return the Address from the database that matches the id,
	 * null if no Address with said id exists.
	 */
	public Address getAddress(int id);
	
	/**
	 * deletes a Address from the database
	 * 
	 * @param Address the Address to be deleted
	 */
	public void deleteAddress(Address address);
	
	/**
	 * updates a Address in the database
	 * 
	 * @param Address the Address to be updated
	 */
	public void updateAddress(Address address);
}
