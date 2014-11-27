package br.com.mkoffice.dto.dashboard;

import java.math.BigDecimal;

import br.com.mkoffice.model.ClienteEntity;

public class ClientePorMontanteCompradoDTO {
	
	private BigDecimal somaValorCompra;
	private ClienteEntity cliente;
	
	public BigDecimal getSomaValorCompra() {
		return somaValorCompra;
	}
	public void setSomaValorCompra(BigDecimal somaValorCompra) {
		this.somaValorCompra = somaValorCompra;
	}
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	
}
