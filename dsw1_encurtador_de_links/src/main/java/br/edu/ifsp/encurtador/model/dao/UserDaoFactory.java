package br.edu.ifsp.encurtador.model.dao;

import br.edu.ifsp.encurtador.model.enums.DaoImplementation;

public class UserDaoFactory {

	public static UserDao getInstance(DaoImplementation implementation) {
		if (DaoImplementation.MYSQL.equals(implementation)) {			
			return new UserDaoImp();
		}
		
		return null;
	}
	
}
