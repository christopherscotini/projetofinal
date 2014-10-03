package br.com.mkoffice.business.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.AgendaBO;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.AgendaRepository;
import br.com.mkoffice.dto.AgendaDTO;
import br.com.mkoffice.model.agenda.AgendaEntity;
import br.com.mkoffice.utils.Adapter;

/**
 * @author christopher.rozario
 *
 */

@Stateless
public class AgendaBOImpl implements AgendaBO{

	@Inject
	private AgendaRepository dao;

	@Override
	public List<AgendaDTO> listarTodos() {
		List<AgendaEntity> entities = dao.findAll();
		List<AgendaDTO> returnzz = new ArrayList<AgendaDTO>();
		for (int i = 0; i < entities.size(); i++) {
			returnzz.add(Adapter.entityToDto(entities.get(i)));
		}
		
		return returnzz;
	}

	@Override
	public List<AgendaDTO> buscarTodasPorUsuario(Long idUsuario) {
		return dao.findByFilter(idUsuario);
	}
	
	@Override
	public AgendaDTO adicionarEntidade(AgendaDTO entidade) {
		return Adapter.entityToDto(dao.insert(Adapter.dtoToEntity(entidade)));
	}

	@Override
	public AgendaDTO editarEntidade(AgendaDTO entidade) {
		return	Adapter.entityToDto(dao.update(Adapter.dtoToEntity(entidade)));
	}
	
	@Override
	public void validateForm(AgendaDTO entidade) throws ValidationFormRequiredException {
		// TODO Auto-generated method stub
		
	}

}
