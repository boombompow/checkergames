package org.soen387.domain.model.player;

import java.sql.SQLException;

import org.soen387.domain.player.mapper.PlayerMapper;

public class PersonProxy implements IPlayer {
	private long id;
	Player pp;
	
	public PersonProxy(long id){
		id=id;
	}
	
	private Player getPP(){
		if(pp==null)
			try {	pp=PlayerMapper.getOBJECT().findById(id);	} 
			catch (SQLException e) {	e.printStackTrace();	}
		
		return pp;
	}
	
	
	public long getId()			{	return id; 					}
	public String getFirstN()	{	return getPP().getFirstN();	}
	public String getLastN()	{	return getPP().getLastN();	}
	public String getEmail()	{	return getPP().getEmail();	}
	public int getVersion()		{	return getPP().getVersion();}


	public void setFirstN(String f) {	}


	public void setLastN(String l) {	}

	
	public void setEmail(String e) {	}


	public void setVersion(int v) {		}
}
