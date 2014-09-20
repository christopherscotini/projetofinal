package br.com.mkoffice.business.bo;

import br.com.mkoffice.model.DefaultConfigurationEntity;


public interface DefaultConfigurationBO  {

	public DefaultConfigurationEntity atualizarConfiguracoes(DefaultConfigurationEntity entity);
	public DefaultConfigurationEntity carregarConfiguracao();
	
}
