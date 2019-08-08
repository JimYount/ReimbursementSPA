package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.beans.DataObject;

public interface QueryDAO {
	public ResultSet getResult(PreparedStatement prepared, PreparedStatement prepared2, DataObject dao) throws SQLException;

	public double getResultDouble(String[][] toCalc) throws SQLException;

	public String[][] showQuery(PreparedStatement pre) throws SQLException;
	
	public boolean displayQuery(String[][] toDisplay);

	public String removeAsset(Connection connection, Scanner in, String table, String conditionField,
			String additionalCondition, String showCondition) throws SQLException;

	public void showStandardOptions(String back);

	public void invalid(boolean invalid);
}
