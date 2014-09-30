package br.com.mkoffice.view.controller.menu.pedidos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.mkoffice.dto.CategoriaDTO;
import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.dto.ParcelasDTO;
import br.com.mkoffice.dto.PedidoDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.CalculadoraUtils;
import br.com.mkoffice.utils.DecimalUtils;
import br.com.mkoffice.utils.MkmtsUtil;
import br.com.mkoffice.view.constants.SituacaoPagamentoEnum;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class EfetuarPedidoBean extends AbstractModelBean implements Serializable{

	private static final long serialVersionUID = 2141967316958457526L;

	private final String TELA_SELEC_PROD_PEDIDO = "/content/m-pedidos/sm-efetuarpedido/selecionarProdutosPedido";
	private final String TELA_ADD_PROD_CARRINHO = "/content/m-pedidos/sm-efetuarpedido/adicionarProdutosCarrinhoPedido";
	private final String TELA_DADOS_PEDIDO = "/content/m-pedidos/sm-efetuarpedido/dadosPedido";
	private final String TELA_CONCLUIR_PEDIDO = "/content/m-pedidos/sm-efetuarpedido/concluirPedido";
	
	private List<CategoriaDTO> comboCategoria;

	//campos de filtro
	private String codMkFiltro;
	private String nomeProdutoFiltro;
	private boolean estoqueFiltro;
	private List<Integer> filtroCategoria;
	
	private PedidoDTO pedidoDTO;
	private List<ItemMovimentadoCarrinhoDTO> listaProdutosEmCatalogo;
	private List<ItemMovimentadoCarrinhoDTO> listaProdutosInclusosPedido;
	private List<ItemMovimentadoCarrinhoDTO> filteredProducts;
	
	private Integer totalProdutosPedido;
	private BigDecimal totalAPagar;
	
	private List<ParcelasEntity> listaParcelamento;
	private List<SelectItem> listaParcelamentoSelectItem;
	private Integer lblNumeroProdutosPedido;
	private Integer lblNumeroItensPedido;
	private Integer numParcelasPagamento;
	private Date dtVctoPrimeiraParcela;
	
	private ItemMovimentadoCarrinhoDTO produtoSelecionadoAddCart;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		carregarComboCategoria();
		listaProdutosEmCatalogo = Adapter.convertCatalogoDtoToItemMovimentadoCarrinho(catalogoBO.buscarEntidadesPorFiltro(null, null, null, false));
		
		produtoSelecionadoAddCart = new ItemMovimentadoCarrinhoDTO();
		totalAPagar = BigDecimal.ZERO;
		totalProdutosPedido = 0;
		for (int i = 0; i < listaProdutosEmCatalogo.size(); i++) {
			listaProdutosEmCatalogo.get(i).setQtdeProdutoCarrinho(0);
			listaProdutosEmCatalogo.get(i).setValorTotalProdutoCarrinho(BigDecimal.ZERO);
		}
		
		return TELA_SELEC_PROD_PEDIDO;
	}

	@Override
	public String pesquisarFiltro() {

		listaProdutosEmCatalogo = Adapter.convertCatalogoDtoToItemMovimentadoCarrinho(catalogoBO.buscarEntidadesPorFiltro(codMkFiltro, nomeProdutoFiltro, filtroCategoria, estoqueFiltro));
		
		return TELA_SELEC_PROD_PEDIDO;
	}

	public void onCheckFiltrarEmEstoque(){
		listaProdutosEmCatalogo = Adapter.convertCatalogoDtoToItemMovimentadoCarrinho(catalogoBO.buscarEntidadesPorFiltro(null, null, null, estoqueFiltro));
	}
	
	public void selectAddProdutoPedido(ItemMovimentadoCarrinhoDTO produto){
		produtoSelecionadoAddCart = produto;
	}
	
	public void limparProdutosSelecionados() {
		for (int i = 0; i < listaProdutosEmCatalogo.size(); i++) {
			listaProdutosEmCatalogo.get(i).setQtdeProdutoCarrinho(0);
			listaProdutosEmCatalogo.get(i).setValorTotalProdutoCarrinho(BigDecimal.ZERO);
		}
		totalAPagar = BigDecimal.ZERO;
		totalProdutosPedido = 0;
		produtoSelecionadoAddCart = new ItemMovimentadoCarrinhoDTO();
	}
	
	public void addProdutoPedido(){
		totalAPagar = BigDecimal.ZERO;
		totalProdutosPedido = 0;
		for (int i = 0; i < listaProdutosEmCatalogo.size(); i++) {
			if(listaProdutosEmCatalogo.get(i).getProduto().getCodProduto().equals(produtoSelecionadoAddCart.getProduto().getCodProduto())){
				listaProdutosEmCatalogo.get(i).setValorTotalProdutoCarrinho(CalculadoraUtils.valorTotalProdutosCarrinho(produtoSelecionadoAddCart.getQtdeProdutoCarrinho(), listaProdutosEmCatalogo.get(i).getProduto().getPreco()));
				listaProdutosEmCatalogo.get(i).setQtdeProdutoCarrinho(produtoSelecionadoAddCart.getQtdeProdutoCarrinho());
			}
			totalProdutosPedido += listaProdutosEmCatalogo.get(i).getQtdeProdutoCarrinho();
			totalAPagar = totalAPagar.add(listaProdutosEmCatalogo.get(i).getValorTotalProdutoCarrinho());
		}
	}
	
	public boolean habilitaBtnAvancarSelecProdutoPedido(){
		for (int i = 0; i < listaProdutosEmCatalogo.size(); i++) {
			if(listaProdutosEmCatalogo.get(i).getQtdeProdutoCarrinho() > 0){
				return true;
			}
		}
		
		return false;
	}
	
	public String navegarRevisarPedido(){
		popularListaProdutosSelecionados();

		pedidoDTO = new PedidoDTO();
		pedidoDTO.setUsuario(getLoginBean().getUsuario());
		pedidoDTO.setDtPedido(new Date());
		pedidoDTO.setListaProdutosComprados(listaProdutosInclusosPedido);
		pedidoDTO.setPontosTotalPedido(CalculadoraUtils.somarTotalPontosPedidoVenda(listaProdutosInclusosPedido));
		pedidoDTO.setValorTotalEmProdutos(CalculadoraUtils.valorTotalProdutosPedido(listaProdutosInclusosPedido));
		pedidoDTO.setPorcDesconto(CalculadoraUtils.calcularPorcentagemDescontoPedido(pedidoDTO.getValorTotalEmProdutos()));
		pedidoDTO.setValorTotalAtacado(CalculadoraUtils.calcularValorTotalAtacado(pedidoDTO.getValorTotalEmProdutos(), pedidoDTO.getPorcDesconto()));
		pedidoDTO.setValorFinalTotalPago(CalculadoraUtils.calcularValorFinalTotalPago(pedidoDTO.getValorTotalAtacado(), pedidoDTO.getValorFrete(),pedidoDTO.getValorBonusUtilizado()));
		pedidoDTO.setLucroTotal(CalculadoraUtils.calcularLucroTotalPedido(pedidoDTO.getValorTotalAtacado(), pedidoDTO.getValorTotalEmProdutos()));
		pedidoDTO.setValorFrete(BigDecimal.ZERO);
		pedidoDTO.setValorBonusUtilizado(BigDecimal.ZERO);
		lblNumeroProdutosPedido = getListaProdutosInclusosPedido().size();
		lblNumeroItensPedido = 0;
		for (int i = 0; i < getListaProdutosInclusosPedido().size(); i++) {
			lblNumeroItensPedido+= getListaProdutosInclusosPedido().get(i).getQtdeProdutoCarrinho();
		}
		
		return TELA_DADOS_PEDIDO;
	}
	
	public String navegarConcluirPedido(){
		try{
			pedidoBO.existePedido(pedidoDTO);
		}catch (BusinessException b) {
			FacesUtils.addErrorMessage(b.getMessage());
			return "";
		}
		
		numParcelasPagamento = null;
		dtVctoPrimeiraParcela = new Date();
		
		pedidoDTO.setFormaDePagamento(getCboFormaPagamento().get(0));
		onChangeFormaPagamento();	
		
		return TELA_CONCLUIR_PEDIDO;
	}
	
	public String executePedido(){
		
		pedidoDTO.setParcelas(prepararParcelas());
		
		try{
			FacesUtils.addInfoMessage("Pedido N° "+pedidoBO.efetuarPedido(pedidoDTO).getCodPedido()+" foi realizado com sucesso.");
		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
		}
		
		
		return iniciarTela();
	}

	public void onChangePercDesconto(){
		pedidoDTO.setValorTotalAtacado(CalculadoraUtils.calcularValorTotalAtacado(pedidoDTO.getValorTotalEmProdutos(), pedidoDTO.getPorcDesconto()));
		pedidoDTO.setValorFinalTotalPago(CalculadoraUtils.calcularValorFinalTotalPago(pedidoDTO.getValorTotalAtacado(), pedidoDTO.getValorFrete(), pedidoDTO.getValorBonusUtilizado()));
		pedidoDTO.setLucroTotal(CalculadoraUtils.calcularLucroTotalPedido(pedidoDTO.getValorFinalTotalPago(), pedidoDTO.getValorTotalEmProdutos()));
	}
	
	public void onBlurValorFreteAndBonusUtilizado(){
		pedidoDTO.setValorFinalTotalPago(CalculadoraUtils.calcularValorFinalTotalPago(pedidoDTO.getValorTotalAtacado(), pedidoDTO.getValorFrete(), pedidoDTO.getValorBonusUtilizado()));
		pedidoDTO.setLucroTotal(CalculadoraUtils.calcularLucroTotalPedido(pedidoDTO.getValorFinalTotalPago(), pedidoDTO.getValorTotalEmProdutos()));
	}
	
	public void onChangeFormaPagamento(){
		listaParcelamento = new ArrayList<ParcelasEntity>();
		listaParcelamentoSelectItem = new ArrayList<SelectItem>();
		double preco = pedidoDTO.getValorFinalTotalPago().doubleValue();
		DecimalFormat decimalFormat = new DecimalFormat();   
		decimalFormat.setMaximumFractionDigits(2);
		
		for (int i = 0; i < pedidoDTO.getFormaDePagamento().getNumeroParcelas(); i++) {//personalizar a qtde de parcelas
			
			String lblAVista = "R$ "+DecimalUtils.format(BigDecimal.valueOf((preco/(i+1))))+" � vista";
			String lbl = (i+1)+"X sem juros R$ "+DecimalUtils.format(BigDecimal.valueOf((preco/(i+1))));
			
			ParcelasEntity parcela = new ParcelasEntity(null, i== 0 ? lblAVista : lbl, (i+1), BigDecimal.valueOf((preco/(i+1))), null, null, i==0?BigDecimal.valueOf((preco/(i+1))):null);
			
			listaParcelamento.add(parcela);
			listaParcelamentoSelectItem.add(new SelectItem(i, i== 0 ? lblAVista : lbl));
		}
	}
	
	private List<ParcelasDTO> prepararParcelas(){
		List<ParcelasDTO>returnzz = new ArrayList<ParcelasDTO>();
		ParcelasEntity parcelaSelecionada = listaParcelamento.get(numParcelasPagamento);
		
		for (int i = 0; i < numParcelasPagamento+1; i++) {
			SituacaoEntity situacaoPagamento = new SituacaoEntity();
			List<SituacaoEntity>situacaoEntities = getSituacaoPagamentoTodos();
			for (int j = 0; j < situacaoEntities.size(); j++) {
				if(i==0){
					if(situacaoEntities.get(j).getDescSituacao().equalsIgnoreCase(SituacaoPagamentoEnum.PAGO.getLabel())){
						situacaoPagamento = situacaoEntities.get(j);
						break;
					}
				}else{
					if(situacaoEntities.get(j).getDescSituacao().equalsIgnoreCase(SituacaoPagamentoEnum.PENDENTE.getLabel())){
						situacaoPagamento = situacaoEntities.get(j);
						break;
					}
				}
			}
			Calendar c = new GregorianCalendar(MkmtsUtil.extrairAnoDataInteiro(dtVctoPrimeiraParcela), MkmtsUtil.extrairMesDataInteiro(dtVctoPrimeiraParcela), MkmtsUtil.extrairDiaDataInteiro(dtVctoPrimeiraParcela));
			c.set(Calendar.MONTH, (MkmtsUtil.extrairMesDataInteiro(dtVctoPrimeiraParcela)-1) + i);
			ParcelasDTO entity = new ParcelasDTO(null, null, null, c.getTime(), (i+1)+"/"+(numParcelasPagamento+1), (i+1), parcelaSelecionada.getValorParcela(), situacaoPagamento , i==0?c.getTime():null, i==0?parcelaSelecionada.getValorParcela():BigDecimal.ZERO);
			entity.setUsuario(getLoginBean().getUsuario());
			returnzz.add(entity);
		}
		
		return returnzz;
	}

	@Override
	public void limparCamposFiltro() {
		codMkFiltro = null;
		nomeProdutoFiltro = null;
		estoqueFiltro = false;
		filtroCategoria = null;
		listaProdutosInclusosPedido = null;
	}
	
//	a��es retorno de pagina
	public String navegarVoltarSelecProdPedido(){
		return TELA_SELEC_PROD_PEDIDO;
	}
	
	public String navegarVoltarAddProdCarrinho(){
		return TELA_ADD_PROD_CARRINHO;
	}
	
	public String navegarVoltarRevisarPedido(){
		return TELA_DADOS_PEDIDO;
	}
	
	private void popularListaProdutosSelecionados(){
		listaProdutosInclusosPedido = new ArrayList<ItemMovimentadoCarrinhoDTO>();
		
		for (int i = 0; i < listaProdutosEmCatalogo.size(); i++) {
			if(listaProdutosEmCatalogo.get(i).getQtdeProdutoCarrinho() > 0){
				listaProdutosInclusosPedido.add(listaProdutosEmCatalogo.get(i));
			}
		}
	}
	
	private void carregarComboCategoria(){
		setComboCategoria(categoriaBO.listarTodos());
	}
	
	
	
//	GETTERS AND SETTERS

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

	public List<ItemMovimentadoCarrinhoDTO> getListaProdutosEmCatalogo() {
		return listaProdutosEmCatalogo;
	}

	public void setListaProdutosEmCatalogo(List<ItemMovimentadoCarrinhoDTO> listaProdutosEmCatalogo) {
		this.listaProdutosEmCatalogo = listaProdutosEmCatalogo;
	}

	public List<ItemMovimentadoCarrinhoDTO> getListaProdutosInclusosPedido() {
		return listaProdutosInclusosPedido;
	}

	public void setListaProdutosInclusosPedido(
			List<ItemMovimentadoCarrinhoDTO> listaProdutosInclusosPedido) {
		this.listaProdutosInclusosPedido = listaProdutosInclusosPedido;
	}

	public Integer getTotalProdutosPedido() {
		return totalProdutosPedido;
	}

	public void setTotalProdutosPedido(Integer totalProdutosPedido) {
		this.totalProdutosPedido = totalProdutosPedido;
	}

	public BigDecimal getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(BigDecimal totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	public List<CategoriaDTO> getComboCategoria() {
		return comboCategoria;
	}

	public void setComboCategoria(List<CategoriaDTO> comboCategoria) {
		this.comboCategoria = comboCategoria;
	}

	public PedidoDTO getPedidoDTO() {
		return pedidoDTO;
	}

	public void setPedidoDTO(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}

	public Integer getLblNumeroProdutosPedido() {
		return lblNumeroProdutosPedido;
	}

	public void setLblNumeroProdutosPedido(Integer lblNumeroProdutosPedido) {
		this.lblNumeroProdutosPedido = lblNumeroProdutosPedido;
	}

	public Integer getLblNumeroItensPedido() {
		return lblNumeroItensPedido;
	}

	public void setLblNumeroItensPedido(Integer lblNumeroItensPedido) {
		this.lblNumeroItensPedido = lblNumeroItensPedido;
	}

	public List<ParcelasEntity> getListaParcelamento() {
		return listaParcelamento;
	}

	public void setListaParcelamento(List<ParcelasEntity> listaParcelamento) {
		this.listaParcelamento = listaParcelamento;
	}

	public List<SelectItem> getListaParcelamentoSelectItem() {
		return listaParcelamentoSelectItem;
	}

	public void setListaParcelamentoSelectItem(
			List<SelectItem> listaParcelamentoSelectItem) {
		this.listaParcelamentoSelectItem = listaParcelamentoSelectItem;
	}

	public Integer getNumParcelasPagamento() {
		return numParcelasPagamento;
	}

	public void setNumParcelasPagamento(Integer numParcelasPagamento) {
		this.numParcelasPagamento = numParcelasPagamento;
	}

	public Date getDtVctoPrimeiraParcela() {
		return dtVctoPrimeiraParcela;
	}

	public void setDtVctoPrimeiraParcela(Date dtVctoPrimeiraParcela) {
		this.dtVctoPrimeiraParcela = dtVctoPrimeiraParcela;
	}

	public ItemMovimentadoCarrinhoDTO getProdutoSelecionadoAddCart() {
		return produtoSelecionadoAddCart;
	}

	public void setProdutoSelecionadoAddCart(ItemMovimentadoCarrinhoDTO produtoSelecionadoAddCart) {
		this.produtoSelecionadoAddCart = produtoSelecionadoAddCart;
	}
	public List<ItemMovimentadoCarrinhoDTO> getFilteredProducts() {
		return filteredProducts;
	}

	public void setFilteredProducts(List<ItemMovimentadoCarrinhoDTO> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

}
