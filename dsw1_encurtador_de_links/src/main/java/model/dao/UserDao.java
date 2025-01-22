package model.dao;

import model.entity.User;

public interface  UserDao {
	
	boolean insert(User user);
	
	User findByEmail(String email); 

}
