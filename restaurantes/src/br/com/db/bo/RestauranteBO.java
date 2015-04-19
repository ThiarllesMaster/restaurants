package br.com.db.bo;

import java.util.Date;
import java.util.List;

import br.com.db.dto.RestauranteDTO;


public interface RestauranteBO {

	List<String>buscarNomesRestaurantes();
	
	String votar(String nomeUsuario, String nomeRestaurante, Date dataVoto);
	
	List<RestauranteDTO>buscarRestaurantesVotados(Date dataVotacao);
}
