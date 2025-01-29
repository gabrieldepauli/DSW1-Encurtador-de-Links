package br.edu.ifsp.encurtador.model.dao;

import br.edu.ifsp.encurtador.model.enums.DaoImplementation;

public class AcessoDaoFactory {
	
	public static AcessoDao getInstance(DaoImplementation implementation) {
		if (DaoImplementation.MYSQL.equals(implementation)) {
			return new AcessoDaoImp();			
		}
		
		return null;
	}
	
}
