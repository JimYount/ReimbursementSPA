package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public interface CarDAO {
	public String addCar(Connection connection, Scanner in) throws SQLException;
	
	public String buyCar(Connection connection, Scanner in, int userID) throws SQLException ;
}
