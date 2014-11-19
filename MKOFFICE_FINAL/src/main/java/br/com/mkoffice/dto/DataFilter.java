package br.com.mkoffice.dto;

import java.util.Date;

public class DataFilter {

	private Date dataInicio;
	private Date dataFinal;
	
	public DataFilter() {
		dataInicio = null;
		dataFinal = null;
	}
	
	
	public DataFilter(Date dataInicio, Date dataFinal) {
		super();
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
	}


	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	
	
	
}
