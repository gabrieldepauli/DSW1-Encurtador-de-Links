package br.edu.ifsp.encurtador.model.dao;

import br.edu.ifsp.encurtador.model.enums.DaoImplementation;

public class LinkDaoFactory {

	public static LinkDao getInstance(DaoImplementation implementation) {
		if (DaoImplementation.MYSQL.equals(implementation)) {			
			return new LinkDaoImp();
		}
		
		return null;
	}
}
