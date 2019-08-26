package com.revature.services;

import com.revature.beans.Comment;
import com.revature.data.CommentDAO;
import com.revature.data.CommentOracle;

public class CommentServiceOracle implements CommentService {
	CommentDAO comdao = new CommentOracle();
	
	public void requestComment(Comment comment) {
		comdao.requestComment(comment);
	}
}
