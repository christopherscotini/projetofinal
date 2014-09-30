package br.com.mkoffice.business.bo;

import java.util.Date;
import java.util.List;

import br.com.mkoffice.dto.reports.estoque.ProdutosMovimentacaoEstoqueDTO;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;

public interface EstoqueHistoricoBO {

	List<ProdutosMovimentacaoEstoqueDTO> gerarRelatorioMovimentacaoEstoque(
			FluxoEstoqueEntity tipoFluxoEstoqueFiltro, Date dtInicio, Date dtFinal, Long idUsuario);

}
