package br.com.mkoffice.dto.reports.cliente;

import java.math.BigDecimal;
import java.util.Date;

import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class ReportPromocaoClientePorVolumeVendaDTO {

	
	private ClienteEntity cliente;
	private Integer quantidadeVendas;
	private BigDecimal valorTotalVendas;
	private Date dataUltimaVenda;
	private String numCelularString;
	
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
	public String getNumCelularString() {
		if(cliente.getDadosPessoa() != null){
			if ((numCelularString == null || numCelularString.equals("")) && !(cliente.getDadosPessoa().getNumCelular() == null)) {
				return MkmtsUtil.formatarNumTelefoneCelularString(cliente.getDadosPessoa()
						.getNumCelular());
			}
		}
		return numCelularString;
	}
	
	
}
