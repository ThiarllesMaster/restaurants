package br.com.db.bo.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.db.bo.RestauranteBO;
import br.com.db.converter.RestauranteConverter;
import br.com.db.dao.RestauranteDAO;
import br.com.db.dao.UsuarioDAO;
import br.com.db.dao.VotoDAO;
import br.com.db.dto.RestauranteDTO;
import br.com.db.entities.Restaurante;
import br.com.db.entities.Usuario;
import br.com.db.entities.Voto;
import br.com.db.entities.VotoPK;
import br.com.db.utils.DataUtils;
import br.com.db.utils.RestauranteReturnCodes;

@Service(value = "restauranteBO")
public class RestauranteBOImpl implements RestauranteBO, Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	RestauranteDAO restauranteDAO;

	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Autowired
	VotoDAO votoDAO;
	
	@Autowired
	RestauranteConverter restauranteConverter;
	
	@Override
	public List<String> buscarNomesRestaurantes() {
		return restauranteDAO.buscarRestaurantes();
	}

	/**
	 * Método desenvolvido para a realizacao do voto no restaurante
	 * @author Thiarlles
	 */
	@Override
	public String votar(String nomeUsuario, String nomeRestaurante,	Date dataVoto) {
		//Verificar se o restaurante ja foi selecionado na semana
		Date d = restauranteDAO.restauranteSelecionadoSemana(nomeRestaurante);
		if(d == null){
		   return realizarVotacao(nomeUsuario, nomeRestaurante, dataVoto);
			
		} else {
			int diferencaDias = DataUtils.verificarPeriodo(d, dataVoto);
			if(diferencaDias >= 7){
				return RestauranteReturnCodes.RESTAURANTE_SELECIONADO_SEMANA;
			}
			
			return realizarVotacao(nomeUsuario, nomeRestaurante, dataVoto);
		}
		
	}
	
	/**
	 * Método desenvolvido para a votação nos restaurantes
	 * @param nomeUsuario
	 * @param nomeRestaurante
	 * @param dataVoto
	 * @return
	 */
	private String realizarVotacao(String nomeUsuario, String nomeRestaurante,	Date dataVoto){
		boolean votoPermitido = restauranteDAO.votarRestaurante(nomeUsuario, nomeRestaurante, dataVoto);
		if (votoPermitido) {
			// Pode votar no restaurante selecionado
           Voto voto = new Voto();
           voto.setDataVotacao(dataVoto);
           VotoPK votoPK = new VotoPK();
           br.com.db.entities.Restaurante restaurante = restauranteDAO.buscarRegistro("nome", nomeRestaurante);
           Usuario usuario = usuarioDAO.buscarRegistro("nome", nomeUsuario);
           votoPK.setRestaurante(restaurante);
           votoPK.setUsuario(usuario);
           voto.setPk(votoPK);
           votoDAO.save(voto);
           return RestauranteReturnCodes.VOTO_COMPUTADO;
		}
		return RestauranteReturnCodes.VOTO_JA_COMPUTADO;
	}

	@Override
	public List<RestauranteDTO> buscarRestaurantesVotados(Date dataVotacao) {
		List<Restaurante>r = restauranteDAO.buscarRestaurantesVotados(dataVotacao);
		List<RestauranteDTO> restaurantes = restauranteConverter.convertList(r);
		return restaurantes;
	}
	
	

}
