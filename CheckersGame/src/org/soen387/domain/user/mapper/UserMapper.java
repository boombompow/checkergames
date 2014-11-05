package org.soen387.domain.user.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.soen387.domain.model.user.User;
import org.soen387.domain.user.identitymap.UserIdentityMap;
import org.soen387.domain.user.tdg.UserTDG;


public class UserMapper {
	private static UserMapper um;
	private UserMapper(){	}
	
	public static UserMapper getOBJECT(){
		if(um==null)
			um = new UserMapper();
		
		return um;
	}
	
<<<<<<< HEAD
	public void insert(User iu) throws SQLException {
		UserTDG.insert(iu.getId(), iu.getUserN(), iu.getPassW(), iu.getVersion());
		UserIdentityMap.put(iu.getId(), iu);
=======
	public User findUser(String username, String password) throws SQLException {
		ResultSet rs=UserTDG.findUser(username, password);
		User p1 = null;
		if(rs.next()) {
			p1 = new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getInt("version"));
			rs.close();
		} else {
			return null;
		}
		
		return p1;
	}
	
	public void createTable() throws SQLException {
		UserTDG.createTable();
	}
	
	public int insert(User ip) throws SQLException {
		return UserTDG.insert(ip.getId(), ip.getUserN(), ip.getPassW(), ip.getVersion());
>>>>>>> 50be692d522e724a4ef38afbe4b332cdcb586cc5
	}
	
	public void delete(User iu) throws SQLException {
		UserTDG.delete(iu.getId(), iu.getVersion());
		if(UserIdentityMap.has(iu.getId()))
			UserIdentityMap.remove(iu.getId());
	}
	
	public void update(User iu) throws SQLException {
		UserTDG.update(iu.getId(), iu.getUserN(), iu.getPassW());
		if(UserIdentityMap.has(iu.getId()))
			UserIdentityMap.remove(iu.getId());
		UserIdentityMap.put(iu.getId(), iu);
	}
	
	public User findById(long id) throws SQLException {
		if(UserIdentityMap.has(id))
			return UserIdentityMap.get(id);
		else{	
			ResultSet rs=UserTDG.findById(id);
			User p1 = null;
			if(rs.next()) {
				p1 = new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getInt("version"));
				rs.close();
			} 
				
			return p1;
		}
	}
	
	public List<User> findAll() throws SQLException {
		List<User> p = new ArrayList<User>();
		
		ResultSet rs=UserTDG.findAll();
		
		while(rs.next()) {	
			p.add(new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getInt("version")));
		}
		rs.close();
		

		return p;		
	}
	
	public long getId() throws SQLException {
		ResultSet rs=UserTDG.getId();
		long max_id=-1;
		while(rs.next()){
			max_id = rs.getLong("id");
		}
		
		return max_id;
	} 
}
