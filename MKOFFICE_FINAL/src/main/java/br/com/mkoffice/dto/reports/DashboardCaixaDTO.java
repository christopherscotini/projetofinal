package br.com.mkoffice.dto.reports;

import java.math.BigDecimal;
import java.util.List;

import br.com.mkoffice.dto.CatalogoDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.model.produto.CatalogoEntity;

public class DashboardCaixaDTO {
	
	private BigDecimal valorFaturamento;
	private Integer numVendas;
	private Integer numProdutosVendidos;
	private List<ProdutosMaisMenosVendidosDTO> produtosMaisVendidos;
	private ReportPromocaoClientePorVolumeVendaDTO clienteMaisComprou;
	private BigDecimal valorGasto;
	private Integer numPedidos;
	private Integer numProdutosComprados;
	private List<ProdutosMaisMenosVendidosDTO> produtosMenosVendidos;
	
	public BigDecimal getValorFaturamento() {
		return valorFaturamento;
	}
	public void setValorFaturamento(BigDecimal valorFaturamento) {
		this.valorFaturamento = valorFaturamento;
	}
	public Integer getNumVendas() {
		return numVendas;
	}
	public void setNumVendas(Integer numVendas) {
		this.numVendas = numVendas;
	}
	public Integer getNumProdutosVendidos() {
		return numProdutosVendidos;
	}
	public void setNumProdutosVendidos(Integer numProdutosVendidos) {
		this.numProdutosVendidos = numProdutosVendidos;
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
	public Integer getNumPedidos() {
		return numPedidos;
	}
	public void setNumPedidos(Integer numPedidos) {
		this.numPedidos = numPedidos;
	}
	public Integer getNumProdutosComprados() {
		return numProdutosComprados;
	}
	public void setNumProdutosComprados(Integer numProdutosComprados) {
		this.numProdutosComprados = numProdutosComprados;
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

