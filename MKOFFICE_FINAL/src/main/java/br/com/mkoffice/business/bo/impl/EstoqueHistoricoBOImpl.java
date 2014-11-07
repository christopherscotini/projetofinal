package br.com.mkoffice.business.bo.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.EstoqueHistoricoBO;
import br.com.mkoffice.dao.jpa.cadastro.EstoqueHistoricoRepository;

@Stateless
public class EstoqueHistoricoBOImpl implements EstoqueHistoricoBO {

	@Inject
	private EstoqueHistoricoRepository dao = null;

	
	

}
