package br.com.mkoffice.dto.dashboard;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import br.com.mkoffice.utils.MkmtsUtil;

public class BalancoDTO {

	
	private BigDecimal valorBalanco;
	private Calendar data;
	
	
	public BalancoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public BalancoDTO(BigDecimal valorBalanco, Calendar data) {
		super();
		this.valorBalanco = valorBalanco;
		this.data = data;
	}


	public BigDecimal getValorBalanco() {
		return valorBalanco;
	}
	public void setValorBalanco(BigDecimal valorBalanco) {
		this.valorBalanco = valorBalanco;
	}
	public String getMes() {
		return MkmtsUtil.converterDataString(data.getTime(), "yyyy-MM");
	}


	public Calendar getData() {
		return data;
	}


	public void setData(Calendar data) {
		this.data = data;
	}

}
