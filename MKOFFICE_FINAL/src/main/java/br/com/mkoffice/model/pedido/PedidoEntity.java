/**
 * 
 */
package br.com.mkoffice.model.pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.FormaPagamentoEntity;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.constants.PercentDescontoEnum;

/**
 * @author christopher.rozario
 * 
 */
@Entity
@Table(name = "TB_PEDIDO")
public class PedidoEntity implements Serializable {

	private static final long serialVersionUID = 1534040142023718018L;

	@Id
	@Column(name = "ID_PEDIDO", nullable=false, unique=true)
	private Long codPedido;
	
	@Column(name = "DT_PEDIDO")
	private Date dtPedido;
	
	@Column(name = "VL_VLR_TOTAL_PAGO")
	private BigDecimal valorTotalPago;
	
	@Column(name = "VL_VLR_TOTAL_REVENDA")
	private BigDecimal valorTotalEmProdutos;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PC_PERC_DESCONTO")
	private PercentDescontoEnum percDesconto;
	
	@Column(name = "VL_VLR_TOTAL_COM_DESCONTO")
	private BigDecimal vlrTotalComDesconto;
	
	@Column(name = "NU_TOTAL_PONTOS_PEDIDO")
	private Integer pontosTotalPedido;
	
	@Column(name = "VL_LUCRO_TOTAL")
	private BigDecimal lucroTotal;
	
	@Column(name = "VL_FRETE")
	private BigDecimal valorFrete;

	@Column(name = "VL_BONUS_UTILIZADO")
	private BigDecimal valorBonusUtilizado;
	
	@ManyToOne
	@JoinColumn(name = "TB_FORMA_PAGTO_PEDIDO_FK")
	private FormaPagamentoEntity formaDePagamento;

	@OneToMany(mappedBy = "codPedido", fetch = FetchType.LAZY)
	private List<ParcelasEntity> parcelas;

	@OneToMany(mappedBy = "codPedido", fetch = FetchType.LAZY, cascade= CascadeType.PERSIST)
	private List<HistoricoPedidosEntity> pedidoProdutosList;
	
	@ManyToOne
	@JoinColumn(name = "TB_USUARIO_FK")
	private UserEntity usuario;
	
	public PedidoEntity() {

	}
	

	public PedidoEntity(Long codPedido) {
		super();
		this.codPedido = codPedido;
	}

	public PedidoEntity(Long codPedido, Date dtPedido,
			BigDecimal valorTotalPago, BigDecimal valorTotalEmProdutos,
			PercentDescontoEnum percDesconto, BigDecimal vlrTotalComDesconto,
			Integer pontosTotalPedido, BigDecimal lucroTotal,
			BigDecimal valorFrete,
			List<HistoricoPedidosEntity> pedidoProdutosList,
		List<ParcelasEntity> parcelas, FormaPagamentoEntity formaDePagamento) {
		super();
		this.codPedido = codPedido;
		this.dtPedido = dtPedido;
		this.valorTotalPago = valorTotalPago;
		this.valorTotalEmProdutos = valorTotalEmProdutos;
		this.percDesconto = percDesconto;
		this.vlrTotalComDesconto = vlrTotalComDesconto;
		this.pontosTotalPedido = pontosTotalPedido;
		this.lucroTotal = lucroTotal;
		this.valorFrete = valorFrete;
		this.pedidoProdutosList = pedidoProdutosList;
		this.parcelas = parcelas;
		this.formaDePagamento = formaDePagamento;
	}


	public List<ParcelasEntity> getParcelas() {
		return parcelas;
	}


	public void setParcelas(List<ParcelasEntity> parcelas) {
		this.parcelas = parcelas;
	}


	public List<HistoricoPedidosEntity> getPedidoProdutosList() {
		return pedidoProdutosList;
	}

	public void setPedidoProdutosList(
			List<HistoricoPedidosEntity> pedidoProdutosList) {
		this.pedidoProdutosList = pedidoProdutosList;
	}

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

	public BigDecimal getValorTotalPago() {
		return valorTotalPago;
	}

	public void setValorTotalPago(BigDecimal valorTotalPago) {
		this.valorTotalPago = valorTotalPago;
	}

	public BigDecimal getValorTotalEmProdutos() {
		return valorTotalEmProdutos;
	}

	public void setValorTotalEmProdutos(BigDecimal valorTotalEmProdutos) {
		this.valorTotalEmProdutos = valorTotalEmProdutos;
	}

	public PercentDescontoEnum getPercDesconto() {
		return percDesconto;
	}

	public void setPercDesconto(PercentDescontoEnum percDesconto) {
		this.percDesconto = percDesconto;
	}

	public BigDecimal getVlrTotalComDesconto() {
		return vlrTotalComDesconto;
	}

	public void setVlrTotalComDesconto(BigDecimal vlrTotalComDesconto) {
		this.vlrTotalComDesconto = vlrTotalComDesconto;
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

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPedido == null) ? 0 : codPedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoEntity other = (PedidoEntity) obj;
		if (codPedido == null) {
			if (other.codPedido != null)
				return false;
		} else if (!codPedido.equals(other.codPedido))
			return false;
		return true;
	}


	public BigDecimal getValorBonusUtilizado() {
		return valorBonusUtilizado;
	}


	public void setValorBonusUtilizado(BigDecimal valorBonusUtilizado) {
		this.valorBonusUtilizado = valorBonusUtilizado;
	}


	public FormaPagamentoEntity getFormaDePagamento() {
		return formaDePagamento;
	}


	public void setFormaDePagamento(FormaPagamentoEntity formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}


	public UserEntity getUsuario() {
		return usuario;
	}


	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}

}
