package org.soen387.domain.model.player;

public class PersonProxy implements IPlayer {
	private long id;
	Player pp;
	
	public PersonProxy(long id){
		id=id;
	}
	
	private Player getPP(){
		if(pp==null)
			pp=PlayerMapper.find(id);
		
		return pp;
	}
	
	
	public long getId()			{	return id; 		}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#getFirstN()
	 */
	public String getFirstN()	{	return getPP().getFirstN();	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#getLastN()
	 */
	public String getLastN()	{	return getPP().getLastN();	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#getEmail()
	 */
	public String getEmail()	{	return getPP().getEmail();	}
	/* (non-Javadoc)
	 * @see org.soen387.domain.model.player.IPlayer#getVersion()
	 */
	public int getVersion()		{	return getPP().getVersion();	}
}
