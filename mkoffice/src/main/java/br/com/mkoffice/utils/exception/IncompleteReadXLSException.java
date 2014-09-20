package br.com.mkoffice.utils.exception;

import br.com.mkoffice.exceptions.FormatException;

public class IncompleteReadXLSException extends FormatException {


	private static final long serialVersionUID = 524194774578665651L;

	public IncompleteReadXLSException(String message, Throwable cause) {
		super("Possivel erro na leitura do Arquivo: "+message, cause);
	}


}

