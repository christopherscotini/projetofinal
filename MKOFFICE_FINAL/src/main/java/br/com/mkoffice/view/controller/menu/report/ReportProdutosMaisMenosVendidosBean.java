package br.com.mkoffice.view.controller.menu.report;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.reports.estoque.ReportProdutosMaisMenosVendidosDTO;
import br.com.mkoffice.view.controller.AbstractModelBean;


@ManagedBean
@SessionScoped
public class ReportProdutosMaisMenosVendidosBean extends AbstractModelBean implements Serializable{

	private static final long serialVersionUID = 623337381388840139L;
	private final String TELA_PRODUTOS_MAIS_MENOS_VENDIDOS = "/content/m-relatorios/estoque/produtosMaisMenosVendidos.xhtml";
	
	private ReportProdutosMaisMenosVendidosDTO report;
	private Integer anoFiltro;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		return TELA_PRODUTOS_MAIS_MENOS_VENDIDOS;
	}

	@Override
	public void limparCamposFiltro() {
		anoFiltro = null;
		report = null;
	}

	@Override
	public String pesquisarFiltro() {
		report = reportBO.getReportProdutoMaisMenosVendidos(anoFiltro, getLoginBean().getUsuario().getId());
		return null;
	}
	
	
	
	

	public ReportProdutosMaisMenosVendidosDTO getReport() {
		return report;
	}

	public void setReport(ReportProdutosMaisMenosVendidosDTO report) {
		this.report = report;
	}

	public Integer getAnoFiltro() {
		return anoFiltro;
	}

	public void setAnoFiltro(Integer anoFiltro) {
		this.anoFiltro = anoFiltro;
	}

	
}
