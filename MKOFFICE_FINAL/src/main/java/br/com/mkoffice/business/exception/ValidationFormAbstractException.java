package br.com.mkoffice.business.exception;

import javax.ejb.ApplicationException;

import br.com.mkoffice.exceptions.BusinessException;

@ApplicationException(rollback=true)
public abstract class ValidationFormAbstractException extends BusinessException {

	private static final long serialVersionUID = -1375463370969538895L;

	public ValidationFormAbstractException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationFormAbstractException(String message) {
		super(message);
	}
	
}
