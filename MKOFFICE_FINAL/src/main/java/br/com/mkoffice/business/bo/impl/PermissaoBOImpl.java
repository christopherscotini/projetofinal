/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.PermissaoBO;
import br.com.mkoffice.business.exception.RegistroJaCadastradoException;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.PermissaoRepository;
import br.com.mkoffice.model.admin.PermissaoEntity;
import br.com.mkoffice.utils.MkmtsUtil;

/**
 * @author christopher.rozario
 *
 */

@Stateless
public class PermissaoBOImpl implements PermissaoBO{

	@Inject
	private PermissaoRepository dao = null;

	@Override
	public List<PermissaoEntity> listarTodos() {
		return dao.findByFilter(null);
	}

	@Override
	public PermissaoEntity adicionarEntidade(PermissaoEntity entidade) {
		if(!dao.existsPermissao(entidade)){
			entidade.setDescPermissao(entidade.getDescPermissao().toUpperCase());
			return dao.insert(entidade);
		}else{
			throw new RegistroJaCadastradoException("Permiss�o");
		}
	}

	@Override
	public PermissaoEntity editarEntidade(PermissaoEntity entidade) {
		if(!dao.existsPermissao(entidade)){
			entidade.setDescPermissao(entidade.getDescPermissao().toUpperCase());
			return dao.update(entidade);
		}else{
			throw new RegistroJaCadastradoException("Permiss�o");
		}
	}
	
	@Override
	public List<PermissaoEntity> buscarEntidadesPorFiltro(String param) {
		return dao.findByFilter(MkmtsUtil.verificaStringNula(param));
	}

	@Override
	public PermissaoEntity buscarPerfilAdministrador() {

		return dao.findById(1L);
	}
	
	@Override
	public PermissaoEntity buscarPerfilUsuarioComum() {

		return dao.findById(2L);
	}

	@Override
	public void validateForm(PermissaoEntity entidade)
			throws ValidationFormRequiredException {
		// TODO Auto-generated method stub
		
	}
	
}
