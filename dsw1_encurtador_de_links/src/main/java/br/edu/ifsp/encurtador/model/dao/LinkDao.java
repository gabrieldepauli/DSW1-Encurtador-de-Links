package br.edu.ifsp.encurtador.model.dao;

import java.util.List;

import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;

public interface LinkDao {
	
	boolean create(User user, Link link);
	
	Link getByID(User user, int id);
	
	Link getByURLencurtada(String urlEncurtada);
	
	List<Link> getAllLinks(User user);
	
	boolean delete(User user, Link link);
	
	boolean update(User user, Link updatedLink);
}
