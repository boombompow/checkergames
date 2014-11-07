package org.soen387.domain.challenge.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeTDG {
	public static final String TABLE_NAME = "Challenge";
	public static final String COLUMNS = "id, version, status, challenger, challengee";
	public static final String TRUNCATE_TABLE = "TRUNCATE TABLE  " + TABLE_NAME + ";";
	public static final String DROP_TABLE = "DROP TABLE  " + TABLE_NAME + ";";
	public static final String CREATE_TABLE ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" 
			+ "id BIGINT, "
			+ "version int, "
			+ "status int, "
			+ "challenger BIGINT, "
			+ "challengee BIGINT, "
			+ "PRIMARY KEY (id));";

	public static final String UPDATE = "UPDATE " + TABLE_NAME + " "
			+ "SET version=version+1, "
			+ "status=? "
			+ "challenger=? "
			+ "challengee=? "
			+ "WHERE id=? AND version=?;";
	
	public static final String DELETE = "DELETE FROM " + TABLE_NAME + " "
			+ "WHERE id=? AND version=?;";
	public static void deleteChallenge(long id, int version) throws SQLException
	{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE);
		ps.setLong	(1	, id);
		ps.setInt   (2, version); 
		ps.executeUpdate();	
	}
	
	
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
	
	public static void update(long id, int version, int status, long challenger, long challengee) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE);
		ps.setInt	(1	, status);
		ps.setLong	(2	, challenger);
		ps.setLong	(3	, challengee);
		ps.setLong  (4, id);
		ps.setInt   (5, version); 
		ps.executeUpdate();
	}
	
	public static final String INSERT = "INSERT INTO " + TABLE_NAME + " (" + COLUMNS + ") "
			+ "VALUES (?,?,?,?,?);";
	public static int insert(long id, int version, int status, long challenger, long challengee) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT);
		ps.setLong	(1	, id);
		ps.setInt	(2	, version);
		ps.setInt	(3	, status);
		ps.setLong	(4	, challenger);
		ps.setLong	(5	, challengee);
		return ps.executeUpdate();
	}
	
	public static final String FIND_ALL = "SELECT " + COLUMNS + " FROM " + TABLE_NAME + ";";
	public static ResultSet findAll() throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_ALL);
		return ps.executeQuery();
	}
	
	public static final String FIND_BY_PLAYER = "SELECT " + COLUMNS + " FROM " + TABLE_NAME + 
										" WHERE challenger = ? OR challengee = ?;";
	public static ResultSet findByPlayer(long id) throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_BY_PLAYER);
		ps.setLong(1,id);
		ps.setLong(2,id);
		return ps.executeQuery();
	}
	public static final String Check = "SELECT " + COLUMNS + " FROM " + TABLE_NAME + 
			" WHERE (first_player = ? AND second_player = ?) "
			+ "OR (first_player = ? AND second_player = ?);";
	public static ResultSet CheckPlayer(long id, long id2) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_BY_PLAYER);
		ps.setLong(1,id);
		ps.setLong(2,id2);
		ps.setLong(3,id2);
		ps.setLong(4,id);
		return ps.executeQuery();
	}
	
}
