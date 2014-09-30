package br.com.mkoffice.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.mkoffice.dto.reports.estoque.ProdutosMovimentacaoEstoqueDTO;
import br.com.mkoffice.dto.reports.financeiro.ContasReceberReportDTO;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.estoque.EstoqueEntity;


public class AdapterReports {
	
	public static List<ProdutosMovimentacaoEstoqueDTO>conveterMovimentacaoEstoque(List<EstoqueEntity> lista){
		List<ProdutosMovimentacaoEstoqueDTO> retorno = new ArrayList<ProdutosMovimentacaoEstoqueDTO>();
		for (int i = 0; i < lista.size(); i++) {
			ProdutosMovimentacaoEstoqueDTO dto = new ProdutosMovimentacaoEstoqueDTO();
			dto.setProduto(lista.get(i).getCodCatalogo().getDescProduto());
			dto.setTipoMovimento(lista.get(i).getTipoFluxoEstoque().getDescFluxoEstoque());
			dto.setDataMovimento(lista.get(i).getDtMovimentacao());
			dto.setQuantidadeMovimentado(lista.get(i).getQtdeMovimentadoProduto());
			dto.setValorMovimentado(lista.get(i).getValorTotalMovimentadoProduto());
			
			retorno.add(dto);
		}
		
		return retorno;
	}
	
	public static void generateContasReceber(List<ParcelasEntity> lista, List<ContasReceberReportDTO> listaRetorno){
		for (int i = 0; i < lista.size(); i++) {
			listaRetorno.add(new ContasReceberReportDTO(
					  lista.get(i).getCodVenda().getCodVenda()
					, lista.get(i).getCodVenda().getCliente().getDadosPessoa().getNome()
					, lista.get(i).getCodSituacaoParcela().getDescSituacao()
					, lista.get(i).getValorParcela()
					, lista.get(i).getValorPago()
					, lista.get(i).getDtPagamento()));
		}
	}
	
	
}
