package com.revature.services;

import java.util.Set;

import com.revature.beans.Employee;
import com.revature.beans.Submission;

public interface EmployeeService {
	public Employee getEmployee(String user, String pass);
	
	public int getSupervisorID(int employeeID);
	
	public int getDepartmentHead(int employeeID);
	
	public Set<Submission> getSubmissions(int employeeID);
	
	public int getNextHR(int employeeID);
}
