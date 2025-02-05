package br.edu.ifsp.encurtador.model.dao;

import java.util.List;

import br.edu.ifsp.encurtador.model.entity.Acesso;
import br.edu.ifsp.encurtador.model.entity.Link;

public interface AcessoDao extends BasicDao<Acesso> {
	
	boolean create(Acesso acesso);
	
	Acesso getByID(int id);
	
	List<Acesso> getAllAcessos(Link link);
}
