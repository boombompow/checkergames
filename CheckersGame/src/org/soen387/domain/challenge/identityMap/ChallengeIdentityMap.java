package org.soen387.domain.challenge.identityMap;

import java.util.HashMap;

import org.soen387.domain.model.challenge.IChallenge;

public class ChallengeIdentityMap {
	private static ThreadLocal<HashMap< Long, IChallenge>> challengeIM = new ThreadLocal<HashMap< Long, IChallenge>>();
	
	private ChallengeIdentityMap(){}
	
	public static HashMap<Long, IChallenge> getIdentityMap() {
		
		HashMap<Long, IChallenge> HashM = challengeIM.get();
		
		if (HashM == null) {
			HashM = new HashMap<Long,IChallenge>();
			challengeIM.set(HashM);
		}
		
		return HashM;
	}
	
	
	public static void put(long id, IChallenge c)	
	{	
		getIdentityMap().put(id, c);		
	}
	
	public static IChallenge get(long id)			
	{	
		return getIdentityMap().get(id);
	}
	
	public static boolean has(long id)
	{
		HashMap<Long, IChallenge> HashM = getIdentityMap();
		return HashM.containsKey(id);
	}

	public static void remove(long id)
	{
		if(has(id))
		{
			challengeIM.get().remove(id);
		}
	}
}
