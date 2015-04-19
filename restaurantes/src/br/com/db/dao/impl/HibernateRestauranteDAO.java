package br.com.db.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.db.dao.RestauranteDAO;
import br.com.db.entities.Restaurante;


@Repository
public class HibernateRestauranteDAO extends HibernateGenericDAO<Restaurante>
implements RestauranteDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected Class<Restaurante> getEntityClass() {
		return Restaurante.class;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> buscarRestaurantes() {
		String sql = "select r.nome from Restaurante r";
		List<String>nomes = getHibernateTemplate().find(sql);
		
		return nomes;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean votarRestaurante(String usuario, String nomeRestaurante,	Date dataVoto) {
		String sql = "select u.nome from Usuario as u inner join u.votos as v inner join v.pk.restaurante as r "
				+ "where u.nome = :nome and v.dataVotacao = :data_votacao";

		List<String> nomeUsuarios = (List<String>) getHibernateTemplate().findByNamedParam(
				sql,
				new String[] { "nome", "data_votacao" },
				new Object[] { usuario, dataVoto });

		if (nomeUsuarios.size() == 0) {
			return true;
		}
		
		return false;
	}


	@SuppressWarnings({"unchecked" })
	@Override
	public Date restauranteSelecionadoSemana(String nomeRestaurante) {
		String sql = "select v.dataVotacao from Voto as v inner join v.pk.restaurante as r where r.nome = :nome_restaurante order by r.restaurante_id desc limit 1";
		
		List<Date> d = getHibernateTemplate().findByNamedParam(sql, new String[]{"nome_restaurante"}, new Object[]{nomeRestaurante});
		if(d.size() > 0){
			return d.get(0);
		}
		return null;
	}


	@SuppressWarnings({"unchecked" })
	@Override
	public List<Restaurante> buscarRestaurantesVotados(Date date) {
		String sql = "select r from Restaurante as r inner join r.votos as v where v.dataVotacao = :data_votacao";
		List<Restaurante> restaurantes = getHibernateTemplate().findByNamedParam(sql, new String[] { "data_votacao" }, new Object[] { date });
       
		return restaurantes;
	}
}
