package br.com.mkoffice.business.bo;

import br.com.mkoffice.model.admin.DefaultConfigurationEntity;

public interface DefaultConfigurationBO  {

	public DefaultConfigurationEntity atualizarConfiguracoes(DefaultConfigurationEntity entity);
	public DefaultConfigurationEntity carregarConfiguracao();
	
}
