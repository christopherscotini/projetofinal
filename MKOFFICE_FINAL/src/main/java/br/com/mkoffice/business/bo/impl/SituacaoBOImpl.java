/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.SituacaoBO;
import br.com.mkoffice.business.exception.RegistroJaCadastradoException;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.SituacaoRepository;
import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.utils.MkmtsUtil;

/**
 * @author christopher.rozario
 *
 */

@Stateless
public class SituacaoBOImpl implements SituacaoBO{

	@Inject
	private SituacaoRepository dao = null;

	@Override
	public List<SituacaoEntity> listarTodos() {
		return dao.findByFilter(null);
	}

	@Override
	public SituacaoEntity adicionarEntidade(SituacaoEntity entidade) {
		if(!dao.existsSituacao(entidade)){
			return dao.insert(entidade);
		}else{
			throw new RegistroJaCadastradoException("Situa��o");
		}
	}

	@Override
	public SituacaoEntity editarEntidade(SituacaoEntity entidade) {
		if(!dao.existsSituacao(entidade)){
			return dao.update(entidade);
		}else{
			throw new RegistroJaCadastradoException("Situa��o");
		}
	}
	
	@Override
	public List<SituacaoEntity> buscarEntidadesPorFiltro(String param) {
		return dao.findByFilter(MkmtsUtil.verificaStringNula(param));
	}



	@Override
	public void validateForm(SituacaoEntity entidade)
			throws ValidationFormRequiredException {
		// TODO Auto-generated method stub
		
	}
	
}
