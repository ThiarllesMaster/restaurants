package br.com.db.utils;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessageUtils {

	public static void exibirMensagem(String codigo){
		ResourceBundle bundle = ResourceBundle.getBundle("br.com.db.messages.messages");
		String sucesso = bundle.getString(codigo);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(sucesso));

	}	
}
