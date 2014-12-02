package br.com.mkoffice.view.controller.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.MeterGaugeChartModel;

import br.com.mkoffice.dto.dashboard.DashboardOperacionalDTO;
import br.com.mkoffice.model.ParametrosDashboardEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;

@ManagedBean
@SessionScoped
public class DashboardOperacionalBean extends AbstractModelBean{

	private final String TELA_DASHBOARD_CAIXA = "/index.xhtml";
	private DashboardOperacionalDTO dashboardOperacional;
	private MeterGaugeChartModel graficoFaturamento;
	private MeterGaugeChartModel graficoLucroDesejado;
	private BarChartModel graficoRankingClientes;
	private LineChartModel graficoBalanco;
	private ParametrosDashboardEntity parametros;
	
	public DashboardOperacionalBean() {
		
	}
	
	@Override
	public String iniciarTela() {
		dashboardOperacional = dashboardOperacionalBO.gerarDashBoard(loginBean.getUsuario().getId());
		parametros = parametroDashboardBO.carregarParametros(loginBean.getUsuario().getId());
		montarGraficosCaixa();
		montarGraficosCliente();
		
		return TELA_DASHBOARD_CAIXA;
	}

	@Override
	public void limparCamposFiltro() {
		
	}

	@Override
	public String pesquisarFiltro() {
		return null;
	}

	private void montarGraficosCaixa(){
		montarGraficoLucroEstimado();
		montarGraficoFaturamento();
		montarGraficoBalanco();
	}
	
	private void montarGraficoLucroEstimado(){
		
		final Double d3 = parametros.getValorLucroDesejado().doubleValue();
		final Double d2 = d3*2;
		final Double d1 = d2+d3;
		
        List<Number> intervals = new ArrayList<Number>(){{add(d3);add(d2);add(d1);}};

        graficoLucroDesejado = new MeterGaugeChartModel(dashboardOperacional.getValorLucroMesAtual(), intervals);
        graficoLucroDesejado.setTitle("Lucro");
        graficoLucroDesejado.setSeriesColors("cc6666,E7E658,66cc66");
        graficoLucroDesejado.setGaugeLabel("");
        graficoLucroDesejado.setGaugeLabelPosition("bottom");
        graficoLucroDesejado.setLabelHeightAdjust(110);
        graficoLucroDesejado.setIntervalOuterRadius(90);
		
	}
	
	private void montarGraficoFaturamento(){
		final Double d3 = parametros.getValorMetaFaturamento().doubleValue();
		final Double d2 = d3*2;
		final Double d1 = d2+d3;
		
		List<Number> intervals = new ArrayList<Number>(){{add(d3);add(d2);add(d1);}};
		
		graficoFaturamento = new MeterGaugeChartModel(dashboardOperacional.getValorFaturamentoMesAtual(), intervals);
		graficoFaturamento.setTitle("Faturamento");
		graficoFaturamento.setSeriesColors("cc6666,E7E658,66cc66");
		graficoFaturamento.setGaugeLabel("");
		graficoFaturamento.setGaugeLabelPosition("bottom");
		graficoFaturamento.setLabelHeightAdjust(110);
		graficoFaturamento.setIntervalOuterRadius(90);
		
	}
	
	private void montarGraficoBalanco(){
		graficoBalanco = new LineChartModel();
		BigDecimal valorMin = BigDecimal.ZERO;
		BigDecimal valorMax = BigDecimal.ZERO;
		ChartSeries balanco = new ChartSeries();
		balanco.setLabel("Balanço");
		for (int i = 0; i < dashboardOperacional.getHistoricoBalanco().size(); i++) {
			balanco.set(dashboardOperacional.getHistoricoBalanco().get(i).getMes(), dashboardOperacional.getHistoricoBalanco().get(i).getValorBalanco());
			if(dashboardOperacional.getHistoricoBalanco().get(i).getValorBalanco().compareTo(valorMax) > 0){
	    		valorMax = dashboardOperacional.getHistoricoBalanco().get(i).getValorBalanco();
	    	}
			if(dashboardOperacional.getHistoricoBalanco().get(i).getValorBalanco().compareTo(valorMax) < 0){
				valorMin = dashboardOperacional.getHistoricoBalanco().get(i).getValorBalanco();
			}
		}
		
		graficoBalanco.addSeries(balanco);
		
		graficoBalanco.setTitle("Timeline Balanço");
		graficoBalanco.setLegendPosition("e");
		graficoBalanco.getAxes().put(AxisType.X, new CategoryAxis(getMsgs("promocaoclienteporvolumecompra.lbl.titulografico.line.eixoX")));
		Axis yAxis = graficoBalanco.getAxis(AxisType.Y);
		yAxis.setLabel(getMsgs("promocaoclienteporvolumecompra.lbl.titulografico.line.eixoY"));
		valorMax = valorMax.add(valorMax.multiply(new BigDecimal("0.20")));
		valorMin = valorMin.add(valorMin.multiply(new BigDecimal("0.20")));
		if(valorMin.compareTo(BigDecimal.ZERO) >= 0){
			yAxis.setMin(BigDecimal.ZERO.setScale(2));
		}else{
			BigDecimal valorMinPos = valorMin.multiply(new BigDecimal("-1"));
			if(valorMinPos.compareTo(valorMax)<=0){
				valorMin = valorMax.multiply(new BigDecimal("-1"));
			}else{
				valorMax = valorMin.multiply(new BigDecimal("-1"));
			}
			yAxis.setMin(valorMin.setScale(2));
		}
		yAxis.setMax(valorMax.setScale(2));
		
	}
	
	
	private void montarGraficosCliente(){
		montarGraficoRankingClientes();
	}
	

	private void montarGraficoRankingClientes(){
		graficoRankingClientes = new BarChartModel();
		
		ChartSeries clientes = new ChartSeries();
		clientes.setLabel("Clientes");
		
		BigDecimal valorMax = BigDecimal.ZERO;
		if(dashboardOperacional.getRankingClientes().size() <= 0){
			clientes.set("", 0);
			valorMax = new BigDecimal("100");
		}else{
	        for (int i = 0; i < dashboardOperacional.getRankingClientes().size(); i++) {
	        	clientes.set(dashboardOperacional.getRankingClientes().get(i).getCliente().getDadosPessoa().getNome().substring(0, 10), dashboardOperacional.getRankingClientes().get(i).getSomaValorCompra());
	        	if(dashboardOperacional.getRankingClientes().get(i).getSomaValorCompra().compareTo(valorMax) > 0){
	        		valorMax = dashboardOperacional.getRankingClientes().get(i).getSomaValorCompra();
	        	}
	        }
		}
	 
        graficoRankingClientes.addSeries(clientes);
		graficoRankingClientes.setTitle("Ranking das Clientes que mais compraram");
		graficoRankingClientes.setLegendPosition("ne");
         
        Axis xAxis = graficoRankingClientes.getAxis(AxisType.X);
        xAxis.setLabel("Clientes");
         
        Axis yAxis = graficoRankingClientes.getAxis(AxisType.Y);
        yAxis.setLabel("(R$) Montante");
        yAxis.setMin(BigDecimal.ZERO);
        valorMax = valorMax.add(valorMax.multiply(new BigDecimal("0.20")));
        yAxis.setMax(valorMax);
	}
	

	public DashboardOperacionalDTO getDashboardOperacional() {
		return dashboardOperacional;
	}

	public void setDashboardOperacional(DashboardOperacionalDTO dashboardOperacional) {
		this.dashboardOperacional = dashboardOperacional;
	}

	public MeterGaugeChartModel getGraficoFaturamento() {
		return graficoFaturamento;
	}

	public void setGraficoFaturamento(MeterGaugeChartModel graficoFaturamento) {
		this.graficoFaturamento = graficoFaturamento;
	}

	public MeterGaugeChartModel getGraficoLucroDesejado() {
		return graficoLucroDesejado;
	}

	public void setGraficoLucroDesejado(MeterGaugeChartModel graficoLucroDesejado) {
		this.graficoLucroDesejado = graficoLucroDesejado;
	}

	public BarChartModel getGraficoRankingClientes() {
		return graficoRankingClientes;
	}

	public void setGraficoRankingClientes(BarChartModel graficoRankingClientes) {
		this.graficoRankingClientes = graficoRankingClientes;
	}

	public LineChartModel getGraficoBalanco() {
		return graficoBalanco;
	}

	public void setGraficoBalanco(LineChartModel graficoBalanco) {
		this.graficoBalanco = graficoBalanco;
	}


}
