package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.beans.DataObject;

public class CarOracle implements CarDAO {
	private String input;
	private String secondaryInput;
	private QueryOracle query;

	public String addCar(Connection connection, Scanner in) throws SQLException {
		query = new QueryOracle();

		System.out.println("What is the car's make?");
		String input1 = in.nextLine();
		System.out.println("What is the car's model?");
		String input2 = in.nextLine();
		System.out.println("What is the car's color?");
		String input3 = in.nextLine();
		System.out.println("What year was the car made?");
		String input4 = in.nextLine();
		System.out.println("What is the baseline price for the car?");
		String input5 = in.nextLine();
		System.out.println("What is the url of the picture?");
		String input6 = in.nextLine();

		String[] inputs = { input1, input2, input3, input4, input5, input6 };

		query.getResult(
				connection.prepareStatement("INSERT INTO Cars (Make, Model, Color, Year, Price, PicURL) VALUES (?, ?, ?, ?, ?, ?)"),
				connection.prepareStatement("commit"), new DataObject(inputs));

		return "";
	}

	public String buyCar(Connection connection, Scanner in, int userID) throws SQLException {
		boolean invalid = false;
		String[] inputs = new String[3];

		query = new QueryOracle();

		do {
			query.invalid(invalid);
			query.showStandardOptions("show back");
			
			query.displayQuery(query.showQuery(connection.prepareStatement(
					"SELECT * FROM Cars WHERE OwnerID IS NOT NULL")));

			System.out.println("Which car do you want to buy?");
			input = in.nextLine();

			if (input.equalsIgnoreCase("log") || input.equalsIgnoreCase("exit"))
				return input;
			else if (input.equalsIgnoreCase("back"))
				return "2";
			else if (Integer.parseInt(input) > 0) {
				if (query.displayQuery(query.showQuery(connection.prepareStatement(
						"SELECT * FROM Cars WHERE OwnerID IS NOT NULL AND CarID = '" + input + "'")))) {
					System.out.println("What is your offer?");
					secondaryInput = in.nextLine();

					if (Double.class.isInstance(Double.parseDouble((input)))) {
						inputs[0] = input;
						inputs[1] = userID + "";
						inputs[2] = secondaryInput;
						query.getResult(
								connection.prepareStatement(
										"INSERT INTO Offers (OfferCarID, OfferCarUserID, Offer) VALUES (?, ?, ?)"),
								connection.prepareStatement("commit"), new DataObject(inputs));
					}
				} else {
					invalid = true;
				}
			}

		} while (!input.equalsIgnoreCase("back") && !input.equalsIgnoreCase("exit"));

		return "2";
	}
}
