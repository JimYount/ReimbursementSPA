package com.revature.services;

import java.util.Set;

import com.revature.beans.Employee;
import com.revature.beans.Submission;
import com.revature.data.EmployeeDAO;
import com.revature.data.EmployeeOracle;

public class EmployeeServiceOracle implements EmployeeService {
	private EmployeeDAO ed = new EmployeeOracle();
	
	public Employee getEmployee(String user, String pass){
		return ed.getEmployee(user, pass);
	}
	
	public int getSupervisorID(int employeeID) {
		return ed.getSupervisorID(employeeID); 
	}
	
	public int getDepartmentHead(int employeeID) {
		return ed.getDepartmentHead(employeeID);
	}
	
	public Set<Submission> getSubmissions(int employeeID) {
		return ed.getSubmissions(employeeID);
	}
	
	public int getNextHR(int employeeID) {
		return ed.getNextHR(employeeID);
	}
}
