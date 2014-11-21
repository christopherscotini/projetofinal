package br.com.mkoffice.view.controller.menu.report;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.model.estoque.EstoqueEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;


@ManagedBean
@SessionScoped
public class MovimentacaoEstoqueBean extends AbstractModelBean{

	private final String TELA_MOVIMENTACAO_ESTOQUE = "/content/m-relatorios/estoque/movimentacaoEstoque.xhtml";
	private List<EstoqueEntity>report;
	private DataFilter dataFiltro;
	private Long fluxoEstoqueFiltro;
	
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		fluxoEstoqueFiltro = getCboFluxoEstoque().get(0).getId();
		pesquisarFiltro();
		return TELA_MOVIMENTACAO_ESTOQUE;
	}

	@Override
	public void limparCamposFiltro() {
		dataFiltro = new DataFilter(true);
		report = null;
	}

	@Override
	public String pesquisarFiltro() {
		report = reportBO.getReportMovimentacaoEstoque(dataFiltro, fluxoEstoqueFiltro, getLoginBean().getUsuario().getId());
		return TELA_MOVIMENTACAO_ESTOQUE;
	}

	
	
	public List<EstoqueEntity> getReport() {
		return report;
	}

	public void setReport(List<EstoqueEntity> report) {
		this.report = report;
	}

	public Long getFluxoEstoqueFiltro() {
		return fluxoEstoqueFiltro;
	}

	public void setFluxoEstoqueFiltro(Long fluxoEstoqueFiltro) {
		this.fluxoEstoqueFiltro = fluxoEstoqueFiltro;
	}

	public DataFilter getDataFiltro() {
		return dataFiltro;
	}

	public void setDataFiltro(DataFilter dataFiltro) {
		this.dataFiltro = dataFiltro;
	}

}
