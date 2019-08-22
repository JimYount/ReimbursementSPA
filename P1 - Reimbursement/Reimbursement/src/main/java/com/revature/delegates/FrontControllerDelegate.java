package com.revature.delegates;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FrontControllerDelegate {
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException;
}
