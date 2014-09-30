package br.com.mkoffice.view.controller.menu.admin.tipofluxoestoque;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;


@ManagedBean
@SessionScoped
public class FluxoEstoqueBean extends AbstractModelBean implements Serializable{

	private static final long serialVersionUID = -9078557861547962528L;

	private final String TELA_MANTER_FLUXO = "/content/m-admin/fluxoestoque/manterFluxoEstoque.xhtml";
	
	private List<FluxoEstoqueEntity>fluxos;
	private FluxoEstoqueEntity fluxo;
	private FluxoEstoqueEntity fluxoSelecionada;
	private List<FluxoEstoqueEntity> filteredValue;
	private String fluxoFiltro;
	private boolean cadastrar;

	private String tituloTelaIncAltFluxo;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		carregarListaFluxos();
		fluxo = new FluxoEstoqueEntity();
		
		return TELA_MANTER_FLUXO;
	}

	@Override
	public void limparCamposFiltro() {
		fluxoFiltro = null;
		filteredValue = new ArrayList<FluxoEstoqueEntity>();
	}

	@Override
	public String pesquisarFiltro() {
		fluxos = fluxoEstoqueBO.buscarEntidadesPorFiltro(fluxoFiltro);
		
		return TELA_MANTER_FLUXO;
	}

	public void navegarIncluirFluxo(){
		tituloTelaIncAltFluxo = "Incluir Fluxo de estoque";
		fluxo = new FluxoEstoqueEntity();
		cadastrar = true;
	}
	
	public void navegarAlterarFluxo(){
		tituloTelaIncAltFluxo = "Alterar Fluxo de estoque";
		fluxo = fluxoSelecionada;
		cadastrar = false;
	}
	
	public void executeSave(){
		try{
			if (cadastrar) {
				fluxoEstoqueBO.adicionarEntidade(fluxo);
				FacesUtils.addMessageInclusaoSucesso();
			}else{
				fluxoEstoqueBO.editarEntidade(fluxo);
				FacesUtils.addMessageAlteracaoSucesso();
			}
			iniciarTela();

		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
		}
	}

	private void carregarListaFluxos(){
		fluxos = fluxoEstoqueBO.listarTodos();
	}
	
	public void limparSelecaoTabela(){
		fluxoSelecionada = null;
	}
	
	public List<FluxoEstoqueEntity> getFluxos() {
		return fluxos;
	}

	public void setFluxos(List<FluxoEstoqueEntity> fluxos) {
		this.fluxos = fluxos;
	}

	public FluxoEstoqueEntity getFluxo() {
		return fluxo;
	}

	public void setFluxo(FluxoEstoqueEntity fluxo) {
		this.fluxo = fluxo;
	}

	public FluxoEstoqueEntity getFluxoSelecionada() {
		return fluxoSelecionada;
	}

	public void setFluxoSelecionada(FluxoEstoqueEntity fluxoSelecionada) {
		this.fluxoSelecionada = fluxoSelecionada;
	}

	public String getFluxoFiltro() {
		return fluxoFiltro;
	}

	public void setFluxoFiltro(String fluxoFiltro) {
		this.fluxoFiltro = fluxoFiltro;
	}

	public boolean isCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public String getTituloTelaIncAltFluxo() {
		return tituloTelaIncAltFluxo;
	}

	public void setTituloTelaIncAltFluxo(String tituloTelaIncAltFluxo) {
		this.tituloTelaIncAltFluxo = tituloTelaIncAltFluxo;
	}

	public List<FluxoEstoqueEntity> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<FluxoEstoqueEntity> filteredValue) {
		this.filteredValue = filteredValue;
	}
	
}
