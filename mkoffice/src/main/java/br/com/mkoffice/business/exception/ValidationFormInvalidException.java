package br.com.mkoffice.business.exception;



public class ValidationFormInvalidException extends ValidationFormAbstractException {

	private static final long serialVersionUID = 8705252009811865635L;

	public ValidationFormInvalidException(String campo) {
		super("O "+ campo+ " é inválido.");
	}
	
}
