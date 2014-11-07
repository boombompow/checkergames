package org.soen387.domain.player.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.soen387.app.AbstractPageController;
import org.soen387.domain.model.player.Player;
import org.soen387.domain.player.identitymap.PlayerIdentityMap;
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
		AbstractPageController.setupDb();
		PlayerTDG.insert(ip.getId(), ip.getFirstN(), ip.getLastN(), ip.getEmail());
		PlayerIdentityMap.put(ip.getId(), ip);
	}
	
	public void delete(Player ip) throws SQLException {
		AbstractPageController.setupDb();
		PlayerTDG.delete(ip.getId(), ip.getVersion());
		if(PlayerIdentityMap.has(ip.getId()))
			PlayerIdentityMap.remove(ip.getId());
	}
	
	public void update(Player ip) throws SQLException {
		AbstractPageController.setupDb();
		PlayerTDG.update(ip.getId(), ip.getFirstN(), ip.getLastN(), ip.getEmail(), ip.getVersion());
		if(PlayerIdentityMap.has(ip.getId()))
			PlayerIdentityMap.remove(ip.getId());
		PlayerIdentityMap.put(ip.getId(), ip);
	}
	
	public Player findById(long id) throws SQLException {
		AbstractPageController.setupDb();
		if(PlayerIdentityMap.has(id))
			return PlayerIdentityMap.get(id);
		else{
			ResultSet rs=PlayerTDG.findById(id);
			Player p1 = null;
			if(rs.next()) {
				p1 = new Player(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("version"));
				rs.close();
			}
			return p1;
		}
	}
	
	public List<Player> findAll() throws SQLException {
		AbstractPageController.setupDb();
		List<Player> p = new ArrayList<Player>();
		ResultSet rs=PlayerTDG.findAll();
		
		while(rs.next()) {	
			p.add(new Player(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("version")));
		}
		rs.close();
		

		return p;		
	}
	
	public long getId() throws SQLException {
		AbstractPageController.setupDb();
		ResultSet rs=PlayerTDG.getId();
		long max_id=-1;
		while(rs.next()){
			max_id = rs.getLong("id");
		}
		
		return max_id;
	} 
}
