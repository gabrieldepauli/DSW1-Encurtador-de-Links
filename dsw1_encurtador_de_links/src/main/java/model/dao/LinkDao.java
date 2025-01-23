package model.dao;

import java.util.List;

import model.entity.Link;
import model.entity.User;

public interface LinkDao {
	
	boolean create(User user, Link link);
	
	Link getByID(User user, int id);
	
	List<Link> getAllLinks(User user);
	
	boolean delete(User user, Link link);
	
	boolean update(User user ,Link updatedLink, int id);
}
