package com.revature.data.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.data.UserDAO;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class UserOracle implements UserDAO {
	private static Logger log = Logger.getLogger(UserOracle.class);
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public int addUser(User user) {
		int key =0;
		log.trace("Adding user to database.");
		log.trace(user);
		Connection conn = cu.getConnection();
		try{
			conn.setAutoCommit(false);
			String sql = "insert into login (username, pswd, first_name, last_name) values(?,?,?,?)";
			String[] keys = {"id"};
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setString(1,user.getUsername());
			pstm.setString(2, user.getPassword());
			pstm.setString(3, user.getFirst());
			pstm.setString(4, user.getLast());
			
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			
			if(rs.next())
			{
				log.trace("User created.");
				key = rs.getInt(1);
				user.setId(key);
				conn.commit();
			}
			else
			{
				log.trace("User not created.");
				conn.rollback();
			}
		}
		catch(SQLException e)
		{
			LogUtil.rollback(e,conn, UserOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, UserOracle.class);
			}
		}
		return key;
	}

	@Override
	public User getUser(String username, String password) {
		User u = null;
		log.trace("Retrieve user from database.");
		try(Connection conn = cu.getConnection()){
			String sql = "select id, first_name, last_name "
					+ "from login where username=? and pswd = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery();
			//username is unique, this query can only ever return a single result, so if is ok.
			if(rs.next())
			{
				log.trace("User found.");
				u = new User();
				u.setUsername(username);
				u.setPassword(password);
				u.setFirst(rs.getString("first_name"));
				u.setLast(rs.getString("last_name"));
				u.setId(rs.getInt("id"));
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e, UserOracle.class);
		}
		
		return u;
	}
	@Override
	public User getUser(User u){
		log.trace("Retrieve user from database.");
		if(u == null)
		{
			throw new RuntimeException("User u cannot be null");
		}
		try(Connection conn = cu.getConnection()){
			String sql = "select id, first_name, last_name "
					+ "from login where username=? and pswd = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, u.getUsername());
			pstm.setString(2, u.getPassword());
			ResultSet rs = pstm.executeQuery();
			//username is unique, this query can only ever return a single result, so if is ok.
			if(rs.next())
			{
				log.trace("User found.");
				u.setFirst(rs.getString("first_name"));
				u.setLast(rs.getString("last_name"));
				u.setId(rs.getInt("id"));
			} else {
				u.setId(0);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e, UserOracle.class);
		}
		
		return u;
	}
	@Override
	public User getUserById(User u){
		log.trace("Retrieve user from database.");
		if(u == null)
		{
			throw new RuntimeException("User u cannot be null");
		}
		try(Connection conn = cu.getConnection()){
			String sql = "select id, first_name, last_name, username "
					+ "from login where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, u.getId());
			ResultSet rs = pstm.executeQuery();
			//id is unique, this query can only ever return a single result, so if is ok.
			if(rs.next())
			{
				log.trace("User found.");
				u.setFirst(rs.getString("first_name"));
				u.setLast(rs.getString("last_name"));
				u.setUsername(rs.getString("username"));
				u.setId(rs.getInt("id"));
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e, UserOracle.class);
		}
		
		return u;
	}
	@Override
	public void deleteUser(User user) {
		log.trace("Removing user from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from login where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, user.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.trace("User not deleted.");
				conn.rollback();
			}
			else
			{
				log.trace("User deleted.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e, UserOracle.class);
		}
	}

	@Override
	public void updateUser(User user) {
		log.trace("Updating user in database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update login set first_name=?, last_name=?, pswd=? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getFirst());
			pstm.setString(2, user.getLast());
			pstm.setString(3, user.getPassword());
			pstm.setInt(4, user.getId());
			
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.trace("User not updated.");
				conn.rollback();
			}
			else
			{
				log.trace("User updated.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e, UserOracle.class);
		}
	}
}
