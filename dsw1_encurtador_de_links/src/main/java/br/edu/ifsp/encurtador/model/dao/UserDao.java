package br.edu.ifsp.encurtador.model.dao;

import br.edu.ifsp.encurtador.model.entity.User;

public interface  UserDao extends BasicDao<User> {
	
	boolean create(User user);
	
	User findByEmail(String email); 

}
