package br.com.mkoffice.dto.reports.cliente;

import java.math.BigDecimal;
import java.util.Date;

import br.com.mkoffice.model.ClienteEntity;

public class ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO {

	
	private ClienteEntity cliente;
	private BigDecimal valorMontanteJan;
	private BigDecimal valorMontanteFev;
	private BigDecimal valorMontanteMar;
	private BigDecimal valorMontanteAbr;
	private BigDecimal valorMontanteMai;
	private BigDecimal valorMontanteJun;
	private BigDecimal valorMontanteJul;
	private BigDecimal valorMontanteAgo;
	private BigDecimal valorMontanteSet;
	private BigDecimal valorMontanteOut;
	private BigDecimal valorMontanteNov;
	private BigDecimal valorMontanteDez;
	
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	public BigDecimal getValorMontanteJan() {
		return valorMontanteJan;
	}
	public void setValorMontanteJan(BigDecimal valorMontanteJan) {
		this.valorMontanteJan = valorMontanteJan;
	}
	public BigDecimal getValorMontanteFev() {
		return valorMontanteFev;
	}
	public void setValorMontanteFev(BigDecimal valorMontanteFev) {
		this.valorMontanteFev = valorMontanteFev;
	}
	public BigDecimal getValorMontanteMar() {
		return valorMontanteMar;
	}
	public void setValorMontanteMar(BigDecimal valorMontanteMar) {
		this.valorMontanteMar = valorMontanteMar;
	}
	public BigDecimal getValorMontanteAbr() {
		return valorMontanteAbr;
	}
	public void setValorMontanteAbr(BigDecimal valorMontanteAbr) {
		this.valorMontanteAbr = valorMontanteAbr;
	}
	public BigDecimal getValorMontanteMai() {
		return valorMontanteMai;
	}
	public void setValorMontanteMai(BigDecimal valorMontanteMai) {
		this.valorMontanteMai = valorMontanteMai;
	}
	public BigDecimal getValorMontanteJun() {
		return valorMontanteJun;
	}
	public void setValorMontanteJun(BigDecimal valorMontanteJun) {
		this.valorMontanteJun = valorMontanteJun;
	}
	public BigDecimal getValorMontanteJul() {
		return valorMontanteJul;
	}
	public void setValorMontanteJul(BigDecimal valorMontanteJul) {
		this.valorMontanteJul = valorMontanteJul;
	}
	public BigDecimal getValorMontanteAgo() {
		return valorMontanteAgo;
	}
	public void setValorMontanteAgo(BigDecimal valorMontanteAgo) {
		this.valorMontanteAgo = valorMontanteAgo;
	}
	public BigDecimal getValorMontanteSet() {
		return valorMontanteSet;
	}
	public void setValorMontanteSet(BigDecimal valorMontanteSet) {
		this.valorMontanteSet = valorMontanteSet;
	}
	public BigDecimal getValorMontanteOut() {
		return valorMontanteOut;
	}
	public void setValorMontanteOut(BigDecimal valorMontanteOut) {
		this.valorMontanteOut = valorMontanteOut;
	}
	public BigDecimal getValorMontanteNov() {
		return valorMontanteNov;
	}
	public void setValorMontanteNov(BigDecimal valorMontanteNov) {
		this.valorMontanteNov = valorMontanteNov;
	}
	public BigDecimal getValorMontanteDez() {
		return valorMontanteDez;
	}
	public void setValorMontanteDez(BigDecimal valorMontanteDez) {
		this.valorMontanteDez = valorMontanteDez;
	}
	
}
