package br.com.mkoffice.dto.dashboard;

import java.math.BigDecimal;
import java.util.Date;

public class BalancoDTO {

	
	private BigDecimal valorBalanco;
	private String mes;
	
	
	public BalancoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public BalancoDTO(BigDecimal valorBalanco, String mes) {
		super();
		this.valorBalanco = valorBalanco;
		this.mes = mes;
	}


	public BigDecimal getValorBalanco() {
		return valorBalanco;
	}
	public void setValorBalanco(BigDecimal valorBalanco) {
		this.valorBalanco = valorBalanco;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}

}
