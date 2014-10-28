package org.soen387.domain.model.user;

import java.sql.SQLException;
import org.soen387.domain.user.mapper.UserMapper;

public class UserProxy implements IUser {
	long id;
	User up;
	
	public UserProxy(long id){
		this.id = id;
	}
	
	private User getUP(){
		if(up==null)
			try { up = UserMapper.getOBJECT().findById(id);}
			catch (SQLException e) {	e.printStackTrace();	}
		
		return up;
	}
	
	@Override
	public long getId() {	return getUP().getId();}
	@Override
	public String getUserN() {	return getUP().getUserN();}
	@Override
	public String getPassW() {	return getUP().getPassW();}
	@Override
	public int getVersion() {return getUP().getVersion();}

	
	@Override
	public void setUserN(String u) {	getUP().setUserN(u);}
	@Override
	public void setPassW(String p) {	getUP().setPassW(p);}
	@Override
	public void setVersion(int v) {		getUP().setVersion(v);}
	
	
}
