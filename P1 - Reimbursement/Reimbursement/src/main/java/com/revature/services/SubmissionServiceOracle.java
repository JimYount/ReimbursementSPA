package com.revature.services;

import java.util.Set;

import com.revature.beans.Submission;
import com.revature.data.SubmissionDAO;
import com.revature.data.SubmissionOracle;

public class SubmissionServiceOracle implements SubmissionService {
	SubmissionDAO sd = new SubmissionOracle();

	public void makeSubmission(Submission sub) {
		sd.makeSubmission(sub);
	}
	
	public Set<Submission> getSubmissions(int employeeID) {
		return sd.getSubmissions(employeeID);
	}
	
	public void acceptSubmission (int subID) {
		sd.acceptSubmission(subID);
	}
	
	public void rejectSubmission (int subID, String commentReq) {
		sd.rejectSubmission(subID, commentReq);
	}
}
