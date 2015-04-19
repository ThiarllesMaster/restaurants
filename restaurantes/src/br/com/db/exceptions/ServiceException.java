package br.com.db.exceptions;

public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ServiceException(String message, Exception e) {
		super(message, e);
	}

}
