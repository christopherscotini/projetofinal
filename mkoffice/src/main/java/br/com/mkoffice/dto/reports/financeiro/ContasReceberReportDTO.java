package br.com.mkoffice.dto.reports.financeiro;

import java.math.BigDecimal;
import java.util.Date;

public class ContasReceberReportDTO {
	
	private Long codVenda;
	private String cliente;
	private String descSituacao;
	private BigDecimal valorParcela;
	private BigDecimal valorPago;
	private Date dataPagto;

	public ContasReceberReportDTO() {

	}

	public ContasReceberReportDTO(Long codVenda, String cliente,
			String descSituacao, BigDecimal valorParcela, BigDecimal valorPago,
			Date dataPagto) {
		super();
		this.codVenda = codVenda;
		this.cliente = cliente;
		this.descSituacao = descSituacao;
		this.valorParcela = valorParcela;
		this.valorPago = valorPago;
		this.dataPagto = dataPagto;
	}

	public Long getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(Long codVenda) {
		this.codVenda = codVenda;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDescSituacao() {
		return descSituacao;
	}

	public void setDescSituacao(String descSituacao) {
		this.descSituacao = descSituacao;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Date getDataPagto() {
		return dataPagto;
	}

	public void setDataPagto(Date dataPagto) {
		this.dataPagto = dataPagto;
	}
	
}
