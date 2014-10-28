package org.soen387.domain.player.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.soen387.domain.model.player.Player;
import org.soen387.domain.player.tdg.PlayerTDG;


public class PlayerMapper {
	private static PlayerMapper pm;
	private PlayerMapper(){	}
	
	public static PlayerMapper getOBJECT(){
		if(pm==null)
			pm = new PlayerMapper();
		
		return pm;
	}
	
	public void insert(Player ip) throws SQLException {
		PlayerTDG.insert(ip.getId(), ip.getFirstN(), ip.getLastN());
	}
	
//	public void delete(Player ip) throws SQLException {
//		PlayerTDG.delete(ip.getId(), ip.getVersion());
//	}
	
	public void update(Player ip) throws SQLException {
		PlayerTDG.update(ip.getId(), ip.getFirstN(), ip.getLastN());
	}
	
	public Player findById(long id) throws SQLException {
		
		ResultSet rs=PlayerTDG.findById((int)id);
		Player p1 = null;
		if(rs.next()) {
			p1 = new Player(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("version"));
			rs.close();
		} else 
			return null;
		
		
		return p1;
	}
	
	public List<Player> findAll() throws SQLException {
		List<Player> p = new ArrayList<Player>();
		
		ResultSet rs=PlayerTDG.findAll();
		
		while(rs.next()) {	
			p.add(new Player(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("version")));
		}
		rs.close();
		

		return p;		
	}
	
	public long getId() throws SQLException {
		ResultSet rs=PlayerTDG.getId();
		long max_id=-1;
		while(rs.next()){
			max_id = rs.getLong("id");
		}
		
		return max_id;
	} 
}
