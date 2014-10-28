package org.soen387.domain.model.user;

public class User implements IUser {
	long id;
	public String userN;
	public String passW;
	int version;
	
	public User(long id, String u, String p, int v){
		super();
		this.id=id;
		userN=u;
		passW=p;
		version=v;
	}
	
	@Override
	public long getId()		{ return id; 	}
	@Override
	public String getUserN(){ return userN; }
	@Override
	public String getPassW(){ return passW; }
	public int getVersion() { return version;}
	
	@Override
	public void setUserN(String u){userN=u;}
	@Override
	public void setPassW(String p){passW=p;}
	public void setVersion(int v) {version=v;}
}
