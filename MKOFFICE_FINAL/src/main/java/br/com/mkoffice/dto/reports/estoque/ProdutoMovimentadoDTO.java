package br.com.mkoffice.dto.reports.estoque;

import java.util.Date;

import br.com.mkoffice.model.produto.CatalogoEntity;

public class ProdutoMovimentadoDTO {
	
	private CatalogoEntity produto;
	private Integer numProdutosMovimentados;
	private Date dtMovimentado;
	
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
	public Date getDtMovimentado() {
		return dtMovimentado;
	}
	public void setDtMovimentado(Date dtMovimentado) {
		this.dtMovimentado = dtMovimentado;
	}
	
	

}
