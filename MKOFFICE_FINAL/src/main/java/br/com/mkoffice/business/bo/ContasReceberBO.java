package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.ParcelasDTO;
import br.com.mkoffice.model.ParcelasEntity;

public interface ContasReceberBO extends ServiceModel<ParcelasEntity> {

	public List<ParcelasDTO> filtrar(DataFilter dataFiltro, Long situacaoPagamento, Long idUsuario);
	
}
