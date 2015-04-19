package br.com.db.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.db.dao.VotoDAO;
import br.com.db.entities.Voto;

@Repository
public class HibernateVotoDAO extends HibernateGenericDAO<Voto>
implements VotoDAO, Serializable{


	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Voto> getEntityClass() {
		return Voto.class;
	}

}
