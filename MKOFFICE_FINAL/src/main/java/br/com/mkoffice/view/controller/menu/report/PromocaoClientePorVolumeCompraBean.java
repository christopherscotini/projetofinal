package br.com.mkoffice.view.controller.menu.report;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	
	private PieChartModel graficoPieMontanteAnualPorCliente;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		
		return TELA_RELATORIO_PROMOCAO_CLIENTE_VOLUME_DE_COMPRA;
	}

	@Override
	public void limparCamposFiltro() {
		graficoPieMontanteAnualPorCliente = new PieChartModel();
		valorCorteFiltro = BigDecimal.ZERO;
		comboAnosFiltroSelecionado = null;
		relatorio = null;
	}

	@Override
	public String pesquisarFiltro() {
		try{
			relatorio = reportBO.getReportPromocaoClientePorVolume(valorCorteFiltro, null);
			createGraficoPieMontanteAnualPorCliente();
		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
		}
		return null;
	}

	
	
	private void createGraficoPieMontanteAnualPorCliente() {
        
        for (int i = 0; i < relatorio.size(); i++) {
        	graficoPieMontanteAnualPorCliente.set(relatorio.get(i).getCliente().getDadosPessoa().getNome(), relatorio.get(i).getValorTotalVendas());
		}
        
        graficoPieMontanteAnualPorCliente.setTitle(getMsgs("generico.lbl.grafico"));
        graficoPieMontanteAnualPorCliente.setLegendPosition("e");
        graficoPieMontanteAnualPorCliente.setFill(false);
        graficoPieMontanteAnualPorCliente.setShowDataLabels(true);
        graficoPieMontanteAnualPorCliente.setDiameter(100);
        
    }
	
	private LineChartModel lineModel2;
	private void createGraficoLine(){
		lineModel2 = initCategoryModel();
        lineModel2.setTitle("Category Chart");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);
	}
	
	private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
         
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Category Chart");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private CartesianChartModel initLinearModel() {
        CartesianChartModel model = new CartesianChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
     
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
	
//	=============================== MÉTODOS GET E SET =============================== 
	

	public List<Integer> getComboAnosFiltro() {
		comboAnosFiltro = new ArrayList<>();
		comboAnosFiltro.add(2012);
		comboAnosFiltro.add(2013);
		comboAnosFiltro.add(2014);
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

}
