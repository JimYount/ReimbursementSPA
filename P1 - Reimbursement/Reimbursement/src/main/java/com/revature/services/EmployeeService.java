package com.revature.services;

import java.io.IOException;
import java.sql.SQLException;

import com.revature.beans.Employee;

public interface EmployeeService {
	public Employee getEmployee(String user, String pass) throws SQLException, IOException;
}
