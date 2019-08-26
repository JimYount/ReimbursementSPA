package com.revature.data;

import java.sql.Connection;

import com.revature.beans.Submission;
import com.revature.delegates.SubmitDelegate;
import com.revature.utils.ConnectionUtility;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class SubmissionOracle implements SubmissionDAO {
	EmployeeDAO emp = new EmployeeOracle();
	private Logger log = Logger.getLogger(SubmitDelegate.class);
	
	public void makeSubmission(Submission sub) {
		ConnectionUtility connUtil;
		try {
			connUtil = ConnectionUtility.getConnectionUtil();
			Connection connection = connUtil.getConnection();

			connection.setAutoCommit(false);
			
			log.trace("'" + sub.getEmployeeID() + "',"
					+ "'" + sub.getEventTypeID() + "',"
					+ "'" + sub.getEventDate() + "',"
					+ "'" + sub.getEventCountry() + "',"
					+ "'" + sub.getEventState() + "',"
					+ "'" + sub.getEventCity() + "',"
					+ "'" + sub.getDescription() + "',"
					+ "'" + sub.getReimAmount() + "',"
					+ "'" + sub.getGradePresNum() + "',"
					+ "'" + sub.getJustification() + "',"
					+ "'" + sub.getOptEvtAttach() + "',"
					+ "'" + sub.getOptDaysMissed() + "',"
					+ "'" + sub.getStatus() + "',"
					+ "'" + sub.getApprSubDate() + "'");
			
			PreparedStatement prepared = connection.prepareStatement(
					"INSERT INTO Submission (EmployeeID, EventTypeID, EventDate, EventCountry, EventState, EventCity, Descpription, ReimAmount, "
					+ "GradePresNum, Justification, OptEvtAttach, OptDaysMissed, Status, ApprSubDate, AssignedHR) "
					+ "VALUES ("
					+ "'" + sub.getEmployeeID() + "',"
					+ "'" + sub.getEventTypeID() + "',"
					+ "TO_DATE('" + sub.getEventDate() + "', 'YYYY-MM-DD'),"
					+ "'" + sub.getEventCountry() + "',"
					+ "'" + sub.getEventState() + "',"
					+ "'" + sub.getEventCity() + "',"
					+ "'" + sub.getDescription() + "',"
					+ "'" + sub.getReimAmount() + "',"
					+ "'" + sub.getGradePresNum() + "',"
					+ "'" + sub.getJustification() + "',"
					+ "'" + sub.getOptEvtAttach() + "',"
					+ "'" + sub.getOptDaysMissed() + "',"
					+ "'" + sub.getStatus() + "',"
					+ "TO_DATE('" + sub.getApprSubDate() + "', 'YYYY-MM-DD'),"
					+ "'" + sub.getAssignedHR() + "'" 
					+ ")");

				prepared.executeQuery();
				
				prepared = connection.prepareStatement("COMMIT");
				prepared.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Set<Submission> getSubmissions(int employeeID) {
		Set<Submission> set = new HashSet<Submission>();
		Submission submission;

		ConnectionUtility connUtil;

		try {
			connUtil = ConnectionUtility.getConnectionUtil();
			Connection conn = connUtil.getConnection();
			PreparedStatement prepared;
			ResultSet result;
			int subEmployeeID = 0;

			conn.setAutoCommit(false);

			prepared = conn.prepareStatement("SELECT * FROM Submission");
			result = prepared.executeQuery();

			while (result.next()) {
				submission = new Submission();
				submission.setSubmissionID(result.getInt("SubmissionID"));
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

				subEmployeeID = result.getInt("EmployeeID");

				// log.trace("My Employee ID: " + employeeID + " Submission Supervisor ID: " +
				// EmployeeDAO.getSupervisorID(result.getInt("EmployeeID"))
				// + " Submission Department Head ID: " +
				// EmployeeDAO.getDepartmentHead(result.getInt("EmployeeID")) + " Submission
				// Assigned HR: "
				// + result.getInt("AssignedHR")
				// + " Submission Status: " + (4 & submission.getStatus()) + (2 &
				// submission.getStatus()) + (1 & submission.getStatus()));

				 //log.trace(((submission.getStatus() & 1) == 0) + " " + (emp.getSupervisorID(subEmployeeID) == employeeID) +
				 //" " + ((submission.getStatus() & 2) == 0) + " " + (emp.getDepartmentHead(subEmployeeID) == employeeID) +
				 //" " + ((submission.getStatus() & 4) == 0) + " " +
				 //(result.getInt("AssignedHR") == employeeID));

				if (submission.getStatus() < 8) {
					if ((submission.getStatus() & 1) == 0) {
						if (emp.getSupervisorID(subEmployeeID) == employeeID)
							set.add(submission);
					} else if ((submission.getStatus() & 2) == 0) {
						if (emp.getDepartmentHead(subEmployeeID) == employeeID)
							set.add(submission);
					} else if ((submission.getStatus() & 4) == 0) {
						if (result.getInt("AssignedHR") == employeeID)
							set.add(submission);
					}
				}
			}

			return set;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void acceptSubmission(int subID) {
		ConnectionUtility connUtil;

		try {
			connUtil = ConnectionUtility.getConnectionUtil();
			Connection conn = connUtil.getConnection();
			PreparedStatement prepared;

			conn.setAutoCommit(false);

			prepared = conn.prepareStatement("SELECT Status FROM Submission WHERE SubmissionID = '" + subID + "'");
			ResultSet result = prepared.executeQuery();

			// log.trace("SubID: " + subID);

			if (result.next()) {
				int status = result.getInt("Status");

				// log.trace("SubID: " + subID + " " + (status & 1) + " " + (status & 2) + " " +
				// (status & 4));

				if ((status & 1) == 0)
					status += 1;
				else if ((status & 2) == 0)
					status += 2;
				else if ((status & 4) == 0)
					status += 4;

				prepared = conn.prepareStatement(
						"UPDATE Submission SET Status = " + status + "WHERE SubmissionID = '" + subID + "'");
				prepared.executeQuery();

				prepared = conn.prepareStatement("COMMIT");
				prepared.executeQuery();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rejectSubmission(int subID, String commentReq) {
		ConnectionUtility connUtil;

		try {
			connUtil = ConnectionUtility.getConnectionUtil();
			Connection conn = connUtil.getConnection();
			PreparedStatement prepared;

			conn.setAutoCommit(false);

			prepared = conn.prepareStatement("SELECT Status FROM Submission WHERE SubmissionID = '" + subID + "'");
			ResultSet result = prepared.executeQuery();

			// log.trace("SubID: " + subID);

			if (result.next()) {
				prepared = conn
						.prepareStatement("UPDATE Submission SET Status = '8' WHERE SubmissionID = '" + subID + "'");
				prepared.executeQuery();
				
				prepared = conn
						.prepareStatement("UPDATE Submission SET RejectReason = '" + commentReq + "' WHERE SubmissionID = '" + subID + "'");
				prepared.executeQuery();

				prepared = conn.prepareStatement("COMMIT");
				prepared.executeQuery();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
