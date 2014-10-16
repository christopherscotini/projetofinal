package br.com.mkoffice.view.controller.menu.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class PromocaoClientePorVolumeCompraBean extends AbstractModelBean{

	private final String TELA_RELATORIO_PROMOCAO_CLIENTE_VOLUME_DE_COMPRA = "/content/m-relatorios/cliente/promocaoClientePorVolumeCompra.xhtml";
	private List<Integer>comboAnosFiltro;
	private Integer comboAnosFiltroSelecionado;
	private BigDecimal valorCorteFiltro;
	private List<ReportPromocaoClientePorVolumeVendaDTO> relatorio;
	private List<ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO> relatorioDetalhado;
	
	private PieChartModel graficoPieMontanteAnualPorCliente;
	private LineChartModel graficoLineMontanteAnualPorCliente;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		
		return TELA_RELATORIO_PROMOCAO_CLIENTE_VOLUME_DE_COMPRA;
	}

	@Override
	public void limparCamposFiltro() {
		graficoPieMontanteAnualPorCliente = new PieChartModel();
		graficoLineMontanteAnualPorCliente = new LineChartModel();
		valorCorteFiltro = BigDecimal.ZERO;
		comboAnosFiltroSelecionado = null;
		relatorio = null;
	}

	@Override
	public String pesquisarFiltro() {
		try{
			relatorio = reportBO.getReportPromocaoClientePorVolume(valorCorteFiltro, comboAnosFiltroSelecionado);
			relatorioDetalhado = reportBO.getReportPromocaoClientePorVolumeDetalhado(valorCorteFiltro, comboAnosFiltroSelecionado);
			createGraficoPieMontanteAnualPorCliente();
			createGraficoLine();
		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
		}
		return null;
	}

	
	
	private void createGraficoPieMontanteAnualPorCliente() {
		graficoPieMontanteAnualPorCliente = new PieChartModel();
        for (int i = 0; i < relatorio.size(); i++) {
        	graficoPieMontanteAnualPorCliente.set(relatorio.get(i).getCliente().getDadosPessoa().getNome(), relatorio.get(i).getValorTotalVendas());
		}
        
        graficoPieMontanteAnualPorCliente.setTitle(getMsgs("promocaoclienteporvolumecompra.lbl.titulografico.pie"));
        graficoPieMontanteAnualPorCliente.setLegendPosition("e");
        graficoPieMontanteAnualPorCliente.setFill(false);
        graficoPieMontanteAnualPorCliente.setShowDataLabels(true);
        graficoPieMontanteAnualPorCliente.setDiameter(100);
        
    }
	
	private void createGraficoLine(){
		graficoLineMontanteAnualPorCliente = initCategoryModel();
		graficoLineMontanteAnualPorCliente.setTitle(getMsgs("promocaoclienteporvolumecompra.lbl.titulografico.line"));
		graficoLineMontanteAnualPorCliente.setLegendPosition("w");
		graficoLineMontanteAnualPorCliente.setAnimate(true);
		graficoLineMontanteAnualPorCliente.setShowPointLabels(true);
		graficoLineMontanteAnualPorCliente.getAxes().put(AxisType.X, new CategoryAxis(getMsgs("promocaoclienteporvolumecompra.lbl.titulografico.line.eixoX")));
        Axis yAxis = graficoLineMontanteAnualPorCliente.getAxis(AxisType.Y);
        yAxis = graficoLineMontanteAnualPorCliente.getAxis(AxisType.Y);
        yAxis.setLabel(getMsgs("promocaoclienteporvolumecompra.lbl.titulografico.line.eixoY"));
        yAxis.setMin(0);
	}
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        for (int i = 0; i < relatorioDetalhado.size(); i++) {
        	ChartSeries data = new ChartSeries();
        	data.setLabel(relatorioDetalhado.get(i).getCliente().getDadosPessoa().getNome());
        	data.set("JAN", relatorioDetalhado.get(i).getValorMontanteJan());
        	data.set("FEV", relatorioDetalhado.get(i).getValorMontanteFev());
        	data.set("MAR", relatorioDetalhado.get(i).getValorMontanteMar());
        	data.set("ABR", relatorioDetalhado.get(i).getValorMontanteAbr());
        	data.set("MAI", relatorioDetalhado.get(i).getValorMontanteMai());
        	data.set("JUN", relatorioDetalhado.get(i).getValorMontanteJun());
        	data.set("JUL", relatorioDetalhado.get(i).getValorMontanteJul());
        	data.set("AGO", relatorioDetalhado.get(i).getValorMontanteAgo());
        	data.set("SET", relatorioDetalhado.get(i).getValorMontanteSet());
        	data.set("OUT", relatorioDetalhado.get(i).getValorMontanteOut());
        	data.set("NOV", relatorioDetalhado.get(i).getValorMontanteNov());
        	data.set("DEZ", relatorioDetalhado.get(i).getValorMontanteDez());
			
        	model.addSeries(data);
		}
        
        
        return model;
    }
	
//	=============================== MÉTODOS GET E SET =============================== 
	

	public List<Integer> getComboAnosFiltro() {
		comboAnosFiltro = new ArrayList<>();
		Calendar dateInicial = Calendar.getInstance();
		dateInicial.set(Calendar.YEAR, 2012);
		dateInicial.set(Calendar.MONTH, 0);
		dateInicial.set(Calendar.DAY_OF_MONTH, 1);
		dateInicial.set(Calendar.HOUR_OF_DAY, 0);
		Calendar dateFinal = Calendar.getInstance();
		
		while (dateInicial.before(dateFinal)) {
			comboAnosFiltro.add(dateInicial.get(Calendar.YEAR));
			dateInicial.set(Calendar.YEAR, comboAnosFiltro.get(comboAnosFiltro.size()-1)+1);
		}	
		
		return comboAnosFiltro;
	}

	public PieChartModel getGraficoPieMontanteAnualPorCliente() {
		return graficoPieMontanteAnualPorCliente;
	}

	public void setGraficoPieMontanteAnualPorCliente(
			PieChartModel graficoPieMontanteAnualPorCliente) {
		this.graficoPieMontanteAnualPorCliente = graficoPieMontanteAnualPorCliente;
	}

	public void setComboAnosFiltro(List<Integer> comboAnosFiltro) {
		this.comboAnosFiltro = comboAnosFiltro;
	}

	public BigDecimal getValorCorteFiltro() {
		return valorCorteFiltro;
	}

	public void setValorCorteFiltro(BigDecimal valorCorteFiltro) {
		this.valorCorteFiltro = valorCorteFiltro;
	}

	public List<ReportPromocaoClientePorVolumeVendaDTO> getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(List<ReportPromocaoClientePorVolumeVendaDTO> relatorio) {
		this.relatorio = relatorio;
	}

	public Integer getComboAnosFiltroSelecionado() {
		return comboAnosFiltroSelecionado;
	}

	public void setComboAnosFiltroSelecionado(Integer comboAnosFiltroSelecionado) {
		this.comboAnosFiltroSelecionado = comboAnosFiltroSelecionado;
	}

	public LineChartModel getGraficoLineMontanteAnualPorCliente() {
		return graficoLineMontanteAnualPorCliente;
	}

	public void setGraficoLineMontanteAnualPorCliente(
			LineChartModel graficoLineMontanteAnualPorCliente) {
		this.graficoLineMontanteAnualPorCliente = graficoLineMontanteAnualPorCliente;
	}

	public List<ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO> getRelatorioDetalhado() {
		return relatorioDetalhado;
	}

	public void setRelatorioDetalhado(List<ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO> relatorioDetalhado) {
		this.relatorioDetalhado = relatorioDetalhado;
	}
	
	

}
