package br.com.mkoffice.business.bo.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.CategoriaBO;
import br.com.mkoffice.business.exception.RegistroJaCadastradoException;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.CategoriaRepository;
import br.com.mkoffice.dto.CategoriaDTO;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.MkmtsUtil;

@Stateless
public class CategoriaBOImpl implements CategoriaBO{

	@Inject
	private CategoriaRepository dao = null;

	@Override
	public List<CategoriaDTO> buscarEntidadePorFiltro(String descCategoria,
			Integer codSecao) {
		return dao.findByFilter(
				MkmtsUtil.verificaStringNula(descCategoria)
				, MkmtsUtil.verificaIntegerNulo(codSecao));
	}
	
	@Override
	public CategoriaDTO buscarPeloCodigo(Long cod) {
		return Adapter.entityToDto(dao.findById(cod));
	}

	@Override
	public List<CategoriaDTO> listarTodos() {
		return dao.findAllCategorias();
	}

	@Override
	public CategoriaDTO adicionarEntidade(CategoriaDTO entidade) {
		if(dao.existCategoria(entidade)){
			throw new RegistroJaCadastradoException(entidade.getDescCategoria());
		}else{
			return Adapter.entityToDto(dao.insert(Adapter.dtoToEntity(entidade)));
		}
	}

	@Override
	public CategoriaDTO editarEntidade(CategoriaDTO entidade) {
		if(dao.existCategoria(entidade)){
			throw new RegistroJaCadastradoException(entidade.getDescCategoria());
		}else{
			return Adapter.entityToDto(dao.update(Adapter.dtoToEntity(entidade)));
		}

	}


	@Override
	public void validateForm(CategoriaDTO entidade)
			throws ValidationFormRequiredException {
		// TODO Auto-generated method stub
		
	}

}
