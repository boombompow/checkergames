package org.soen387.domain.model.user;

public class User implements IUser {
	long id;
	public String userN;
	public String passW;
	
	public User(long id, String u, String p){
		super();
		this.id=id;
		userN=u;
		passW=p;
	}
	
	@Override
	public long getId()		{ return id; 	}
	@Override
	public String getUserN(){ return userN; }
	@Override
	public String getPassW(){ return passW; }
	
	@Override
	public void setUserN(String u){userN=u;}
	@Override
	public void setPassW(String p){passW=p;}
}
