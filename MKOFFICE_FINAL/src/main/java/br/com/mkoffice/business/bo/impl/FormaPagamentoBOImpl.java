/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.FormaPagamentoBO;
import br.com.mkoffice.business.exception.RegistroJaCadastradoException;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.FormaPagamentoRepository;
import br.com.mkoffice.model.admin.FormaPagamentoEntity;
import br.com.mkoffice.utils.MkmtsUtil;

/**
 * @author christopher.rozario
 *
 */

@Stateless
public class FormaPagamentoBOImpl implements FormaPagamentoBO{

	@Inject
	private FormaPagamentoRepository dao = null;

	@Override
	public List<FormaPagamentoEntity> listarTodos() {
		return dao.findByFilter(null);
	}

	@Override
	public FormaPagamentoEntity adicionarEntidade(FormaPagamentoEntity entidade) {
		if(!dao.existsFormaPagamento(entidade)){
			entidade.setDescFormaPgto(entidade.getDescFormaPgto().toUpperCase());
			if(entidade.getNumeroParcelas() == null){
				entidade.setNumeroParcelas(new Integer(1));
			}
			return dao.insert(entidade);
		}else{
			throw new RegistroJaCadastradoException("Forma de Pagamento");
		}
	}

	@Override
	public FormaPagamentoEntity editarEntidade(FormaPagamentoEntity entidade) {
		if(!dao.existsFormaPagamento(entidade)){
			entidade.setDescFormaPgto(entidade.getDescFormaPgto().toUpperCase());
			return dao.update(entidade);
		}else{
			throw new RegistroJaCadastradoException("Forma de Pagamento");
		}
	}
	
	@Override
	public List<FormaPagamentoEntity> buscarEntidadesPorFiltro(String param) {
		return dao.findByFilter(MkmtsUtil.verificaStringNula(param));
	}



	@Override
	public void validateForm(FormaPagamentoEntity entidade)
			throws ValidationFormRequiredException {
		// TODO Auto-generated method stub
		
	}
	
}
