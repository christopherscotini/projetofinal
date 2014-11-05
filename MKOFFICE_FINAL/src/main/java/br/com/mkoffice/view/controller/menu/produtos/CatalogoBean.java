package br.com.mkoffice.view.controller.menu.produtos;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.CatalogoDTO;
import br.com.mkoffice.dto.CategoriaDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.admin.CategoriaEntity;
import br.com.mkoffice.view.constants.MessagesConstants;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.controller.menu.produtos.vo.CatalogoVO;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class CatalogoBean extends AbstractModelBean implements Serializable{

	private static final long serialVersionUID = -7041383649713983949L;
	private final boolean CONTEM_NO_ESTOQUE = true;
	private final boolean NAO_CONTEM_NO_ESTOQUE = false;
	private final String TELA_CATALOGO_MK = "/content/m-produtos/catalogoProdutosMaryKay";
	

	private List<CategoriaDTO> comboCategoria;
	
	
/*********************************************
		CAMPOS E METODOS TELA CATALOGO	
**********************************************/
	private CatalogoVO produtoVo;
	private CatalogoDTO produtoDto;
	private CatalogoDTO produtoSelecionado;
	private List<CatalogoDTO> listaProduto;
	private List<CatalogoDTO> filteredProdutos;
	private String codMkFiltro;
	private String nomeProdutoFiltro;
	private boolean estoqueFiltro;
	private List<Integer> filtroCategoria;
	private String codProdutoDisponibilidade;
	private String tituloDialog;
	
	public void autoComplete(){
		
	}
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		carregarComboCategoria();
		carregarAllListaCatalogo();
		instaciarProdutoDTO();
		
		return TELA_CATALOGO_MK;
	}
	
	@Override
	public void limparCamposFiltro() {
		codMkFiltro = null;
		nomeProdutoFiltro = null;
		filtroCategoria = null;
		estoqueFiltro = false;
	}
	
	@Override
	public String pesquisarFiltro() {
		listaProduto = catalogoBO.buscarEntidadesPorFiltro(codMkFiltro, nomeProdutoFiltro, filtroCategoria, estoqueFiltro);
		return "";
	}
	
	public String btnLimparFiltroPesquisa(){
		limparCamposFiltro();
		carregarAllListaCatalogo();
		return "";
	}
	
	public void addProdutoDialog(){
		produtoDto = new CatalogoDTO();
		instaciarProdutoDTO();
		carregarComboCategoria();
	}

	public void executeSaveProdutoDialog(){
		try{
			catalogoBO.adicionarEntidade(produtoDto);
			FacesUtils.addInfoMessage(MessagesConstants.INCLUSAO_SUCESSO+"\nCód. Produto: "+produtoDto.getCodProduto()+"\nProduto: "+produtoDto.getDescProduto());
			carregarAllListaCatalogo();
		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
		}
	}
	
	public void onCheckFiltrarEmEstoque(){
		listaProduto = catalogoBO.buscarEntidadesPorFiltro(null, null, null, isEstoqueFiltro());
	}	
	
	private void carregarComboCategoria(){
		comboCategoria = categoriaBO.listarTodos();
	}

	private void carregarAllListaCatalogo(){
		listaProduto = catalogoBO.buscarEntidadesPorFiltro(null, null, null, NAO_CONTEM_NO_ESTOQUE);
	}	
	
	private void instaciarProdutoDTO(){
		produtoDto = new CatalogoDTO();
		produtoDto.setCodCategoria(new CategoriaEntity());
		
	}

	/** GET E SET **/

	public List<CategoriaDTO> getComboCategoria() {
		return comboCategoria;
	}

	public void setComboCategoria(List<CategoriaDTO> comboCategoria) {
		this.comboCategoria = comboCategoria;
	}

	public CatalogoVO getProdutoVo() {
		return produtoVo;
	}

	public void setProdutoVo(CatalogoVO produtoVo) {
		this.produtoVo = produtoVo;
	}

	public CatalogoDTO getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(CatalogoDTO produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public List<CatalogoDTO> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<CatalogoDTO> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public String getCodMkFiltro() {
		return codMkFiltro;
	}

	public void setCodMkFiltro(String codMkFiltro) {
		this.codMkFiltro = codMkFiltro;
	}

	public String getNomeProdutoFiltro() {
		return nomeProdutoFiltro;
	}

	public void setNomeProdutoFiltro(String nomeProdutoFiltro) {
		this.nomeProdutoFiltro = nomeProdutoFiltro;
	}

	public boolean isEstoqueFiltro() {
		return estoqueFiltro;
	}

	public void setEstoqueFiltro(boolean estoqueFiltro) {
		this.estoqueFiltro = estoqueFiltro;
	}

	public List<Integer> getFiltroCategoria() {
		return filtroCategoria;
	}

	public void setFiltroCategoria(List<Integer> filtroCategoria) {
		this.filtroCategoria = filtroCategoria;
	}

	public String getCodProdutoDisponibilidade() {
		return codProdutoDisponibilidade;
	}

	public void setCodProdutoDisponibilidade(String codProdutoDisponibilidade) {
		this.codProdutoDisponibilidade = codProdutoDisponibilidade;
	}

	public CatalogoDTO getProdutoDto() {
		return produtoDto;
	}

	public void setProdutoDto(CatalogoDTO produtoDto) {
		this.produtoDto = produtoDto;
	}

	public String getTituloDialog() {
		return tituloDialog;
	}

	public void setTituloDialog(String tituloDialog) {
		this.tituloDialog = tituloDialog;
	}

	public List<CatalogoDTO> getFilteredProdutos() {
		return filteredProdutos;
	}

	public void setFilteredProdutos(List<CatalogoDTO> filteredProdutos) {
		this.filteredProdutos = filteredProdutos;
	}
	
}
