package br.com.db.dao;

import java.util.List;


public interface GenericDAO<E> {

	E save(E entity);
	
	List<E>buscarRegistros(String property, Object value);
	
	List<E>buscarRegistros();
	
	void saveOrUpdate(E entity);
	
	E buscarRegistro(String property, Object value);
	
	boolean removerRegistro(E entity);
	
	List<E>buscarRegistrosList(String propriedade, List<Integer>valores);
	
	List<E>buscarRegistrosListP(String propriedade, List<String>valores);
	
}
