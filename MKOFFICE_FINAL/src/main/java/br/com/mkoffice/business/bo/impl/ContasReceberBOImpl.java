/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.ContasReceberBO;
import br.com.mkoffice.business.exception.ValidationFormAbstractException;
import br.com.mkoffice.dao.jpa.cadastro.ParcelaRepository;
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
	public List<ParcelasEntity> filtrar(Date dataInicial, Date dataFinal, Long situacaoPagamento) {
		if(situacaoPagamento.equals(1L)){
			return dao.filterByDateSituacaoPagoContasReceber(dataInicial, dataFinal);
		}else{
			if(situacaoPagamento.equals(2L)){
				return dao.filterByDateSituacaoPendenteContasReceber(dataInicial, dataFinal);
			}else{
				if(situacaoPagamento.equals(99L)){
					List<ParcelasEntity>entities = new ArrayList<ParcelasEntity>();
					entities.addAll(dao.filterByDateSituacaoPagoContasReceber(dataInicial, dataFinal));
					entities.addAll(dao.filterByDateSituacaoPendenteContasReceber(dataInicial, dataFinal));
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
