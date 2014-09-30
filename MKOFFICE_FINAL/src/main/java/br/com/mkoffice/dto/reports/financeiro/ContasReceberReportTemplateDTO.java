package br.com.mkoffice.dto.reports.financeiro;

import java.math.BigDecimal;
import java.util.List;


public class ContasReceberReportTemplateDTO {

	
	private String dtInicio;
	private String dtFim;
	private BigDecimal valorTotalMovimentado;
	private boolean onlyQuitados;
	
	private List<ContasReceberReportDTO>listaParcelas;
	
	public String getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}
	public String getDtFim() {
		return dtFim;
	}
	public void setDtFim(String dtFim) {
		this.dtFim = dtFim;
	}

	public BigDecimal getValorTotalMovimentado() {
		valorTotalMovimentado = BigDecimal.ZERO;
		for (int i = 0; i < getListaParcelas().size(); i++) {
			if(onlyQuitados){
				valorTotalMovimentado = valorTotalMovimentado.add(getListaParcelas().get(i).getValorPago());
			}else{
				valorTotalMovimentado = valorTotalMovimentado.add(getListaParcelas().get(i).getValorParcela());
			}
		}
		return valorTotalMovimentado;
	}
	
	public void setValorTotalMovimentado(BigDecimal valorTotalMovimentado) {
		this.valorTotalMovimentado = valorTotalMovimentado;
	}
	public List<ContasReceberReportDTO> getListaParcelas() {
		return listaParcelas;
	}
	public void setListaParcelas(List<ContasReceberReportDTO> listaParcelas) {
		this.listaParcelas = listaParcelas;
	}
	public boolean isOnlyQuitados() {
		return onlyQuitados;
	}
	public void setOnlyQuitados(boolean onlyQuitados) {
		this.onlyQuitados = onlyQuitados;
	}
	
}
