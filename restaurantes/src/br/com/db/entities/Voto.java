package br.com.db.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@AssociationOverrides(
{@AssociationOverride(name = "pk.usuario", joinColumns = @JoinColumn(name = "usuario_id")),
@AssociationOverride(name = "pk.restaurante", joinColumns = @JoinColumn(name = "restaurante_id"))}
)
public class Voto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Temporal(TemporalType.DATE)
	private Date dataVotacao;

	private VotoPK pk = new VotoPK();
	
		
	public Date getDataVotacao() {
		return dataVotacao;
	}

	public void setDataVotacao(Date dataVotacao) {
		this.dataVotacao = dataVotacao;
	}

	@EmbeddedId
	public VotoPK getPk() {
		return pk;
	}

	public void setPk(VotoPK pk) {
		this.pk = pk;
	}
	


	

}
