package br.com.mtonon.parkingapi.service.exception;

public class PasswordInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PasswordInvalidException(String message) {
		super(message);
	}

}
