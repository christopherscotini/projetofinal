package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.model.admin.FormaPagamentoEntity;

public interface FormaPagamentoBO extends ServiceModel<FormaPagamentoEntity> {
	
	public List<FormaPagamentoEntity> buscarEntidadesPorFiltro(String param);
	
}
