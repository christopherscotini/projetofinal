/**
 * 
 */
package br.com.mkoffice.view.controller.menu.vendas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.ParcelasDTO;
import br.com.mkoffice.dto.VendaDTO;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.view.constants.CodeBeanConstants;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.controller.menu.clientes.vo.ClienteVO;
import br.com.mkoffice.view.controller.menu.vendas.vo.VendaVO;

/**
 * @author christopher.rozario
 *
 */
@ManagedBean
@SessionScoped
public class HistoricoVendaBean extends AbstractModelBean{
	
	private final String TELA_LISTAR_HISTORICO_VENDAS = "/content/m-venda/listarHistoricoVendas";
	private final String TELA_DETALHAR_VENDA = "/content/m-venda/detalharVenda";
	
	private VendaDTO vendaSelecionada;
	private List<VendaDTO>listaVendasEfetuadas;
	private List<VendaDTO>filteredValue;
	
	private BigDecimal totalVendidoLbl;
	private String codVendaFiltro;
	private String nomeClienteFiltro;
	private DataFilter dataFiltro;
	
	
	private ClienteVO clienteVo;
	private VendaVO vendaVo;
	
	private ParcelasDTO parcelaSelecionadaPagamento;
	
	
	private boolean chamadaExterna;
	private Integer codeBeanExterno;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		carregarListaDeVendas();
		vendaSelecionada = null;

		return TELA_LISTAR_HISTORICO_VENDAS;
	}

	@Override
	public void limparCamposFiltro() {
		codVendaFiltro = null;
		nomeClienteFiltro = null;
		dataFiltro = new DataFilter(true);
		filteredValue = null;
	}

	@Override
	public String pesquisarFiltro() {
		listaVendasEfetuadas = vendaBO.filtrarVenda(codVendaFiltro, nomeClienteFiltro, dataFiltro, getLoginBean().getUsuario().getId());
		vendaSelecionada = null;
		
		return TELA_LISTAR_HISTORICO_VENDAS;
	}
	
	public String navegarDetalharVenda(){
		clienteVo = Adapter.dtoToVo(vendaSelecionada.getCliente());
		vendaVo = Adapter.dtoToVo(vendaSelecionada);
		isDesabilitaPagamentoParcela();
		
		return TELA_DETALHAR_VENDA;
	}

	public String navegarEditarVenda(){
		
		return "";
	}
	
	public String voltarTelaHistoricoVendas(){
		if(chamadaExterna){
			if(codeBeanExterno.equals(CodeBeanConstants.CLIENTE_BEAN)){
				return "/content/m-cliente/detCliente";
			}
		}
		
		return pesquisarFiltro();
	}
	
	
	public void onclickBtnPagamentoParcela(ParcelasDTO p){
		parcelaSelecionadaPagamento = p;
		parcelaSelecionadaPagamento.setDtPagamento(new Date());
	}
	
	public void efetuarPagamentoParcela(){
		parcelaBO.efetuarPagamento(parcelaSelecionadaPagamento);
		vendaSelecionada = vendaBO.filtrarVenda(vendaSelecionada.getCodVenda().toString(), null, null, getLoginBean().getUsuario().getId()).get(0);
		vendaVo = Adapter.dtoToVo(vendaSelecionada);
		isDesabilitaPagamentoParcela();
	}
	

//	=============================== M�TODOS PRIVATES =============================== 
	private void isDesabilitaPagamentoParcela(){
		for (int i = 0; i < vendaSelecionada.getParcelas().size(); i++) {
			if(vendaSelecionada.getParcelas().get(i).isQuitado()){
				vendaSelecionada.getParcelas().get(i).setDesabilitaPagamentoParcela(true);
			}else{
				if( i > 0){
					if(vendaSelecionada.getParcelas().get(i-1).isQuitado()){
						if(i == (vendaSelecionada.getParcelas().size()-1)){
							vendaSelecionada.getParcelas().get(i).setDesabilitaPagamentoParcela(false);
						}else{
							if(!vendaSelecionada.getParcelas().get(i-1).isDesabilitaPagamentoParcela()){
								vendaSelecionada.getParcelas().get(i).setDesabilitaPagamentoParcela(true);
							}else{
								vendaSelecionada.getParcelas().get(i).setDesabilitaPagamentoParcela(false);
							}
						}
					}else{
						vendaSelecionada.getParcelas().get(i).setDesabilitaPagamentoParcela(true);
					}
				}else{
					vendaSelecionada.getParcelas().get(i).setDesabilitaPagamentoParcela(true);
				}
			}
		}  
			
	}
	
	private void carregarListaDeVendas(){
		listaVendasEfetuadas = vendaBO.listarVendas(getLoginBean().getUsuario().getId());
	}
	
/***************	GETs E SETs		***************/

	public BigDecimal getTotalVendidoLbl() {
		totalVendidoLbl = BigDecimal.ZERO;
		for (int i = 0; i < getListaVendasEfetuadas().size(); i++) {
			totalVendidoLbl = totalVendidoLbl.add(getListaVendasEfetuadas().get(i).getValorVenda());
		}
		return totalVendidoLbl;
	}

	public VendaDTO getVendaSelecionada() {
		return vendaSelecionada;
	}

	public void setVendaSelecionada(VendaDTO vendaSelecionada) {
		this.vendaSelecionada = vendaSelecionada;
	}

	public List<VendaDTO> getListaVendasEfetuadas() {
		return listaVendasEfetuadas;
	}

	public void setListaVendasEfetuadas(List<VendaDTO> listaVendasEfetuadas) {
		this.listaVendasEfetuadas = listaVendasEfetuadas;
	}

	public void setTotalVendidoLbl(BigDecimal totalVendidoLbl) {
		this.totalVendidoLbl = totalVendidoLbl;
	}

	public String getCodVendaFiltro() {
		return codVendaFiltro;
	}

	public void setCodVendaFiltro(String codVendaFiltro) {
		this.codVendaFiltro = codVendaFiltro;
	}

	public String getNomeClienteFiltro() {
		return nomeClienteFiltro;
	}

	public void setNomeClienteFiltro(String nomeClienteFiltro) {
		this.nomeClienteFiltro = nomeClienteFiltro;
	}

	public ClienteVO getClienteVo() {
		return clienteVo;
	}

	public void setClienteVo(ClienteVO clienteVo) {
		this.clienteVo = clienteVo;
	}

	public VendaVO getVendaVo() {
		return vendaVo;
	}

	public void setVendaVo(VendaVO vendaVo) {
		this.vendaVo = vendaVo;
	}
	
	//DETALHE DA VENDA
	public Integer getNumeroTotalProdutosVendidos(){
		Integer retorno = 0;
		for (int i = 0; i < vendaSelecionada.getListaDeProdutos().size(); i++) {
			retorno += vendaSelecionada.getListaDeProdutos().get(i).getQtdeProdutoCarrinho();
		}
		return retorno;
	}

	public ParcelasDTO getParcelaSelecionadaPagamento() {
		return parcelaSelecionadaPagamento;
	}

	public void setParcelaSelecionadaPagamento(
			ParcelasDTO parcelaSelecionadaPagamento) {
		this.parcelaSelecionadaPagamento = parcelaSelecionadaPagamento;
	}

	public boolean isChamadaExterna() {
		return chamadaExterna;
	}

	public void setChamadaExterna(boolean chamadaExterna) {
		this.chamadaExterna = chamadaExterna;
	}

	public Integer getCodeBeanExterno() {
		return codeBeanExterno;
	}

	public void setCodeBeanExterno(Integer codeBeanExterno) {
		this.codeBeanExterno = codeBeanExterno;
	}

	public List<VendaDTO> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<VendaDTO> filteredValue) {
		this.filteredValue = filteredValue;
	}

	public DataFilter getDataFiltro() {
		return dataFiltro;
	}

	public void setDataFiltro(DataFilter dataFiltro) {
		this.dataFiltro = dataFiltro;
	}

	

}
