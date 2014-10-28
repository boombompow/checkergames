package org.soen387.domain.model.user;

import java.sql.SQLException;

public class UserProxy implements IUser {
	long id;
	User up;
	
	public UserProxy(long id){
		this.id = id;
	}
	
	private User getUP(){
		if(up==null)
			try { up = UserMapper.getOJECT().findById(id);}
			catch (SQLException e) {	e.printStackTrace();	}
		
		return up;
	}
	
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUserN() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassW() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserN(String u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPassW(String p) {
		// TODO Auto-generated method stub

	}

}
