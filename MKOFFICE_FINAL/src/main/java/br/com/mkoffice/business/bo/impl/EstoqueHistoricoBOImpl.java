package br.com.mkoffice.business.bo.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.EstoqueHistoricoBO;
import br.com.mkoffice.dao.jpa.cadastro.EstoqueHistoricoRepository;
import br.com.mkoffice.dto.reports.estoque.ProdutosMovimentacaoEstoqueDTO;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.utils.AdapterReports;

@Stateless
public class EstoqueHistoricoBOImpl implements EstoqueHistoricoBO {

	@Inject
	private EstoqueHistoricoRepository dao = null;

	@Override
	public List<ProdutosMovimentacaoEstoqueDTO> gerarRelatorioMovimentacaoEstoque(
			FluxoEstoqueEntity tipoFluxoEstoque, Date dtInicio, Date dtFinal, Long idUsuario) {

		return AdapterReports.conveterMovimentacaoEstoque(dao.generateMovimentacaoEstoqueReport(tipoFluxoEstoque, dtInicio, dtFinal, idUsuario));
	}
	
	

}
