package br.com.mkoffice.dto.reports.caixa;

import java.math.BigDecimal;
import java.util.List;

import br.com.mkoffice.dto.dashboard.BalancoDTO;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class ReportLucroVendasDTO {

	private List<ParcelasEntity> gastos;
	private List<ParcelasEntity> faturamento;
	private BigDecimal valorLucroPeriodo;
	
	
	public List<ParcelasEntity> getGastos() {
		return gastos;
	}
	public void setGastos(List<ParcelasEntity> gastos) {
		this.gastos = gastos;
	}
	public List<ParcelasEntity> getFaturamento() {
		return faturamento;
	}
	public void setFaturamento(List<ParcelasEntity> faturamento) {
		this.faturamento = faturamento;
	}
	public BigDecimal getValorLucroPeriodo() {
		return MkmtsUtil.verificaBigDecimalNulo(valorLucroPeriodo);
	}
	public void setValorLucroPeriodo(BigDecimal valorLucroPeriodo) {
		this.valorLucroPeriodo = valorLucroPeriodo;
	}
	
	
}
