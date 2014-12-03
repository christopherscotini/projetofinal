package br.com.mkoffice.dto.reports.estoque;

import br.com.mkoffice.model.constants.PercentDescontoEnum;
import br.com.mkoffice.model.pedido.PedidoEntity;

public class PedidoPorPercentualDescontoDTO {

	private Integer numPedido;
	private PercentDescontoEnum desconto;
	
	public Integer getNumPedido() {
		return numPedido;
	}
	public void setNumPedido(Integer numPedido) {
		this.numPedido = numPedido;
	}
	public PercentDescontoEnum getDesconto() {
		return desconto;
	}
	public void setDesconto(PercentDescontoEnum desconto) {
		this.desconto = desconto;
	}
	
	
	
}
