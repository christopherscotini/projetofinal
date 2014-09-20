package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.dto.CatalogoDTO;
import br.com.mkoffice.model.CatalogoEntity;

public interface CatalogoBO extends ServiceModel<CatalogoDTO> {

	final boolean ADICIONA_ESTOQUE = true;
	final boolean REMOVE_ESTOQUE = false;

	public List<CatalogoDTO> buscarEntidadesPorFiltro(String codMk,
			String nomeProduto, List<Integer> codCategoria,
			boolean estoqueFiltro);

	public List<CatalogoDTO> buscarItensExistentesNoCatalogo(
			List<String> codsProdutos);

	public boolean carregaPlanilhaCatalogoAtualizado(
			List<CatalogoEntity> catalogo);
}
