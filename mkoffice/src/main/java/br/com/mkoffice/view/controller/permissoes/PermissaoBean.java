package br.com.mkoffice.view.controller.permissoes;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.PermissaoDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.view.AbstractModelBean;
import br.com.mkoffice.view.controller.utils.FacesUtils;


@ManagedBean
@SessionScoped
public class PermissaoBean extends AbstractModelBean{

	private final String TELA_MANTER_PERMISSAO = "/content/m-admin/permissao/manterPermissao.xhtml";
	
	private List<PermissaoDTO>permissoes;
	private PermissaoDTO permissao;
	private PermissaoDTO permissaoSelecionada;
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

		return "";
	}
	
	public void navegarIncluirPermissao(){
		tituloTelaIncAltPermissao = "Incluir Permiss�o";
		permissao = new PermissaoDTO();
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

	public List<PermissaoDTO> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoDTO> permissoes) {
		this.permissoes = permissoes;
	}

	public PermissaoDTO getPermissao() {
		return permissao;
	}

	public void setPermissao(PermissaoDTO permissao) {
		this.permissao = permissao;
	}

	public PermissaoDTO getPermissaoSelecionada() {
		return permissaoSelecionada;
	}

	public void setPermissaoSelecionada(PermissaoDTO permissaoSelecionada) {
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
