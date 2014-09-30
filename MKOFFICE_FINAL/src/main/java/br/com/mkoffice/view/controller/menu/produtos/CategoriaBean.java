package br.com.mkoffice.view.controller.menu.produtos;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.mkoffice.dto.CategoriaDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.admin.CategoriaEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class CategoriaBean extends AbstractModelBean implements Serializable{

	private static final long serialVersionUID = -7041383649713983949L;
	
	private final String TELA_LISTAR_CATEGORIAS = "/content/m-produtos/listarCategorias";
	
	private List<CategoriaEntity> comboCategoria;
	private String tituloTelaIncAltCategoria;
	
	
/*********************************************
		CAMPOS E METODOS TELA CATALOGO	
**********************************************/
	private CategoriaDTO categoria;
	private CategoriaDTO categoriaSelecionada;
	private List<CategoriaDTO> listaCategorias;
	private String descCategoriaFiltro;
	private Integer codSecaoFiltro;
	private boolean cadastrar;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		carregarAllListaCategoria();
		
		return TELA_LISTAR_CATEGORIAS;
	}
	
	@Override
	public void limparCamposFiltro() {
		descCategoriaFiltro = null;
		codSecaoFiltro = null;
		cadastrar = false;
		limparSelecaoTabela();
	}
	public void limparSelecaoTabela() {
		categoriaSelecionada = null;
	}
	@Override
	public String pesquisarFiltro() {
		listaCategorias = categoriaBO.buscarEntidadePorFiltro(descCategoriaFiltro, codSecaoFiltro);
		return "";
	}
	
	public void carregarAllListaCategoria(){
		listaCategorias = categoriaBO.listarTodos();
	}	
	
	public String btnLimparFiltroPesquisa(){
		limparCamposFiltro();
		carregarAllListaCategoria();
		return "";
	}
	
	public String prepareEdit(){
		tituloTelaIncAltCategoria = "Alterar Categoria";
		cadastrar = false;
		categoria = categoriaSelecionada;
		return "";
		
	}
	public String prepareInsert(){
		tituloTelaIncAltCategoria = "Incluir Categoria";
		cadastrar = true;
		categoria = new CategoriaDTO();
		
		return "";
		
	}
	public String executeSave(){
		try{
			
		if (cadastrar) {
			categoriaBO.adicionarEntidade(categoria);
			FacesUtils.addMessageInclusaoSucesso();
		}else{
			categoriaBO.editarEntidade(categoria);
			FacesUtils.addMessageAlteracaoSucesso();
		}
		}catch(BusinessException be){
			FacesUtils.addErrorMessage(be.getMessage());
		}
		return iniciarTela();
	}
	
	public String voltarInclusao(){
		return TELA_LISTAR_CATEGORIAS;
	}
	
	public String limparFormulario(){
		categoria = new CategoriaDTO();
		return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
	}
	
	/** GET E SET **/

	public List<CategoriaEntity> getComboCategoria() {
		return comboCategoria;
	}

	public void setComboCategoria(List<CategoriaEntity> comboCategoria) {
		this.comboCategoria = comboCategoria;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public CategoriaDTO getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(CategoriaDTO categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public List<CategoriaDTO> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<CategoriaDTO> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public String getDescCategoriaFiltro() {
		return descCategoriaFiltro;
	}

	public void setDescCategoriaFiltro(String descCategoriaFiltro) {
		this.descCategoriaFiltro = descCategoriaFiltro;
	}

	public Integer getCodSecaoFiltro() {
		return codSecaoFiltro;
	}

	public void setCodSecaoFiltro(Integer codSecaoFiltro) {
		this.codSecaoFiltro = codSecaoFiltro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTELA_LISTAR_CATEGORIAS() {
		return TELA_LISTAR_CATEGORIAS;
	}

	public String getTituloTelaIncAltCategoria() {
		return tituloTelaIncAltCategoria;
	}

	public void setTituloTelaIncAltCategoria(String tituloTelaIncAltCategoria) {
		this.tituloTelaIncAltCategoria = tituloTelaIncAltCategoria;
	}

	public boolean isCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}
	
}
