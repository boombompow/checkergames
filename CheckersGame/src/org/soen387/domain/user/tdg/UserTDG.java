package org.soen387.domain.user.tdg;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserTDG {
	public static final String TABLE_NAME = "user";
	public static final String COLUMNS = "id, username, password, version";
	public static final String TRUNCATE_TABLE = "TRUNCATE TABLE  " + TABLE_NAME + ";";
	public static final String DROP_TABLE = "DROP TABLE  " + TABLE_NAME + ";";
	public static final String CREATE_TABLE ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" 
											+ "id BIGINT, "
											+ "username VARCHAR(22), "
											+ "password VARCHAR(22),"
											+ "version INT,"
											+ "FOREIGN KEY (id) REFERENCES player(id)); ";
	
	public static final String INSERT = "INSERT INTO " + TABLE_NAME + " (" + COLUMNS + ") "
										+ "VALUES (?,?,?,?);";
	
	public static final String UPDATE = "UPDATE " + TABLE_NAME + " "
										+ "SET version=version+1, "
										+ "username=?, "
										+ "password=? "
										+ "WHERE id=? AND version=?;";
	
	public static final String DELETE = "DELETE FROM " + TABLE_NAME + " "
										+ "WHERE id=? AND version=?;";
	
	public static final String FIND_BY_ID = "SELECT " + COLUMNS + " FROM " + TABLE_NAME + 
												" WHERE id = ?;";
	
	public static final String FIND_USER = "SELECT * FROM " + TABLE_NAME + 
												" WHERE username = ? AND password = ?;";
	
	public static final String FIND_ALL = "SELECT " + COLUMNS + " FROM " + TABLE_NAME + ";";
	
	public static void createTable() throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		Statement update = con.createStatement();
		update.execute(CREATE_TABLE);
	}

	public static void dropTable() throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		Statement update = con.createStatement();
		update.execute(TRUNCATE_TABLE);
		update = con.createStatement();
		update.execute(DROP_TABLE);
	}
	
	public static int insert(long id, String user, String pass, int v) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT);
		ps.setLong	(1	, id);
		ps.setString(2	, user);
		ps.setString(3	, pass);
		ps.setInt	(4	, v);		
		
		System.out.println(ps.toString());
		return ps.executeUpdate();
	}
	
	public static int delete(long id, int v) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE);
		ps.setLong	(1	, id);
		ps.setInt	(2	, v);		
		
		System.out.println(ps.toString());
		return ps.executeUpdate();
	}
	
	public static int update(long id, String user, String pass) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE);
		ps.setString(1	, user);
		ps.setString(2	, pass);
		ps.setLong	(3	, id);
		ps.setInt	(4	, 1);
		System.out.println(ps.toString());
		return ps.executeUpdate();
	}
	
	public static ResultSet findById(long id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_BY_ID);
		ps.setLong(1,id);
		
		return ps.executeQuery();		
	}
	
	public static ResultSet findAll() throws SQLException {
	  	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_ALL);
		
		return ps.executeQuery();
	}
	
	public static ResultSet findUser(String username, String password) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_USER);
		ps.setString(1, username);
		ps.setString(2, password);
		
		return ps.executeQuery();		
	}
	
	public static ResultSet getId() throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT id FROM user ORDER BY id DESC LIMIT 1;";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	} 
}
