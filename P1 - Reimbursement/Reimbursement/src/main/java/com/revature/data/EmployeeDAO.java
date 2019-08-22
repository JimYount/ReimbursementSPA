package com.revature.data;

import java.io.IOException;
import java.sql.SQLException;

import com.revature.beans.Employee;

public interface EmployeeDAO {
	public int getSupervisorID(int employeeID);
	public String getEmail(int employeeID);
	public Employee getEmployee(String user, String pass) throws SQLException, IOException;
}
