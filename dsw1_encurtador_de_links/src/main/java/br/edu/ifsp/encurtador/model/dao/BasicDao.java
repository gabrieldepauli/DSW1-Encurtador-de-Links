package br.edu.ifsp.encurtador.model.dao;

public interface BasicDao<DataType> {
	boolean create(DataType data);
}
