package br.com.mkoffice.dto.dashboard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import br.com.mkoffice.dto.dashboard.ClientePorMontanteCompradoDTO;
import br.com.mkoffice.model.venda.VendaEntity;
import br.com.mkoffice.utils.DecimalUtils;
import br.com.mkoffice.view.converter.DecimalConverter;
import br.com.mkoffice.view.converter.PercentConverter;

public class DashboardOperacionalDTO {
	
	private final BigDecimal _100 = new BigDecimal("100.00");
	private BigDecimal valorFaturamentoMesAnterior;
	private BalancoDTO balanco;
	private List<BalancoDTO>historicoBalanco;
	private BigDecimal valorFaturamentoMesAtual;
	private BigDecimal valorGasto;
	private BigDecimal valorLucroMesAnterior;
	private BigDecimal valorLucroMesAtual;
	private List<VendaEntity>listaUltimasVendas;
	private List<ClientePorMontanteCompradoDTO>rankingClientes;
	private BigDecimal percentualDiferencaFaturamentoMesAtualMesAnterior;
	private BigDecimal percentualDiferencaLucroMesAtualMesAnterior;
	
	
	public BigDecimal getValorFaturamentoMesAnterior() {
		if(valorFaturamentoMesAnterior == null){
			return BigDecimal.ZERO;
		}
		return valorFaturamentoMesAnterior;
	}
	public void setValorFaturamentoMesAnterior(
			BigDecimal valorFaturamentoMesAnterior) {
		this.valorFaturamentoMesAnterior = valorFaturamentoMesAnterior;
	}
	public BigDecimal getValorFaturamentoMesAtual() {
		if(valorFaturamentoMesAtual == null){
			return BigDecimal.ZERO;
		}
		return valorFaturamentoMesAtual;
	}
	public void setValorFaturamentoMesAtual(BigDecimal valorFaturamentoMesAtual) {
		this.valorFaturamentoMesAtual = valorFaturamentoMesAtual;
	}
	public BigDecimal getValorGasto() {
		if(valorGasto == null){
			return BigDecimal.ZERO;
		}
		return valorGasto;
	}
	public void setValorGasto(BigDecimal valorGasto) {
		this.valorGasto = valorGasto;
	}
	public List<VendaEntity> getListaUltimasVendas() {
		return listaUltimasVendas;
	}
	public void setListaUltimasVendas(List<VendaEntity> listaUltimasVendas) {
		this.listaUltimasVendas = listaUltimasVendas;
	}
	public List<ClientePorMontanteCompradoDTO> getRankingClientes() {
		return rankingClientes;
	}
	public void setRankingClientes(List<ClientePorMontanteCompradoDTO> rankingClientes) {
		this.rankingClientes = rankingClientes;
	}
	public String getPercentualDiferencaFaturamentoMesAtualMesAnterior() {
		return DecimalUtils.formatPercent(percentualDiferencaFaturamentoMesAtualMesAnterior);
	}
	public void setPercentualDiferencaFaturamentoMesAtualMesAnterior(BigDecimal percentualDiferencaFaturamentoMesAtualMesAnterior) {
		this.percentualDiferencaFaturamentoMesAtualMesAnterior = percentualDiferencaFaturamentoMesAtualMesAnterior;
	}
	public boolean isPercentualDiferencaFaturamentoMesAtualMesAnteriorPositivo(){
		return percentualDiferencaFaturamentoMesAtualMesAnterior.compareTo(BigDecimal.ZERO) >= 0;
	}
	
	public void calcularPercentualDiferencaFaturamentoMesAtualMesAnterior(){
		if(valorFaturamentoMesAnterior.compareTo(BigDecimal.ZERO) == 0 ){
			setPercentualDiferencaFaturamentoMesAtualMesAnterior(valorFaturamentoMesAtual.multiply(_100));
		}else{
			setPercentualDiferencaFaturamentoMesAtualMesAnterior(valorFaturamentoMesAtual.multiply(_100).divide(valorFaturamentoMesAnterior, RoundingMode.HALF_EVEN));
		}
	}
	public BigDecimal getValorLucroMesAnterior() {
		if(valorLucroMesAnterior == null){
			return BigDecimal.ZERO;
		}
		return valorLucroMesAnterior;
	}
	public void setValorLucroMesAnterior(BigDecimal valorLucroMesAnterior) {
		this.valorLucroMesAnterior = valorLucroMesAnterior;
	}
	public BigDecimal getValorLucroMesAtual() {
		if(valorLucroMesAtual == null){
			return BigDecimal.ZERO;
		}
		return valorLucroMesAtual;
	}
	public void setValorLucroMesAtual(BigDecimal valorLucroMesAtual) {
		this.valorLucroMesAtual = valorLucroMesAtual;
	}
	public String getPercentualDiferencaLucroMesAtualMesAnterior() {
		return DecimalUtils.formatPercent(percentualDiferencaLucroMesAtualMesAnterior);
	}
	public void setPercentualDiferencaLucroMesAtualMesAnterior(
			BigDecimal percentualDiferencaLucroMesAtualMesAnterior) {
		this.percentualDiferencaLucroMesAtualMesAnterior = percentualDiferencaLucroMesAtualMesAnterior;
	}
	
	public boolean isPercentualDiferencaLucroMesAtualMesAnteriorPositivo(){
		return percentualDiferencaLucroMesAtualMesAnterior.compareTo(BigDecimal.ZERO) >= 0;
	}
	
	public void calcularPercentualDiferencaLucroMesAtualMesAnterior(){
		if(valorFaturamentoMesAnterior.compareTo(BigDecimal.ZERO) == 0 ){
			setPercentualDiferencaLucroMesAtualMesAnterior(valorLucroMesAtual.multiply(_100));
		}else{
			setPercentualDiferencaLucroMesAtualMesAnterior(valorLucroMesAtual.multiply(_100).divide(valorLucroMesAnterior, RoundingMode.HALF_EVEN));
		}
	}
	public List<BalancoDTO> getHistoricoBalanco() {
		return historicoBalanco;
	}
	public void setHistoricoBalanco(List<BalancoDTO> historicoBalanco) {
		this.historicoBalanco = historicoBalanco;
	}
	public BalancoDTO getBalanco() {
		return balanco;
	}
	public void setBalanco(BalancoDTO balanco) {
		this.balanco = balanco;
	}
	

}

