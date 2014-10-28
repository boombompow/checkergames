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

	public Player(long id) {
		super();
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#getId()
	 */
	public long getId()			{	return id; 		}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#getFirstN()
	 */
	public String getFirstN()	{	return firstN;	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#getLastN()
	 */
	public String getLastN()	{	return lastN;	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#getEmail()
	 */
	public String getEmail()	{	return email;	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#getVersion()
	 */
	public int getVersion()		{	return version;	}
	
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#setFirstN(java.lang.String)
	 */
	public void setFirstN(String f){	firstN=f;	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#setLastN(java.lang.String)
	 */
	public void setLastN(String l) {	lastN=l;	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#setEmail(java.lang.String)
	 */
	public void setEmail(String e) {	email=e;	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#setVersion(int)
	 */
	public void setVersion(int v)  {	version=v;	}
}
