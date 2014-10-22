package br.com.mkoffice.view.controller.menu.caixa;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;

@ManagedBean
@SessionScoped
public class ContasReceberBean extends AbstractModelBean{

	private final String TELA_CONTAS_RECEBER = "/content/m-caixa/contasReceber.xhtml";
	
	private SituacaoEntity situacaoPagamentoFiltro;
	private Date dataInicioFiltro;
	private Date dataFinalFiltro;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		
		return TELA_CONTAS_RECEBER;
	}

	@Override
	public void limparCamposFiltro() {
		situacaoPagamentoFiltro = null;
	}

	@Override
	public String pesquisarFiltro() {
		
		return null;
	}

	public List<SituacaoEntity> getComboSituacaoPagamento() {
		return situacaoBO.listarTodos();
	}

	public SituacaoEntity getSituacaoPagamentoFiltro() {
		return situacaoPagamentoFiltro;
	}

	public void setSituacaoPagamentoFiltro(SituacaoEntity situacaoPagamentoFiltro) {
		this.situacaoPagamentoFiltro = situacaoPagamentoFiltro;
	}

	public Date getDataInicioFiltro() {
		return dataInicioFiltro;
	}

	public void setDataInicioFiltro(Date dataInicioFiltro) {
		this.dataInicioFiltro = dataInicioFiltro;
	}

	public Date getDataFinalFiltro() {
		return dataFinalFiltro;
	}

	public void setDataFinalFiltro(Date dataFinalFiltro) {
		this.dataFinalFiltro = dataFinalFiltro;
	}

}
