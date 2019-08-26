package com.revature.data;

import java.util.Set;

import com.revature.beans.Employee;

public interface EmployeeDAO {
	/**
	 * Inserts an employee into the database.
	 * 
	 * @param Employee the Employee object to be inserted
	 */
	public void addEmployee(Employee employee);
	
	/**
	 * returns a user object from the database
	 * 
	 * @param emp the employee objected holding the id of the employee to be retrieved
	 * @return the Employee from the database that matches the id,
	 * an empty object if no Employee with said id exists.
	 */
	public Employee getEmployee(Employee emp);
	
	/**
	 * returns a list of all Employees in database
	 * 
	 * @return list of Employees in database
	 */
	public Set<Employee> getEmployees();
	
	/**
	 * deletes a Employee from the database
	 * 
	 * @param Employee the Employee to be deleted
	 */
	public void deleteEmployee(Employee employee);
	
	/**
	 * updates a Employee in the database
	 * 
	 * @param Employee the Employee to be updated
	 */
	public void updateEmployee(Employee employee);
}
