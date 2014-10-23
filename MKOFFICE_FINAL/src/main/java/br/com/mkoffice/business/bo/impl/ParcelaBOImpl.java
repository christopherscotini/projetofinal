package br.com.mkoffice.business.bo.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.ParcelaBO;
import br.com.mkoffice.business.exception.ValidationFormAbstractException;
import br.com.mkoffice.dao.jpa.cadastro.ParcelaRepository;
import br.com.mkoffice.dao.jpa.cadastro.SituacaoRepository;
import br.com.mkoffice.dto.ParcelasDTO;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.view.constants.SituacaoPagamentoEnum;

@Stateless
public class ParcelaBOImpl implements ParcelaBO {

	@Inject
	private ParcelaRepository dao;
	@Inject
	private SituacaoRepository situacaoDao;

	public ParcelaBOImpl() {

	}

	public List listarTodos() {
		return Adapter.listEntityToListDto(dao.findByFilter(null));
	}

	public ParcelasDTO adicionarEntidade(ParcelasDTO entidade) {
		return Adapter.entityToDto((ParcelasEntity) dao.insert(Adapter
				.dtoToEntity(entidade)));
	}

	public ParcelasDTO editarEntidade(ParcelasDTO entidade) {
		return Adapter.entityToDto((ParcelasEntity) dao.update(Adapter
				.dtoToEntity(entidade)));
	}

	public boolean efetuarPagamento(ParcelasDTO parcela) {
		try {
			parcela.setValorPago(parcela.getValorParcela());
			parcela.setCodSituacaoParcela((SituacaoEntity) situacaoDao
					.findByFilter(SituacaoPagamentoEnum.PAGO.getLabel()).get(0));
			dao.update(Adapter.dtoToEntity(parcela));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void validateForm(ParcelasDTO entidade) throws ValidationFormAbstractException {

	}

}
