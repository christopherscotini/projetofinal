package br.com.mkoffice.view.controller.menu.caixa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;

@ManagedBean
@SessionScoped
public class ContasReceberBean extends AbstractModelBean{

	private final String TELA_CONTAS_RECEBER = "/content/m-caixa/contasReceber.xhtml";
	
	private Long situacaoPagamentoFiltro;
	private Date dataInicioFiltro;
	private Date dataFinalFiltro;
	private List<ParcelasEntity>parcelas;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		
		return TELA_CONTAS_RECEBER;
	}

	@Override
	public void limparCamposFiltro() {
		situacaoPagamentoFiltro = null;
		dataInicioFiltro = null;
		dataFinalFiltro = null;
	}

	@Override
	public String pesquisarFiltro() {
		parcelas = contasReceberBO.filtrar(dataInicioFiltro, dataFinalFiltro, situacaoPagamentoFiltro);
		return null;
	}

	public List<SituacaoEntity> getComboSituacaoPagamento() {
		SituacaoEntity todos = new SituacaoEntity();
		todos.setId(99L);
		todos.setDescSituacao(getMsgs("contasreceber.lbl.todos"));;
		List<SituacaoEntity> ret = situacaoBO.listarTodos();
		ret.add(todos);
		return ret;
	}

	public Long getSituacaoPagamentoFiltro() {
		return situacaoPagamentoFiltro;
	}

	public void setSituacaoPagamentoFiltro(Long situacaoPagamentoFiltro) {
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

	public List<ParcelasEntity> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelasEntity> parcelas) {
		this.parcelas = parcelas;
	}

}
