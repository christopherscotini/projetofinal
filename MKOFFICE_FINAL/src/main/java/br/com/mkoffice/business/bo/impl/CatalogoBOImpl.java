package br.com.mkoffice.business.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.CatalogoBO;
import br.com.mkoffice.business.exception.RegistroJaCadastradoException;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.CatalogoRepository;
import br.com.mkoffice.dto.CatalogoDTO;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.MkmtsUtil;

@Stateless
public class CatalogoBOImpl implements CatalogoBO {

	@Inject
	private CatalogoRepository dao = null;

	public List<CatalogoDTO> buscarEntidadesPorFiltro(String codMk, String nomeProduto,
			List<Integer> codCategoria, boolean estoqueFiltro) {

		List<Integer> codCat = codCategoria;
		if (codCat == null) {
			codCat = new ArrayList<Integer>();
		} else {
			if (codCat.isEmpty()) {
				codCat = new ArrayList<Integer>();
			}
		}

		return dao.findCatalogoByFiltros(
				MkmtsUtil.verificaStringNulaLong(codMk),
				MkmtsUtil.verificaStringNula(nomeProduto), codCat,
				estoqueFiltro);
	}

	@Override
	public List<CatalogoDTO> buscarItensExistentesNoCatalogo(
			List<String> codsProdutos) {
		return dao.buscarItensExistentesNoCatalogo(codsProdutos);
	}

	@Override
	public CatalogoDTO adicionarEntidade(CatalogoDTO dto) {
		dto.setAtivo(ADICIONA_ESTOQUE);
		if (dao.existsProduct(dto.getCodProduto())) {
			throw new RegistroJaCadastradoException("Produto:"
					+ dto.getCodProduto() + " já cadastrado");
		} else {
			return Adapter.entityToDto(dao
					.insert(Adapter.dtoToEntity(dto)));
		}
	}

	@Override
	public List<CatalogoDTO> listarTodos() {
		return dao.findCatalogoByFiltros(null, null, null, false);
	}

	@Override
	public CatalogoDTO editarEntidade(CatalogoDTO entidade) {
		return null;
	}

	@Override
	public boolean carregaPlanilhaCatalogoAtualizado(
			List<CatalogoEntity> catalogo) {
		return dao.carregaPlanilhaCatalogoAtualizado(catalogo);
	}
	
	@Override
	public void validateForm(CatalogoDTO entidade)
			throws ValidationFormRequiredException {
		// TODO Auto-generated method stub
		
	}

}
