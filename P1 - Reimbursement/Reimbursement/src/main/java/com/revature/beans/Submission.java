package com.revature.beans;

public class Submission {
	private int submissionID; 
	private int employeeID;
	private int eventTypeID;
	private String eventDate; 
    private String eventCountry; 
    private String eventState; 
    private String eventCity; 
    private String description;
    private double reimAmount; 
    private double gradePresNum; 
    private String justification; 
    private String optEvtAttach; 
    private int optDaysMissed;
    private int status;
    private boolean passed;
    private String apprSubDate;
    private int assignedHR;
	private String rejectReason;
	
    public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public int getAssignedHR() {
		return assignedHR;
	}
	public void setAssignedHR(int assignedHR) {
		this.assignedHR = assignedHR;
	}
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
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventCountry() {
		return eventCountry;
	}
	public void setEventCountry(String eventCountry) {
		this.eventCountry = eventCountry;
	}
	public String getEventState() {
		return eventState;
	}
	public void setEventState(String eventState) {
		this.eventState = eventState;
	}
	public String getEventCity() {
		return eventCity;
	}
	public void setEventCity(String eventCity) {
		this.eventCity = eventCity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getReimAmount() {
		return reimAmount;
	}
	public void setReimAmount(double reimAmount) {
		this.reimAmount = reimAmount;
	}
	public double getGradePresNum() {
		return gradePresNum;
	}
	public void setGradePresNum(double gradePresNum) {
		this.gradePresNum = gradePresNum;
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
	public String getApprSubDate() {
		return apprSubDate;
	}
	public void setApprSubDate(String apprSubDate) {
		this.apprSubDate = apprSubDate;
	}
}
