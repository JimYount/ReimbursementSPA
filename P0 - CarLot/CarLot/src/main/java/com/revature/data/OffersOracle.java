package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.beans.DataObject;

public class OffersOracle implements OffersDAO {
	public String manageOffers(Connection connection, Scanner in) throws SQLException {
		QueryOracle query = new QueryOracle();
		String[][] result = null;
		String input = "";
		String secondaryInput = "";
		String input3 = "";
		boolean invalid = false;

		do {
			query.invalid(invalid);
			query.showStandardOptions("show back");

			System.out.println("Which offer do you want to modify?");
			input = in.nextLine();

			if (input.equalsIgnoreCase("log") || input.equalsIgnoreCase("exit"))
				return input;
			else if (input.equalsIgnoreCase("back"))
				return "5";

			if (!query.displayQuery(
					query.showQuery(connection.prepareStatement("SELECT * FROM Offers WHERE OfferID = " + input))))
				invalid = true;
			else {
				System.out.println("1. Reject Offer");
				System.out.println("2. Accept Offer");
				System.out.println("Type anything else to cancel.");
				secondaryInput = in.nextLine();

				switch (Integer.parseInt(secondaryInput)) {
				case 2:
					do {
						System.out.println("How long is the car loan in months? (24 - 84)");
						input3 = in.nextLine();

						if (Integer.parseInt(input3) < 24 || Integer.parseInt(input3) > 84) {
							System.out.println("Loan length invalid, re-enter.");
						}
					} while (Integer.parseInt(input3) < 24 || Integer.parseInt(input3) > 84);

					result = query
							.showQuery(connection.prepareStatement("SELECT * FROM Offers WHERE OfferID = " + input));
					String[] inputs = { result[0][2], result[0][3], input3, input };
					query.getResult(connection.prepareStatement(
							"UPDATE Cars SET OwnerID = ?, Price = ?, PayMonths = ?, BoughtYear = "
							+ "YEAR(CURRENT_DATE()), BoughtMonth = MONTH(CURRENT_DATE()) WHERE CarID = "
							+ query.returnFirst(query.showQuery(connection.prepareStatement("SELECT OfferCarUserID FROM Offers WHERE OfferID = " + input)))),
							connection.prepareStatement("commit"), new DataObject(inputs));
				case 1:
					String[] inputs2 = { input };
					query.getResult(connection.prepareStatement("DELETE FROM Offers WHERE OfferID = " + "?"),
							connection.prepareStatement("commit"), new DataObject(inputs2));
					break;
				default:
					invalid = true;
					break;
				}
			}

		} while (!input.equalsIgnoreCase("back") && !input.equalsIgnoreCase("exit"));

		return "5";
	}
}
