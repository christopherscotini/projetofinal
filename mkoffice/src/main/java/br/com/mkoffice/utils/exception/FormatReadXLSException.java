package br.com.mkoffice.utils.exception;

import br.com.mkoffice.exceptions.FormatException;

public class FormatReadXLSException extends FormatException {


	private static final long serialVersionUID = 524194774578665651L;

	public FormatReadXLSException(String message, Throwable cause) {
		super("Erro na leitura do Arquivo: "+message, cause);
	}


}

