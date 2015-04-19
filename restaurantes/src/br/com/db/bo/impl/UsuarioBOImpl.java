package br.com.db.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.db.bo.UsuarioBO;
import br.com.db.dao.UsuarioDAO;

@Service(value = "usuarioBO")
public class UsuarioBOImpl implements UsuarioBO{

	@Autowired
	UsuarioDAO usuarioDAO;

	@Override
	public boolean usuarioExiste(String nome) {
		return usuarioDAO.buscarRegistro("nome", nome) != null;
	}
	
	
	
}
