package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.dto.CategoriaDTO;
import br.com.mkoffice.model.produto.CatalogoEntity;

public interface CategoriaBO extends ServiceModel<CategoriaDTO>{

	public List<CategoriaDTO> buscarEntidadePorFiltro(String descCategoria, Integer descSecao);
	public CategoriaDTO buscarPeloCodigo(Long cod);
}
