package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.dto.ClienteDTO;

public interface ClienteBO extends ServiceModel<ClienteDTO>{

	//telefoneFiltro pode ser CELULAR OU TELEFONE
	 List<ClienteDTO> buscarEntidadePorFiltro(String codClienteFiltro, String email, String nomeFiltro, String celularFiltro, Long idUsuario);
}
