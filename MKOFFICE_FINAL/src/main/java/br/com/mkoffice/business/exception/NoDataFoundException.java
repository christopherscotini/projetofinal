package br.com.mkoffice.business.exception;

import javax.ejb.ApplicationException;

import br.com.mkoffice.exceptions.BusinessException;

@ApplicationException(rollback=true)
public class NoDataFoundException extends BusinessException {

	private static final long serialVersionUID = 5077420954080626074L;

	public NoDataFoundException(String registro) {
		super("Não foram encontrados "+registro );		
	}
}
