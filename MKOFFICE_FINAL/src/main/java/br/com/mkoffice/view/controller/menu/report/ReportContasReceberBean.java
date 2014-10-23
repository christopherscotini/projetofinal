package br.com.mkoffice.view.controller.menu.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.ImageIcon;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.mkoffice.dto.reports.financeiro.ContasReceberReportDTO;
import br.com.mkoffice.dto.reports.financeiro.ContasReceberReportTemplateDTO;
import br.com.mkoffice.utils.MkmtsUtil;
import br.com.mkoffice.utils.ReportFactory;
import br.com.mkoffice.utils.constants.TiposRelatorio;
import br.com.mkoffice.view.constants.ExtensionFileEnum;
import br.com.mkoffice.view.controller.AbstractModelBean;

@ManagedBean
@SessionScoped
public class ReportContasReceberBean extends AbstractModelBean {

	private final String TELA_REPORT_CONTAS_RECEBER = "/content/m-relatorios/finaceiro/reportContasReceber.xhtml";
	private final String NAME_RELATORIO_CONTAS_RECEBER = "Relatório de Contas à  Receber";
	private final String PATH_REPORT_CONTAS_RECEBER = "financeiro/contas_a_receber.jasper";
	
	private TiposRelatorio tipoRelatorio;
	private List<ContasReceberReportDTO>parcelasFiltradas;
	private Date dataInicial;
	private Date dataFinal;
	

	@Override
	public String iniciarTela() {
		resetFiltros();
		parcelasFiltradas = new ArrayList<ContasReceberReportDTO>();
		tipoRelatorio = null;
		
		return TELA_REPORT_CONTAS_RECEBER;
	}

	@Override
	public void limparCamposFiltro() {
		resetFiltros();
	}

	@Override
	public String pesquisarFiltro() {
		
		return "";
	}

	public StreamedContent generateReport(){
		List<ContasReceberReportTemplateDTO> beanCollection = new ArrayList<ContasReceberReportTemplateDTO>();
		ContasReceberReportTemplateDTO reportParamsTemplate = new ContasReceberReportTemplateDTO();
		reportParamsTemplate.setDtInicio(MkmtsUtil.converterDataString(dataInicial, "dd/MM/yyyy"));
		reportParamsTemplate.setDtFim(MkmtsUtil.converterDataString(dataFinal, "dd/MM/yyyy"));
		reportParamsTemplate.setListaParcelas(parcelasFiltradas);
		beanCollection.add(reportParamsTemplate);
		
		ImageIcon logo = new ImageIcon(FacesContext.getCurrentInstance().getExternalContext().getRealPath("images/alerta.jpg"));
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("LOGO_REPORT", logo.getImage());
		params.put("DATE_GENERATE_REPORT", new Date());
		
		if (tipoRelatorio.equals(TiposRelatorio.PDF)) {
			ReportFactory rf = new ReportFactory(PATH_REPORT_CONTAS_RECEBER, params, tipoRelatorio, beanCollection);
			return new DefaultStreamedContent(rf.getReportStream(), "", NAME_RELATORIO_CONTAS_RECEBER + ExtensionFileEnum.PDF.getLabel());
		}
		
		return null;
	}
	
	public StreamedContent generateReportPDF() {
		tipoRelatorio = TiposRelatorio.PDF;
		return generateReport();
	}

	public String generateReportXLS() {
		tipoRelatorio = TiposRelatorio.XLS;
		return "";
	}

	private void resetFiltros() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
		dataInicial = cal.getTime();
		dataFinal = new Date();
	}

	
	public TiposRelatorio getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(TiposRelatorio tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public List<ContasReceberReportDTO> getParcelasFiltradas() {
		return parcelasFiltradas;
	}

	public void setParcelasFiltradas(List<ContasReceberReportDTO> parcelasFiltradas) {
		this.parcelasFiltradas = parcelasFiltradas;
	}
	
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	
	
}
