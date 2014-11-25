package br.com.mkoffice.dto.reports;

import java.math.BigDecimal;
import java.util.List;

import br.com.mkoffice.dto.CatalogoDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.model.produto.CatalogoEntity;

public class DashboardCaixaDTO {
	
	private BigDecimal valorFaturamento;
	private BigDecimal valorGasto;
	private ReportPromocaoClientePorVolumeVendaDTO clienteMaisComprou;
	private List<ProdutosMaisMenosVendidosDTO> produtosMaisVendidos;
	private List<ProdutosMaisMenosVendidosDTO> produtosMenosVendidos;
	
	public BigDecimal getValorFaturamento() {
		return valorFaturamento;
	}
	public void setValorFaturamento(BigDecimal valorFaturamento) {
		this.valorFaturamento = valorFaturamento;
	}
	public List<ProdutosMaisMenosVendidosDTO> getProdutosMaisVendidos() {
		return produtosMaisVendidos;
	}
	public void setProdutosMaisVendidos(List<ProdutosMaisMenosVendidosDTO> produtosMaisVendidos) {
		this.produtosMaisVendidos = produtosMaisVendidos;
	}
	public BigDecimal getValorGasto() {
		return valorGasto;
	}
	public void setValorGasto(BigDecimal valorGasto) {
		this.valorGasto = valorGasto;
	}
	public List<ProdutosMaisMenosVendidosDTO> getProdutosMenosVendidos() {
		return produtosMenosVendidos;
	}
	public void setProdutosMenosVendidos(List<ProdutosMaisMenosVendidosDTO> produtosMenosVendidos) {
		this.produtosMenosVendidos = produtosMenosVendidos;
	}
	public ReportPromocaoClientePorVolumeVendaDTO getClienteMaisComprou() {
		return clienteMaisComprou;
	}
	public void setClienteMaisComprou(ReportPromocaoClientePorVolumeVendaDTO clienteMaisComprou) {
		this.clienteMaisComprou = clienteMaisComprou;
	}
	

}

