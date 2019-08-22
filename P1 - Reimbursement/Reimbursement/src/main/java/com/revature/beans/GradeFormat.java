package com.revature.beans;

public class GradeFormat {
	private int formatID;
    private int gradeCutoff;
    private int presentation;

    public int getFormatID() {
		return formatID;
	}
	public void setFormatID(int formatID) {
		this.formatID = formatID;
	}
	public int getGradeCutoff() {
		return gradeCutoff;
	}
	public void setGradeCutoff(int gradeCutoff) {
		this.gradeCutoff = gradeCutoff;
	}
	public int getPresentation() {
		return presentation;
	}
	public void setPresentation(int presentation) {
		this.presentation = presentation;
	}
}
