package br.com.mkoffice.view.controller.menu.caixa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.business.exception.NoDataFoundException;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class ContasPagarBean extends AbstractModelBean{

	private final String TELA_CONTAS_PAGAR = "/content/m-caixa/contasPagar.xhtml";
	
	private Long situacaoPagamentoFiltro;
	private DataFilter dataFiltro;
	private List<ParcelasEntity>parcelas;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		pesquisarFiltro();
		return TELA_CONTAS_PAGAR;
	}

	@Override
	public void limparCamposFiltro() {
		situacaoPagamentoFiltro = getComboSituacaoPagamento().get(0).getId();
		dataFiltro = new DataFilter(true);
	}

	@Override
	public String pesquisarFiltro() {
		
		try{
			parcelas = contasPagarBO.filtrar(dataFiltro, situacaoPagamentoFiltro);
		}catch(NoDataFoundException ndf){
			parcelas = null;
			FacesUtils.addErrorMessage(ndf.getMessage());
		}catch (BusinessException b) {
			FacesUtils.addErrorMessage(b.getMessage());
		}
		
		return TELA_CONTAS_PAGAR;
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
