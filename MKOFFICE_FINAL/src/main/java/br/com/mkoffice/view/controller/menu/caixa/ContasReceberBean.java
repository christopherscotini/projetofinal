package br.com.mkoffice.view.controller.menu.caixa;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;

@ManagedBean
@SessionScoped
public class ContasReceberBean extends AbstractModelBean{

	private final String TELA_CONTAS_RECEBER = "/content/m-caixa/contasReceber.xhtml";
	
	private Long situacaoPagamentoFiltro;
	private DataFilter dataFiltro;
	private List<ParcelasEntity>parcelas;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		pesquisarFiltro();
		return TELA_CONTAS_RECEBER;
	}

	@Override
	public void limparCamposFiltro() {
		situacaoPagamentoFiltro = getComboSituacaoPagamento().get(0).getId();
		dataFiltro = new DataFilter(true);
	}

	@Override
	public String pesquisarFiltro() {
		parcelas = contasReceberBO.filtrar(dataFiltro, situacaoPagamentoFiltro, loginBean.getUsuario().getId());
		return null;
	}

	public List<SituacaoEntity> getComboSituacaoPagamento() {
		SituacaoEntity todos = new SituacaoEntity();
		todos.setId(99L);
		todos.setDescSituacao(getMsgs("contaspagar.lbl.todos"));;
		List<SituacaoEntity> ret = new ArrayList<SituacaoEntity>();
		ret.add(todos);
		ret.addAll(situacaoBO.listarTodos());
		return ret;
	}

	public Long getSituacaoPagamentoFiltro() {
		return situacaoPagamentoFiltro;
	}

	public void setSituacaoPagamentoFiltro(Long situacaoPagamentoFiltro) {
		this.situacaoPagamentoFiltro = situacaoPagamentoFiltro;
	}

	public List<ParcelasEntity> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelasEntity> parcelas) {
		this.parcelas = parcelas;
	}

	public DataFilter getDataFiltro() {
		return dataFiltro;
	}

	public void setDataFiltro(DataFilter dataFiltro) {
		this.dataFiltro = dataFiltro;
	}

}
