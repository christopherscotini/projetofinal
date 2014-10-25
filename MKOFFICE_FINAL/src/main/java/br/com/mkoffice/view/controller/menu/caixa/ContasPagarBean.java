package br.com.mkoffice.view.controller.menu.caixa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.business.exception.NoDataFoundException;
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
	private Date dataInicioFiltro;
	private Date dataFinalFiltro;
	private List<ParcelasEntity>parcelas;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		pesquisarFiltro();
		return TELA_CONTAS_PAGAR;
	}

	@Override
	public void limparCamposFiltro() {
		situacaoPagamentoFiltro = null;
		dataInicioFiltro = null;
		dataFinalFiltro = null;
	}

	@Override
	public String pesquisarFiltro() {
		
		try{
			parcelas = contasPagarBO.filtrar(dataInicioFiltro, dataFinalFiltro, situacaoPagamentoFiltro);
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
