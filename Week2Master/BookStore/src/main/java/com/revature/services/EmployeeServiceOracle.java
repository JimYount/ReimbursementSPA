package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.beans.User;
import com.revature.data.EmployeeDAO;
import com.revature.data.EmployeeOracle;
import com.revature.data.UserDAO;
import com.revature.data.UserOracle;

public class EmployeeServiceOracle implements EmployeeService {
	private Logger log = Logger.getLogger(EmployeeServiceOracle.class);
	private UserDAO ud = new UserOracle();
	private EmployeeDAO ed = new EmployeeOracle();
	
	@Override
	public Employee getEmployee(String username, String password) {
		Employee emp = new Employee(0, username, password, null, null);
		emp = (Employee) ud.getUser(emp);
		emp = ed.getEmployee(emp);
		if(emp.getId()==0)
		{
			log.trace("No employee found");
			return null;
		}
		if(emp.getSupervisor()!=null)
		{
			log.trace("Retrieving supervisor");
			emp.setSupervisor(getEmployeeById(emp.getSupervisor().getId()));
		}
		return emp;
	}

	@Override
	public Employee getEmployeeById(int i) {
		log.trace("retrieving employee by id: "+i);
		Employee emp = new Employee(i);
		emp = (Employee) ud.getUserById(emp);
		emp = ed.getEmployee(emp);
		if(emp.getId()==0)
		{
			log.trace("No employee found");
			return null;
		}
		if(emp.getSupervisor()!=null)
		{
			log.trace("Retrieving supervisor");
			emp.setSupervisor(getEmployeeById(emp.getSupervisor().getId()));
		}
		return emp;
	}

	@Override
	public Set<Employee> getEmployees() {
		Set<Employee> empList = ed.getEmployees();
		for(Employee e : empList)
		{
			ud.getUserById(e);
			e.setSupervisor(getEmployeeById(e.getSupervisor().getId()));
		}
		return empList;
	}

	@Override
	public void deleteEmployee(Employee emp) {
		ed.deleteEmployee(emp);

	}

	@Override
	public void updateEmployee(Employee emp) {
		ud.updateUser(emp);
		if(emp.getSupervisor().getFirst()!=null)
			ed.updateEmployee(emp.getSupervisor());
		ed.updateEmployee(emp);

	}

	@Override
	public void addEmployee(Employee emp) {
		User u = ud.getUser(ud.getUser(emp.getUsername(), emp.getPassword()));
		if(u==null)
		{
			ud.addUser(emp);
		}
		Employee supervisor = ed.getEmployee(emp.getSupervisor());
		if(supervisor!=null)
		{
			emp.setSupervisor(supervisor);
		}
	}

}
