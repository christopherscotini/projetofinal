package br.com.mkoffice.view.controller.menu.report;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.reports.cliente.ReportRetencaoClientesDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class RetencaoClienteBean extends AbstractModelBean{

	private final String TELA_RETENCAO_CLIENTES = "/content/m-relatorios/cliente/retencaoClientes.xhtml";
	private Date dataCorteFiltro;
	private List<ReportRetencaoClientesDTO>relatorio;
	
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		
		return TELA_RETENCAO_CLIENTES;
	}

	@Override
	public void limparCamposFiltro() {
		dataCorteFiltro = null;
		relatorio = null;
	}

	@Override
	public String pesquisarFiltro() {
		try{
			relatorio = reportBO.getReportRetencaoClientes(dataCorteFiltro);
		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
		}
		
		return null;
	}

	public Date getDataCorteFiltro() {
		return dataCorteFiltro;
	}

	public void setDataCorteFiltro(Date dataCorteFiltro) {
		this.dataCorteFiltro = dataCorteFiltro;
	}

	public List<ReportRetencaoClientesDTO> getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(List<ReportRetencaoClientesDTO> relatorio) {
		this.relatorio = relatorio;
	}

	
}
