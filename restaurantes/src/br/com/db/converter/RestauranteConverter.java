package br.com.db.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.db.dto.RestauranteDTO;
import br.com.db.entities.Restaurante;

@Component
public class RestauranteConverter implements Converter<Restaurante, RestauranteDTO>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public RestauranteDTO convert(Restaurante restaurante) {
		RestauranteDTO restauranteDTO = new RestauranteDTO();
		restauranteDTO.setNome(restaurante.getNome());
		return restauranteDTO;
	}
	
	public List<RestauranteDTO>convertList(List<Restaurante>restaurantes){
		List<RestauranteDTO>restauranteDTOs = new ArrayList<RestauranteDTO>();
		for(Restaurante r : restaurantes){
			restauranteDTOs.add(convert(r));
		}
		return restauranteDTOs;
	}

}
