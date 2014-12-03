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
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.ParcelasDTO;
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
	public List<ParcelasDTO> filtrar(DataFilter dataFiltro, Long situacaoPagamento, Long idUsuario) {
		if(situacaoPagamento.equals(1L)){
			return dao.filterByDateSituacaoPagoContasReceber(dataFiltro, idUsuario);
		}else{
			if(situacaoPagamento.equals(2L)){
				return dao.filterByDateSituacaoPendenteContasReceber(dataFiltro, idUsuario);
			}else{
				if(situacaoPagamento.equals(99L)){
					List<ParcelasDTO>entities = new ArrayList<ParcelasDTO>();
					entities.addAll(dao.filterByDateSituacaoPagoContasReceber(dataFiltro, idUsuario));
					entities.addAll(dao.filterByDateSituacaoPendenteContasReceber(dataFiltro, idUsuario));
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
