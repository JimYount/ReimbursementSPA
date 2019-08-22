package com.revature.beans;

import java.sql.Date;

public class Submission {
	private int submissionID; 
	private int employeeID;
	private int eventTypeID;
	private Date submitDate; 
    private String locale; 
    private String descpription;
    private double reimAmount; 
    private int gradeFormatID; 
    private String justification; 
    private String optEvtAttach; 
    private int optDaysMissed;
    private int status;
    private boolean passed;
    private Date apprSubDate;
    
	public int getSubmissionID() {
		return submissionID;
	}
	public void setSubmissionID(int submissionID) {
		this.submissionID = submissionID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public int getEventTypeID() {
		return eventTypeID;
	}
	public void setEventTypeID(int eventTypeID) {
		this.eventTypeID = eventTypeID;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getDescpription() {
		return descpription;
	}
	public void setDescpription(String descpription) {
		this.descpription = descpription;
	}
	public double getReimAmount() {
		return reimAmount;
	}
	public void setReimAmount(double reimAmount) {
		this.reimAmount = reimAmount;
	}
	public int getGradeFormatID() {
		return gradeFormatID;
	}
	public void setGradeFormatID(int gradeFormatID) {
		this.gradeFormatID = gradeFormatID;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getOptEvtAttach() {
		return optEvtAttach;
	}
	public void setOptEvtAttach(String optEvtAttach) {
		this.optEvtAttach = optEvtAttach;
	}
	public int getOptDaysMissed() {
		return optDaysMissed;
	}
	public void setOptDaysMissed(int optDaysMissed) {
		this.optDaysMissed = optDaysMissed;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isPassed() {
		return passed;
	}
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	public Date getApprSubDate() {
		return apprSubDate;
	}
	public void setApprSubDate(Date apprSubDate) {
		this.apprSubDate = apprSubDate;
	}
}
