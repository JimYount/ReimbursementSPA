package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.beans.Submission;
import com.revature.controller.FrontController;
import com.revature.utils.ConnectionUtility;

public class EmployeeOracle implements EmployeeDAO {
	private PreparedStatement prepared;
	private ResultSet result;
	private Connection conn;
	private Logger log = Logger.getLogger(FrontController.class);

	public String getEmail(int employeeID) {
		return "";
	}

	public Employee getEmployee(String user, String pass)  {
		log.trace("Finding Employee");
		
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getSupervisorID(int employeeID) {
		ConnectionUtility connUtil;

		try {
			connUtil = ConnectionUtility.getConnectionUtil();
			Connection conn = connUtil.getConnection();
			
			conn.setAutoCommit(false);
			
			PreparedStatement prepared = conn.prepareStatement("SELECT Supervisor FROM Employees WHERE EmployeeID = '" + employeeID + "'");
			
			ResultSet result = prepared.executeQuery();
			
			if (result.next())
				return result.getInt("Supervisor");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getDepartmentHead(int employeeID) {
		ConnectionUtility connUtil;
		int departmentID = 0;

		try {
			connUtil = ConnectionUtility.getConnectionUtil();
			Connection conn = connUtil.getConnection();
			
			conn.setAutoCommit(false);

			PreparedStatement prepared = conn.prepareStatement("SELECT DepartmentID FROM Employees WHERE EmployeeID = '" + employeeID + "'");
			
			ResultSet result = prepared.executeQuery();

			if (result.next())
				departmentID = result.getInt("DepartmentID");
			
			prepared = conn.prepareStatement("SELECT DeptHeadID FROM Department WHERE DepartmentID = '" + departmentID + "'");
			
			result = prepared.executeQuery();
			
			if (result.next())
				return result.getInt("DeptHeadID");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public Set<Submission> getSubmissions(int employeeID) {
		Set<Submission> set = new HashSet<Submission>();
		Submission submission;
		
		ConnectionUtility connUtil;

		try {
			connUtil = ConnectionUtility.getConnectionUtil();
			Connection conn = connUtil.getConnection();
			
			conn.setAutoCommit(false);
			
			PreparedStatement prepared = conn.prepareStatement("SELECT * FROM Submission WHERE EmployeeID = '" + employeeID + "'");
			
			ResultSet result = prepared.executeQuery();
			
			while (result.next()) {
				submission = new Submission();
				submission.setEventTypeID(result.getInt("EventTypeID"));
				submission.setEventDate(result.getString("EventDate"));
				submission.setEventCountry(result.getString("EventCountry"));
				submission.setEventState(result.getString("EventState"));
				submission.setEventCity(result.getString("EventCity"));
				submission.setDescription(result.getString("Descpription"));
				submission.setReimAmount(result.getDouble("ReimAmount"));
				submission.setGradePresNum(result.getDouble("GradePresNum"));
				submission.setJustification(result.getString("Justification"));
				submission.setOptEvtAttach(result.getString("OptEvtAttach"));
				submission.setOptDaysMissed(result.getInt("OptDaysMissed"));
				submission.setStatus(result.getInt("Status"));
				submission.setPassed(result.getBoolean("Passed"));
				submission.setApprSubDate(result.getString("ApprSubDate"));
				submission.setRejectReason(result.getString("RejectReason"));
			    set.add(submission);
			}
			
			return set;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getNextHR(int employeeID) {
		ConnectionUtility connUtil;
		int humResID = 0;
		int hrAssignedNum = 0;

		try {
			connUtil = ConnectionUtility.getConnectionUtil();
			Connection conn = connUtil.getConnection();
			
			conn.setAutoCommit(false);
			
			PreparedStatement prepared = conn.prepareStatement("SELECT * FROM Employees WHERE DepartmentID = '2' ORDER BY 'HRAssignedNum'");
			
			ResultSet result = prepared.executeQuery();
			
			while (result.next()) {
					humResID = result.getInt("EmployeeID");
					hrAssignedNum = result.getInt("HRAssignedNum");
				if (employeeID != humResID){
					break;
				}
			}
			
			hrAssignedNum++;
			
			prepared = conn.prepareStatement("UPDATE Employees SET HRAssignedNum = '" + hrAssignedNum + "' WHERE EmployeeID = '" + humResID + "'");
			prepared.executeQuery();
			
			prepared = conn.prepareStatement("COMMIT");
			prepared.executeQuery();
			
			return humResID;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
