package com.revature.beans;

public class Comment {
	private int commentID;
    private String comments;
    private int submissionID;
    private int commenterID;
    
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getSubmissionID() {
		return submissionID;
	}
	public void setSubmissionID(int submissionID) {
		this.submissionID = submissionID;
	}
	public int getCommenterID() {
		return commenterID;
	}
	public void setCommenterID(int commenterID) {
		this.commenterID = commenterID;
	}
}
