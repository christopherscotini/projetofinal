package br.com.mkoffice.view.controller.menu.admin.permissoes;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.admin.PermissaoEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;


@ManagedBean
@SessionScoped
public class PermissaoBean extends AbstractModelBean{

	private final String TELA_MANTER_PERMISSAO = "/content/m-admin/permissao/manterPermissao.xhtml";
	
	private List<PermissaoEntity>permissoes;
	private PermissaoEntity permissao;
	private PermissaoEntity permissaoSelecionada;
	private String permissaoFiltro;
	private boolean cadastrar;

	private String tituloTelaIncAltPermissao;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		limparSelecaoTabela();
		carregarListaPermissoes();
		
		return TELA_MANTER_PERMISSAO;
	}

	@Override
	public void limparCamposFiltro() {
		permissaoFiltro = null;
	}

	@Override
	public String pesquisarFiltro() {
		permissoes = permissaoBO.buscarEntidadesPorFiltro(permissaoFiltro);
		permissaoSelecionada = null;
		
		return "";
	}
	
	public void navegarIncluirPermissao(){
		tituloTelaIncAltPermissao = "Incluir Permiss�o";
		permissao = new PermissaoEntity();
		cadastrar = true;
	}
	
	public void navegarAlterarPermissao(){
		tituloTelaIncAltPermissao = "Alterar Permiss�o";
		permissao = permissaoSelecionada;
		cadastrar = false;
	}
	
	public void executeSave(){
		try{
			if (cadastrar) {
				permissaoBO.adicionarEntidade(permissao);
				FacesUtils.addMessageInclusaoSucesso();
			}else{
				permissaoBO.editarEntidade(permissao);
				FacesUtils.addMessageAlteracaoSucesso();
			}
			pesquisarFiltro();

		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
		}
	}

	private void carregarListaPermissoes(){
		permissoes = permissaoBO.listarTodos();
	}
	
	public void limparSelecaoTabela(){
		permissaoSelecionada = null;
	}

	public List<PermissaoEntity> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoEntity> permissoes) {
		this.permissoes = permissoes;
	}

	public PermissaoEntity getPermissao() {
		return permissao;
	}

	public void setPermissao(PermissaoEntity permissao) {
		this.permissao = permissao;
	}

	public PermissaoEntity getPermissaoSelecionada() {
		return permissaoSelecionada;
	}

	public void setPermissaoSelecionada(PermissaoEntity permissaoSelecionada) {
		this.permissaoSelecionada = permissaoSelecionada;
	}

	public String getPermissaoFiltro() {
		return permissaoFiltro;
	}

	public void setPermissaoFiltro(String permissaoFiltro) {
		this.permissaoFiltro = permissaoFiltro;
	}

	public boolean isCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public String getTituloTelaIncAltPermissao() {
		return tituloTelaIncAltPermissao;
	}

	public void setTituloTelaIncAltPermissao(String tituloTelaIncAltPermissao) {
		this.tituloTelaIncAltPermissao = tituloTelaIncAltPermissao;
	}
	
}
