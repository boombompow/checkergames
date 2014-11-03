package org.soen387.domain.player.identitymap;

import java.util.HashMap;
import org.soen387.domain.model.player.Player;


public class PlayerIdentityMap {

	public static ThreadLocal<HashMap< Long, Player>> players = new ThreadLocal<HashMap< Long, Player>>();
	
	
	public static HashMap<Long, Player> getIdentityMap() {
		
		HashMap<Long, Player> HashM = players.get();
		
		if (HashM == null) {
			HashM = new HashMap<Long,Player>();
			players.set(HashM);
		}
		
		return HashM;
	}
	
	
	public void put(long id, Player p)	{	getIdentityMap().put(id, p);		}
	public Player get(long id)			{	return getIdentityMap().get(id);	}
	
		
	
	
	
}
