package br.com.mkoffice.dto.metricas;

import java.math.BigDecimal;

public class EstoqueMetricasDTO {
	
	private Integer somaTodosProdutosEmEstoque;
	private Integer somaToTalPontosEmEstoque;
	private BigDecimal valorToTalRevendaProdutosEmEstoque;

	public Integer getSomaTodosProdutosEmEstoque() {
		return somaTodosProdutosEmEstoque;
	}
	public void setSomaTodosProdutosEmEstoque(Integer somaTodosProdutosEmEstoque) {
		this.somaTodosProdutosEmEstoque = somaTodosProdutosEmEstoque;
	}
	public Integer getSomaToTalPontosEmEstoque() {
		return somaToTalPontosEmEstoque;
	}
	public void setSomaToTalPontosEmEstoque(Integer somaToTalPontosEmEstoque) {
		this.somaToTalPontosEmEstoque = somaToTalPontosEmEstoque;
	}
	public BigDecimal getValorToTalRevendaProdutosEmEstoque() {
		return valorToTalRevendaProdutosEmEstoque;
	}
	public void setValorToTalRevendaProdutosEmEstoque(
			BigDecimal valorToTalRevendaProdutosEmEstoque) {
		this.valorToTalRevendaProdutosEmEstoque = valorToTalRevendaProdutosEmEstoque;
	}

}
