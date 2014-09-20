package br.com.mkoffice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.model.PedidoEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class PedidoDTO {

	private Long codPedido;
	private Date dtPedido;
	private BigDecimal valorTotalAtacado;
	private BigDecimal valorFinalTotalPago;
	private BigDecimal valorTotalEmProdutos;
	private Integer pontosTotalPedido;
	private BigDecimal lucroTotal;
	private BigDecimal valorFrete;
	private BigDecimal valorBonusUtilizado;
	private Integer porcDesconto;
	private List<ItemMovimentadoCarrinhoDTO>listaProdutosComprados;
	private List<ParcelasDTO>parcelas;
	private FormaPagamentoDTO formaDePagamento;
	private UserDTO usuario;
	
	private String descSituacaoPagamento;
	
	public PedidoDTO() {
		// TODO Auto-generated constructor stub
	}	
	
	public PedidoDTO(Long codPedido, Date dtPedido,
			BigDecimal valorTotalAtacado, BigDecimal valorFinalTotalPago,
			BigDecimal valorTotalEmProdutos, Integer pontosTotalPedido,
			BigDecimal lucroTotal, BigDecimal valorFrete,
			BigDecimal valorBonusUtilizado, Integer porcDesconto,
			List<ItemMovimentadoCarrinhoDTO> listaProdutosComprados,
			List<ParcelasDTO> parcelas,
			List<String> arrayIDsProdutos,
			FormaPagamentoDTO formaDePagamento) {
		super();
		this.codPedido = codPedido;
		this.dtPedido = dtPedido;
		this.valorTotalAtacado = valorTotalAtacado;
		this.valorFinalTotalPago = valorFinalTotalPago;
		this.valorTotalEmProdutos = valorTotalEmProdutos;
		this.pontosTotalPedido = pontosTotalPedido;
		this.lucroTotal = lucroTotal;
		this.valorFrete = valorFrete;
		this.valorBonusUtilizado = valorBonusUtilizado;
		this.porcDesconto = porcDesconto;
		this.listaProdutosComprados = listaProdutosComprados;
		this.parcelas = parcelas;
		this.arrayIDsProdutos = arrayIDsProdutos;
		this.formaDePagamento = formaDePagamento;
	}
	
	public static PedidoDTO entityToDto(PedidoEntity entity){
		PedidoDTO dto = new PedidoDTO();

		dto.setCodPedido(entity.getCodPedido());
		dto.setDtPedido(entity.getDtPedido());
		dto.setLucroTotal(entity.getLucroTotal());
		dto.setPorcDesconto(entity.getPercDesconto().getCodigo());
		dto.setPontosTotalPedido(entity.getPontosTotalPedido());
		dto.setValorFrete(entity.getValorFrete());
		dto.setValorTotalEmProdutos(entity.getValorTotalEmProdutos());
		dto.setValorFinalTotalPago(entity.getValorTotalPago());
		dto.setValorTotalAtacado(entity.getVlrTotalComDesconto());
		dto.setValorBonusUtilizado(entity.getValorBonusUtilizado());
		dto.setListaProdutosComprados(ItemMovimentadoCarrinhoDTO.historicoPedidoToItemMovimentado(entity.getPedidoProdutosList()));
		dto.setParcelas(ParcelasDTO.listEntityToListDto(entity.getParcelas()));
		dto.setFormaDePagamento(FormaPagamentoDTO.convertEntotyToDTO(entity.getFormaDePagamento()));
		dto.setUsuario(UserDTO.entityToDTO(entity.getUsuario()));
		
		return dto;
	}
	
	private List<String> arrayIDsProdutos;
	
	public Long getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(Long codPedido) {
		this.codPedido = codPedido;
	}
	public Date getDtPedido() {
		return dtPedido;
	}
	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}
	public BigDecimal getValorTotalAtacado() {
		return valorTotalAtacado;
	}
	public void setValorTotalAtacado(BigDecimal valorTotalAtacado) {
		this.valorTotalAtacado = valorTotalAtacado;
	}
	public BigDecimal getValorFinalTotalPago() {
		return valorFinalTotalPago;
	}
	public void setValorFinalTotalPago(BigDecimal valorFinalTotalPago) {
		this.valorFinalTotalPago = valorFinalTotalPago;
	}
	public BigDecimal getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}
	public BigDecimal getValorTotalEmProdutos() {
		return valorTotalEmProdutos;
	}
	public void setValorTotalEmProdutos(BigDecimal valorTotalEmProdutos) {
		this.valorTotalEmProdutos = valorTotalEmProdutos;
	}
	public Integer getPontosTotalPedido() {
		return pontosTotalPedido;
	}
	public void setPontosTotalPedido(Integer pontosTotalPedido) {
		this.pontosTotalPedido = pontosTotalPedido;
	}
	public BigDecimal getLucroTotal() {
		return lucroTotal;
	}
	public void setLucroTotal(BigDecimal lucroTotal) {
		this.lucroTotal = lucroTotal;
	}
	public List<ItemMovimentadoCarrinhoDTO> getListaProdutosComprados() {
		return listaProdutosComprados;
	}
	public void setListaProdutosComprados(List<ItemMovimentadoCarrinhoDTO> listaProdutosComprados) {
		this.listaProdutosComprados = listaProdutosComprados;
	}
	public Integer getPorcDesconto() {
		return porcDesconto;
	}
	public void setPorcDesconto(Integer porcDesconto) {
		this.porcDesconto = porcDesconto;
	}
	public String getDtPedidoFormatado() {
		return dtPedido == null ? "" : MkmtsUtil.converterDataString(dtPedido, "dd/MM/yyyy");
	}
	public List<String> getArrayIDsProdutos() {
		return arrayIDsProdutos;
	}
	public void setArrayIDsProdutos(List<String> arrayIDsProdutos) {
		this.arrayIDsProdutos = arrayIDsProdutos;
	}
	public BigDecimal getValorBonusUtilizado() {
		return valorBonusUtilizado;
	}
	public void setValorBonusUtilizado(BigDecimal valorBonusUtilizado) {
		this.valorBonusUtilizado = valorBonusUtilizado;
	}
	public List<ParcelasDTO> getParcelas() {
		return parcelas;
	}
	public void setParcelas(List<ParcelasDTO> parcelas) {
		this.parcelas = parcelas;
	}

	public FormaPagamentoDTO getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaPagamentoDTO formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public String getDescSituacaoPagamento() {
		return descSituacaoPagamento;
	}

	public void setDescSituacaoPagamento(String descSituacaoPagamento) {
		this.descSituacaoPagamento = descSituacaoPagamento;
	}

	public UserDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UserDTO usuario) {
		this.usuario = usuario;
	}
}
