package br.com.mkoffice.view.controller.menu.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import br.com.mkoffice.dto.reports.estoque.MovimentacaoEstoqueReportTemplateDTO;
import br.com.mkoffice.dto.reports.estoque.ProdutosMovimentacaoEstoqueDTO;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.utils.MkmtsUtil;
import br.com.mkoffice.utils.ReportFactory;
import br.com.mkoffice.utils.constants.TiposRelatorio;
import br.com.mkoffice.view.constants.ExtensionFileEnum;
import br.com.mkoffice.view.controller.AbstractModelBean;



@ManagedBean
@SessionScoped
public class ReportEstoqueBean extends AbstractModelBean implements Serializable{

	private static final long serialVersionUID = -842476797447264933L;

	private final String RELATORIO_MOVIMENTACAO_ESTOQUE = "/content/m-relatorios/estoque/reportMovimentacaoEstoque";
	private final String NAME_RELATORIO_MOVIMENTACAO_ESTOQUE = "Relat�rio de Movimenta��o de Estoque";
	
	private List<FluxoEstoqueEntity> listaFluxoEstoque;
	private FluxoEstoqueEntity tipoFluxoEstoqueFiltro;
	private Date dataInicial;
	private Date dataFinal;
	private  String tipoExportacao;
	private  BigDecimal valorTotalMovimentado;
	
	private List<ProdutosMovimentacaoEstoqueDTO> listaMovimentacaoHistorico;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		listaFluxoEstoque = fluxoEstoqueBO.listarTodos();
		
		return RELATORIO_MOVIMENTACAO_ESTOQUE;
	}

	@Override
	public void limparCamposFiltro() {
		tipoFluxoEstoqueFiltro = null;
		dataInicial = null;
		dataFinal = null;
		listaMovimentacaoHistorico = null;
		
	}

	@Override
	public String pesquisarFiltro() {
		listaMovimentacaoHistorico = estoqueHistoricoBO.gerarRelatorioMovimentacaoEstoque(tipoFluxoEstoqueFiltro, dataInicial, dataFinal, getLoginBean().getUsuario().getId());
		valorTotalMovimentado = BigDecimal.ZERO;
		for (int i = 0; i < listaMovimentacaoHistorico.size(); i++) {
			valorTotalMovimentado = valorTotalMovimentado.add(listaMovimentacaoHistorico.get(i).getValorMovimentado());
		}
		return "";
	}

	public StreamedContent generateReport(){
		List<MovimentacaoEstoqueReportTemplateDTO> beanCollection = new ArrayList<MovimentacaoEstoqueReportTemplateDTO>();
		MovimentacaoEstoqueReportTemplateDTO reportParamsTemplate = new MovimentacaoEstoqueReportTemplateDTO();
		reportParamsTemplate.setTipoFluxoFiltro(tipoFluxoEstoqueFiltro.getDescFluxoEstoque());
		reportParamsTemplate.setDtInicio(MkmtsUtil.converterDataString(dataInicial, "dd/MM/yyyy"));
		reportParamsTemplate.setDtFim(MkmtsUtil.converterDataString(dataFinal, "dd/MM/yyyy"));
		reportParamsTemplate.setListaProdutosFiltrados(listaMovimentacaoHistorico);
		reportParamsTemplate.setQtdeTotalMovimentado(getListaMovimentacaoHistorico().size());
		beanCollection.add(reportParamsTemplate);
		
		ImageIcon logo = new ImageIcon(FacesContext.getCurrentInstance().getExternalContext().getRealPath("images/logo_unidade2.jpg"));
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("tipoFluxoFiltro", reportParamsTemplate.getTipoFluxoFiltro());
		params.put("dataGeracao", MkmtsUtil.converterDataString(new Date(), "dd/MM/yyyy"));
		params.put("dtInicio", reportParamsTemplate.getDtInicio());
		params.put("dtFim", reportParamsTemplate.getDtFim());
		params.put("logo", logo.getImage());
		
		if (tipoExportacao.equalsIgnoreCase(TiposRelatorio.PDF.toString())) {
			ReportFactory rf = new ReportFactory("estoque/movimentacao-estoque-report.jasper", params, TiposRelatorio.PDF, beanCollection);
			return new DefaultStreamedContent(rf.getReportStream(), "", NAME_RELATORIO_MOVIMENTACAO_ESTOQUE + ExtensionFileEnum.PDF.getLabel());
		}
		
		if (tipoExportacao.equalsIgnoreCase(TiposRelatorio.XLS.toString())) {
			ReportFactory rf = new ReportFactory("estoque/movimentacao-estoque-report.jasper", params,TiposRelatorio.XLS, beanCollection);
			return new DefaultStreamedContent(rf.getReportStream(), "", NAME_RELATORIO_MOVIMENTACAO_ESTOQUE + ExtensionFileEnum.XLS.getLabel());
		}
		
		return null;
	}
	
	public StreamedContent generateReportPDF() {
		tipoExportacao = TiposRelatorio.PDF.toString();
		return generateReport();
	}
	
	public StreamedContent generateReportXLS() {
		tipoExportacao = TiposRelatorio.XLS.toString();
		return generateReport();
	}
	
	public FluxoEstoqueEntity getTipoFluxoEstoqueFiltro() {
		return tipoFluxoEstoqueFiltro;
	}

	public void setTipoFluxoEstoqueFiltro(
			FluxoEstoqueEntity tipoFluxoEstoqueFiltro) {
		this.tipoFluxoEstoqueFiltro = tipoFluxoEstoqueFiltro;
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

	public List<ProdutosMovimentacaoEstoqueDTO> getListaMovimentacaoHistorico() {
		return listaMovimentacaoHistorico;
	}

	public void setListaMovimentacaoHistorico(
			List<ProdutosMovimentacaoEstoqueDTO> listaMovimentacaoHistorico) {
		this.listaMovimentacaoHistorico = listaMovimentacaoHistorico;
	}

	public String getTipoExportacao() {
		return tipoExportacao;
	}

	public void setTipoExportacao(String tipoExportacao) {
		this.tipoExportacao = tipoExportacao;
	}

	public BigDecimal getValorTotalMovimentado() {
		return valorTotalMovimentado;
	}

	public void setValorTotalMovimentado(BigDecimal valorTotalMovimentado) {
		this.valorTotalMovimentado = valorTotalMovimentado;
	}

	public List<FluxoEstoqueEntity> getListaFluxoEstoque() {
		return listaFluxoEstoque;
	}

	public void setListaFluxoEstoque(List<FluxoEstoqueEntity> listaFluxoEstoque) {
		this.listaFluxoEstoque = listaFluxoEstoque;
	}

}
