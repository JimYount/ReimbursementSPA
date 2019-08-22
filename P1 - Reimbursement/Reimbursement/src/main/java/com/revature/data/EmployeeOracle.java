package com.revature.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.controller.FrontController;
import com.revature.utils.ConnectionUtility;

public class EmployeeOracle implements EmployeeDAO {
	private PreparedStatement prepared;
	private ResultSet result;
	private Connection conn;
	private Logger log = Logger.getLogger(FrontController.class);

	public int getSupervisorID(int employeeID) {
		return 0;
	}

	public String getEmail(int employeeID) {
		return "";
	}

	public Employee getEmployee(String user, String pass) throws SQLException, IOException {
		Employee emp = new Employee();
		ConnectionUtility connUtil;

		connUtil = ConnectionUtility.getConnectionUtil();
		conn = connUtil.getConnection();

		conn.setAutoCommit(false);

		prepared = conn.prepareStatement(
				"SELECT * FROM Employees WHERE UserName = '" + user + "' AND UserPass = '" + pass + "'");

		result = prepared.executeQuery();
		if (result.next()) {
			emp.setEmployeeID(Integer.parseInt(result.getString("EmployeeID")));
			emp.setEmail(result.getString("Email"));
			emp.setUserName(result.getString("UserName"));
			emp.setUserPass(result.getString("UserPass"));
			emp.setFirstName(result.getString("FirstName"));
			emp.setLastName(result.getString("LastName"));
			emp.setSupervisor(Integer.parseInt(result.getString("Supervisor")));
			emp.setDepartmentID(Integer.parseInt(result.getString("DepartmentID")));
		} else
			return null;

		return emp;
	}
}
