package org.soen387.domain.checkerboard.identityMap;

import java.util.HashMap;

import org.soen387.domain.model.checkerboard.ICheckerBoard;

public class CheckerBoardIdentityMap {
	private static ThreadLocal<HashMap< Long, ICheckerBoard>> checkerBoardIM = new ThreadLocal<HashMap< Long, ICheckerBoard>>();
	
	private CheckerBoardIdentityMap(){}
	
	public static HashMap<Long, ICheckerBoard> getIdentityMap() {
		
		HashMap<Long, ICheckerBoard> HashM = checkerBoardIM.get();
		
		if (HashM == null) {
			HashM = new HashMap<Long,ICheckerBoard>();
			checkerBoardIM.set(HashM);
		}
		
		return HashM;
	}
	
	
	public static void put(long id, ICheckerBoard c)	
	{	
		getIdentityMap().put(id, c);		
	}
	
	public static ICheckerBoard get(long id)			
	{	
		return getIdentityMap().get(id);	
	}
	
	public static boolean has(long id)
	{
		HashMap<Long, ICheckerBoard> HashM = checkerBoardIM.get();
		return HashM.containsKey(id);
	}

	public static void remove(long id)
	{
		if(has(id))
		{
			checkerBoardIM.get().remove(id);
		}
	}
}
