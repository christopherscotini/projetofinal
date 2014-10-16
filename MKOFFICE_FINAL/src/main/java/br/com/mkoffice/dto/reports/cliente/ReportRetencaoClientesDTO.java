package br.com.mkoffice.dto.reports.cliente;

import java.util.Date;

import br.com.mkoffice.model.ClienteEntity;

public class ReportRetencaoClientesDTO {

	
	private ClienteEntity cliente;
	private Date dataUltimaCompra;
	
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	public Date getDataUltimaCompra() {
		return dataUltimaCompra;
	}
	public void setDataUltimaCompra(Date dataUltimaCompra) {
		this.dataUltimaCompra = dataUltimaCompra;
	}
	
}
