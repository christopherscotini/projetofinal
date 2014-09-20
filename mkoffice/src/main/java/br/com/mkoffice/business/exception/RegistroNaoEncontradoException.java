package br.com.mkoffice.business.exception;

import br.com.mkoffice.exceptions.BusinessException;


public class RegistroNaoEncontradoException extends BusinessException {

	private static final long serialVersionUID = 8705252009811865635L;

	public RegistroNaoEncontradoException(String registro) {
		super(registro + " não encontrado.");		
	}
}
