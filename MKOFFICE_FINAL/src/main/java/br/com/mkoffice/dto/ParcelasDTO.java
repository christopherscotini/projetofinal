/**
 * 
 */
package br.com.mkoffice.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.pedido.PedidoEntity;
import br.com.mkoffice.model.venda.VendaEntity;

/**
 * @author christopher.rozario
 *
 */
public class ParcelasDTO {

	private Long id;
	private VendaEntity codVenda;
	private PedidoEntity codPedido;
	private Date dtVencimento;
	private String descricao;
	private Integer numParcela;
	private BigDecimal valorParcela;
	private SituacaoEntity codSituacaoParcela;
	private Date dtPagamento;
	private BigDecimal valorPago;
	private UserEntity usuario;

	private boolean desabilitaPagamentoParcela;
	
	public ParcelasDTO() {
		// TODO Auto-generated constructor stub
	}

	public ParcelasDTO(Long id, VendaEntity codVenda, PedidoEntity codPedido,
			Date dtVencimento, String descricao, Integer numParcela,
			BigDecimal valorParcela, SituacaoEntity codSituacaoParcela,
			Date dtPagamento, BigDecimal valorPago) {
		super();
		this.id = id;
		this.codVenda = codVenda;
		this.codPedido = codPedido;
		this.dtVencimento = dtVencimento;
		this.descricao = descricao;
		this.numParcela = numParcela;
		this.valorParcela = valorParcela;
		this.codSituacaoParcela = codSituacaoParcela;
		this.dtPagamento = dtPagamento;
		this.valorPago = valorPago;
	}

	public boolean isQuitado(){
		return dtPagamento != null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VendaEntity getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(VendaEntity codVenda) {
		this.codVenda = codVenda;
	}

	public PedidoEntity getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(PedidoEntity codPedido) {
		this.codPedido = codPedido;
	}

	public Date getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public SituacaoEntity getCodSituacaoParcela() {
		return codSituacaoParcela;
	}

	public void setCodSituacaoParcela(SituacaoEntity codSituacaoParcela) {
		this.codSituacaoParcela = codSituacaoParcela;
	}

	public Date getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public boolean isDesabilitaPagamentoParcela() {
		return desabilitaPagamentoParcela;
	}

	public void setDesabilitaPagamentoParcela(boolean desabilitaPagamentoParcela) {
		this.desabilitaPagamentoParcela = desabilitaPagamentoParcela;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}
	
}