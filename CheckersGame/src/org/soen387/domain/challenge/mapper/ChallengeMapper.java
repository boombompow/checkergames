package org.soen387.domain.challenge.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.soen387.domain.challenge.identityMap.ChallengeIdentityMap;
import org.soen387.domain.challenge.tdg.ChallengeTDG;
import org.soen387.domain.model.challenge.Challenge;
import org.soen387.domain.model.challenge.ChallengeStatus;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.player.mapper.PlayerMapper;
import org.soen387.domain.user.mapper.UserMapper;


public class ChallengeMapper {
	private static ChallengeMapper cm;
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
	
	public static ChallengeMapper getOBJECT(){
		if(cm==null)
			cm = new ChallengeMapper();
		
		return cm;
	}
	
	public static void createTable() throws SQLException
	{	
		ChallengeTDG.createTable();
	}
	
	public static void dropTable() throws SQLException
	{		
		ChallengeTDG.dropTable();
	}
	
	public static List<IChallenge> findAll() throws MapperException 
	{
		try {
            ResultSet rs = ChallengeTDG.findAll();
            ArrayList<IChallenge> c = new ArrayList<IChallenge>();
    	    while(rs.next()) {
    	        c.add(new Challenge(rs.getLong("id"),
    	        		rs.getInt("version"),
    	        		ChallengeStatus.values()[rs.getInt("status")],
    	        		PlayerMapper.getOBJECT().findById(rs.getLong("challenger")),
    	        		PlayerMapper.getOBJECT().findById(rs.getLong("challengee"))
    	        		));
    	        if(!ChallengeIdentityMap.has(c.get(c.size()-1).getId()))
    	        	ChallengeIdentityMap.put(c.get(c.size()-1).getId(), c.get(c.size()-1));
    	    }	
    	    return c;
        } catch (SQLException e) {
            throw new MapperException(e);
        }
	}
	
	public static IChallenge findById(long id) throws MapperException{
		if(ChallengeIdentityMap.has(id))
		{
			return ChallengeIdentityMap.get(id);
		}
		try{
			ResultSet rs = ChallengeTDG.findByPlayer(id);
			IChallenge c = null;
			if(rs.next()) {
				c = new Challenge(rs.getLong("id"),
		        		rs.getInt("version"),
		        		ChallengeStatus.values()[rs.getInt("status")],
		        		PlayerMapper.getOBJECT().findById(rs.getLong("challenger")),
		        		PlayerMapper.getOBJECT().findById(rs.getLong("challengee"))
		        		);
				rs.close();
			}
			ChallengeIdentityMap.put(id, c);
			return c;
		} catch (SQLException e){
			throw new MapperException(e);
		}
	}
	
	public static List<IChallenge> findAllById(long id) throws MapperException{
        try {
            ResultSet rs = ChallengeTDG.findByPlayer(id);
            return buildCollection(rs);
        } catch (SQLException e) {
            throw new MapperException(e);
        }
	}
	
	public static void insert(IChallenge c) throws SQLException {
		ChallengeTDG.insert(c.getId(), c.getVersion(), status(c), c.getChallenger().getId(), c.getChallengee().getId());
		ChallengeIdentityMap.put(c.getId(), c);
	}
	
	public static void delete(IChallenge c) throws SQLException {
		ChallengeTDG.deleteChallenge(c.getId(), c.getVersion());
		ChallengeIdentityMap.remove(c.getId());
	}
	
	public static void update(IChallenge c) throws SQLException {
		ChallengeTDG.update(c.getId(), c.getVersion(), status(c), c.getChallenger().getId(), c.getChallengee().getId());
		ChallengeIdentityMap.remove(c.getId());
		ChallengeIdentityMap.put(c.getId(), c);
	}
	
	public static List<IChallenge> buildCollection(ResultSet rs)
		    throws SQLException {
		    ArrayList<IChallenge> c = new ArrayList<IChallenge>();
		    
		    while(rs.next()) {
		        c.add(new Challenge(rs.getLong("id"),rs.getInt("version"),
		        		ChallengeStatus.values()[rs.getInt("status")],
		        		PlayerMapper.getOBJECT().findById(rs.getLong("challenger")),
		        		PlayerMapper.getOBJECT().findById(rs.getLong("challengee"))     
		        		));
		    }
		    return c;
		}
}
