package br.com.db.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Restaurante implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer restaurante_id;
	
	@Column
	private String nome;
	
    @OneToMany(mappedBy = "pk.restaurante")
	private List<Voto>votos;


	public Integer getRestaurante_id() {
		return restaurante_id;
	}

	public void setRestaurante_id(Integer restaurante_id) {
		this.restaurante_id = restaurante_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}
	
	
	
	
	

}
