package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public interface UsersDAO {
	public String[][] addUser(Connection connection, Scanner in, int level, String addressUser) throws SQLException;
	
	public void manageAccount(Connection connection, Scanner in, int userID) throws SQLException ;
}
