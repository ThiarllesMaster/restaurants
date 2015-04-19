package br.com.db.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.db.dao.UsuarioDAO;
import br.com.db.entities.Usuario;

@Repository
public class HibernateUsuarioDAO extends HibernateGenericDAO<Usuario>
implements UsuarioDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Usuario> getEntityClass() {
		return Usuario.class;
	}

}
