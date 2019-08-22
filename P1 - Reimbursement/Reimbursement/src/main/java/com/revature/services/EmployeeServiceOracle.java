package com.revature.services;

import java.io.IOException;
import java.sql.SQLException;

import com.revature.beans.Employee;
import com.revature.data.EmployeeDAO;
import com.revature.data.EmployeeOracle;

public class EmployeeServiceOracle implements EmployeeService {
	private EmployeeDAO ed = new EmployeeOracle();
	
	public Employee getEmployee(String user, String pass) throws SQLException, IOException{
		return ed.getEmployee(user, pass);
	}
}
