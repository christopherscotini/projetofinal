package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.model.ParcelasEntity;

public interface ContasReceberBO extends ServiceModel<ParcelasEntity> {

	public List<ParcelasEntity> filtrar(DataFilter dataFiltro, Long situacaoPagamento, Long idUsuario);
	
}
