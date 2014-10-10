/**
 * 
 */
package br.com.mkoffice.view.controller.menu.vendas;

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

import br.com.mkoffice.business.exception.NoDataFoundException;
import br.com.mkoffice.dto.ClienteDTO;
import br.com.mkoffice.dto.EstoqueDTO;
import br.com.mkoffice.dto.ParcelasDTO;
import br.com.mkoffice.dto.VendaDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.CalculadoraUtils;
import br.com.mkoffice.utils.DecimalUtils;
import br.com.mkoffice.utils.MkmtsUtil;
import br.com.mkoffice.view.constants.SituacaoPagamentoEnum;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.controller.menu.clientes.vo.ClienteVO;
import br.com.mkoffice.view.utils.FacesUtils;

/**
 * @author christopher.rozario
 *
 */
@ManagedBean
@SessionScoped
public class EfetuarVendaBean extends AbstractModelBean implements Serializable{

	private static final long serialVersionUID = 6656782255682492222L;

	private final String TELA_SELECIONAR_CLIENTE = "/content/m-venda/selecionarClienteVenda";
	private final String TELA_SELECIONAR_PRODUTOS = "/content/m-venda/selecionarProdutosVenda";
	private final String TELA_DADOS_VENDA = "/content/m-venda/dadosVenda";
	private final String TELA_FIN_VENDA = "/content/m-venda/finalizarVenda";

	private List<ClienteDTO>listaClientes;
	private ClienteDTO clienteSelecionado;
	private ClienteVO clienteVo;
	private boolean clienteSelecionadoFlag;
	
	private String codClienteFiltro;
	private String emailFiltro;
	private String nomeFiltro;
	private String celularFiltro;
	
	private VendaDTO venda;
	private List<EstoqueDTO> listaProdutosDisponiveis;// lista de produtos em estoque
	private List<EstoqueDTO>listaProdutosSelecionadosParaVenda;//usado somente para capturar os produtos que devem ir para o carrinho
	
	private Integer totalProdutosVenda;
	private BigDecimal totalAPagar;
	private BigDecimal totalLucro;
	
	private String codMkFiltro;
	private String nomeProdutoFiltro;
	private List<Integer> filtroCategoria;
	
	private List<ParcelasEntity> listaParcelamento;
	private List<SelectItem> listaParcelamentoSelectItem;
	private Integer lblNumeroProdutosPedido;
	private Integer lblNumeroItensPedido;
	private Integer numParcelasPagamento;
	private Date dtVctoPrimeiraParcela;
	
	private List<EstoqueDTO>filteredProdutcsSale;
	private EstoqueDTO produtoSelecionadoAddCarrinho;
	
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		carregarListaClientes();
		
		return TELA_SELECIONAR_CLIENTE;
	}

	@Override
	public String pesquisarFiltro() {
		try{
			listaClientes = clienteBO.buscarEntidadePorFiltro(codClienteFiltro, emailFiltro, nomeFiltro, celularFiltro, getLoginBean().getUsuario().getId());
		}catch(NoDataFoundException ndf){
			listaClientes = null;
			FacesUtils.addErrorMessage(ndf.getMessage());
		}catch (BusinessException b) {
			FacesUtils.addErrorMessage(b.getMessage());
		}
		return TELA_SELECIONAR_CLIENTE;
	}
	
	public String pesquisarFiltroProdutos() {
		listaProdutosDisponiveis = estoqueBO.filtrarEstoque(codMkFiltro, nomeProdutoFiltro, filtroCategoria, getLoginBean().getUsuario().getId());
		
		return TELA_SELECIONAR_PRODUTOS;
	}
	
	public String navegarSelecionarProdutos(){
		clienteVo = Adapter.dtoToVo(clienteSelecionado);
		venda = new VendaDTO();
		venda.setUsuario(getLoginBean().getUsuario());
		
		listaProdutosSelecionadosParaVenda = null;
		carregarListaProdutos();
		
		produtoSelecionadoAddCarrinho = new EstoqueDTO();
		venda.setValorVenda(BigDecimal.ZERO);
		totalProdutosVenda = 0;

		if(listaProdutosDisponiveis.isEmpty()){
			FacesUtils.addInfoMessage("Nï¿½o hï¿½ produtos em estoque.");
		}else{
			for (int i = 0; i < listaProdutosDisponiveis.size(); i++) {
				listaProdutosDisponiveis.get(i).setQtdeMovimentadoProduto(new Integer(0));
				listaProdutosDisponiveis.get(i).setQtdeTotalPontosMovimentadoProduto(CalculadoraUtils.somarTotalPontosCarrinho(listaProdutosDisponiveis.get(i).getQtdeMovimentadoProduto(), listaProdutosDisponiveis.get(i).getCodCatalogo().getPontosPorUnidade()));
				listaProdutosDisponiveis.get(i).setValorTotalMovimentadoProduto(CalculadoraUtils.valorTotalProdutosCarrinho(listaProdutosDisponiveis.get(i).getQtdeMovimentadoProduto(), listaProdutosDisponiveis.get(i).getCodCatalogo().getPreco()));
			}
		}

		return TELA_SELECIONAR_PRODUTOS;
	}
	
	public void limparProdutosSelecionados() {
		for (int i = 0; i < listaProdutosDisponiveis.size(); i++) {
			listaProdutosDisponiveis.get(i).setQtdeMovimentadoProduto(0);
			listaProdutosDisponiveis.get(i).setValorTotalMovimentadoProduto(BigDecimal.ZERO);
		}
		venda.setValorVenda(BigDecimal.ZERO);
		totalProdutosVenda = 0;
		produtoSelecionadoAddCarrinho = new EstoqueDTO();
	}
	
	public void selectAddProdutoVenda(EstoqueDTO produto){
		produtoSelecionadoAddCarrinho = produto;
	}
	
	public void addProdutoVenda() {  
		venda.setValorVenda(BigDecimal.ZERO);
		totalProdutosVenda = 0;
		for (int i = 0; i < listaProdutosDisponiveis.size(); i++) {
			if(listaProdutosDisponiveis.get(i).getCodCatalogo().getCodProduto().equals(produtoSelecionadoAddCarrinho.getCodCatalogo().getCodProduto())){
				listaProdutosDisponiveis.get(i).setValorTotalMovimentadoProduto(CalculadoraUtils.valorTotalProdutosCarrinho(produtoSelecionadoAddCarrinho.getQtdeMovimentadoProduto(), produtoSelecionadoAddCarrinho.getCodCatalogo().getPreco()));
				listaProdutosDisponiveis.get(i).setQtdeMovimentadoProduto(produtoSelecionadoAddCarrinho.getQtdeMovimentadoProduto());
			}
			totalProdutosVenda += listaProdutosDisponiveis.get(i).getQtdeMovimentadoProduto();
			venda.setValorVenda(venda.getValorVenda().add(listaProdutosDisponiveis.get(i).getValorTotalMovimentadoProduto()));
		}
	}
	
	public boolean habilitaBtnAvancarSelecProdutoVenda(){
		for (int i = 0; i < listaProdutosDisponiveis.size(); i++) {
			if(listaProdutosDisponiveis.get(i).getQtdeMovimentadoProduto() > 0){
				return true;
			}
		}
		
		return false;
	}
	
	public String navegarDadosVenda(){
		popularListaProdutosSelecionados();
		
		venda.setCliente(clienteSelecionado);
		totalAPagar = venda.getValorVenda();
		venda.setDataVenda(new Date());
		venda.setValorDescontoVenda(BigDecimal.ZERO);
		venda.setListaDeProdutos(Adapter.convertEstoqueDtoToItemMovimentadoCarrinho(listaProdutosSelecionadosParaVenda));
		venda.setValorLucroVenda(vendaBO.calculaValorLucroVenda(venda));
		venda.setQtdeTotalPontosVendidos(CalculadoraUtils.somarTotalPontosPedidoVenda(venda.getListaDeProdutos()));
		lblNumeroProdutosPedido = getListaProdutosSelecionadosParaVenda().size();
		lblNumeroItensPedido = 0;
		for (int i = 0; i < getListaProdutosSelecionadosParaVenda().size(); i++) {
			lblNumeroItensPedido+= getListaProdutosSelecionadosParaVenda().get(i).getQtdeMovimentadoProduto();
			venda.getListaDeProdutos().get(i).setSomaPontosProdutoCarrinho(CalculadoraUtils.somarTotalPontosCarrinho(venda.getListaDeProdutos().get(i).getQtdeProdutoCarrinho(), venda.getListaDeProdutos().get(i).getProduto().getPontosPorUnidade()));
		}
		totalLucro = venda.getValorLucroVenda();
		return TELA_DADOS_VENDA;
	}
	
	public void onBlurValorDesconto(){
		totalAPagar = CalculadoraUtils.calcularValorFinalTotalPago(venda.getValorVenda(), BigDecimal.ZERO, venda.getValorDescontoVenda());
		totalLucro = venda.getValorLucroVenda();
		totalLucro = totalLucro.subtract(venda.getValorVenda().subtract(totalAPagar));
	}
	
	public String navegarFinalizarPagamento(){
		
		numParcelasPagamento = null;
		dtVctoPrimeiraParcela = new Date();
		venda.setFormaPagamento(getCboFormaPagamento().get(0));
		onChangeFormaPagamento();	
		
		return TELA_FIN_VENDA;
	}
	
	public String navegarConcluirVenda(){
		
		venda.setValorLucroVenda(totalLucro);
		venda.setValorVenda(totalAPagar);
		venda.setParcelas(prepararParcelas());
		
		try{
			FacesUtils.addInfoMessage("Sua venda foi efetuada com sucesso.\nCód.Venda: "+vendaBO.efetuarVenda(venda).getCodVenda());
		}catch(BusinessException be){
			FacesUtils.addErrorMessage("Sua venda não foi efetuada com sucesso.");
		}
		
		return iniciarTela();
	}
	
	public void onChangeFormaPagamento(){
		listaParcelamento = new ArrayList<ParcelasEntity>();
		listaParcelamentoSelectItem = new ArrayList<SelectItem>();
		double preco = venda.getValorVenda().doubleValue();
		DecimalFormat decimalFormat = new DecimalFormat();   
		decimalFormat.setMaximumFractionDigits(2);
		
		for (int i = 0; i < venda.getFormaPagamento().getNumeroParcelas(); i++) {//personalizar a qtde de parcelas
			
			String lblAVista = "R$ "+DecimalUtils.format(BigDecimal.valueOf((preco/(i+1))))+" à vista";
			String lbl = (i+1)+"X sem juros R$ "+DecimalUtils.format(BigDecimal.valueOf((preco/(i+1))));
			
			ParcelasEntity parcela = new ParcelasEntity(null, i== 0 ? lblAVista : lbl, (i+1), BigDecimal.valueOf((preco/(i+1))), null, null, i==0?BigDecimal.valueOf((preco/(i+1))):null);
			
			listaParcelamento.add(parcela);
			listaParcelamentoSelectItem.add(new SelectItem(i, i== 0 ? lblAVista : lbl));
		}
	}

	@Override
	public void limparCamposFiltro() {
		listaClientes = null;
		clienteSelecionado = null;
		clienteVo = null;
		codClienteFiltro = null;
		emailFiltro = null;
		nomeFiltro = null;
		celularFiltro = null;
		codMkFiltro = null;
		nomeProdutoFiltro = null;
		filtroCategoria = null;
	}
	
	public String voltarParaSelecionarClientes(){
		return TELA_SELECIONAR_CLIENTE;
	}
	
	public String voltarParaSelecionarProdutos(){
		return TELA_SELECIONAR_PRODUTOS;
	}
	
	public String voltarParaDadosDaVenda(){
		return TELA_DADOS_VENDA;
	}
	
//	=============================== Mï¿½TODOS PRIVATES =============================== 
	private void carregarListaProdutos(){
		listaProdutosDisponiveis = estoqueBO.listarEstoqueCompleto(getLoginBean().getUsuario().getId());
	}
	
	private void carregarListaClientes(){
		try{
			listaClientes = clienteBO.buscarEntidadePorFiltro(null, null, null, null, getLoginBean().getUsuario().getId());
		}catch(BusinessException b){
			FacesUtils.addInfoMessage(b.getMessage());
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
	
	private void popularListaProdutosSelecionados(){
		listaProdutosSelecionadosParaVenda = new ArrayList<EstoqueDTO>();
		for (int i = 0; i < listaProdutosDisponiveis.size(); i++) {
			if(listaProdutosDisponiveis.get(i).getQtdeMovimentadoProduto() > 0){
				listaProdutosSelecionadosParaVenda.add(listaProdutosDisponiveis.get(i));
			}
		}
	}
	
	/***************	GETs E SETs		***************/

	public List<ClienteDTO> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<ClienteDTO> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ClienteDTO getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(ClienteDTO clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public ClienteVO getClienteVo() {
		return clienteVo;
	}

	public void setClienteVo(ClienteVO clienteVo) {
		this.clienteVo = clienteVo;
	}

	public boolean isClienteSelecionadoFlag() {
		return clienteSelecionadoFlag;
	}

	public void setClienteSelecionadoFlag(boolean clienteSelecionadoFlag) {
		this.clienteSelecionadoFlag = clienteSelecionadoFlag;
	}

	public VendaDTO getVenda() {
		return venda;
	}

	public void setVenda(VendaDTO venda) {
		this.venda = venda;
	}

	public List<EstoqueDTO> getListaProdutosDisponiveis() {
		return listaProdutosDisponiveis;
	}

	public void setListaProdutosDisponiveis(
			List<EstoqueDTO> listaProdutosDisponiveis) {
		this.listaProdutosDisponiveis = listaProdutosDisponiveis;
	}

	public List<EstoqueDTO> getListaProdutosSelecionadosParaVenda() {
		return listaProdutosSelecionadosParaVenda;
	}

	public void setListaProdutosSelecionadosParaVenda(
			List<EstoqueDTO> listaProdutosSelecionadosParaVenda) {
		this.listaProdutosSelecionadosParaVenda = listaProdutosSelecionadosParaVenda;
	}

	public BigDecimal getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(BigDecimal totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	public String getCodClienteFiltro() {
		return codClienteFiltro;
	}

	public void setCodClienteFiltro(String codClienteFiltro) {
		this.codClienteFiltro = codClienteFiltro;
	}

	public String getEmailFiltro() {
		return emailFiltro;
	}

	public void setEmailFiltro(String emailFiltro) {
		this.emailFiltro = emailFiltro;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public String getCelularFiltro() {
		return celularFiltro;
	}

	public void setCelularFiltro(String celularFiltro) {
		this.celularFiltro = celularFiltro;
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

	public List<Integer> getFiltroCategoria() {
		return filtroCategoria;
	}

	public void setFiltroCategoria(List<Integer> filtroCategoria) {
		this.filtroCategoria = filtroCategoria;
	}

	public List<SelectItem> getListaParcelamentoSelectItem() {
		return listaParcelamentoSelectItem;
	}

	public void setListaParcelamentoSelectItem(
			List<SelectItem> listaParcelamentoSelectItem) {
		this.listaParcelamentoSelectItem = listaParcelamentoSelectItem;
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

	public Integer getNumParcelasPagamento() {
		return numParcelasPagamento;
	}

	public void setNumParcelasPagamento(Integer numParcelasPagamento) {
		this.numParcelasPagamento = numParcelasPagamento;
	}

	public void setListaParcelamento(List<ParcelasEntity> listaParcelamento) {
		this.listaParcelamento = listaParcelamento;
	}

	public Date getDtVctoPrimeiraParcela() {
		return dtVctoPrimeiraParcela;
	}

	public void setDtVctoPrimeiraParcela(Date dtVctoPrimeiraParcela) {
		this.dtVctoPrimeiraParcela = dtVctoPrimeiraParcela;
	}

	public List<EstoqueDTO> getFilteredProdutcsSale() {
		return filteredProdutcsSale;
	}

	public void setFilteredProdutcsSale(List<EstoqueDTO> filteredProdutcsSale) {
		this.filteredProdutcsSale = filteredProdutcsSale;
	}

	public Integer getTotalProdutosVenda() {
		return totalProdutosVenda;
	}

	public void setTotalProdutosVenda(Integer totalProdutosVenda) {
		this.totalProdutosVenda = totalProdutosVenda;
	}

	public List<ParcelasEntity> getListaParcelamento() {
		return listaParcelamento;
	}

	public EstoqueDTO getProdutoSelecionadoAddCarrinho() {
		return produtoSelecionadoAddCarrinho;
	}

	public void setProdutoSelecionadoAddCarrinho(
			EstoqueDTO produtoSelecionadoAddCarrinho) {
		this.produtoSelecionadoAddCarrinho = produtoSelecionadoAddCarrinho;
	}

	public BigDecimal getTotalLucro() {
		return totalLucro;
	}

	public void setTotalLucro(BigDecimal totalLucro) {
		this.totalLucro = totalLucro;
	}

}
