package org.soen387.domain.challenge.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.soen387.domain.challenge.identityMap.ChallengeIdentityMap;
import org.soen387.domain.challenge.tdg.ChallengeTDG;
import org.soen387.domain.checkerboard.mapper.CheckerBoardDataMapper;
import org.soen387.domain.model.challenge.Challenge;
import org.soen387.domain.model.challenge.ChallengeStatus;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.player.IPlayer;
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
	
	public static boolean check1(IPlayer challenger, IPlayer challengee)
	{
		if(challenger == challengee)
			return false;
		else
		{
			try {
				List<IChallenge> c = ChallengeMapper.check(challenger.getId(), challengee.getId());
				for(int i = 0; i < c.size(); i++)
				{
					if(c.get(i).getStatus().toString().equals("Open"))
					{
						return false;
					}
				}
				List<ICheckerBoard> p = CheckerBoardDataMapper.findAllPlayer(challenger.getId(), challengee.getId());
				for(int i = 0; i < p.size(); i++)
				{
					if(p.get(i).getStatus().toString().equals("Ongoing"))
					{
						return false;
					}
				}
				
			} catch (MapperException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static List<IChallenge> check(long id, long id2) throws MapperException
	{
		try {
	        ResultSet rs = ChallengeTDG.CheckPlayer(id, id2);
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
		IChallenge c = null;
		try{
			ResultSet rs = ChallengeTDG.findByPlayer(id);
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
		} catch (SQLException e){
			throw new MapperException(e);
		}
		return c;
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
}
