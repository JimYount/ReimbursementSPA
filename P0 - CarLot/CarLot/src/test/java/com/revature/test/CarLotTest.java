package com.revature.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.data.QueryOracle;
import com.revature.utils.ConnectionUtility;

public class CarLotTest {
	/*private static final QueryOracle query = new QueryOracle();
	
	try (ConnectionUtility connUtil = ConnectionUtility.getConnectionUtil();){
	Connection connection = connUtil.getConnection();
	}
	catch (IOException e) {
		e.printStackTrace();
	} 
	
	@Test
	public void testSelect() {
		assertEquals({"Jim", "Yount"}, query.showQuery(connection.prepareStatement("SELECT FIRSTNAME, LASTNAME FROM USERS")));
	}*/
}
