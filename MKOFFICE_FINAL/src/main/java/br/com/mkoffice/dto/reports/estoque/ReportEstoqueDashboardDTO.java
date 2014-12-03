package br.com.mkoffice.dto.reports.estoque;

import java.math.BigDecimal;
import java.util.List;

public class ReportEstoqueDashboardDTO {

	private List<ProdutoMovimentadoDTO> produtosMaisAntigosEmEstoque;
	private BigDecimal valorEstoqueAtacado;
	private BigDecimal valorLucroRevenda;
	private BigDecimal valorEstoqueRevenda;
	private List<PedidoPorPercentualDescontoDTO> pedidoPorPercentualDescontoList;
	
	public List<ProdutoMovimentadoDTO> getProdutosMaisAntigosEmEstoque() {
		return produtosMaisAntigosEmEstoque;
	}
	public void setProdutosMaisAntigosEmEstoque(
			List<ProdutoMovimentadoDTO> produtosMaisAntigosEmEstoque) {
		this.produtosMaisAntigosEmEstoque = produtosMaisAntigosEmEstoque;
	}
	public BigDecimal getValorEstoqueAtacado() {
		return valorEstoqueAtacado;
	}
	public void setValorEstoqueAtacado(BigDecimal valorEstoqueAtacado) {
		this.valorEstoqueAtacado = valorEstoqueAtacado;
	}
	public BigDecimal getValorLucroRevenda() {
		return valorLucroRevenda;
	}
	public void setValorLucroRevenda(BigDecimal valorLucroRevenda) {
		this.valorLucroRevenda = valorLucroRevenda;
	}
	public BigDecimal getValorEstoqueRevenda() {
		return valorEstoqueRevenda;
	}
	public void setValorEstoqueRevenda(BigDecimal valorEstoqueRevenda) {
		this.valorEstoqueRevenda = valorEstoqueRevenda;
	}
	public List<PedidoPorPercentualDescontoDTO> getPedidoPorPercentualDescontoList() {
		return pedidoPorPercentualDescontoList;
	}
	public void setPedidoPorPercentualDescontoList(
			List<PedidoPorPercentualDescontoDTO> pedidoPorPercentualDescontoList) {
		this.pedidoPorPercentualDescontoList = pedidoPorPercentualDescontoList;
	}
	
	
	
}
