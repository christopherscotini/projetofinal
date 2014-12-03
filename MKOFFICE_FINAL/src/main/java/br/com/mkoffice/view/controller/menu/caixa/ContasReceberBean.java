package br.com.mkoffice.view.controller.menu.caixa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.ParcelasDTO;
import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.view.controller.AbstractModelBean;

@ManagedBean
@SessionScoped
public class ContasReceberBean extends AbstractModelBean{

	private final String TELA_CONTAS_RECEBER = "/content/m-caixa/contasReceber.xhtml";
	
	private Long situacaoPagamentoFiltro;
	private DataFilter dataFiltro;
	private List<ParcelasDTO>parcelas;
	private ParcelasDTO parcelaSelecionadaPagamento;
	
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
		isDesabilitaPagamentoParcela();  
		
		return null;
	}

	public void onclickBtnPagamentoParcela(ParcelasDTO p){
		parcelaSelecionadaPagamento = p;
		parcelaSelecionadaPagamento.setDtPagamento(new Date());
	}
	
	public void btnCancelarModal(){
		for (int i = 0; i < parcelas.size(); i++) {
			if (!parcelas.get(i).isDesabilitaPagamentoParcela()) {
				parcelas.get(i).setDtPagamento(null);
			}
		}
	}
	
	public void efetuarPagamentoParcela(){
		parcelaBO.efetuarPagamento(parcelaSelecionadaPagamento);
		isDesabilitaPagamentoParcela();
	}

	private void isDesabilitaPagamentoParcela(){
		for (int i = 0; i < parcelas.size(); i++) {
			if(parcelas.get(i).isQuitado()){
				parcelas.get(i).setDesabilitaPagamentoParcela(true);
			}else{
				if( i > 0){
					if(parcelas.get(i-1).isQuitado()){
						if(i == (parcelas.size()-1)){
							parcelas.get(i).setDesabilitaPagamentoParcela(false);
						}else{
							if(!parcelas.get(i-1).isDesabilitaPagamentoParcela()){
								parcelas.get(i).setDesabilitaPagamentoParcela(true);
							}else{
								parcelas.get(i).setDesabilitaPagamentoParcela(false);
							}
						}
					}else{
						parcelas.get(i).setDesabilitaPagamentoParcela(true);
					}
				}else{
					parcelas.get(i).setDesabilitaPagamentoParcela(true);
				}
			}
		}
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

	public List<ParcelasDTO> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelasDTO> parcelas) {
		this.parcelas = parcelas;
	}

	public DataFilter getDataFiltro() {
		return dataFiltro;
	}

	public void setDataFiltro(DataFilter dataFiltro) {
		this.dataFiltro = dataFiltro;
	}

	public ParcelasDTO getParcelaSelecionadaPagamento() {
		return parcelaSelecionadaPagamento;
	}

	public void setParcelaSelecionadaPagamento(
			ParcelasDTO parcelaSelecionadaPagamento) {
		this.parcelaSelecionadaPagamento = parcelaSelecionadaPagamento;
	}

}
