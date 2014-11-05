package br.com.mkoffice.dto.reports;

import java.math.BigDecimal;

import br.com.mkoffice.model.produto.CatalogoEntity;

public class ProdutosMaisMenosVendidosDTO {

	private CatalogoEntity produto;
	private Integer qtde;
	private BigDecimal valor;
	private Integer diasParados;
	public CatalogoEntity getProduto() {
		return produto;
	}
	public void setProduto(CatalogoEntity produto) {
		this.produto = produto;
	}
	public Integer getQtde() {
		return qtde;
	}
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getDiasParados() {
		return diasParados;
	}
	public void setDiasParados(Integer diasParados) {
		this.diasParados = diasParados;
	}
	
}
