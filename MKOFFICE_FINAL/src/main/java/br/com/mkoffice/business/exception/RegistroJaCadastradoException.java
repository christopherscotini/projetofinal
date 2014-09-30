package br.com.mkoffice.business.exception;

import javax.ejb.ApplicationException;

import br.com.mkoffice.exceptions.BusinessException;

@ApplicationException(rollback=true)
public class RegistroJaCadastradoException extends BusinessException {

	private static final long serialVersionUID = 8705252009811865635L;

	public RegistroJaCadastradoException(String registro) {
		super(registro + " já está cadastrado(a).");
	}
}
