package org.soen387.domain.checkerboard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.soen387.domain.checkerboard.identityMap.CheckerBoardIdentityMap;
import org.soen387.domain.checkerboard.tdg.CheckerBoardTDG;
import org.soen387.domain.model.checkerboard.CheckerBoard;
import org.soen387.domain.model.checkerboard.GameStatus;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.player.mapper.PlayerMapper;

public class CheckerBoardDataMapper {
	
	public static List<ICheckerBoard> buildCollection(ResultSet rs)
		    throws SQLException {
		    ArrayList<ICheckerBoard> l = new ArrayList<ICheckerBoard>();
		    while(rs.next()) {
		    	String piecesString = rs.getString("pieces");
		    	char[][] pieces = new char[8][8];
		    	for(int i=0; i < 8; i++) {
		    		for(int j=0; j < 8; j++) {
		    			pieces[j][i] = piecesString.charAt(i*8+j);
		    		}
		    	}
		    	
		        l.add(new CheckerBoard(rs.getLong("id"),
		        		rs.getInt("version"),
		        		GameStatus.values()[rs.getInt("status")],
		        		pieces,
		        		PlayerMapper.getOBJECT().findById(rs.getLong("first_player")),
		        		PlayerMapper.getOBJECT().findById(rs.getLong("second_player")),
		        		PlayerMapper.getOBJECT().findById(rs.getLong("current_player"))
		        		));
		        if(!CheckerBoardIdentityMap.has(l.get(l.size()-1).getId()))
		        	CheckerBoardIdentityMap.put(l.get(l.size()-1).getId(), l.get(l.size()-1));
		    }
		    return l;
		}

	public static List<ICheckerBoard> findAll() throws MapperException {
        try {
            ResultSet rs = CheckerBoardTDG.findAll();
            return buildCollection(rs);
        } catch (SQLException e) {
            throw new MapperException(e);
        }
	}
	public static List<ICheckerBoard> findByPlayer(long id) throws MapperException{
		try{
			ResultSet rs = CheckerBoardTDG.findByPlayer(id);
			return buildCollection(rs);
		} catch (SQLException e){
			throw new MapperException(e);
		}
	}
	
	public static List<ICheckerBoard> findAllPlayer(long id, long id2) throws MapperException{
		try{
			ResultSet rs = CheckerBoardTDG.findAllPlayer(id, id2);
			return buildCollection(rs);
		} catch (SQLException e){
			throw new MapperException(e);
		}
	}
	
	public static int status(ICheckerBoard c)
	{
		switch(c.getStatus())
		{
			case Ongoing: return 0;
			case Tied: return 1;
			case Won: return 2;
		}
		return -1;
	}
	
	public static void update(ICheckerBoard c) throws SQLException
	{
		char[][] pieces = c.getPieces();
		String piece = "";
    	for(int i=0; i < 8; i++) {
    		for(int j=0; j < 8; j++) {
    			piece = piece + pieces[j][i];		
    		}
    	}	
		CheckerBoardTDG.update(c.getId(), c.getVersion(), status(c), piece, 
				c.getFirstPlayer().getId(), c.getSecondPlayer().getId(), c.getCurrentPlayer().getId());
		CheckerBoardIdentityMap.remove(c.getId());
		CheckerBoardIdentityMap.put(c.getId(), c);
	}
	
	public static ICheckerBoard findById(long id) throws SQLException, MapperException
	{
		if(CheckerBoardIdentityMap.has(id))
		{
			return CheckerBoardIdentityMap.get(id);
		}
		try{
			ResultSet rs = CheckerBoardTDG.findById(id);
			return buildCollection(rs).get(0);
		} catch (SQLException e){
			throw new MapperException(e);
		}	
	}
	
	public static void insert(ICheckerBoard c) throws SQLException {
		char[][] pieces = c.getPieces();
		String piece = "";
    	for(int i=0; i < 8; i++) {
    		for(int j=0; j < 8; j++) {
    			piece = piece + pieces[j][i];		
    		}
    	}	
    	CheckerBoardTDG.insert(c.getId(), c.getVersion(), status(c), piece, 
				c.getFirstPlayer().getId(), c.getSecondPlayer().getId(), c.getCurrentPlayer().getId());
    	CheckerBoardIdentityMap.put(c.getId(), c);
	}
	
	public static void delete(ICheckerBoard c) throws SQLException {
		CheckerBoardTDG.delete(c.getId(), c.getVersion());
		CheckerBoardIdentityMap.remove(c.getId());
	}
	
	public static void createTable() throws SQLException
	{
		CheckerBoardTDG.createTable();
	}
	
	public static void dropTable() throws SQLException
	{
		CheckerBoardTDG.dropTable();
	}
}
