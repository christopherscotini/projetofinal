package br.com.mkoffice.view.controller.menu.report;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.model.estoque.EstoqueEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;


@ManagedBean
@SessionScoped
public class MovimentacaoEstoqueBean extends AbstractModelBean{

	private final String TELA_MOVIMENTACAO_ESTOQUE = "/content/m-relatorios/estoque/movimentacaoEstoque.xhtml";
	private List<EstoqueEntity>report;
	private Date dataInicioFiltro;
	private Date dataFimFiltro;
	private Long fluxoEstoqueFiltro;
	
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		
		return TELA_MOVIMENTACAO_ESTOQUE;
	}

	@Override
	public void limparCamposFiltro() {
		dataInicioFiltro = null;
		dataFimFiltro = null;
		report = null;
	}

	@Override
	public String pesquisarFiltro() {
		report = reportBO.getReportMovimentacaoEstoque(dataInicioFiltro, dataFimFiltro, fluxoEstoqueFiltro, getLoginBean().getUsuario().getId());
		return TELA_MOVIMENTACAO_ESTOQUE;
	}

	
	
	public List<EstoqueEntity> getReport() {
		return report;
	}

	public void setReport(List<EstoqueEntity> report) {
		this.report = report;
	}

	public Date getDataInicioFiltro() {
		return dataInicioFiltro;
	}

	public void setDataInicioFiltro(Date dataInicioFiltro) {
		this.dataInicioFiltro = dataInicioFiltro;
	}

	public Date getDataFimFiltro() {
		return dataFimFiltro;
	}

	public void setDataFimFiltro(Date dataFimFiltro) {
		this.dataFimFiltro = dataFimFiltro;
	}

	public Long getFluxoEstoqueFiltro() {
		return fluxoEstoqueFiltro;
	}

	public void setFluxoEstoqueFiltro(Long fluxoEstoqueFiltro) {
		this.fluxoEstoqueFiltro = fluxoEstoqueFiltro;
	}

}
