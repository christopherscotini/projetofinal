package br.com.mkoffice.business;

import java.util.List;

import br.com.mkoffice.business.exception.ValidationFormAbstractException;


public interface ServiceModel<T> {

	public List<T> listarTodos();
	T adicionarEntidade(T entidade);
	T editarEntidade(T entidade);
	void validateForm(T entidade) throws ValidationFormAbstractException;
	
}
