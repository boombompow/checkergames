package org.soen387.domain.challenge.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.soen387.domain.challenge.tdg.ChallengeTDG;
import org.soen387.domain.model.challenge.Challenge;
import org.soen387.domain.model.challenge.ChallengeStatus;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.player.mapper.PlayerMapper;


public class ChallengeMapper {
	
	public static int status(IChallenge c)
	{
		switch(c.getStatus())
		{
			case Open: return 0;
			case Accepted: return 1;
			case Refused: return 2;
		}
		return -1;
	}
	
	public static void createTable() throws SQLException
	{
		ChallengeTDG.createTable();
	}
	
	public static void dropTable() throws SQLException
	{
		ChallengeTDG.dropTable();
	}
	
	public static List<Challenge> buildCollection(ResultSet rs) throws SQLException
	{
		ArrayList<Challenge> l = new ArrayList<Challenge>();
	    while(rs.next()) {
	        l.add(new Challenge(rs.getLong("id"),
	        		rs.getInt("version"),
	        		ChallengeStatus.values()[rs.getInt("status")],
	        		PlayerMapper.getOBJECT().findById(rs.getLong("challenger")),
	        		PlayerMapper.getOBJECT().findById(rs.getLong("challengee"))
	        		));
	        
	    }
	   
	    return l;
	}
	
	public static List<Challenge> findAll() throws MapperException 
	{
		try {
            ResultSet rs = ChallengeTDG.findAll();
            return buildCollection(rs);
        } catch (SQLException e) {
            throw new MapperException(e);
        }
	}
	
	public static List<Challenge> findById(long id) throws MapperException{
		try{
			ResultSet rs = ChallengeTDG.findByPlayer(id);
			return buildCollection(rs);
		} catch (SQLException e){
			throw new MapperException(e);
		}
	}
	
	public static void insert(IChallenge c) throws SQLException {
		ChallengeTDG.insert(c.getId(), c.getVersion(), status(c), c.getChallenger().getId(), c.getChallengee().getId());
	}
	
	public static void delete(IChallenge c) throws SQLException {
		ChallengeTDG.deleteChallenge(c.getId(), c.getVersion());
	}
}
