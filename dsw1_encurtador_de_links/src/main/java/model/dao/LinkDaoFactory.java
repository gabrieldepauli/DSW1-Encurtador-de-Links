package model.dao;

import model.enums.DaoImplementation;

public class LinkDaoFactory {

	public static LinkDao getInstance(DaoImplementation implementation) {
		if (DaoImplementation.MYSQL.equals(implementation)) {			
			return new LinkDaoImp();
		}
		
		return null;
	}
}
