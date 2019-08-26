package com.revature.services;

import java.util.Set;

import com.revature.beans.Submission;

public interface SubmissionService {
	public void makeSubmission(Submission sub);
	
	public Set<Submission> getSubmissions(int employeeID);
	
	public void acceptSubmission (int subID);
	
	public void rejectSubmission (int subID, String commentReq);
}
