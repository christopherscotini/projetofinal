package br.com.mkoffice.dto.reports.estoque;

import java.util.List;

public class ReportProdutosMaisMenosVendidosDTO {
	
	private List<ProdutoMovimentadoDTO>produtosMaisVendidos;
	private List<ProdutoMovimentadoDTO>produtosMenosVendidos;
	
	public List<ProdutoMovimentadoDTO> getProdutosMaisVendidos() {
		return produtosMaisVendidos;
	}
	public void setProdutosMaisVendidos(
			List<ProdutoMovimentadoDTO> produtosMaisVendidos) {
		this.produtosMaisVendidos = produtosMaisVendidos;
	}
	public List<ProdutoMovimentadoDTO> getProdutosMenosVendidos() {
		return produtosMenosVendidos;
	}
	public void setProdutosMenosVendidos(
			List<ProdutoMovimentadoDTO> produtosMenosVendidos) {
		this.produtosMenosVendidos = produtosMenosVendidos;
	}

}
