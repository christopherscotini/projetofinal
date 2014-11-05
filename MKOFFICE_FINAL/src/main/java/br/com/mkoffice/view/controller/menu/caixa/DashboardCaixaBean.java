package br.com.mkoffice.view.controller.menu.caixa;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.reports.DashboardCaixaDTO;
import br.com.mkoffice.view.controller.AbstractModelBean;

@ManagedBean
@SessionScoped
public class DashboardCaixaBean extends AbstractModelBean{

	private final String TELA_DASHBOARD_CAIXA = "/content/m-caixa/dashboardCaixa.xhtml";
	private DashboardCaixaDTO dashboardCaixa;
	
	
	@Override
	public String iniciarTela() {
		dashboardCaixa = reportBO.getReportVisaoGeralCaixa(new Date());
		return TELA_DASHBOARD_CAIXA;
	}

	@Override
	public void limparCamposFiltro() {
		
	}

	@Override
	public String pesquisarFiltro() {
		return null;
	}

	public DashboardCaixaDTO getDashboardCaixa() {
		return dashboardCaixa;
	}

	public void setDashboardCaixa(DashboardCaixaDTO dashboardCaixa) {
		this.dashboardCaixa = dashboardCaixa;
	}

}
