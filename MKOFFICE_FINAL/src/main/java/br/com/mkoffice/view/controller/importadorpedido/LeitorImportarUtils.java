package br.com.mkoffice.view.controller.importadorpedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.model.produto.CatalogoEntity;

public class LeitorImportarUtils {
	
	
	public static DadosPedido ler(Document doc){
		DadosPedido dado = new DadosPedido();
		if(doc != null){
			Elements dadosPedido = doc.select("div[class = innerContainer reportPage]");// <div class="innerContainer reportPage">
			extrairDetalhesPedido(dado, dadosPedido);
			Elements produtos = doc.select("table[class = ReportFrame]");// <table class="ReportFrame"
			extrairProdutosPedido(dado, produtos);
			
		}
		return dado;
	}

	
	private static void extrairDetalhesPedido(DadosPedido dado, Elements dadosPedido) {
		dado.setNumeroPedido(Long.parseLong(dadosPedido.get(0).select("table").get(0).select("tr").get(0).select("td").get(1).select("span").get(0).text()));
	}
	
	private static void extrairProdutosPedido(DadosPedido dado, Elements produtos) {
		Elements listaProdutos = produtos.select("tr");
		dado.setProdutos(new ArrayList<ItemMovimentadoCarrinhoDTO>());
		for (int i = 1; i < listaProdutos.size(); i++) {
			ItemMovimentadoCarrinhoDTO dto = new ItemMovimentadoCarrinhoDTO();
			CatalogoEntity e = new CatalogoEntity();
			e.setCodProduto(Long.valueOf(listaProdutos.get(i).select("td").get(0).text()));
			e.setDescProduto(listaProdutos.get(i).select("td").get(1).text());
			e.setPreco(new BigDecimal(listaProdutos.get(i).select("td").get(2).text().replace(",", ".")));
			dto.setQtdeProdutoCarrinho(Integer.valueOf(listaProdutos.get(i).select("td").get(3).text().split("/")[0]));
			dto.setValorTotalProdutoCarrinho(new BigDecimal(listaProdutos.get(i).select("td").get(4).text().replace(",", ".")));
			dto.setSomaPontosProdutoCarrinho(Integer.parseInt(listaProdutos.get(i).select("td").get(5).text()));
			e.setPontosPorUnidade(dto.getSomaPontosProdutoCarrinho()/dto.getQtdeProdutoCarrinho());
			dto.setProduto(e);
			dado.getProdutos().add(dto);
		}
	}


//	<td class="reportCellDisplay" style="white-space:nowrap;">10022936</td>
//	<td class="reportCellDisplay" style="width:300px;">Batom Cremoso Amber Suede</td>
//	<td class="reportCellDisplay" align="right" style="white-space:nowrap;">37,00</td>
//	<td class="reportCellDisplayCenter" style="white-space:nowrap;">1/0/0</td>
//	<td class="reportCellDisplay" align="right" style="white-space:nowrap;">37,00</td>
//	<td class="reportCellDisplay" align="right" style="white-space:nowrap;">19</td>
//	

}
