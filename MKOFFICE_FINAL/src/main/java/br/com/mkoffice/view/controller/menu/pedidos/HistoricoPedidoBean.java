/**
 * 
 */
package br.com.mkoffice.view.controller.menu.pedidos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.ParcelasDTO;
import br.com.mkoffice.dto.PedidoDTO;
import br.com.mkoffice.view.controller.AbstractModelBean;

/**
 * @author christopher.rozario
 *
 */
@ManagedBean
@SessionScoped
public class HistoricoPedidoBean extends AbstractModelBean {

	private final String TELA_LISTAR_PEDIDOS = "/content/m-pedidos/sm-consultarpedido/listarHistoricoPedidos";
	private final String TELA_DETALHE_PEDIDO = "/content/m-pedidos/sm-consultarpedido/detalharHistoricoPedido";

	// CAMPOS DE FILTRO
	private String codPedidoFiltro;
	private DataFilter dtFiltro;

	private List<PedidoDTO> filteredValue;
	private List<PedidoDTO> listaDePedidosRealizados;
	private PedidoDTO pedidoSelecionado;
	private PedidoDTO pedidoDto;

	private BigDecimal vlrTotalRevendaLbl;
	private BigDecimal vlrTotalAtacadoLbl;

	private ParcelasDTO parcelaSelecionadaPagamento;

	@Override
	public String iniciarTela() {
		limparCamposFiltro();
//		carregarListaPedidosRealizados();
		pesquisarFiltro();
		pedidoSelecionado = null;
		// Map<String, BigDecimal>calcValores =
		// PedidoCalcUtils.calcularValorTotalPedido(listaDePedidosRealizados);
		// vlrTotalRevendaLbl = calcValores.get("vlrTotalRevenda");
		// vlrTotalAtacadoLbl = calcValores.get("vlrTotalAtacado");

		return TELA_LISTAR_PEDIDOS;
	}

	@Override
	public void limparCamposFiltro() {
		codPedidoFiltro = null;
		dtFiltro = new DataFilter(true);
		vlrTotalRevendaLbl = null;
		vlrTotalAtacadoLbl = null;
		setFilteredValue(null);
	}

	public void limparPedidoSelecionado() {
		pedidoSelecionado = null;
	}

	@Override
	public String pesquisarFiltro() {
		listaDePedidosRealizados = pedidoBO.consultarPedidos(codPedidoFiltro,
				dtFiltro, getLoginBean().getUsuario().getId());
		pedidoSelecionado = null;

		return TELA_LISTAR_PEDIDOS;
	}

	public String navegarDetalharPedido() {
		isDesabilitaPagamentoParcela();
		return TELA_DETALHE_PEDIDO;
	}

	public String navegarVoltarDetalhePedido() {

		return pesquisarFiltro();
	}

	public void onclickBtnPagamentoParcela(ParcelasDTO p) {
		parcelaSelecionadaPagamento = p;
		parcelaSelecionadaPagamento.setDtPagamento(new Date());
	}

	public void btnCancelarModal() {
		for (int i = 0; i < pedidoSelecionado.getParcelas().size(); i++) {
			if (!pedidoSelecionado.getParcelas().get(i)
					.isDesabilitaPagamentoParcela()) {
				pedidoSelecionado.getParcelas().get(i).setDtPagamento(null);
			}
		}
	}

	public void efetuarPagamentoParcela() {
		parcelaBO.efetuarPagamento(parcelaSelecionadaPagamento);
		pedidoSelecionado = pedidoBO.consultarPedidos(
				pedidoSelecionado.getCodPedido().toString(), null,
				getLoginBean().getUsuario().getId()).get(0);
		isDesabilitaPagamentoParcela();
	}

	// =============================== M�TODOS PRIVATES
	// ===============================
	private void isDesabilitaPagamentoParcela() {
		for (int i = 0; i < pedidoSelecionado.getParcelas().size(); i++) {
			if (pedidoSelecionado.getParcelas().get(i).isQuitado()) {
				pedidoSelecionado.getParcelas().get(i)
						.setDesabilitaPagamentoParcela(true);
			} else {
				if (i > 0) {
					if (pedidoSelecionado.getParcelas().get(i - 1).isQuitado()) {
						if (i == (pedidoSelecionado.getParcelas().size() - 1)) {
							pedidoSelecionado.getParcelas().get(i)
									.setDesabilitaPagamentoParcela(false);
						} else {
							if (!pedidoSelecionado.getParcelas().get(i - 1)
									.isDesabilitaPagamentoParcela()) {
								pedidoSelecionado.getParcelas().get(i)
										.setDesabilitaPagamentoParcela(true);
							} else {
								pedidoSelecionado.getParcelas().get(i)
										.setDesabilitaPagamentoParcela(false);
							}
						}
					} else {
						pedidoSelecionado.getParcelas().get(i)
								.setDesabilitaPagamentoParcela(true);
					}
				} else {
					pedidoSelecionado.getParcelas().get(i)
							.setDesabilitaPagamentoParcela(true);
				}
			}
		}

	}

	private void carregarListaPedidosRealizados() {
		listaDePedidosRealizados = pedidoBO
				.listarPedidosRealizados(getLoginBean().getUsuario().getId());
	}

	// GETTERS AND SETTERS
	public String getCodPedidoFiltro() {
		return codPedidoFiltro;
	}

	public void setCodPedidoFiltro(String codPedidoFiltro) {
		this.codPedidoFiltro = codPedidoFiltro;
	}

	public List<PedidoDTO> getListaDePedidosRealizados() {
		return listaDePedidosRealizados;
	}

	public void setListaDePedidosRealizados(
			List<PedidoDTO> listaDePedidosRealizados) {
		this.listaDePedidosRealizados = listaDePedidosRealizados;
	}

	public PedidoDTO getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(PedidoDTO pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}

	public BigDecimal getVlrTotalRevendaLbl() {
		return vlrTotalRevendaLbl;
	}

	public void setVlrTotalRevendaLbl(BigDecimal vlrTotalRevendaLbl) {
		this.vlrTotalRevendaLbl = vlrTotalRevendaLbl;
	}

	public BigDecimal getVlrTotalAtacadoLbl() {
		return vlrTotalAtacadoLbl;
	}

	public void setVlrTotalAtacadoLbl(BigDecimal vlrTotalAtacadoLbl) {
		this.vlrTotalAtacadoLbl = vlrTotalAtacadoLbl;
	}

	public PedidoDTO getPedidoDto() {
		return pedidoDto;
	}

	public void setPedidoDto(PedidoDTO pedidoDto) {
		this.pedidoDto = pedidoDto;
	}

	public ParcelasDTO getParcelaSelecionadaPagamento() {
		return parcelaSelecionadaPagamento;
	}

	public void setParcelaSelecionadaPagamento(
			ParcelasDTO parcelaSelecionadaPagamento) {
		this.parcelaSelecionadaPagamento = parcelaSelecionadaPagamento;
	}

	public List<PedidoDTO> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<PedidoDTO> filteredValue) {
		this.filteredValue = filteredValue;
	}

	public DataFilter getDtFiltro() {
		return dtFiltro;
	}

	public void setDtFiltro(DataFilter dtFiltro) {
		this.dtFiltro = dtFiltro;
	}

}
