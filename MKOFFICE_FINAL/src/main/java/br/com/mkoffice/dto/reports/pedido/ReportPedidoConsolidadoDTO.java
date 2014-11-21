package br.com.mkoffice.dto.reports.pedido;

import java.math.BigDecimal;
import java.util.List;

import br.com.mkoffice.dto.PedidoDTO;
import br.com.mkoffice.model.pedido.PedidoEntity;

public class ReportPedidoConsolidadoDTO {
	
	private List<PedidoDTO> pedidos;
	private Integer numeroPedidos = 0;
	private BigDecimal valorTotalPagoPedidos = BigDecimal.ZERO;
	private BigDecimal valorTotalRevendaPedidos = BigDecimal.ZERO;
	private Integer numeroProdutosPedidos = 0;
	private Integer numeroTotalPontosPedidos = 0;
	
	public List<PedidoDTO> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoDTO> pedidos) {
		this.pedidos = pedidos;
	}
	public Integer getNumeroPedidos() {
		return numeroPedidos;
	}
	public void setNumeroPedidos(Integer numeroPedidos) {
		this.numeroPedidos = numeroPedidos;
	}
	public BigDecimal getValorTotalPagoPedidos() {
		return valorTotalPagoPedidos;
	}
	public void setValorTotalPagoPedidos(BigDecimal valorTotalPagoPedidos) {
		this.valorTotalPagoPedidos = valorTotalPagoPedidos;
	}
	public BigDecimal getValorTotalRevendaPedidos() {
		return valorTotalRevendaPedidos;
	}
	public void setValorTotalRevendaPedidos(BigDecimal valorTotalRevendaPedidos) {
		this.valorTotalRevendaPedidos = valorTotalRevendaPedidos;
	}
	public Integer getNumeroProdutosPedidos() {
		return numeroProdutosPedidos;
	}
	public void setNumeroProdutosPedidos(Integer numeroProdutosPedidos) {
		this.numeroProdutosPedidos = numeroProdutosPedidos;
	}
	public Integer getNumeroTotalPontosPedidos() {
		return numeroTotalPontosPedidos;
	}
	public void setNumeroTotalPontosPedidos(Integer numeroTotalPontosPedidos) {
		this.numeroTotalPontosPedidos = numeroTotalPontosPedidos;
	}
	
	
}
