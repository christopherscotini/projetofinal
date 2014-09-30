package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.model.admin.PermissaoEntity;

public interface PermissaoBO extends ServiceModel<PermissaoEntity> {

	public List<PermissaoEntity> buscarEntidadesPorFiltro(String param);

	public PermissaoEntity buscarPerfilUsuarioComum();
	public PermissaoEntity buscarPerfilAdministrador();
}
