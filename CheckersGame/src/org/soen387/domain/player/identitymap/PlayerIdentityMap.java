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
	
	
	public static void put(long id, Player p)	{	getIdentityMap().put(id, p);				}
	public static Player get(long id)			{	return getIdentityMap().get(id);			}
	public static boolean has(long id)			{	return getIdentityMap().containsKey(id);	}
	public static Player remove(long id)		{	return getIdentityMap().remove(id);			}
	
}
