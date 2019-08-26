package com.revature.data.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Author;
import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.beans.Genre;
import com.revature.data.BookDAO;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class BookOracle implements BookDAO{
	private static Logger log = Logger.getLogger(BookOracle.class);
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public int addBook(Book b) {
		int key = 0;
		log.trace("adding book to the database: "+b);
		Connection conn = cu.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "insert into book(isbn10, isbn13, title, "
					+ "price, stock, cover) values (?,?,?,?,?,?)";
			String[] keys = {"id"};
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setString(1, b.getIsbn10());
			pstm.setString(2, b.getIsbn13());
			pstm.setString(3, b.getTitle());
			pstm.setDouble(4, b.getPrice());
			pstm.setInt(5, b.getStock());
			pstm.setString(6, b.getCover());
			
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			if(rs.next()) {
				log.trace("Book created");
				key = rs.getInt(1);
				b.setId(key);
				addAuthors(conn, key, b.getAuthors());
				addGenres(conn, key, b.getGenres());
				conn.commit();
			} else {
				log.trace("book not created!");
				conn.rollback();
			}
		} catch (SQLException e) {
			LogUtil.rollback(e, conn, BookOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				LogUtil.logException(e1, BookOracle.class);
			}
		}
		return key;
	}

	private int addAuthors(Connection conn, int bookId, Set<Author> authors) throws SQLException {
		String sql;
		PreparedStatement pstm;
		int numInserted = 0;
		if(authors==null) {
			return 0;
		}
		for(Author a: authors) {
			log.trace("Inserting authors into book_author");
			sql = "insert into book_author(book_id, author_id) values (?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bookId);
			pstm.setInt(2, a.getId());
			int result = pstm.executeUpdate();
			if(result != 1) {
				log.warn("Author insertion failed.");
			} else {
				numInserted++;
			}
		}
		return numInserted;
	}
	private int addGenres(Connection conn, int bookId, Set<Genre> genres) throws SQLException {
		String sql;
		PreparedStatement pstm;
		int numInserted = 0;
		if (genres == null)
			return 0;
		for (Genre g : genres) {
			log.trace("Inserting genres into book_genre");
			sql = "insert into book_genre (book_id, genre_id) values (?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bookId);
			pstm.setInt(2, g.getId());
			int result = pstm.executeUpdate();
			if (result != 1) {
				log.warn("Genre insertion failed.");
			} else {
				numInserted++;
			}
		}
		return numInserted;
	}

	@Override
	public Book getBook(int i) {
		Book b = null;
		log.trace("Retrieving book from database.");
		try (Connection conn = cu.getConnection()) {
			String sql = "select isbn10, isbn13, title, price, stock, cover from book where id=?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, i);

			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				log.trace("Book found!");
				b = new Book();
				b.setId(i);
				b.setIsbn10(rs.getString("isbn10"));
				b.setIsbn13(rs.getString("isbn13"));
				b.setPrice(rs.getDouble("price"));
				b.setStock(rs.getInt("stock"));
				b.setTitle(rs.getString("title"));
				b.setCover(rs.getString("cover"));
				b.setGenres(new HashSet<Genre>());
				b.setAuthors(new HashSet<Author>());
			}
		} catch (Exception e) {
			LogUtil.logException(e, BookOracle.class);
		}
		return b;
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		Book b = null;
		log.trace("retrieving book from database.");
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, isbn10, isbn13, title, price, cover, stock from book where isbn10=? or isbn13=?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, isbn);
			pstm.setString(2, isbn);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				log.trace("Book found.");
				b = new Book();
				b.setId(rs.getInt("id"));
				b.setIsbn10(rs.getString("isbn10"));
				b.setIsbn13(rs.getString("isbn13"));
				b.setPrice(rs.getDouble("price"));
				b.setStock(rs.getInt("stock"));
				b.setCover(rs.getString("cover"));
				b.setTitle(rs.getString("title"));
				b.setGenres(new HashSet<Genre>());
				b.setAuthors(new HashSet<Author>());
			}
		} catch (Exception e) {
			LogUtil.logException(e, BookOracle.class);
		}
		return b;
	}

	@Override
	public Set<Book> getBooks() {
		Set<Book> bookList = new HashSet<Book>();
		log.trace("retrieving all books from database.");
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, isbn10, isbn13, title, price, stock, cover from book";

			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setIsbn10(rs.getString("isbn10"));
				b.setIsbn13(rs.getString("isbn13"));
				b.setPrice(rs.getDouble("price"));
				b.setStock(rs.getInt("stock"));
				b.setTitle(rs.getString("title"));
				b.setCover(rs.getString("cover"));
				b.setGenres(new HashSet<Genre>());
				b.setAuthors(new HashSet<Author>());
				bookList.add(b);
			}
		} catch (Exception e) {
			LogUtil.logException(e, BookOracle.class);
		}
		return bookList;
	}

	@Override
	public Set<Book> getBooksByAuthor(Author a) {
		Set<Book> bookList = new HashSet<Book>();
		log.trace("retrieving all books from database.");
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, isbn10, isbn13, title, price, stock, cover"
					+ " from book join book_author on book.id=book_author.book_id where author_id =?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, a.getId());

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setIsbn10(rs.getString("isbn10"));
				b.setIsbn13(rs.getString("isbn13"));
				b.setPrice(rs.getDouble("price"));
				b.setStock(rs.getInt("stock"));
				b.setTitle(rs.getString("title"));
				b.setCover(rs.getString("cover"));
				b.setGenres(new HashSet<Genre>());
				b.setAuthors(new HashSet<Author>());
				bookList.add(b);
			}
		} catch (Exception e) {
			LogUtil.logException(e, BookOracle.class);
		}
		return bookList;
	}

	@Override
	public Set<Book> getReadingList(Customer c) {
		Set<Book> bookList = new HashSet<Book>();
		log.trace("retrieving all books from database.");
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, isbn10, isbn13, title, price, stock, cover"
					+ " from book join reading_list on book.id=reading_list.book_id where cust_id =?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, c.getId());

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setIsbn10(rs.getString("isbn10"));
				b.setIsbn13(rs.getString("isbn13"));
				b.setPrice(rs.getDouble("price"));
				b.setStock(rs.getInt("stock"));
				b.setTitle(rs.getString("title"));
				b.setCover(rs.getString("cover"));
				b.setGenres(new HashSet<Genre>());
				b.setAuthors(new HashSet<Author>());
				bookList.add(b);
			}
		} catch (Exception e) {
			LogUtil.logException(e, BookOracle.class);
		}
		return bookList;
	}

	@Override
	public void updateBook(Book b) {
		log.trace("updating book in database: " + b);
		Connection conn = cu.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "update book set isbn10=?, isbn13=?, title=?, price=?, stock=?, cover=?" + " where id=?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, b.getIsbn10());
			pstm.setString(2, b.getIsbn13());
			pstm.setString(3, b.getTitle());
			pstm.setDouble(4, b.getPrice());
			pstm.setInt(5, b.getStock());
			pstm.setString(6, b.getCover());
			pstm.setInt(7, b.getId());

			int result = pstm.executeUpdate();

			if (result == 1) {
				log.trace("Book updated");
				removeAuthors(conn, b.getId());
				removeGenres(conn, b.getId());

				log.trace("Adding authors to book_author");
				int numberInserted = addAuthors(conn, b.getId(), b.getAuthors());
				if (b.getAuthors() != null && numberInserted != b.getAuthors().size()) {
					log.warn("Rolling back book insertion.");
					conn.rollback();
					return;
				} else {
					log.trace("Book associated with all authors!");
				}
				log.trace("Adding genres to book_genre");
				numberInserted = addGenres(conn, b.getId(), b.getGenres());
				if (b.getGenres() != null && numberInserted != b.getGenres().size()) {
					log.warn("Rolling back book insertion.");
					conn.rollback();
					return;
				} else {
					log.trace("Book associated with all genres!");
				}
				log.trace("Operations complete.");
				conn.commit();
			}
		} catch (SQLException e) {
			LogUtil.rollback(e, conn, BookOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, BookOracle.class);
			}
		}
	}
	@Override
	public void deleteBook(Book b) {
		log.trace(b);
		Connection conn = cu.getConnection();
		try {
			conn.setAutoCommit(false);
			removeAuthors(conn, b.getId());
			removeGenres(conn, b.getId());
			String sql = "delete from book where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, b.getId());

			int result = pstm.executeUpdate();
			if (result == 1) {
				log.trace("Book deleted.");
				conn.commit();
			} else {
				log.trace("Book not deleted.");
				conn.rollback();
			}
		} catch (SQLException e) {
			LogUtil.rollback(e, conn, BookOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, BookOracle.class);
			}
		}
	}
	private void removeGenres(Connection conn, int bookId) throws SQLException {
		log.trace("removing all genres from book_genre");
		String sql = "delete from book_genre where book_id=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, bookId);
		pstm.executeUpdate();
	}

	private void removeAuthors(Connection conn, int bookId) throws SQLException {
		log.trace("removing all authors from book_author");
		String sql = "delete from book_author where book_id=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, bookId);
		pstm.executeUpdate();
	}
}
