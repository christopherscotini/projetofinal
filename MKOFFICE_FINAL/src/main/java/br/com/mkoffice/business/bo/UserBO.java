package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.model.admin.UserEntity;


public interface UserBO extends ServiceModel<UserEntity>{


	public UserEntity verificarLogin(UserEntity usuario);
	public List<UserEntity> buscarEntidadePorFiltro(String username);
	
}
