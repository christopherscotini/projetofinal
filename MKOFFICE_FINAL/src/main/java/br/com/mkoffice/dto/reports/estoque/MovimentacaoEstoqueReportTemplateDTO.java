package br.com.mkoffice.dto.reports.estoque;

import java.math.BigDecimal;
import java.util.List;


public class MovimentacaoEstoqueReportTemplateDTO {

	
	private String tipoFluxoFiltro;
	private String dtInicio;
	private String dtFim;
	private Integer qtdeTotalMovimentado;
	private BigDecimal valorTotalMovimentado;
	
	private List<ProdutosMovimentacaoEstoqueDTO>listaProdutosFiltrados;
	
	public String getTipoFluxoFiltro() {
		return tipoFluxoFiltro;
	}
	public void setTipoFluxoFiltro(String tipoFluxoFiltro) {
		this.tipoFluxoFiltro = tipoFluxoFiltro;
	}
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
	public List<ProdutosMovimentacaoEstoqueDTO> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}
	public void setListaProdutosFiltrados(
			List<ProdutosMovimentacaoEstoqueDTO> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}
	public Integer getQtdeTotalMovimentado() {
		qtdeTotalMovimentado = 0;
		for (int i = 0; i < getListaProdutosFiltrados().size(); i++) {
			qtdeTotalMovimentado += getListaProdutosFiltrados().get(i).getQuantidadeMovimentado();
		}
		
		return qtdeTotalMovimentado;
	}
	public void setQtdeTotalMovimentado(Integer qtdeTotalMovimentado) {
		this.qtdeTotalMovimentado = qtdeTotalMovimentado;
	}
	public BigDecimal getValorTotalMovimentado() {
		valorTotalMovimentado = BigDecimal.ZERO;
		for (int i = 0; i < getListaProdutosFiltrados().size(); i++) {
			valorTotalMovimentado = valorTotalMovimentado.add(getListaProdutosFiltrados().get(i).getValorMovimentado());
		}
		return valorTotalMovimentado;
	}
	public void setValorTotalMovimentado(BigDecimal valorTotalMovimentado) {
		this.valorTotalMovimentado = valorTotalMovimentado;
	}
	
}
