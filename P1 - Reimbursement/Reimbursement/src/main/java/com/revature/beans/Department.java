package com.revature.beans;

public class Department {
	private int departmentID ;
    private String deptName;
    private int deptHeadID;
    
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getDeptHeadID() {
		return deptHeadID;
	}
	public void setDeptHeadID(int deptHeadID) {
		this.deptHeadID = deptHeadID;
	}
}
