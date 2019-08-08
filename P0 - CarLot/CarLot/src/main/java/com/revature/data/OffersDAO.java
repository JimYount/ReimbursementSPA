package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public interface OffersDAO {
	public String manageOffers(Connection connection, Scanner in) throws SQLException;
}
