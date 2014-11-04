package org.soen387.domain.user.identitymap;
import java.util.HashMap;
import org.soen387.domain.model.user.User;


public class UserIdentityMap {
	
	
	public static ThreadLocal<HashMap< Long, User>> users = new ThreadLocal<HashMap< Long, User>>();
	public static HashMap<Long, User> getIdentityMap() {
		
		HashMap<Long, User> HashM = users.get();
		
		if (HashM == null) {
			HashM = new HashMap<Long,User>();
			users.set(HashM);
		}
		
		return HashM;
	}
	
	
	public static void put(long id, User p)	{	getIdentityMap().put(id, p);		}
	public static User get(long id)			{	return getIdentityMap().get(id);	}
	public static boolean has(long id)			{	return getIdentityMap().containsKey(id);	}
	public static User remove(long id)		{	return getIdentityMap().remove(id);			}
	
}
