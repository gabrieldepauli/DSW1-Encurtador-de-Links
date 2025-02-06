package br.edu.ifsp.encurtador.model.dao;

import java.sql.SQLException;

import br.edu.ifsp.encurtador.model.entity.User;

public interface  UserDao extends BasicDao<User> {
	
	boolean create(User user) throws SQLException;
	
	User findByEmail(String email); 

}
