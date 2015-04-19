package br.com.db.managed.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.db.bo.RestauranteBO;
import br.com.db.bo.UsuarioBO;
import br.com.db.dto.RestauranteDTO;
import br.com.db.utils.FacesMessageUtils;
import br.com.db.utils.RestauranteReturnCodes;

@ManagedBean(name = "restauranteMBean")
@ViewScoped
public class RestauranteMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String usuario;
	
	@ManagedProperty(value = "#{restauranteBO}")
	private RestauranteBO restauranteBO;
	
	@ManagedProperty(value = "#{usuarioBO}")
	private UsuarioBO usuarioBO;
	
	private List<String>opcoes;
	private List<String>nomesRestaurantes;
	private Date dataVotacao;
	private CartesianChartModel votosModel;
	private String nomeUsuario;
	
	@PostConstruct
	public void initialize(){
		opcoes = new ArrayList<String>();
		nomesRestaurantes = restauranteBO.buscarNomesRestaurantes();
		
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public RestauranteBO getRestauranteBO() {
		return restauranteBO;
	}

	public void setRestauranteBO(RestauranteBO restauranteBO) {
		this.restauranteBO = restauranteBO;
	}

	public List<String> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<String> opcoes) {
		this.opcoes = opcoes;
	}

	public List<String> getNomesRestaurantes() {
		return nomesRestaurantes;
	}

	public void setNomesRestaurantes(List<String> nomesRestaurantes) {
		this.nomesRestaurantes = nomesRestaurantes;
	}

	public Date getDataVotacao() {
		return dataVotacao;
	}

	public void setDataVotacao(Date dataVotacao) {
		this.dataVotacao = dataVotacao;
	}

	public CartesianChartModel getVotosModel() {
		return votosModel;
	}

	public void setVotosModel(CartesianChartModel votosModel) {
		this.votosModel = votosModel;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public UsuarioBO getUsuarioBO() {
		return usuarioBO;
	}

	public void setUsuarioBO(UsuarioBO usuarioBO) {
		this.usuarioBO = usuarioBO;
	}

	public String processarVoto() throws ParseException{
		//Validar o usuário
		if(nomeUsuario == null){
			FacesMessageUtils.exibirMensagem(RestauranteReturnCodes.INFORMAR_NOME);
			
		} else {
			boolean usuarioExiste = usuarioBO.usuarioExiste(nomeUsuario);
			if(!usuarioExiste){
				FacesMessageUtils.exibirMensagem(RestauranteReturnCodes.USUARIO_INEXISTENTE);
			}else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String date = dateFormat.format(new Date());
				if(opcoes.size() == 1){
					String codigo = restauranteBO.votar(nomeUsuario, opcoes.get(0), dateFormat.parse(date));
				    FacesMessageUtils.exibirMensagem(codigo);
				} else if (opcoes.size() > 1) {
					FacesMessageUtils.exibirMensagem(RestauranteReturnCodes.UM_VOTO_POR_DIA);
				}
				this.limparCampos();
			
			}
					
			
		} 
		return "";
		
	}
	
	public String verificarVotos(){
		
		votosModel = new CartesianChartModel();
		Integer votosPanorama = 0;
		Integer votosPallatos = 0;
		Integer votosPredioCinq = 0;
		Integer votosSaborFam = 0;
		
		List<RestauranteDTO>restaurantes = restauranteBO.buscarRestaurantesVotados(dataVotacao);
		for(RestauranteDTO r : restaurantes){
			if(r.getNome().equals("Panorama")){
				votosPanorama = votosPanorama + 1;
			} else if (r.getNome().equals("Pallatos")) {
				votosPallatos = votosPallatos + 1;
			} else if (r.getNome().equals("Predio 50")) {
				votosPredioCinq = votosPredioCinq + 1;
			} else {
				votosSaborFam = votosSaborFam + 1;
			}
		}
		
		ChartSeries panoramaSerie = new ChartSeries();
		panoramaSerie.setLabel("Panorama");
		panoramaSerie.set("Panorama", votosPanorama);
		
		ChartSeries pallatosSerie = new ChartSeries();
		pallatosSerie.setLabel("Pallatos");
		pallatosSerie.set("Pallatos", votosPallatos);
		
		ChartSeries cinqSerie = new ChartSeries();
		cinqSerie.setLabel("Restaurante 50");
		cinqSerie.set("Restaurante 50", votosPredioCinq);
		
		ChartSeries saborFamSerie = new ChartSeries();
		saborFamSerie.setLabel("Sabor Família");
		saborFamSerie.set("Sabor Familia", votosSaborFam);
		
		votosModel.addSeries(panoramaSerie);
		votosModel.addSeries(pallatosSerie);
		votosModel.addSeries(cinqSerie);
		votosModel.addSeries(saborFamSerie);
	
		return "";
	}
	
	
	private void limparCampos(){
		opcoes.clear();
	}
	
}
