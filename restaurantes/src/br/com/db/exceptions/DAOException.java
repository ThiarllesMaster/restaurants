package br.com.db.exceptions;


public class DAOException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DAOException(String message, Exception e){
		super(message, e);
	}
	
}
