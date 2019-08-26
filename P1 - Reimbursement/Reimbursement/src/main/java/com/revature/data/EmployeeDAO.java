package com.revature.data;

import java.util.Set;

//import org.apache.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.beans.Submission;

public interface EmployeeDAO {
	public String getEmail(int employeeID);
	public Employee getEmployee(String user, String pass);
	//public Logger log = Logger.getLogger(EmployeeDAO.class);
	
	public int getSupervisorID(int employeeID);
	
	public int getDepartmentHead(int employeeID);
	
	public Set<Submission> getSubmissions(int employeeID);
	
	public int getNextHR(int employeeID);
}
