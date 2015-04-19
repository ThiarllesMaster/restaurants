package br.com.db.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class VotoPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private Restaurante restaurante;
	
	@ManyToOne
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	@ManyToOne
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	

}
