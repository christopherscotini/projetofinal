package br.com.mkoffice.view.controller.menu.produtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.CategoriaDTO;
import br.com.mkoffice.dto.EstoqueDTO;
import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.dto.metricas.EstoqueMetricasDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.admin.CategoriaEntity;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.CalculadoraUtils;
import br.com.mkoffice.view.constants.TipoFluxoEstoqueEnum;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class EstoqueBean extends AbstractModelBean implements Serializable{

	private static final long serialVersionUID = -7041383649713983949L;
	
	private final String TELA_LISTAR_ESTOQUE = "/content/m-produtos/listarEstoque";
	private final String TELA_SELECIONAR_QTDE_PROD_ESTOQUE = "/content/m-produtos/selecionarProdutoEstoque";
	private final String TELA_ADD_PRODUTO_ESTOQUE = "/content/m-produtos/adicionarProdutosCarrinhoEstoque";
	
	private List<CategoriaDTO> comboCategoria;
	
	
/*********************************************
		CAMPOS E METODOS TELA CATALOGO	
**********************************************/
	private EstoqueDTO produtoEstoqueDto;
	private List<EstoqueDTO> listaProdutoEstoque;
	private EstoqueMetricasDTO estoqueMetricasDTO;
	private String codMkFiltro;
	private String nomeProdutoFiltro;
	private List<Integer> filtroCategoria;
	private Long idProdutoDisponibilidade;
	
	private BigDecimal valorTotalEstoqueLbl;
	private BigDecimal precoCustoEstoqueLbl;
	private Integer quantidadeProdutoEstoqueLbl;
	
	private boolean habilitaEntradaProduto;
	private boolean habilitaSaidaProduto;
	
/*****	TELA: adicionarProdutoEstoque	*****/
	private List<ItemMovimentadoCarrinhoDTO> filteredProducts;
	private Integer totalProdutosMovimentacao;
	private List<ItemMovimentadoCarrinhoDTO> listaProdutoCatalogo;
	private List<ItemMovimentadoCarrinhoDTO> listaProdutoCatalogoSelecionados;
	private String codMkFiltroAdicionar;
	private String nomeProdutoFiltroAdicionar;
	private List<Integer> filtroCategoriaAdicionar;
	private boolean estoqueFiltroAdicionar;
	private String titutoloAdicionarProdutosEstoque;
	private List<FluxoEstoqueEntity> cboTipoFluxoEstoque;
	private FluxoEstoqueEntity tipoFluxoEstoqueAdicionar;

	
	@Override
	public String iniciarTela() {
		setFilteredProducts(null);
		limparCamposFiltro();
		carregarComboCategoria();
		listaProdutoEstoque = estoqueBO.listarEstoqueCompleto(getLoginBean().getUsuario().getId());
		estoqueMetricasDTO = estoqueBO.calcularMetricasListagemEstoque(codMkFiltro, nomeProdutoFiltro, filtroCategoria, getLoginBean().getUsuario().getId());
		
		return TELA_LISTAR_ESTOQUE;
	}
	
	@Override
	public void limparCamposFiltro() {
		codMkFiltro = null;
		nomeProdutoFiltro = null;
		filtroCategoria = null;
		produtoEstoqueDto = new EstoqueDTO();
		produtoEstoqueDto.setCodCatalogo(new CatalogoEntity());
		produtoEstoqueDto.getCodCatalogo().setCodCategoria(new CategoriaEntity());
		listaProdutoCatalogoSelecionados = null;
	}
	
	@Override
	public String pesquisarFiltro() {
		listaProdutoEstoque = estoqueBO.filtrarEstoque(codMkFiltro, nomeProdutoFiltro, filtroCategoria, getLoginBean().getUsuario().getId());
		estoqueMetricasDTO  = estoqueBO.calcularMetricasListagemEstoque(codMkFiltro, nomeProdutoFiltro, filtroCategoria, getLoginBean().getUsuario().getId());
		
		return "";
	}
	
	public void addOrRemoveProductFromStock(){
		if(null != idProdutoDisponibilidade){
			produtoEstoqueDto = estoqueBO.buscarProdutoEstoque(idProdutoDisponibilidade, getLoginBean().getUsuario().getId());
			habilitaEntradaProduto = true;
			habilitaSaidaProduto = false;
			produtoEstoqueDto.setQtdeMovimentadoProduto(1);
			produtoEstoqueDto.setDtMovimentacao(new Date());
			cboTipoFluxoEstoque = fluxoEstoqueBO.listarFluxosParaMovEstoque();
		}
	}
	
	public void atualizarEstoque(){
		produtoEstoqueDto.setUsuario(getLoginBean().getUsuario());
		produtoEstoqueDto.setQtdeTotalPontosMovimentadoProduto(produtoEstoqueDto.getCodCatalogo().getPontosPorUnidade() * produtoEstoqueDto.getQtdeMovimentadoProduto());
		produtoEstoqueDto.setValorTotalMovimentadoProduto(produtoEstoqueDto.getCodCatalogo().getPreco().multiply(BigDecimal.valueOf(produtoEstoqueDto.getQtdeMovimentadoProduto())));
		estoqueBO.atualizarEstoquePorProduto(produtoEstoqueDto);
		pesquisarFiltro();
	}
	

	
	
	
/*****	TELA: selecionarProdutoEstoque	*****/
	public String selecionarProdutosParaEstoque(){
		limparCamposFiltroAdicionar();
		pesquisarFiltroAdicionar();
		cboTipoFluxoEstoque = fluxoEstoqueBO.listarFluxosParaMovEstoque();
		tipoFluxoEstoqueAdicionar = cboTipoFluxoEstoque.get(0);
		
		return TELA_SELECIONAR_QTDE_PROD_ESTOQUE;
	}
	
	public String voltarConsultarEstoque(){
		
		return TELA_LISTAR_ESTOQUE;
	}	
	
	public void limparCamposFiltroAdicionar() {
		codMkFiltroAdicionar = null;
		nomeProdutoFiltroAdicionar = null;
		filtroCategoriaAdicionar = null;
		estoqueFiltroAdicionar = false;
	}
	
	public void limparProdutosSelecionados() {
		listaProdutoCatalogoSelecionados = null;
		
	}
	
	public String pesquisarFiltroAdicionar() {
		listaProdutoCatalogo = Adapter.convertCatalogoDtoToItemMovimentadoCarrinho(catalogoBO.buscarEntidadesPorFiltro(codMkFiltroAdicionar, nomeProdutoFiltroAdicionar, filtroCategoriaAdicionar, estoqueFiltroAdicionar));
		
		return "";
	}
	
	public void filtrarListaPeloTipoDeFluxo(){
		if(isFluxoSaida()){
			listaProdutoCatalogo = Adapter.convertCatalogoDtoToItemMovimentadoCarrinho(catalogoBO.buscarEntidadesPorFiltro(codMkFiltroAdicionar, nomeProdutoFiltroAdicionar, filtroCategoriaAdicionar, true));
		}else{
			listaProdutoCatalogo = Adapter.convertCatalogoDtoToItemMovimentadoCarrinho(catalogoBO.buscarEntidadesPorFiltro(codMkFiltroAdicionar, nomeProdutoFiltroAdicionar, filtroCategoriaAdicionar, false));
		}
		
		limparCamposFiltroAdicionar();
	}
	
	public void verificaTipoFLuxo(){
		if(produtoEstoqueDto == null){return;}
		if(produtoEstoqueDto.getTipoFluxoEstoque() == null){
			habilitaEntradaProduto = false;
			habilitaSaidaProduto = false;
		}else{
			if(produtoEstoqueDto.getTipoFluxoEstoque().getDescFluxoEstoque().equalsIgnoreCase(TipoFluxoEstoqueEnum.ENTRADA_MANUAL.toString())){
				habilitaEntradaProduto = true;
				habilitaSaidaProduto = false;
				titutoloAdicionarProdutosEstoque = "Entrada de estoque";
			}else{
				habilitaEntradaProduto = false;
				habilitaSaidaProduto = true;
				titutoloAdicionarProdutosEstoque = "Saida de estoque";
			}
		}
	}

/*****	TELA: adicionarProdutoEstoque	*****/
	public String selecionarQuantidadeProdutosEstoque(){
		for (int i = 0; i < listaProdutoCatalogoSelecionados.size(); i++) {
			listaProdutoCatalogoSelecionados.get(i).setQtdeProdutoCarrinho(new Integer(1));
		}
		
		if(isFluxoSaida()){
			habilitaEntradaProduto = false;
			habilitaSaidaProduto = true;
		}else{
			habilitaEntradaProduto = true;
			habilitaSaidaProduto = false;
		}
		
		return TELA_ADD_PRODUTO_ESTOQUE;
	}
	
	public void onChangeQtde(ItemMovimentadoCarrinhoDTO produto) {  
		for (int i = 0; i < listaProdutoCatalogoSelecionados.size(); i++) {
			if(listaProdutoCatalogoSelecionados.get(i).getProduto().getCodProduto().equals(produto.getProduto().getCodProduto())){
				listaProdutoCatalogoSelecionados.get(i).setSomaPontosProdutoCarrinho(CalculadoraUtils.somarTotalPontosCarrinho(produto.getQtdeProdutoCarrinho(), listaProdutoCatalogoSelecionados.get(i).getProduto().getPontosPorUnidade()));
				listaProdutoCatalogoSelecionados.get(i).setValorTotalProdutoCarrinho(CalculadoraUtils.valorTotalProdutosCarrinho(listaProdutoCatalogoSelecionados.get(i).getQtdeProdutoCarrinho(), listaProdutoCatalogoSelecionados.get(i).getProduto().getPreco()));
			}
		}
	}
	
	public String concluirMovimentacao(){
		try{
			
			for (int i = 0; i < listaProdutoCatalogoSelecionados.size(); i++) {
				listaProdutoCatalogoSelecionados.get(i).setTipoFluxoEstoque(tipoFluxoEstoqueAdicionar);
			}
			
			estoqueBO.atualizarEstoque(listaProdutoCatalogoSelecionados);
			
			FacesUtils.addInfoMessage("Movimenta��o efetuada com sucesso.");
		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
		}
		
		return iniciarTela();
	}
	
	public String voltarSelecionarProdutoEstoque(){
		
		return TELA_SELECIONAR_QTDE_PROD_ESTOQUE;
	}	
	
	
/*************** PRIVATES ***************/
	
	private void carregarComboCategoria(){
		comboCategoria = categoriaBO.listarTodos();
	}
	
	private boolean isFluxoSaida(){
		return getTipoFluxoEstoqueAdicionar().getDescFluxoEstoque().equalsIgnoreCase(TipoFluxoEstoqueEnum.SAIDA_DEMONSTRACAO.toString())
					|| getTipoFluxoEstoqueAdicionar().getDescFluxoEstoque().equalsIgnoreCase(TipoFluxoEstoqueEnum.SAIDA_EMPRESTIMO.toString())
					|| getTipoFluxoEstoqueAdicionar().getDescFluxoEstoque().equalsIgnoreCase(TipoFluxoEstoqueEnum.SAIDA_MANUAL.toString())
					|| getTipoFluxoEstoqueAdicionar().getDescFluxoEstoque().equalsIgnoreCase(TipoFluxoEstoqueEnum.SAIDA_PRESENTE.toString())
					|| getTipoFluxoEstoqueAdicionar().getDescFluxoEstoque().equalsIgnoreCase(TipoFluxoEstoqueEnum.SAIDA_VENDA.toString());
	}
	
/*************** GET E SET ***************/
	
	public List<CategoriaDTO> getComboCategoria() {
		return comboCategoria;
	}

	public void setComboCategoria(List<CategoriaDTO> comboCategoria) {
		this.comboCategoria = comboCategoria;
	}

	public List<EstoqueDTO> getListaProdutoEstoque() {
		return listaProdutoEstoque;
	}

	public void setListaProdutoEstoque(List<EstoqueDTO> listaProdutoEstoque) {
		this.listaProdutoEstoque = listaProdutoEstoque;
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

	public boolean isEstoqueFiltroAdicionar() {
		return estoqueFiltroAdicionar;
	}

	public void setEstoqueFiltroAdicionar(boolean estoqueFiltroAdicionar) {
		this.estoqueFiltroAdicionar = estoqueFiltroAdicionar;
	}

	public List<Integer> getFiltroCategoria() {
		return filtroCategoria;
	}

	public void setFiltroCategoria(List<Integer> filtroCategoria) {
		this.filtroCategoria = filtroCategoria;
	}

	public Long getIdProdutoDisponibilidade() {
		return idProdutoDisponibilidade;
	}

	public void setIdProdutoDisponibilidade(Long idProdutoDisponibilidade) {
		this.idProdutoDisponibilidade = idProdutoDisponibilidade;
	}

	public EstoqueDTO getProdutoEstoqueDto() {
		return produtoEstoqueDto;
	}

	public void setProdutoEstoqueDto(EstoqueDTO produtoEstoqueDto) {
		this.produtoEstoqueDto = produtoEstoqueDto;
	}

	public BigDecimal getValorTotalEstoqueLbl() {
		return valorTotalEstoqueLbl;
	}

	public void setValorTotalEstoqueLbl(BigDecimal valorTotalEstoqueLbl) {
		this.valorTotalEstoqueLbl = valorTotalEstoqueLbl;
	}

	public BigDecimal getPrecoCustoEstoqueLbl() {
		return precoCustoEstoqueLbl;
	}

	public void setPrecoCustoEstoqueLbl(BigDecimal precoCustoEstoqueLbl) {
		this.precoCustoEstoqueLbl = precoCustoEstoqueLbl;
	}

	public Integer getQuantidadeProdutoEstoqueLbl() {
		return quantidadeProdutoEstoqueLbl;
	}

	public void setQuantidadeProdutoEstoqueLbl(
			Integer quantidadeProdutoEstoqueLbl) {
		this.quantidadeProdutoEstoqueLbl = quantidadeProdutoEstoqueLbl;
	}

	public boolean isHabilitaEntradaProduto() {
		return habilitaEntradaProduto;
	}

	public void setHabilitaEntradaProduto(boolean habilitaEntradaProduto) {
		this.habilitaEntradaProduto = habilitaEntradaProduto;
	}

	public boolean isHabilitaSaidaProduto() {
		return habilitaSaidaProduto;
	}

	public void setHabilitaSaidaProduto(boolean habilitaSaidaProduto) {
		this.habilitaSaidaProduto = habilitaSaidaProduto;
	}

	public List<ItemMovimentadoCarrinhoDTO> getListaProdutoCatalogo() {
		return listaProdutoCatalogo;
	}

	public void setListaProdutoCatalogo(List<ItemMovimentadoCarrinhoDTO> listaProdutoCatalogo) {
		this.listaProdutoCatalogo = listaProdutoCatalogo;
	}

	public List<ItemMovimentadoCarrinhoDTO> getListaProdutoCatalogoSelecionados() {
		return listaProdutoCatalogoSelecionados;
	}

	public void setListaProdutoCatalogoSelecionados(
			List<ItemMovimentadoCarrinhoDTO> listaProdutoCatalogoSelecionados) {
		this.listaProdutoCatalogoSelecionados = listaProdutoCatalogoSelecionados;
	}

	public String getCodMkFiltroAdicionar() {
		return codMkFiltroAdicionar;
	}

	public void setCodMkFiltroAdicionar(String codMkFiltroAdicionar) {
		this.codMkFiltroAdicionar = codMkFiltroAdicionar;
	}

	public String getNomeProdutoFiltroAdicionar() {
		return nomeProdutoFiltroAdicionar;
	}

	public void setNomeProdutoFiltroAdicionar(String nomeProdutoFiltroAdicionar) {
		this.nomeProdutoFiltroAdicionar = nomeProdutoFiltroAdicionar;
	}

	public List<Integer> getFiltroCategoriaAdicionar() {
		return filtroCategoriaAdicionar;
	}

	public void setFiltroCategoriaAdicionar(List<Integer> filtroCategoriaAdicionar) {
		this.filtroCategoriaAdicionar = filtroCategoriaAdicionar;
	}

	public void setTipoFluxoEstoqueAdicionar(FluxoEstoqueEntity tipoFluxoEstoqueAdicionar) {
		this.tipoFluxoEstoqueAdicionar = tipoFluxoEstoqueAdicionar;
	}

	public String getTitutoloAdicionarProdutosEstoque() {
		return titutoloAdicionarProdutosEstoque;
	}

	public void setTitutoloAdicionarProdutosEstoque(
			String titutoloAdicionarProdutosEstoque) {
		this.titutoloAdicionarProdutosEstoque = titutoloAdicionarProdutosEstoque;
	}

	public EstoqueMetricasDTO getEstoqueMetricasDTO() {
		return estoqueMetricasDTO;
	}

	public void setEstoqueMetricasDTO(EstoqueMetricasDTO estoqueMetricasDTO) {
		this.estoqueMetricasDTO = estoqueMetricasDTO;
	}

	public List<FluxoEstoqueEntity> getCboTipoFluxoEstoque() {
		return cboTipoFluxoEstoque;
	}

	public void setCboTipoFluxoEstoque(List<FluxoEstoqueEntity> cboTipoFluxoEstoque) {
		this.cboTipoFluxoEstoque = cboTipoFluxoEstoque;
	}

	public FluxoEstoqueEntity getTipoFluxoEstoqueAdicionar() {
		return tipoFluxoEstoqueAdicionar;
	}

	public Integer getTotalProdutosMovimentacao() {
		return totalProdutosMovimentacao;
	}

	public void setTotalProdutosMovimentacao(Integer totalProdutosMovimentacao) {
		this.totalProdutosMovimentacao = totalProdutosMovimentacao;
	}

	public List<ItemMovimentadoCarrinhoDTO> getFilteredProducts() {
		return filteredProducts;
	}

	public void setFilteredProducts(List<ItemMovimentadoCarrinhoDTO> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

}
