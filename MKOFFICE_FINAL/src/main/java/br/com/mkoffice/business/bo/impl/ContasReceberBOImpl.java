/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.ContasReceberBO;
import br.com.mkoffice.business.exception.ValidationFormAbstractException;
import br.com.mkoffice.dao.jpa.cadastro.ParcelaRepository;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.model.ParcelasEntity;

/**
 * @author christopher.rozario
 *
 */

@Stateless
public class ContasReceberBOImpl implements ContasReceberBO{

	@Inject
	private ParcelaRepository dao = null;

	
	@Override
	public List<ParcelasEntity> filtrar(DataFilter dataFiltro, Long situacaoPagamento) {
		if(situacaoPagamento.equals(1L)){
			return dao.filterByDateSituacaoPagoContasReceber(dataFiltro);
		}else{
			if(situacaoPagamento.equals(2L)){
				return dao.filterByDateSituacaoPendenteContasReceber(dataFiltro);
			}else{
				if(situacaoPagamento.equals(99L)){
					List<ParcelasEntity>entities = new ArrayList<ParcelasEntity>();
					entities.addAll(dao.filterByDateSituacaoPagoContasReceber(dataFiltro));
					entities.addAll(dao.filterByDateSituacaoPendenteContasReceber(dataFiltro));
					return entities;
				}
			}
		}
		return null;
	}
	
	
	@Override
	public List<ParcelasEntity> listarTodos() {
		return null;
	}

	@Override
	public ParcelasEntity adicionarEntidade(ParcelasEntity entidade) {
		return null;
	}

	@Override
	public ParcelasEntity editarEntidade(ParcelasEntity entidade) {
		return null;
	}

	@Override
	public void validateForm(ParcelasEntity entidade) throws ValidationFormAbstractException {
		
	}

	
}
