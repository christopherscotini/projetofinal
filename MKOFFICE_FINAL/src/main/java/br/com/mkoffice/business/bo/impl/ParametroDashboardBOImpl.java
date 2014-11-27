package br.com.mkoffice.business.bo.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.ParametroDashboardBO;
import br.com.mkoffice.dao.jpa.cadastro.ParametrosDashboardRepository;
import br.com.mkoffice.model.ParametrosDashboardEntity;

@Stateless
public class ParametroDashboardBOImpl implements ParametroDashboardBO {

	@Inject
	private ParametrosDashboardRepository parametrosDashboardRepository = null;

	@Override
	public ParametrosDashboardEntity carregarParametros(Long idUsuario) {

		return parametrosDashboardRepository.findByUsuario(idUsuario);
	}
	

	
}
