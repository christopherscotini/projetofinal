package br.com.mkoffice.business.exception;



public class ValidationFormRequiredException extends ValidationFormAbstractException {

	private static final long serialVersionUID = 8705252009811865635L;

	public ValidationFormRequiredException(String campo) {
		super("O campo " + campo + " é obrigatório.");
	}
	
}
