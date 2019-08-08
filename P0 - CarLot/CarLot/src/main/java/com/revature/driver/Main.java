package com.revature.driver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.data.CarOracle;
import com.revature.data.OffersOracle;
import com.revature.data.QueryOracle;
import com.revature.data.UsersOracle;
import com.revature.utils.ConnectionUtility;

public class Main {
	private static String input;
	private static String secondaryInput;
	private static boolean invalid;
	private static Scanner in;

	public static void main(String[] args) throws SQLException, IOException {
		in = new Scanner(System.in);
		input = "";
		secondaryInput = "";
		invalid = false;
		boolean secondTime = false;
		int userID = 0;
		int userLevel = 0;
		int availableOptions = 0;
		String[][] resultString = null;
		String input3 = "";
		QueryOracle query = new QueryOracle();
		CarOracle lot = new CarOracle();
		UsersOracle users = new UsersOracle();
		OffersOracle offers = new OffersOracle();

		ConnectionUtility connUtil = ConnectionUtility.getConnectionUtil();
		Connection connection = connUtil.getConnection();

		connection.setAutoCommit(false);

		while (!input3.equalsIgnoreCase("exit") && !input.equalsIgnoreCase("exit")) {
			do {
				do {
					query.invalid(invalid);

					secondTime = false;

					System.out.println("Type a number for the corresponding option.");
					System.out.println("1. Log In");
					System.out.println("2. Register");
					input3 = in.nextLine();

					if (input3.equalsIgnoreCase("exit")) {
						in.close();
						return;
					}

					if (input3.matches("[0-9]+")) {
						if (Integer.parseInt(input3) == 2) {
							resultString = users.addUser(connection, in, 1, "your");
						} else if (Integer.parseInt(input3) == 1) {
							System.out.println("Please Log in\n");
							System.out.println("Username:");
							input = in.nextLine();
							System.out.println("Password:");
							secondaryInput = in.nextLine();

							resultString = query.showQuery(connection.prepareStatement("SELECT "
									+ "CarUserID, FirstName, LastName, AccountType " + "FROM CarUsers"
									+ " WHERE UserName = '" + input + "' AND Password = '" + secondaryInput + "'"));
						}

					}

					invalid = true;

				} while (resultString == null);
			} while (resultString.length <= 0);

			userID = Integer.parseInt(resultString[0][0]);
			userLevel = Integer.parseInt(resultString[0][3]);
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
					System.out.print(resultString[0][1] + " " + resultString[0][2] + "!\n\n");
				}

				secondTime = true;
				query.invalid(invalid);

				query.showStandardOptions("");

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
						query.displayQuery(query
								.showQuery(connection.prepareStatement("SELECT * FROM Cars WHERE OwnerID IS NULL")));
						break;
					case 2:
						if (userLevel == 1 || userLevel == 2) {
							
							input = lot.buyCar(connection, in, userID);
						} else if (userLevel == 3) {
							System.out.println(
									"Monthly Yield:" + query.getResultDouble(query.showQuery(connection.prepareStatement(
											"SELECT SUM(Price/PayMonths) FROM Cars WHERE OwnerID IS NOT NULL"))));
						}
						break;
					case 3:
						if (userLevel == 1 || userLevel == 2) {
							query.displayQuery(query.showQuery(
									connection.prepareStatement("SELECT * FROM Cars WHERE OwnerID = " + userID)));
						} else if (userLevel == 3) {
							users.addUser(connection, in, 1, "the customer's");
						}
						break;
					case 4:
				if (userLevel == 1 || userLevel == 2) {
							users.manageAccount(connection, in, userID);
						} else if (userLevel == 3) {
							input = query.removeAsset(connection, in, "CarUsers", "CarUserID", "AND AccountType = 1",
									" WHERE AccountType = '1'");
						}
						break;
					case 5:
						query.displayQuery(query.showQuery(connection.prepareStatement("SELECT * FROM Offers")));
						input = offers.manageOffers(connection, in);
						break;
					case 6:
						input = new CarOracle().addCar(connection, in);
						break;
					case 7:
						input = query.removeAsset(connection, in, "Cars", "CarID", "", "");
						break;
					case 8:
						query.displayQuery(query.showQuery(connection.prepareStatement("SELECT * FROM Payments")));
						break;
					case 9:
						users.addUser(connection, in, 2, "the employee's");
						break;
					case 10:
						input = query.removeAsset(connection, in, "CarUsers", "CarUserID", "AND AccountType = 2",
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
	}
}
