package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.model.admin.SituacaoEntity;

public interface SituacaoBO extends ServiceModel<SituacaoEntity> {

	public List<SituacaoEntity> buscarEntidadesPorFiltro(String param);
}
