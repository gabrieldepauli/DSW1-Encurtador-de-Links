package br.edu.ifsp.encurtador.model.dao;

import java.sql.SQLException;

public interface BasicDao<DataType> {
	boolean create(DataType data) throws SQLException;
}
