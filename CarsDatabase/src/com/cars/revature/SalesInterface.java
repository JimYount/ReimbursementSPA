package com.cars.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class SalesInterface {
	private static String input;
	private static String secondaryInput;
	private static boolean invalid;
	private static Scanner in;
	private static ResultSet result;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		input = "";
		secondaryInput = "";
		invalid = false;
		result = null;
		boolean secondTime = false;
		int userID = 0;
		int userLevel = 0;
		int availableOptions = 0;
		String input3 = "";

		try (Connection connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@carsales.cskckr7v2ody.us-east-2.rds.amazonaws.com:1521:ORCL", "jimayount",
				"5al35Pa55");) {

			connection.setAutoCommit(false);

			while (!input.equalsIgnoreCase("exit")) {
				do {
					do {
						if (invalid)
							System.out.println("\n\nInvalid entry, please re-enter.\n");

						System.out.println("Type a number for the corresponding option.");
						System.out.println("1. Log In");
						System.out.println("2. Register");
						input3 = in.nextLine();
						
						if (input.equalsIgnoreCase("exit")) {
							in.close();
							return;
						}
							
						if (input3.matches("[0-9]+")) {
							if (Integer.parseInt(input3) == 2) {
								result = addUser(connection, 1, "your");
							} else if (Integer.parseInt(input3) == 1) {
								System.out.println("Please Log in\n");
								System.out.println("Username:");
								input = in.nextLine();
								System.out.println("Password:");
								secondaryInput = in.nextLine();
								
								result = getResult(connection
										.prepareStatement("SELECT " + "CarUserID, FirstName, LastName, AccountType "
												+ "FROM CarUsers" + " WHERE UserName = '" + input
												+ "' AND Password = '" + secondaryInput + "'"));
							}

									
						}

						invalid = true;
					} while (result == null);
				} while (!result.next());

				userID = result.getInt("CarUserID");
				userLevel = result.getInt("AccountType");
				invalid = false;

				do {
					if (!secondTime) {
						System.out.print("\nWelcome, ");
						switch (userLevel) {
						case 1:
							System.out.print("customer ");
							break;
						case 2:
							System.out.print("employee ");
							break;
						case 3:
							System.out.print("owner ");
							break;
						}
						System.out.print(result.getString("FirstName") + " " + result.getString("LastName") + "!\n\n");
					}

					secondTime = true;

					showStandardOptions("");

					if (userLevel == 1 || userLevel == 2) {
						System.out.println("Please type a choice.");
						System.out.println("1. See All Available Cars");
						System.out.println("2. Buy a Car");
						System.out.println("3. See My Cars");
						System.out.println("4. View My Balance");
						availableOptions = 4;
					}
					if (userLevel == 2) {
						System.out.println("5. Manage Offers");
						System.out.println("6. Add a Car");
						System.out.println("7. Remove a Car");
						System.out.println("8. View all Payments");
						availableOptions = 8;
					}
					if (userLevel == 3) {
						System.out.println("1. See All Available Cars");
						System.out.println("2. Calculate Monthly Yield");
						System.out.println("3. Add a Customer");
						System.out.println("4. Remove a Customer");
						System.out.println("5. Manage Offers");
						System.out.println("6. Add a Car");
						System.out.println("7. Remove a Car");
						System.out.println("8. View all Payments");
						System.out.println("9. Add an Employee");
						System.out.println("10. Remove an Employee");
						availableOptions = 10;
					}
					input = in.nextLine();
					System.out.println();

					if (input.matches("[0-9]+")) {
						switch (Integer.parseInt(input)) {
						case 1:
							showQuery(connection.prepareStatement("SELECT * FROM Cars WHERE OwnerID IS NULL"));
							break;
						case 2:
							if (userLevel == 1 || userLevel == 2) {
								showQuery(connection
										.prepareStatement("SELECT Price/PayMonths FROM Cars WHERE OwnerID IS NOT NULL"));
								input = buyCar(connection, userID);
							} else if (userLevel == 3) {
								System.out.println("Monthly Yield:" + getResultInt(connection.prepareStatement(
										"SELECT SUM(Price/PayMonths) FROM Cars WHERE OwnerID IS NOT NULL")));
							}
							break;
						case 3:
							if (userLevel == 1 || userLevel == 2) {
								showQuery(connection.prepareStatement("SELECT * FROM Cars WHERE OwnerID = " + userID));
							} else if (userLevel == 3) {
								addUser(connection, 1, "the customer's");
							}
							break;
						case 4:
							if (userLevel == 1 || userLevel == 2) {
								System.out.println("You owe: "
										+ getResultInt(connection.prepareStatement(
												"SELECT SUM(Price) FROM Cars WHERE OwnerID = " + userID))
										+ " at a rate of "
										+ getResultInt(connection.prepareStatement(
												"SELECT SUM(Price/PayMonths) FROM Cars WHERE OwnerID = " + userID))
										+ " per month.");
							} else if (userLevel == 3) {
								input = removeAsset(connection, "CarUsers", "CarUserID", "AND AccountType = 1",
										" WHERE AccountType = '1'");
							}
							break;
						case 5:
							showQuery(connection.prepareStatement("SELECT * FROM Offers"));
							input = manageOffers(connection);
							break;
						case 6:
							addCar(connection);
							break;
						case 7:
							input = removeAsset(connection, "Cars", "CarID", "", "");
							break;
						case 8:
							showQuery(connection.prepareStatement("SELECT * FROM Payments"));
							break;
						case 9:
							addUser(connection, 2, "the employee's");
							break;
						case 10:
							input = removeAsset(connection, "CarUsers", "CarUserID", "AND AccountType = 2",
									" WHERE AccountType = '2'");
							break;
						}
					}

					if (input.equalsIgnoreCase("log") || input.equalsIgnoreCase("exit"))
						break;

					if (input.matches("[0-9]+")) {
						if (Integer.parseInt(input) > availableOptions || Integer.parseInt(input) <= 0)
							invalid = true;
					}
				} while (!input.equalsIgnoreCase("log") || input.equalsIgnoreCase("exit"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		in.close();
	}

	private static ResultSet getResult(PreparedStatement prepared) throws SQLException {
		return prepared.executeQuery();
	}

	private static int getResultInt(PreparedStatement pre) throws SQLException {
		result = pre.executeQuery();

		if (result.next())
			return result.getInt(1);

		return 0;
	}

	private static boolean showQuery(PreparedStatement pre) throws SQLException {
		result = pre.executeQuery();
		ResultSetMetaData meta = result.getMetaData();
		boolean gotQuery = false;

		while (result.next()) {
			System.out.println();
			for (int i = 1; i < meta.getColumnCount(); i++) {
				System.out.print(result.getString(i) + "  ");
			}

			gotQuery = true;
		}
		System.out.println();
		System.out.println();

		return gotQuery;
	}

	private static String removeAsset(Connection connection, String table, String conditionField,
			String additionalCondition, String showCondition) throws SQLException {
		invalid = false;

		do {
			showStandardOptions("show back");

			showQuery(connection.prepareStatement("SELECT * FROM " + table + showCondition));

			System.out.println("Type the number that you want to remove:");
			input = in.nextLine();

			if (input.equalsIgnoreCase("log") || input.equalsIgnoreCase("exit"))
				return input;
			else if (input.equalsIgnoreCase("back"))
				return "1";

			if (!getResult(connection.prepareStatement(
					"SELECT * FROM " + table + " WHERE " + conditionField + " = " + input + " " + additionalCondition))
							.next())
				invalid = true;
			else {
				getResult(connection.prepareStatement(
						"DELETE FROM " + table + " WHERE " + conditionField + " = " + input + additionalCondition));
				// getResult(connection.prepareStatement("ALTER TABLE " + table + "
				// AUTO_INCREMENT=1"));
			}
		} while (!input.equalsIgnoreCase("back") && !input.equalsIgnoreCase("log") && !input.equalsIgnoreCase("exit"));

		return "1";
	}

	private static String buyCar(Connection connection, int userID) throws SQLException {
		invalid = false;

		do {
			showStandardOptions("show back");

			System.out.println("Which car do you want to buy?");
			input = in.nextLine();

			if (input.equalsIgnoreCase("log") || input.equalsIgnoreCase("exit"))
				return input;
			else if (input.equalsIgnoreCase("back"))
				return "2";
			else if (Integer.parseInt(input) > 0) {
				if (showQuery(connection
						.prepareStatement("SELECT * FROM Cars WHERE OwnerID IS NOT NULL AND CarID = '" + input + "'"))) {
					System.out.println("What is your offer?");
					secondaryInput = in.nextLine();

					if (Double.class.isInstance(Double.parseDouble((input)))) {
						getResult(connection
								.prepareStatement("INSERT INTO Offers (OfferCarID, OfferCarUserID, Offer) VALUES ("
										+ Integer.parseInt(input) + ", " + userID + ", "
										+ Double.parseDouble((secondaryInput)) + ")"));
					}
				} else {
					invalid = true;
				}
			}

		} while (!input.equalsIgnoreCase("back") && !input.equalsIgnoreCase("exit"));

		return "2";
	}

	private static String manageOffers(Connection connection) throws SQLException {
		invalid = false;
		String input3 = "";

		do {
			showStandardOptions("show back");

			System.out.println("Which offer do you want to modify?");
			input = in.nextLine();

			if (input.equalsIgnoreCase("log") || input.equalsIgnoreCase("exit"))
				return input;
			else if (input.equalsIgnoreCase("back"))
				return "5";

			if (!showQuery(connection.prepareStatement("SELECT * FROM Offers WHERE OfferID = " + input)))
				invalid = true;
			else {
				System.out.println("1. Reject Offer");
				System.out.println("2. Accept Offer");
				System.out.println("Type anything else to cancel.");
				secondaryInput = in.nextLine();

				do {
					System.out.println("How long is the car loan in months? (24 - 84)");
					input3 = in.nextLine();

					if (Integer.parseInt(input3) < 24 || Integer.parseInt(input3) > 84) {
						System.out.println("Loan length invalid, re-enter.");
					}
				} while (Integer.parseInt(input3) < 24 || Integer.parseInt(input3) > 84);

				switch (Integer.parseInt(secondaryInput)) {
				case 2:
					result = getResult(connection.prepareStatement("SELECT * FROM Offers WHERE OfferID = " + input));
					getResult(connection.prepareStatement("UPDATE Cars SET OwnerID = '"
							+ result.getString("OfferCarUserID") + "'), Price = '" + result.getString("Offer")
							+ "', PayMonths = '" + input3
							+ "', BoughtYear = YEAR(CURRENT_DATE()), BoughtMonth = MONTH(CURRENT_DATE()) "
							+ "WHERE CarID = (SELECT OfferCarUserID FROM Offers WHERE OfferID = '" + input + "')"));
				case 1:
					getResult(connection.prepareStatement("DELETE FROM Offers WHERE OfferID = " + input));
					// getResult(connection.prepareStatement("ALTER TABLE Offers
					// AUTO_INCREMENT=1"));
					break;
				default:
					invalid = true;
					break;
				}
			}

		} while (!input.equalsIgnoreCase("back") && !input.equalsIgnoreCase("exit"));

		return "5";
	}

	private static void addCar(Connection connection) throws SQLException {
		String input1 = "";
		String input2 = "";
		String input3 = "";
		String input4 = "";
		String input5 = "";
		String input6 = "";

		System.out.println("What is the car's make?");
		input1 = in.nextLine();
		System.out.println("What is the car's model?");
		input2 = in.nextLine();
		System.out.println("What is the car's color?");
		input3 = in.nextLine();
		System.out.println("What year was the car made?");
		input4 = in.nextLine();
		System.out.println("What is the baseline price for the car?");
		input5 = in.nextLine();
		System.out.println("What is the url of the picture?");
		input6 = in.nextLine();

		getResult(connection.prepareStatement("INSERT INTO Cars (Make, Model, Color, Year, Price, PicURL) VALUES ('" + input1
				+ "', '" + input2 + "', '" + input3 + "', '" + input4 + "', '" + input5 + "', '" + input6 + "')"));
		
		getResult(connection.prepareStatement("COMMIT"));

		return;
	}

	private static ResultSet addUser(Connection connection, int level, String addressUser) throws SQLException {
		String input1 = "";
		String input2 = "";
		String input3 = "";
		String input4 = "";
		String input5 = "";
		invalid = false;

		System.out.println("What is " + addressUser + " first name?");
		input1 = in.nextLine();
		System.out.println("What is " + addressUser + " last name?");
		input2 = in.nextLine();
		System.out.println("Type a username");
		input3 = in.nextLine();

		do {
			if (invalid)
				System.out.println("Passwords do not match, re-enter.");

			System.out.println("Type a password");
			input4 = in.nextLine();
			System.out.println("Confirm password");
			input5 = in.nextLine();

			invalid = true;
		} while (!input4.contentEquals(input5));

		getResult(connection.prepareStatement(
				"INSERT INTO CarUsers (FirstName, LastName, UserName, Password, AccountType) VALUES ('" + input1
						+ "', '" + input2 + "', '" + input3 + "', '" + input4 + "', '" + level + "')"));

		return getResult(connection
				.prepareStatement("SELECT " + "CarUserID, FirstName, LastName, AccountType "
						+ "FROM CarUsers" + " WHERE UserName = '" + input3
						+ "' AND Password = '" + input4 + "'"));
	}

	private static void showStandardOptions(String back) {
		System.out.println("\nType log at any time to log out.");
		if (back.equalsIgnoreCase("show back"))
			System.out.println("Type back to go back to main menu.");

		System.out.println();

		if (invalid) {
			System.out.println("Invalid input, please re-enter.");
		}

		return;
	}
}
