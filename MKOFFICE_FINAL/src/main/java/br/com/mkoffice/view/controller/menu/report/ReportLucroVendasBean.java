package br.com.mkoffice.view.controller.menu.report;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.reports.caixa.ReportLucroVendasDTO;
import br.com.mkoffice.view.controller.AbstractModelBean;


@ManagedBean
@SessionScoped
public class ReportLucroVendasBean extends AbstractModelBean{

	private final String TELA_RELATORIO_LUCRO = "/content/m-relatorios/caixa/reportLucroDetalhado.xhtml";
	private DataFilter dataFiltro;
	private ReportLucroVendasDTO report;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		pesquisarFiltro();
		return TELA_RELATORIO_LUCRO;
	}

	@Override
	public void limparCamposFiltro() {
		dataFiltro = new DataFilter(true);
		report = null;
	}

	@Override
	public String pesquisarFiltro() {
		
		report = reportBO.getReportLucroDetalhado(dataFiltro, loginBean.getUsuario().getId());
		
		return TELA_RELATORIO_LUCRO;
	}

	public DataFilter getDataFiltro() {
		return dataFiltro;
	}

	public void setDataFiltro(DataFilter dataFiltro) {
		this.dataFiltro = dataFiltro;
	}

	public ReportLucroVendasDTO getReport() {
		return report;
	}

	public void setReport(ReportLucroVendasDTO report) {
		this.report = report;
	}

	
}
