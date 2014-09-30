package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.dto.ConsultoraDTO;

/**
 * @author christopher.rozario
 *
 */
public interface ConsultoraBO extends ServiceModel<ConsultoraDTO>{

	//telefoneFiltro pode ser CELULAR OU TELEFONE
	 List<ConsultoraDTO> buscarEntidadePorFiltro(String codConsultoraFiltro, String email, String nomeFiltro, String celularFiltro, Long idUsuario);

}
