package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.dto.UserDTO;


public interface UserBO extends ServiceModel<UserDTO>{


	public UserDTO verificarLogin(UserDTO usuario);
	public List<UserDTO> buscarEntidadePorFiltro(String username);
	
}
