package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.dto.PermissaoDTO;

public interface PermissaoBO extends ServiceModel<PermissaoDTO> {

	public List<PermissaoDTO> buscarEntidadesPorFiltro(String param);

	public PermissaoDTO buscarPerfilUsuarioComum();
	public PermissaoDTO buscarPerfilAdministrador();
}
