package br.com.mkoffice.dto.reports.estoque;

import br.com.mkoffice.model.produto.CatalogoEntity;

public class ProdutoMovimentadoDTO {
	
	private CatalogoEntity produto;
	private Integer numProdutosMovimentados;
	
	public CatalogoEntity getProduto() {
		return produto;
	}
	public void setProduto(CatalogoEntity produto) {
		this.produto = produto;
	}
	public Integer getNumProdutosMovimentados() {
		return numProdutosMovimentados;
	}
	public void setNumProdutosMovimentados(Integer numProdutosMovimentados) {
		this.numProdutosMovimentados = numProdutosMovimentados;
	}
	
	

}
