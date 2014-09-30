package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;

public interface FluxoEstoqueBO extends ServiceModel<FluxoEstoqueEntity> {
	
	public List<FluxoEstoqueEntity> buscarEntidadesPorFiltro(String param);
	public List<FluxoEstoqueEntity> listarFluxosParaMovEstoque();
	
}
