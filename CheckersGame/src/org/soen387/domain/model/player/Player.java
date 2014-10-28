package org.soen387.domain.model.player;
/**
 * 
 *This is an incomplete stub. Fix it.
 */
public class Player implements IPlayer {
	long id;
	private String firstN;
	private String lastN;
	public String email;
	int version;

	public Player(long id, String f, String l, String e, int v) {
		super();
		this.id = id;
		firstN=f;
		lastN=l;
		email=e;
		version=v;
	}
	
	public long getId()			{	return id; 		}
	public String getFirstN()	{	return firstN;	}
	public String getLastN()	{	return lastN;	}
	public String getEmail()	{	return email;	}
	public int getVersion()		{	return version;	}
	
	public void setFirstN(String f){	firstN=f;	}
	public void setLastN(String l) {	lastN=l;	}
	public void setEmail(String e) {	email=e;	}
	public void setVersion(int v)  {	version=v;	}
}
