package br.edu.ifsp.encurtador.model.dao;

import br.edu.ifsp.encurtador.model.enums.DaoImplementation;

public interface DaoFactory {
	BasicDao getInstance(DaoImplementation implementation);
}
