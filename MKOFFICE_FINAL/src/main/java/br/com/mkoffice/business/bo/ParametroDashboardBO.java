package br.com.mkoffice.business.bo;

import br.com.mkoffice.model.ParametrosDashboardEntity;


public interface ParametroDashboardBO {

	ParametrosDashboardEntity carregarParametros(Long idUsuario);
	
}
