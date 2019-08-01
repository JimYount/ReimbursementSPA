package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Address;
import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class CustomerOracle implements CustomerDAO
{
	private Logger log = Logger.getLogger(CustomerOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public void addCustomer(Customer customer) {
		Connection conn = cu.getConnection();
		try {
			log.trace("Inserting customer into db");
			conn.setAutoCommit(false);
			String sql = "insert into customer (ID, address_id, fav_color) values (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,customer.getId());
			ps.setInt(2, customer.getAddress().getId());
			ps.setString(3, customer.getFavoriteColor());
			int result = ps.executeUpdate();
			if(result!=1)
			{
				log.warn("Insertion of customer failed.");
				conn.rollback();
			}
			else {
				log.trace("Successful insertion of customer");
				conn.commit();
			}
		}
		catch(SQLException e)
		{
			LogUtil.rollback(e,conn,CustomerOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,CustomerOracle.class);
			}
		}
	}

	@Override
	public Customer getCustomer(Customer cust) {
		if(cust==null)
		{
			throw new RuntimeException("cust can't be null");
		}
		try(Connection conn = cu.getConnection())
		{
			String sql = "select ID, address_id, fav_color from Customer where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,cust.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				log.trace("This is a customer");
				cust.setFavoriteColor(rs.getString("fav_color"));
				cust.setAddress(new Address(rs.getInt(1),null,null,null,null,null));
			}
			else
			{
				log.trace("This is not a customer");
				cust.setFirst(null);
				cust.setLast(null);
				cust.setId(0);
				cust.setPassword(null);
				cust.setUsername(null);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,CustomerOracle.class);
		}
		return cust;
	}

	@Override
	public Set<Customer> getCustomers() {
		Set<Customer> custList = new HashSet<Customer>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "Select id, address_id, fav_color from customer";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Customer cust = new Customer();
				cust.setAddress(new Address(rs.getInt("address_id")));
				cust.setFavoriteColor(rs.getString("fav_color"));
				cust.setId(rs.getInt("id"));
				custList.add(cust);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,CustomerOracle.class);
		}
		return custList;
	}
	@Override
	public void updateCustomer(Customer customer)
	{
		log.trace("Updating customer from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			removeReadingList(conn,customer.getId());
			
			String sql = "update customer set address_id = ?, fav_color = ? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, customer.getAddress().getId());
			pstm.setString(2, customer.getFavoriteColor());
			pstm.setInt(3, customer.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("customer not updated.");
				conn.rollback();
			}
			else
			{
				log.trace("customer updated.");
				addReadingList(conn, customer.getId(), customer.getReadingList());
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,CustomerOracle.class);
		}
	}

	@Override
	public void deleteCustomer(Customer customer) {
		log.trace("Removing customer from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			removeReadingList(conn,customer.getId());
			
			String sql = "delete from customer where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, customer.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("customer not deleted.");
				conn.rollback();
			}
			else
			{
				log.trace("customer deleted.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,CustomerOracle.class);
		}
	}
	private void removeReadingList(Connection conn, int cust_id) throws SQLException {
		log.trace("removing all books from readinglist");
		String sql = "delete from reading_list where cust_id=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, cust_id);
		pstm.executeUpdate();
	}
	/**
	 * This method will add authors to the book_author table for later retrieval
	 * @param conn the Connection object to the database
	 * @param book_id the id of the book to be associated with the author
	 * @param authors A list of authors to be associated with the book
	 * @return the number of authors successfully inserted.
	 * @throws SQLException
	 */
	private int addReadingList(Connection conn, int cust_id, Set<Book> rList) throws SQLException{
		String sql;
		PreparedStatement pstm;
		int numInserted = 0;
		for(Book b : rList)
		{
			log.trace("Inserting books into reading_list");
			sql = "insert into reading_list (cust_id, book_id) values (?,?)";
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1, cust_id);
			pstm.setInt(2, b.getId());
			int result = pstm.executeUpdate();
			if(result != 1)
			{
				log.warn("Genre insertion failed.");
			}
			else
			{
				numInserted++;
			}
		}
		return numInserted;
	}
}
