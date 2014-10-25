package br.com.mkoffice.business.bo;

import java.util.Date;
import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.SituacaoEntity;

public interface ContasPagarBO extends ServiceModel<ParcelasEntity> {

	public List<ParcelasEntity> filtrar(Date dataInicial, Date dataFinal, Long situacaoPagamento);
	
}
