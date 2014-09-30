package br.com.mkoffice.dto.reports.estoque;

import java.math.BigDecimal;
import java.util.Date;

public class ProdutosMovimentacaoEstoqueDTO {
	
	private String produto;
	private String tipoMovimento;
	private Date dataMovimento;
	private Integer quantidadeMovimentado;
	private BigDecimal valorMovimentado;

	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getTipoMovimento() {
		return tipoMovimento;
	}
	public String getTipoMovimentoDescricao() {
		return tipoMovimento;
	}
	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}
	public Date getDataMovimento() {
		return dataMovimento;
	}
	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
	public BigDecimal getValorMovimentado() {
		return valorMovimentado;
	}
	public void setValorMovimentado(BigDecimal valorMovimentado) {
		this.valorMovimentado = valorMovimentado;
	}
	public Integer getQuantidadeMovimentado() {
		return quantidadeMovimentado;
	}
	public void setQuantidadeMovimentado(Integer quantidadeMovimentado) {
		this.quantidadeMovimentado = quantidadeMovimentado;
	}

}
