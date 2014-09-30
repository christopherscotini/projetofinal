package br.com.mkoffice.view.controller.menu.admin.formapagamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.admin.FormaPagamentoEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;


@ManagedBean
@SessionScoped
public class FormaPagamentoBean extends AbstractModelBean implements Serializable{

	private static final long serialVersionUID = -9078557861547962528L;

	private final String TELA_MANTER_FORMA_PGTO= "/content/m-admin/formapagto/manterFormaPagto.xhtml";
	
	private List<FormaPagamentoEntity> formaPagtos;
	private FormaPagamentoEntity formaPagto;
	private List<FormaPagamentoEntity> filteredValue;
	private FormaPagamentoEntity formaPagtoSelecionada;
	private String formaPagtoFiltro;
	private boolean cadastrar;

	private String tituloTelaIncAltFormaPagto;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		carregarListaFormaPagto();
		formaPagto = new FormaPagamentoEntity();
		
		return TELA_MANTER_FORMA_PGTO;
	}

	@Override
	public void limparCamposFiltro() {
		formaPagtoFiltro = null;
		filteredValue = new ArrayList<FormaPagamentoEntity>();
	}

	@Override
	public String pesquisarFiltro() {
		formaPagtos = formaPagamentoBO.buscarEntidadesPorFiltro(formaPagtoFiltro);
		return TELA_MANTER_FORMA_PGTO;
	}

	public void navegarIncluirFluxo(){
		tituloTelaIncAltFormaPagto = "Incluir Forma de pagamento";
		formaPagto = new FormaPagamentoEntity();
		cadastrar = true;
	}
	
	public void navegarAlterarFluxo(){
		tituloTelaIncAltFormaPagto = "Alterar Forma de pagamento";
		formaPagto = formaPagtoSelecionada;
		cadastrar = false;
	}
	
	public void executeSave(){
		try{
			if (cadastrar) {
				formaPagamentoBO.adicionarEntidade(formaPagto);
				FacesUtils.addMessageInclusaoSucesso();
			}else{
				formaPagamentoBO.editarEntidade(formaPagto);
				FacesUtils.addMessageAlteracaoSucesso();
			}
			iniciarTela();

		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
		}
	}

	private void carregarListaFormaPagto(){
		formaPagtos = formaPagamentoBO.listarTodos();
	}
	
	public void limparSelecaoTabela(){
		formaPagtoSelecionada = null;
	}

	public List<FormaPagamentoEntity> getFormaPagtos() {
		return formaPagtos;
	}

	public void setFormaPagtos(List<FormaPagamentoEntity> formaPagtos) {
		this.formaPagtos = formaPagtos;
	}

	public FormaPagamentoEntity getFormaPagto() {
		return formaPagto;
	}

	public void setFormaPagto(FormaPagamentoEntity formaPagto) {
		this.formaPagto = formaPagto;
	}

	public FormaPagamentoEntity getFormaPagtoSelecionada() {
		return formaPagtoSelecionada;
	}

	public void setFormaPagtoSelecionada(FormaPagamentoEntity formaPagtoSelecionada) {
		this.formaPagtoSelecionada = formaPagtoSelecionada;
	}

	public String getFormaPagtoFiltro() {
		return formaPagtoFiltro;
	}

	public void setFormaPagtoFiltro(String formaPagtoFiltro) {
		this.formaPagtoFiltro = formaPagtoFiltro;
	}

	public boolean isCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public String getTituloTelaIncAltFormaPagto() {
		return tituloTelaIncAltFormaPagto;
	}

	public void setTituloTelaIncAltFormaPagto(String tituloTelaIncAltFormaPagto) {
		this.tituloTelaIncAltFormaPagto = tituloTelaIncAltFormaPagto;
	}

	public String getTELA_MANTER_FORMA_PGTO() {
		return TELA_MANTER_FORMA_PGTO;
	}

	public List<FormaPagamentoEntity> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<FormaPagamentoEntity> filteredValue) {
		this.filteredValue = filteredValue;
	}

}
