package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.dto.AgendaDTO;
import br.com.mkoffice.model.agenda.AgendaEntity;


public interface AgendaBO extends ServiceModel<AgendaDTO>{

	List<AgendaDTO> buscarTodasPorUsuario(Long idUsuario);
}
