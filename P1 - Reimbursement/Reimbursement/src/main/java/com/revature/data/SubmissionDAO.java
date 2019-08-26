package com.revature.data;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Submission;

public interface SubmissionDAO {
	public void makeSubmission(Submission sub);

	public Logger log = Logger.getLogger(EmployeeDAO.class);

	public Set<Submission> getSubmissions(int employeeID);

	public void acceptSubmission(int subID);

	public void rejectSubmission(int subID, String commentReq);
}
