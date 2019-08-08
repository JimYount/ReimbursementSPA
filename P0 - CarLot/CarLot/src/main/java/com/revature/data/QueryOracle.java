package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.beans.DataObject;

public class QueryOracle implements QueryDAO {
	private boolean invalid;
	private ResultSet result;
	private String input;

	public ResultSet getResult(PreparedStatement prepared, PreparedStatement prepared2, DataObject dao)
			throws SQLException {

		for (int i = 1; i <= dao.getValues().length; i++) {
				prepared.setString(i, dao.getValues()[i - 1]);
		}

		ResultSet result = prepared.executeQuery();
		
		prepared2.executeQuery();

		return result;
	}
	
	public String returnFirst(String[][] input) {
		return input[0][0];
	}

	public double getResultDouble(String[][] toCalc) throws SQLException {
		if (toCalc[0][0] == null)
			return 0;

		if (toCalc.length > 0 && toCalc[0][0].matches("[0-9]+"))
			return Double.parseDouble(toCalc[0][0]);
		else
			return 0;
	}

	public String[][] showQuery(PreparedStatement pre) throws SQLException {
		result = pre.executeQuery();
		ResultSetMetaData meta = result.getMetaData();
		int k = 0;

		while (result.next()) {
			k++;
		}

		DataObject dao = new DataObject(k, meta.getColumnCount());
		ResultSet result2 = pre.executeQuery();

		for (int i = 0; result2.next(); i++) {
			for (int j = 0; j < meta.getColumnCount(); j++) {
				dao.setResult(i, j, result2.getString(j + 1));
			}
		}

		return dao.getResult();
	}

	public boolean displayQuery(String[][] toDisplay) {
		boolean gotQuery = false;

		for (int i = 0; i < toDisplay.length; i++) {
			gotQuery = true;
			System.out.println();
			for (int j = 0; j < toDisplay[0].length; j++) {
				System.out.print(toDisplay[i][j] + " ");
			}
		}
		System.out.println();
		System.out.println();

		return gotQuery;
	}

	public String removeAsset(Connection connection, Scanner in, String table, String conditionField,
			String additionalCondition, String showCondition) throws SQLException {
		invalid = false;

		do {
			showStandardOptions("show back");

			displayQuery(showQuery(connection.prepareStatement("SELECT * FROM " + table + showCondition)));

			System.out.println("Type the number that you want to remove:");
			input = in.nextLine();

			if (input.equalsIgnoreCase("log") || input.equalsIgnoreCase("exit"))
				return input;
			else if (input.equalsIgnoreCase("back"))
				return "1";

			if (input.matches("[0-9]+"))
				if (!displayQuery(showQuery(connection.prepareStatement("SELECT * FROM " + table + " WHERE "
						+ conditionField + " = " + input + " " + additionalCondition))))
					invalid = true;
				else {
					String[] inputs = { input };
					getResult(connection.prepareStatement(
							"DELETE FROM " + table + " WHERE " + conditionField + " = ? " + additionalCondition),
							connection.prepareStatement("commit"), new DataObject(inputs));
				}
		} while (!input.equalsIgnoreCase("back") && !input.equalsIgnoreCase("log") && !input.equalsIgnoreCase("exit"));

		return "1";
	}

	public void showStandardOptions(String back) {
		System.out.println("\nType log at any time to log out.");
		if (back.equalsIgnoreCase("show back"))
			System.out.println("Type back to go back to main menu.");

		System.out.println();

		if (invalid) {
			System.out.println("Invalid input, please re-enter.");
		}

		return;
	}

	public void invalid(boolean invalid) {
		if (invalid)
			System.out.println("\n\nInvalid entry, please re-enter.\n");
	}
}
