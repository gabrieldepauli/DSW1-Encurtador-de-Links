package br.edu.ifsp.encurtador.model.dao;

import java.sql.SQLException;
import java.util.List;

import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;

public interface LinkDao extends BasicDao<Link> {
	
	boolean create(Link link) throws SQLException;
	
	Link getByID(int id);
	
	Link getByURLencurtada(String urlEncurtada);
	
	List<Link> getAllLinks(User user);
	
	boolean delete(Link link);
	
	boolean update(Link updatedLink) throws SQLException;
}
