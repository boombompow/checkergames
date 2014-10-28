package org.soen387.domain.user.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.soen387.domain.model.user.User;
import org.soen387.domain.user.tdg.UserTDG;


public class UserMapper {
	private static UserMapper um;
	private UserMapper(){	}
	
	public static UserMapper getOBJECT(){
		if(um==null)
			um = new UserMapper();
		
		return um;
	}
	
	public int insert(User ip) throws SQLException {
		return UserTDG.insert(ip.getId(), ip.getUserN(), ip.getPassW(), ip.getVersion());
	}
	
	public int delete(User ip) throws SQLException {
		return UserTDG.delete(ip.getId(), ip.getVersion());
	}
	
	public int update(User ip) throws SQLException {
		return UserTDG.update(ip.getId(), ip.getUserN(), ip.getPassW(), ip.getVersion());
	}
	
	public User findById(long id) throws SQLException {
		
		ResultSet rs=UserTDG.findById(id);
		User p1 = null;
		if(rs.next()) {
			p1 = new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getInt("version"));
			rs.close();
		} else 
			return null;
		
		
		return p1;
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
