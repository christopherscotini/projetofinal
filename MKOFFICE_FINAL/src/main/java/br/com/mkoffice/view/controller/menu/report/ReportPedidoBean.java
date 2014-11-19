package br.com.mkoffice.view.controller.menu.report;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.reports.cliente.ReportRetencaoClientesDTO;
import br.com.mkoffice.dto.reports.pedido.ReportPedidoConsolidadoDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class ReportPedidoBean extends AbstractModelBean{

	private final String TELA_REPORT_PEDIDOS = "/content/m-relatorios/pedidos/reportConsolidadoPedidos.xhtml";
	private DataFilter dataFiltro;
	private ReportPedidoConsolidadoDTO relatorio;
	
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		
		return TELA_REPORT_PEDIDOS;
	}

	@Override
	public void limparCamposFiltro() {
		dataFiltro = new DataFilter();
		relatorio = null;
	}

	@Override
	public String pesquisarFiltro() {
		try{
			relatorio = reportBO.getReportPedidoConsolidado(dataFiltro, getLoginBean().getUsuario().getId());
		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
		}
		
		return null;
	}

	
	public DataFilter getDataFiltro() {
		return dataFiltro;
	}

	public void setDataFiltro(DataFilter dataFiltro) {
		this.dataFiltro = dataFiltro;
	}

	public ReportPedidoConsolidadoDTO getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(ReportPedidoConsolidadoDTO relatorio) {
		this.relatorio = relatorio;
	}

}
