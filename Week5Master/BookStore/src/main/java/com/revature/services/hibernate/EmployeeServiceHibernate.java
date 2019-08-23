package com.revature.services.hibernate;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.data.EmployeeDAO;
import com.revature.data.hibernate.EmployeeHibernate;
import com.revature.services.EmployeeService;

public class EmployeeServiceHibernate implements EmployeeService {
	private Logger log = Logger.getLogger(EmployeeServiceHibernate.class);
	private EmployeeDAO ed = new EmployeeHibernate();
	
	@Override
	public Employee getEmployee(String username, String password) {
		Employee emp = new Employee(null, username, password, null, null);
		emp = ed.getEmployee(emp);

		
		return emp;
	}

	@Override
	public Employee getEmployeeById(int i) {
		log.trace("retrieving employee by id: "+i);
		Employee emp = new Employee(i, null, null, null, null);

		
		emp = ed.getEmployee(emp);

		return emp;
	}

	@Override
	public Set<Employee> getEmployees() {
		Set<Employee> empList = ed.getEmployees();

		
		return empList;
	}

	@Override
	public void deleteEmployee(Employee emp) {
		ed.deleteEmployee(emp);

	}

	@Override
	public void updateEmployee(Employee emp) {
		ed.updateEmployee(emp);

	}

	@Override
	public void addEmployee(Employee emp) {
		ed.addEmployee(emp);
	}

}
