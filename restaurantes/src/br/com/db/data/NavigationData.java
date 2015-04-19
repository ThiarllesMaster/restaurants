package br.com.db.data;

import java.io.Serializable;

public class NavigationData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String description;
	private String url;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
