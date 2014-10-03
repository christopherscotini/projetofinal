/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.TipoAgendaBO;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.TipoAgendaRepository;
import br.com.mkoffice.model.agenda.TipoAgendaEntity;

/**
 * @author christopher.rozario
 *
 */

@Stateless
public class TipoAgendaBOImpl implements TipoAgendaBO{

	@Inject
	private TipoAgendaRepository dao = null;

	@Override
	public List<TipoAgendaEntity> listarTodos() {
		return dao.findByFilter("");
	}

	@Override
	public TipoAgendaEntity adicionarEntidade(TipoAgendaEntity entidade) {
		return null;
	}

	@Override
	public TipoAgendaEntity editarEntidade(TipoAgendaEntity entidade) {
		return null;
	}
	
	@Override
	public void validateForm(TipoAgendaEntity entidade)
			throws ValidationFormRequiredException {
		// TODO Auto-generated method stub
		
	}

	
}
