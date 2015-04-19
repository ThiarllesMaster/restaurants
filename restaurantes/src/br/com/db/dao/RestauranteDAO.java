package br.com.db.dao;

import java.util.Date;
import java.util.List;

import br.com.db.entities.Restaurante;



public interface RestauranteDAO extends GenericDAO<Restaurante>{

	/**
	 * M�todo para retornar os nomes dos restaurantes
	 * @return
	 */
	public List<String>buscarRestaurantes();
	
	
	
	/**
	 * M�todo desenvolvido para realiza��o do voto do usu�rio
	 * @param usuario
	 * @param nomeRestaurante
	 * @param dataVoto
	 * @return
	 */
	public boolean votarRestaurante(String usuario, String nomeRestaurante, Date dataVoto);
	
	
	/**
	 * Verificar a data do restaurante selecionado
	 * @param nomeRestaurante
	 * @return
	 */
	public Date restauranteSelecionadoSemana(String nomeRestaurante);
	
	
	
	/**
	 * M�todo desenvolvido para retornar os restaurantes votados dado um per�odo informado
	 * @param date
	 * @return
	 */
	public List<Restaurante>buscarRestaurantesVotados(Date date);
}
