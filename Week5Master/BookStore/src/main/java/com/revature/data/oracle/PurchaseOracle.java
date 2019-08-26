package com.revature.data.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.beans.InvoiceLine;
import com.revature.beans.Purchase;
import com.revature.data.PurchaseDAO;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

import oracle.jdbc.OracleTypes;

public class PurchaseOracle implements PurchaseDAO{

	private static Logger log = Logger.getLogger(PurchaseOracle.class);
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public double addBookToCart(Purchase purchase, Book book) {
		double total = 0;
		
		try(Connection conn = cu.getConnection()) {
			String sql = "call add_book_to_cart(?,?,?,?)";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, purchase.getId());
			cstmt.setInt(2, book.getId());
			cstmt.registerOutParameter(3, OracleTypes.NUMBER);
			cstmt.registerOutParameter(4, OracleTypes.CURSOR);
			cstmt.executeUpdate();
			
			// retrieve our cursor
			ResultSet rs = (ResultSet) cstmt.getObject(4);
			// retrieve our total
			total = cstmt.getDouble(3);
			
			while(rs.next()) {
				log.trace("Purchase: "+rs.getInt(1)+"\t"
						+ "Book: "+rs.getInt(2)+"\tQuantity: "+rs.getInt(3));
				
			}
		} catch(Exception e) {
			LogUtil.logException(e, PurchaseOracle.class);
		}
		return total;
	}

	@Override
	public double removeBookFromCart(Purchase purchase, Book book) {
		double total = 0;
		try (Connection conn = cu.getConnection()) {
			CallableStatement cstmt = conn.prepareCall("call remove_book_from_cart(?,?,?,?)");
			cstmt.setInt(1, purchase.getId());
			cstmt.setInt(2, book.getId());
			cstmt.registerOutParameter(3, OracleTypes.NUMBER);
			cstmt.registerOutParameter(4, OracleTypes.CURSOR);
			cstmt.executeUpdate();
			ResultSet rs = (ResultSet) cstmt.getObject(4);

			total = cstmt.getDouble(3);
			log.trace("Total=" + total);
			purchase.setTotal(total);
			while (rs.next()) {
				log.trace("Purchase: " + rs.getInt(1) + "\tBook: " + rs.getInt(2) + "\tQuantity: " + rs.getInt(3));
			}
		} catch (Exception e) {
			LogUtil.logException(e, PurchaseOracle.class);
		}
		return total;
	}

	@Override
	public void emptyCart(Purchase purchase) {
		try (Connection conn = cu.getConnection()) {
			CallableStatement cstmt = conn.prepareCall("call empty_cart(?)");
			cstmt.setInt(1, purchase.getId());
			cstmt.executeUpdate();
		} catch (Exception e) {
			LogUtil.logException(e, PurchaseOracle.class);
		}
	}

	@Override
	public int addPurchase(Purchase p) {
		int key = 0;
		log.trace("Adding purchase to database.");
		log.trace(p);
		Connection conn = cu.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "insert into purchase (customer_id, status) values(?, ?)";
			String[] keys = { "id" };
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setInt(1, p.getCust().getId());
			pstm.setString(2, p.getStatus());
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();

			if (rs.next()) {
				log.trace("purchase created.");
				key = rs.getInt(1);
				p.setId(key);
				conn.commit();
			} else {
				log.trace("purchase not created.");
				conn.rollback();
			}
		} catch (SQLException e) {
			LogUtil.rollback(e, conn, PurchaseOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, PurchaseOracle.class);
			}
		}
		return key;
	}

	@Override
	public Purchase getPurchase(int i) {
		Purchase p = null;

		try (Connection conn = cu.getConnection()) {
			String sql = "select p.id as id, p.customer_id as cust, p.total as tot, p.status as status,"
					+ "pb.book_id as book, pb.quantity as quan from purchase p left outer join "
					+ "purchase_book pb on p.id = pb.purchase_id where p.id =?";
			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setInt(1, i);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				log.trace("Purcahse found.");
				p = new Purchase();
				p.setId(rs.getInt("id"));
				p.setCust(new Customer(rs.getInt("cust")));

				p.setTotal(rs.getDouble("tot"));
				p.setStatus(rs.getString("status"));
				p.setInvoiceLines(new HashSet<InvoiceLine>());
				do {
					if (rs.getInt("book") != 0) {
						InvoiceLine in = new InvoiceLine();
						in.setBook(new Book());
						in.getBook().setId(rs.getInt("book"));
						in.setQuantity(rs.getInt("quan"));
						p.getInvoiceLines().add(in);
					}
				} while (rs.next());
			}
		} catch (Exception e) {
			LogUtil.logException(e, PurchaseOracle.class);
		}

		return p;
	}

	@Override
	public Set<Purchase> getPurchases() {
		Set<Purchase> purchases = new HashSet<Purchase>();
		log.trace("Retrieve all purchases from database.");
		try (Connection conn = cu.getConnection()) {
			String sql = "select p.id as id, p.customer_id as cust, p.total as tot,"
					+ " p.status as status, pb.book_id as book, pb.quantity as quan"
					+ " from purchase p full outer join purchase_book pb on p.id = "
					+ "pb.purchase_id order by pb.purchase_id";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				log.trace("Purchase found.");
				Purchase p = new Purchase();
				// customer proxy, lol
				p.setId(rs.getInt("id"));

				p.setCust(new Customer());
				p.getCust().setId(rs.getInt("cust"));
				p.setStatus(rs.getString("status"));

				p.setTotal(rs.getDouble("tot"));
				p.setInvoiceLines(new HashSet<InvoiceLine>());
				do {
					// check if it is the same purchase
					if(rs.getInt("id") != p.getId()) {
						// if it isn't, we make a new one.
						purchases.add(p);
						p= new Purchase();
						p.setId(rs.getInt("id"));

						p.setCust(new Customer());
						p.getCust().setId(rs.getInt("cust"));
						p.setStatus(rs.getString("status"));

						p.setTotal(rs.getDouble("tot"));
						p.setInvoiceLines(new HashSet<InvoiceLine>());
					}
					// check to see if there is invoice information
					if (rs.getInt("book") != 0) {
						// add a new invoice line.
						InvoiceLine in = new InvoiceLine();
						in.setBook(new Book());
						in.getBook().setId(rs.getInt("book"));
						in.setQuantity(rs.getInt("quan"));
						p.getInvoiceLines().add(in);
					}
				} while (rs.next());
				purchases.add(p);
			} // end while
		} catch (Exception e) {
			LogUtil.logException(e, PurchaseOracle.class);
		}

		return purchases;
	}

	@Override
	public Set<Purchase> getPurchasesByCustomer(Customer c) {
		Set<Purchase> purchases = new HashSet<Purchase>();
		log.trace("Retrieve purchases by customer from database.");
		try (Connection conn = cu.getConnection()) {
			String sql = "select p.id as id, p.customer_id as cust, p.total as tot, p.status as status, pb.book_id as book, pb.quantity as quan"
					+ " from purchase p full outer join purchase_book pb on p.id = pb.purchase_id where p.customer_id=? order by pb.purchase_id";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, c.getId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Purchase p = new Purchase();
				// customer proxy, lol
				p.setId(rs.getInt("id"));

				p.setCust(c);

				p.setTotal(rs.getDouble("tot"));
				p.setStatus(rs.getString("status"));
				p.setInvoiceLines(new HashSet<InvoiceLine>());
				do {
					if(rs.getInt("id")!=p.getId())
					{
						purchases.add(p);
						p = new Purchase();
						p.setId(rs.getInt("id"));

						p.setCust(new Customer());
						p.setCust(c);
						p.setStatus(rs.getString("status"));

						p.setTotal(rs.getDouble("tot"));
						p.setInvoiceLines(new HashSet<InvoiceLine>());
					}
					if (rs.getInt("book") != 0) {
						InvoiceLine in = new InvoiceLine();
						in.setBook(new Book());
						in.getBook().setId(rs.getInt("book"));
						in.setQuantity(rs.getInt("quan"));
						p.getInvoiceLines().add(in);
					}
				} while (rs.next());
				purchases.add(p);
			}
		} catch (Exception e) {
			LogUtil.logException(e, PurchaseOracle.class);
		}

		return purchases;
	}

	@Override
	public void updatePurchase(Purchase p) {
		log.trace("Updating purchase in database.");
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update purchase set status = ? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, p.getStatus());
			pstm.setInt(2, p.getId());
			int number = pstm.executeUpdate();
			if (number != 1) {
				log.trace("purchase not updated.");
				conn.rollback();
			} else {
				log.trace("purchase updated.");
				conn.commit();
			}
		} catch (Exception e) {
			LogUtil.logException(e, PurchaseOracle.class);
		}
	}

	@Override
	public void deletePurchase(Purchase p) {
		log.trace("Removing purchase from database.");
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			this.removeInvoiceLines(conn, p.getId());
			String sql = "delete from purchase where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, p.getId());
			int number = pstm.executeUpdate();
			if (number != 1) {
				log.trace("purchase not deleted.");
				conn.rollback();
			} else {
				log.trace("purchase deleted.");
				conn.commit();
			}
		} catch (Exception e) {
			LogUtil.logException(e, PurchaseOracle.class);
		}
	}

	private void removeInvoiceLines(Connection conn, int purchase_id) throws SQLException {
		log.trace("removing all purchases from book_purchase");
		String sql = "delete from book_purchase where purchase_id=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, purchase_id);
		pstm.executeUpdate();
	}

	@Override
	public Purchase getPurchaseByStatus(Customer cust, String status) {
		Purchase p = null;
		log.info("Retrieve purchase from database.");
		log.trace(cust);
		log.trace(status);
		try (Connection conn = cu.getConnection()) {
			String sql = "select p.id as id, p.customer_id as cust, p.total as tot, p.status as status, pb.book_id as book, pb.quantity as quan"
					+ " from purchase p full outer join purchase_book pb on p.id = pb.purchase_id where p.status=? and p.customer_id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, status);
			pstm.setInt(2, cust.getId());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				log.trace("Purchase found.");
				p = new Purchase();
				// customer proxy, lol
				p.setId(rs.getInt("id"));

				p.setCust(new Customer());
				p.getCust().setId(rs.getInt("cust"));

				p.setTotal(rs.getDouble("tot"));
				p.setStatus(rs.getString("status"));
				p.setInvoiceLines(new HashSet<InvoiceLine>());
				log.trace(p);
				do {
					if (rs.getInt("book") != 0) {
						InvoiceLine in = new InvoiceLine();
						in.setBook(new Book());
						in.getBook().setId(rs.getInt("book"));
						in.setQuantity(rs.getInt("quan"));
						p.getInvoiceLines().add(in);
					}
				} while (rs.next());
				log.trace(p);
			}
		} catch (Exception e) {
			LogUtil.logException(e, PurchaseOracle.class);
		}

		return p;
	}

}
