/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.DefaultConfigurationBO;
import br.com.mkoffice.dao.jpa.cadastro.DefaultConfigurationRepository;
import br.com.mkoffice.model.admin.DefaultConfigurationEntity;

/**
 * @author christopher.rozario
 *
 */

@Stateless
public class DefaultConfigurationBOImpl implements DefaultConfigurationBO{

	@Inject
	private DefaultConfigurationRepository dao = null;

	@Override
	public DefaultConfigurationEntity atualizarConfiguracoes(
			DefaultConfigurationEntity entity) {

		return dao.update(entity);
	}
	
	@Override
	public DefaultConfigurationEntity carregarConfiguracao() {
		return dao.findById(1L);
	}


}
