package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class EmployeeOracle implements EmployeeDAO {

	private Logger log = Logger.getLogger(EmployeeOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public void addEmployee(Employee employee) {
		Connection conn = cu.getConnection();
		try {
			log.trace("Inserting employee into db");
			conn.setAutoCommit(false);
			String sql = "insert into emp (id, sup_id, title) values (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,employee.getId());
			ps.setInt(2, employee.getSupervisor().getId());
			ps.setString(3, employee.getTitle());
			int result = ps.executeUpdate();
			if(result!=1)
			{
				log.warn("Insertion of employee failed.");
				conn.rollback();
			}
			else {
				log.trace("Successful insertion of employee");
				conn.commit();
			}
		}
		catch(SQLException e)
		{
			LogUtil.rollback(e,conn,EmployeeOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,EmployeeOracle.class);
			}
		}
	}

	@Override
	public Employee getEmployee(Employee emp) {
		if(emp==null)
		{
			throw new RuntimeException("emp can't be null");
		}
		try(Connection conn = cu.getConnection())
		{
			String sql = "select ID, sup_id, title from emp where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, emp.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				log.trace("This is a employee");
				if(rs.getObject("sup_id")==null)
				{
					log.trace("null supervisor");
				} else {
					emp.setSupervisor(new Employee(rs.getInt("sup_id")));
				}
				emp.setTitle(rs.getString("title"));
			}
			else
			{
				log.trace("This is not a employee");
				emp.setFirst(null);
				emp.setLast(null);
				emp.setId(0);
				emp.setPassword(null);
				emp.setUsername(null);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,EmployeeOracle.class);
		}
		return emp;
	}

	@Override
	public Set<Employee> getEmployees() {
		Set<Employee> empList = new HashSet<Employee>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "Select e.id as \"id\", e.sup_id as \"sup_id\""
					+ ", e.title as \"title\", s.title as \"stitle\""
					+ ", s.sup_id as \"ssupid\""
					+ " from emp e left outer join emp s on e.sup_id = s.id";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Employee emp = new Employee();
				if(rs.getObject("sup_id")==null)
				{
					log.trace("null supervisor");
				} else {
					emp.setSupervisor(new Employee(rs.getInt("sup_id")));
					emp.getSupervisor().setSupervisor(new Employee(rs.getInt("ssupid")));
					emp.getSupervisor().setTitle(rs.getString("stitle"));
				}
				emp.setTitle(rs.getString("title"));
				emp.setId(rs.getInt("id"));
				empList.add(emp);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,EmployeeOracle.class);
		}
		return empList;
	}
	@Override
	public void updateEmployee(Employee employee)
	{
		log.trace("Updating employee from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "update emp set sup_id = ?, title = ? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, employee.getSupervisor().getId());
			pstm.setString(2, employee.getTitle());
			pstm.setInt(3, employee.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("employee not updated.");
				conn.rollback();
			}
			else
			{
				log.trace("employee updated.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,EmployeeOracle.class);
		}
	}

	@Override
	public void deleteEmployee(Employee employee) {
		log.trace("Removing employee from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "delete from emp where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, employee.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("employee not deleted.");
				conn.rollback();
			}
			else
			{
				log.trace("employee deleted.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,EmployeeOracle.class);
		}
	}

}
