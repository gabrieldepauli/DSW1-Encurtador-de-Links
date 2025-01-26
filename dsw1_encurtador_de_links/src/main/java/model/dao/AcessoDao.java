package model.dao;

import java.util.List;

import model.entity.Acesso;
import model.entity.Link;

public interface AcessoDao {
	
	boolean create(Link link, Acesso acesso);
	
	Acesso getByID(int id);
	
	List<Acesso> getAllAcessos(Link link);
}
