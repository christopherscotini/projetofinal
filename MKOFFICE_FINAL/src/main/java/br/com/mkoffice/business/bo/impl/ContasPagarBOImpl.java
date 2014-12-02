/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.ContasPagarBO;
import br.com.mkoffice.business.exception.ValidationFormAbstractException;
import br.com.mkoffice.dao.jpa.cadastro.ParcelaRepository;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.utils.MkmtsUtil;

/**
 * @author christopher.rozario
 *
 */

@Stateless
public class ContasPagarBOImpl implements ContasPagarBO{

	@Inject
	private ParcelaRepository dao = null;

	
	@Override
	public List<ParcelasEntity> filtrar(DataFilter dataFiltro, Long situacaoPagamento, Long idUsuario) {
		situacaoPagamento = MkmtsUtil.verificaLongNulo(situacaoPagamento == null ? 0 : Long.valueOf(situacaoPagamento));
		
		if(situacaoPagamento.equals(1L)){
			return dao.filterByDateSituacaoPagoContasPagar(dataFiltro, idUsuario);
		}else{
			if(situacaoPagamento.equals(2L)){
				return dao.filterByDateSituacaoPendenteContasPagar(dataFiltro, idUsuario);
			}else{
				if(situacaoPagamento.equals(99L)){
					List<ParcelasEntity>entities = new ArrayList<ParcelasEntity>();
					entities.addAll(dao.filterByDateSituacaoPagoContasPagar(dataFiltro, idUsuario));
					entities.addAll(dao.filterByDateSituacaoPendenteContasPagar(dataFiltro, idUsuario));
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
