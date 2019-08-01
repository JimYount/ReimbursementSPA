package com.revature.driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.Genre;
import com.revature.beans.User;
import com.revature.data.GenreDAO;
import com.revature.data.GenreOracle;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class Driver {
	private static Logger log = Logger.getLogger(Driver.class);
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public static void main(String[] args) {
		//firstAttempt();
		//System.out.println(getGenre(2));
		//login();
		daos();
	}

	private static void daos() {
		GenreDAO gd = new GenreOracle();
		for(Genre g : gd.getGenres()) {
			log.trace(g);
		}
	}

	private static void login() {
		Scanner scan = new Scanner(System.in);
		String username = null;
		String password = null;
		User u = null;
		System.out.println("Username: ");
		username = scan.nextLine();
		System.out.println("Password: ");
		password = scan.nextLine();
		
		u = getUser2(username, password);
		
		log.trace(u);
	}

	private static User getUser2(String username, String password) {
		// You are not allowed to use Statements
		User u = null;
		String sql = "Select id, first_name, last_name,"
				+ " username, pswd from "
				+ "login where username = ? and pswd= ?";
		try(Connection conn = cu.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setFirst(rs.getString("first_name"));
				u.setLast(rs.getString("last_name"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("pswd"));
			}
		} catch(Exception e) {
			LogUtil.logException(e, Driver.class);
		}
		return u;
	}

	private static User getUser(String username, String password) {
		User u = null;
		String sql = "Select id, first_name, last_name,"
				+ " username, pswd from "
				+ "login where username ='"+username
				+"' and pswd='"+password+"'";
		try(Connection conn = cu.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setFirst(rs.getString("first_name"));
				u.setLast(rs.getString("last_name"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("pswd"));
			}
		} catch(Exception e) {
			LogUtil.logException(e, Driver.class);
		}
		return u;
	}

	private static Genre getGenre(int id) {
		Genre g = null;
		String sql= "select genre from genre where id = "+id;
		try(Connection conn = cu.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				g = new Genre();
				g.setId(id);
				g.setGenre(rs.getString("genre"));
			}
		} catch(Exception e) {
			LogUtil.logException(e, Driver.class);
		}
		return g;
	}
	private static void firstAttempt() {
		//String sql = "select * from book";
		String sql = "select id, title from book";
		try(Connection conn = cu.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				//log.trace(rs.getInt(1)+" "+rs.getString(4));
				log.trace(rs.getInt("id")+" "+rs.getString("title"));
			}
		} catch(SQLException e) {
			LogUtil.logException(e, Driver.class);
		}
	}
}
