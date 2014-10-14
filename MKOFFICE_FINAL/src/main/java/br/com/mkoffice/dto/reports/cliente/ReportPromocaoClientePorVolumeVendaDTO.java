package br.com.mkoffice.dto.reports.cliente;

import java.math.BigDecimal;
import java.util.Date;

import br.com.mkoffice.model.ClienteEntity;

public class ReportPromocaoClientePorVolumeVendaDTO {

	
	private ClienteEntity cliente;
	private Integer quantidadeVendas;
	private BigDecimal valorTotalVendas;
	private Date dataUltimaVenda;
	
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	public Integer getQuantidadeVendas() {
		return quantidadeVendas;
	}
	public void setQuantidadeVendas(Integer quantidadeVendas) {
		this.quantidadeVendas = quantidadeVendas;
	}
	public BigDecimal getValorTotalVendas() {
		return valorTotalVendas;
	}
	public void setValorTotalVendas(BigDecimal valorTotalVendas) {
		this.valorTotalVendas = valorTotalVendas;
	}
	public Date getDataUltimaVenda() {
		return dataUltimaVenda;
	}
	public void setDataUltimaVenda(Date dataUltimaVenda) {
		this.dataUltimaVenda = dataUltimaVenda;
	}
	
	
}
