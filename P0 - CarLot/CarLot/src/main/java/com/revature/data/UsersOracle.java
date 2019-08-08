package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.beans.DataObject;

public class UsersOracle implements UsersDAO {
	public String[][] addUser(Connection connection, Scanner in, int level, String addressUser) throws SQLException {
		QueryOracle query = new QueryOracle();
		String input4 = "";
		String input5 = "";
		boolean invalid = false;

		System.out.println("What is " + addressUser + " first name?");
		String input1 = in.nextLine();
		System.out.println("What is " + addressUser + " last name?");
		String input2 = in.nextLine();
		System.out.println("Type a username");
		String input3 = in.nextLine();

		do {
			query.invalid(invalid);

			System.out.println("Type a password");
			input4 = in.nextLine();
			System.out.println("Confirm password");
			input5 = in.nextLine();

			invalid = true;
		} while (!input4.contentEquals(input5));

		String[] inputs = { input1, input2, input3, input4, level + "" };
		query.getResult(connection.prepareStatement(
				"INSERT INTO CarUsers (FirstName, LastName, UserName, Password, AccountType) VALUES (?, ?, ?, ?, ?)"),
				connection.prepareStatement("commit"), new DataObject(inputs));

		return query.showQuery(
				connection.prepareStatement("SELECT CarUserID, FirstName, LastName, AccountType FROM CarUsers"
						+ " WHERE UserName = '" + input3 + "' AND Password = '" + input4 + "'"));
	}

	public void manageAccount(Connection connection, Scanner in, int userID) throws SQLException {
		String input = "";
		QueryOracle query = new QueryOracle();

		do {

			query.showStandardOptions("back");

			System.out.println("You owe: " + ((query.getResultDouble(query.showQuery(
					connection.prepareStatement("SELECT SUM(Price) FROM Cars WHERE OwnerID = '" + userID + "'"))))
					- (query.getResultDouble(query.showQuery(connection.prepareStatement(
							"SELECT ROUND(SUM(PaidAmount), 2) FROM Payments WHERE PayerID = '" + userID + "'"))))));

			System.out.println("Type an amount to pay some of your balance.");

			input = in.nextLine();

			if (input.equalsIgnoreCase("log") || input.equalsIgnoreCase("exit"))
				return;
			else if (input.equalsIgnoreCase("back"))
				return;

			if (input.matches("[0-9]+")) {
				String[] inputs = { userID + "", input };
				query.getResult(connection.prepareStatement("INSERT INTO Payments (PayerID, PaidAmount) VALUES (?, ?)"),
						connection.prepareStatement("Commit"), new DataObject(inputs));
			}
			/*
			 * + " at a rate of " +
			 * query.getResultDouble(query.showQuery(connection.prepareStatement(
			 * "SELECT ROUND(SUM(Price/PayMonths), 2) FROM Cars WHERE OwnerID = '" + userID
			 * + "'"))) + " per month."
			 */
		} while (!input.equalsIgnoreCase("back") && !input.equalsIgnoreCase("log") && !input.equalsIgnoreCase("exit"));
	}
}
