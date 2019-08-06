package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Author;
import com.revature.beans.Book;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class AuthorOracle implements AuthorDAO {
	private static Logger log = Logger.getLogger(AuthorOracle.class);
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public int addAuthor(Author a) {
		int key =0;
		log.trace("Adding author to database.");
		log.trace(a);
		Connection conn = cu.getConnection();
		try{
			conn.setAutoCommit(false);
			String sql = "insert into author (firstname,lastname,aboutblurb) values(?,?,?)";
			String[] keys = {"id"};
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setString(1,a.getFirst());
			pstm.setString(2, a.getLast());
			pstm.setString(3, a.getAbout());
			
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			
			if(rs.next())
			{
				log.trace("author created.");
				key = rs.getInt(1);
				a.setId(key);
				conn.commit();
			}
			else
			{
				log.trace("author not created.");
				conn.rollback();
			}
		}
		catch(SQLException e)
		{
			LogUtil.rollback(e,conn,AuthorOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,AuthorOracle.class);
			}
		}
		return key;
	}
	@Override
	public Author getAuthor(int id) {
		Author a = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting author with id: "+id);
			String sql = "Select firstname, lastname, aboutblurb from author where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if(rs.next())
			{
				log.trace("Author found.");
				a = new Author();
				a.setId(id);
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));

			}
		} catch (Exception e) {
			LogUtil.logException(e,AuthorOracle.class);
		}
		return a;
	}
	@Override
	public Author getAuthorByName(String firstname, String lastname) {
		Author a = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting author with firstname ="+firstname+" and lastname="+lastname);
			String sql = "Select id, aboutblurb from author where firstname=? and lastname=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, firstname);
			pstm.setString(2, lastname);
			ResultSet rs = pstm.executeQuery();
			if(rs.next())
			{
				log.trace("Author found.");
				a = new Author();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(firstname);
				a.setLast(lastname);

			}
		} catch (Exception e) {
			LogUtil.logException(e,AuthorOracle.class);
		}
		return a;
	}
	@Override
	public Set<Author> getAuthors() {
		Set<Author> authors = new HashSet<Author>();
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting all authors");
			String sql = "Select id, firstname, lastname, aboutblurb from author";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Author a = new Author();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));
				authors.add(a);
			}
		} catch (Exception e) {
			LogUtil.logException(e,AuthorOracle.class);
		}
		return authors;
	}
	@Override
	public Set<Author> getAuthorsByBook(Book b) {
		Set<Author> authors = new HashSet<Author>();
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting all authors by book");
			String sql = "Select id, firstname, lastname, aboutblurb from author join book_author"
					+ " on author.id=book_author.author_id where book_id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, b.getId());
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Author a = new Author();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));
				authors.add(a);
			}
		} catch (Exception e) {
			LogUtil.logException(e,AuthorOracle.class);
		}
		return authors;
	}
	@Override
	public void updateAuthor(Author a) {
		Connection conn = cu.getConnection();
		try	{
			conn.setAutoCommit(false);
			String sql = "update author set firstname=?, lastname=?, aboutblurb=? where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, a.getFirst());
			pstm.setString(2, a.getLast());
			pstm.setString(3, a.getAbout());
			pstm.setInt(4, a.getId());
			
			int result = pstm.executeUpdate();
			
			if(result == 1)
			{
				log.trace("author updated");
				conn.commit();
			}
			else {
				log.trace("author update failed");
				conn.rollback();
			}
		} catch(SQLException e) {
			LogUtil.rollback(e, conn, AuthorOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,AuthorOracle.class);
			}
		}
	}
	@Override
	public void deleteAuthor(Author a) {
		log.trace("Deleting author: "+a);
		Connection conn = cu.getConnection();
		try	{
			conn.setAutoCommit(false);
			String sql = "delete from author where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, a.getId());
			
			int result = pstm.executeUpdate();
			
			if(result == 1)
			{
				log.trace("author deleted");
				conn.commit();
			}
			else {
				log.trace("author delete failed");
				conn.rollback();
			}
		} catch(SQLException e) {
			LogUtil.rollback(e, conn, AuthorOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,AuthorOracle.class);
			}
		}
	}
	
	
}
