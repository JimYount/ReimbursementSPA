package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.beans.Address;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class AddressOracle implements AddressDAO {
	private Logger log = Logger.getLogger(AddressOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public int addAddress(Address address) {
		int key = 0;
		log.trace("Adding user to database.");
		log.trace(address);
		Connection conn = cu.getConnection();
		try{
			conn.setAutoCommit(false);
			String sql = "insert into address (lineone, linetwo, city, state, zip) values(?,?,?,?,?)";
			String[] keys = {"id"};
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setString(1,address.getLineOne());
			pstm.setString(2, address.getLineTwo());
			pstm.setString(3, address.getCity());
			pstm.setString(4, address.getState());
			pstm.setString(5, address.getZip());
			
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			
			if(rs.next())
			{
				log.trace("address created.");
				key = rs.getInt(1);
				address.setId(key);
				conn.commit();
			}
			else
			{
				log.trace("address not created.");
				conn.rollback();
			}
		}
		catch(SQLException e)
		{
			LogUtil.rollback(e,conn,AddressOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,AddressOracle.class);
			}
		}
		return key;
	}

	@Override
	public Address getAddress(int id) {
		Address a = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("retrieving address information");
			String sql = "select lineone, linetwo, city, state, zip from address where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				log.trace("address found");
				a = new Address();
				a.setId(id);
				a.setLineOne(rs.getString("lineone"));
				a.setLineTwo(rs.getString("linetwo"));
				a.setCity(rs.getString("city"));
				a.setState(rs.getString("state"));
				a.setZip(rs.getString("zip"));
			}
		} catch (Exception e) {
			LogUtil.logException(e,AddressOracle.class);
		}
		return a;
	}

	@Override
	public void deleteAddress(Address address) {
		log.trace("Removing address from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from address where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, address.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("address not deleted.");
				conn.rollback();
			}
			else
			{
				log.trace("address deleted.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,AddressOracle.class);
		}
	}

	@Override
	public void updateAddress(Address address) {
		log.trace("Updating address from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update address set lineone=?, linetwo=?, city=?, state=?, zip=? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, address.getLineOne());
			pstm.setString(2, address.getLineTwo());
			pstm.setString(3, address.getCity());
			pstm.setString(4, address.getState());
			pstm.setString(5, address.getZip());
			pstm.setInt(6, address.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("address not updated.");
				conn.rollback();
			}
			else
			{
				log.trace("address updated.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,AddressOracle.class);
		}
	}

}
