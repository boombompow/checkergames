package org.soen387.domain.player.tdg;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class PlayerTDG {
	public static final String TABLE_NAME = "player";
	public static final String COLUMNS = "id, first_name, last_name, email, version";
	public static final String TRUNCATE_TABLE = "TRUNCATE TABLE  " + TABLE_NAME + ";";
	public static final String DROP_TABLE = "DROP TABLE  " + TABLE_NAME + ";";
	public static final String CREATE_TABLE ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" 
											+ "id BIGINT PRIMARY KEY, "
											+ "first_name VARCHAR(22), "
											+ "last_name VARCHAR(22),"
											+ "email VARCHAR(22),"
											+ "version INT); ";
	
	public static final String INSERT = "INSERT INTO " + TABLE_NAME + " (" + COLUMNS + ") "
										+ "VALUES (?,?,?,?,?);";
	
	public static final String UPDATE = "UPDATE " + TABLE_NAME + " "
										+ "SET version=version+1, "
										+ "first_name=?, "
										+ "last_name=?, "
										+ "email=?"
										+ "WHERE id=? AND version=?;";
	
	public static final String DELETE = "DELETE FROM " + TABLE_NAME + " "
										+ "WHERE id=? AND version=?;";
	
	public static final String FIND_BY_ID = "SELECT " + COLUMNS + " FROM " + TABLE_NAME + 
												" WHERE id = ?;";
	
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
	
	public static int insert(long id, String f, String l, String e) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT);
		ps.setLong	(1	, id);
		ps.setString(2	, f);
		ps.setString(3	, l);
		ps.setString(4	, e);
		ps.setInt	(5	, 1);
		
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
	
	public static int update(long id, String f, String l, String e, int v) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE);
		ps.setString(1	, f);
		ps.setString(2	, l);
		ps.setString(3	, e);
		ps.setLong	(4	, id);
		ps.setInt	(5	, v);
		
		System.out.println(ps.toString());
		return ps.executeUpdate();
	}
	
	public static ResultSet findById(long id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_BY_ID);
		ps.setLong(1,id);
		ResultSet rs = ps.executeQuery();
		
		return rs;		
	}
	
	public static ResultSet findAll() throws SQLException {
	  	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_ALL);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	
	public static ResultSet getId() throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT id FROM player ORDER BY id DESC LIMIT 1;";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	} 
}
