package br.com.db.dao;

import java.util.Date;
import java.util.List;

import br.com.db.entities.Restaurante;



public interface RestauranteDAO extends GenericDAO<Restaurante>{

	/**
	 * Método para retornar os nomes dos restaurantes
	 * @return
	 */
	public List<String>buscarRestaurantes();
	
	
	
	/**
	 * Método desenvolvido para realização do voto do usuário
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
	 * Método desenvolvido para retornar os restaurantes votados dado um período informado
	 * @param date
	 * @return
	 */
	public List<Restaurante>buscarRestaurantesVotados(Date date);
}
