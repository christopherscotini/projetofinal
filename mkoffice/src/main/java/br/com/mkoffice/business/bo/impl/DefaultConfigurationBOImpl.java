/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.DefaultConfigurationBO;
import br.com.mkoffice.model.DefaultConfigurationEntity;
import br.com.mkoffice.repository.dao.DefaultConfigurationRepository;

/**
 * @author christopher.rozario
 *
 */

@Stateless
public class DefaultConfigurationBOImpl implements DefaultConfigurationBO{

	@Inject
	private DefaultConfigurationRepository configRepository = null;

	@Override
	public DefaultConfigurationEntity atualizarConfiguracoes(
			DefaultConfigurationEntity entity) {

		return configRepository.update(entity);
	}
	
	@Override
	public DefaultConfigurationEntity carregarConfiguracao() {
		return configRepository.findById(1L);
	}

}
