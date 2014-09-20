package br.com.mkoffice.repository;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, ID extends Serializable> {

	final String _ESPACE = " ";
	
	T insert(T entity);
	
	void saveAll(List<T> entities);

	T update(T entity);

	void delete(T entity);

	List<T> findAll();

	T findById(ID primaryKey);

}