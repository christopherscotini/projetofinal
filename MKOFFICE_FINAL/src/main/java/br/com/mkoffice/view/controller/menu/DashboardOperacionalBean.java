package br.com.mkoffice.view.controller.menu;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.reports.DashboardCaixaDTO;
import br.com.mkoffice.view.controller.AbstractModelBean;

@ManagedBean
@SessionScoped
public class DashboardOperacionalBean extends AbstractModelBean{

	private final String TELA_DASHBOARD_CAIXA = "/content/dashboardOperacional.xhtml";
	private DashboardCaixaDTO dashboardCaixa;
	
	
	public DashboardOperacionalBean() {
		
	}
	
	@Override
	public String iniciarTela() {
		dashboardCaixa = reportBO.getDashboardOperacional(new Date(), getLoginBean().getUsuario().getId());
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
