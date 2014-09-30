package br.com.mkoffice.ws;

/**
 * Exceção lançada quando uma busca por número de CEP não retorna nada.
 *  
 * @author Fábio Franco Uechi
 *
 */
public class CEPNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CEPNaoEncontradoException(int numeroCEP, Throwable cause) {
		super("O CEP " + numeroCEP + " n�o foi encontrado.", cause);
	}

	public CEPNaoEncontradoException(String query, Throwable cause) {
		super("CEP '" + query + "' n�o encontrado.", cause);
	}

}
