package br.com.db.managed.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.db.data.NavigationData;

@ManagedBean(name = "navigationBean")
@ViewScoped
public class NavigationBean implements Serializable{


	private static final long serialVersionUID = 1L;
	List<NavigationData>navigations;
	
	@PostConstruct
	public void initialize(){
		navigations = new ArrayList<NavigationData>();
		NavigationData home = new NavigationData();
		home.setDescription("Home");
		home.setUrl("admin_home.jsf");
		
		NavigationData restaurantes = new NavigationData();
		restaurantes.setDescription("Restaurantes");
		restaurantes.setUrl("restaurantes.jsf");
		
		NavigationData votacao = new NavigationData();
		votacao.setDescription("Votação");
		votacao.setUrl("votacao.jsf");
		
		navigations.add(home);
		navigations.add(restaurantes);
		navigations.add(votacao);
	}

	public List<NavigationData> getNavigations() {
		return navigations;
	}

	public void setNavigations(List<NavigationData> navigations) {
		this.navigations = navigations;
	}

	
	
}
