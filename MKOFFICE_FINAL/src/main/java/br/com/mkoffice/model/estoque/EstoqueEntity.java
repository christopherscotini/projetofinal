package br.com.mkoffice.model.estoque;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.constants.PercentDescontoEnum;
import br.com.mkoffice.model.pedido.PedidoEntity;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.model.venda.VendaEntity;


@Entity
@Table(name = "TB_ESTOQUE")
public class EstoqueEntity implements Serializable {

	private static final long serialVersionUID = 1981222972220871359L;

	@Id
	@Column(name = "ID_ESTOQUE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NU_QTDE_PRODUTO_MOVIMENTADO")
	private Integer qtdeMovimentadoProduto;

	@Column(name = "NU_QTDE_PRODUTO_ESTOQUE_ATUAL")
	private Integer qtdeEmEstoqueAtual;

	@Column(name = "NU_SOMA_PONTOS_MOVIMENTADOS")
	private Integer qtdeTotalPontosMovimentadoProduto;

	@Column(name = "VL_VLR_TOTAL_MOVIMENTADO")
	private BigDecimal valorTotalMovimentadoProduto;
	
	@ManyToOne
	@JoinColumn(name = "TB_FLUXO_ESTOQUE_ESTOQUE_FK")
	private FluxoEstoqueEntity tipoFluxoEstoque;

	@Enumerated(EnumType.STRING)
	@Column(name = "NU_PERC_DESCONTO")
	private PercentDescontoEnum percDesconto;
	
	@Column(name = "DT_MOVIMENTACAO")
	private Date dtMovimentacao;

	@Column(name = "FL_DISPONIVEL")
	private boolean disponivel;
	
	@ManyToOne
	@JoinColumn(name = "TB_PEDIDO_TB_ESTOQUE_FK", nullable = true)
	private PedidoEntity codPedido;

	@ManyToOne
	@JoinColumn(name = "TB_VENDA_TB_ESTOQUE_FK", nullable = true)
	private VendaEntity codVenda;

	@ManyToOne
	@JoinColumn(name = "TB_CATALOGO_TB_ESTOQUE_FK", nullable = false)
	private CatalogoEntity codCatalogo;
	
	@ManyToOne
	@JoinColumn(name = "TB_USUARIO_FK")
	private UserEntity usuario;

	public EstoqueEntity() {
	}
	
	public EstoqueEntity(Long id, Integer qtdeMovimentadoProduto,
			Integer qtdeTotalPontosMovimentadoProduto,
			BigDecimal valorTotalMovimentadoProduto,
			FluxoEstoqueEntity tipoFluxoEstoque,
			PercentDescontoEnum percDesconto, Date dtMovimentacao,
			PedidoEntity codPedido, VendaEntity codVenda,
			CatalogoEntity codCatalogo,
			boolean disponivel, Integer qtdeEmEstoqueAtual) {
		super();
		this.id = id;
		this.qtdeMovimentadoProduto = qtdeMovimentadoProduto;
		this.qtdeTotalPontosMovimentadoProduto = qtdeTotalPontosMovimentadoProduto;
		this.valorTotalMovimentadoProduto = valorTotalMovimentadoProduto;
		this.tipoFluxoEstoque = tipoFluxoEstoque;
		this.percDesconto = percDesconto;
		this.dtMovimentacao = dtMovimentacao;
		this.codPedido = codPedido;
		this.codVenda = codVenda;
		this.codCatalogo = codCatalogo;
		this.disponivel = disponivel;
		this.qtdeEmEstoqueAtual = qtdeEmEstoqueAtual;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQtdeMovimentadoProduto() {
		return qtdeMovimentadoProduto;
	}

	public void setQtdeMovimentadoProduto(Integer qtdeMovimentadoProduto) {
		this.qtdeMovimentadoProduto = qtdeMovimentadoProduto;
	}

	public Integer getQtdeTotalPontosMovimentadoProduto() {
		return qtdeTotalPontosMovimentadoProduto;
	}

	public void setQtdeTotalPontosMovimentadoProduto(
			Integer qtdeTotalPontosMovimentadoProduto) {
		this.qtdeTotalPontosMovimentadoProduto = qtdeTotalPontosMovimentadoProduto;
	}

	public BigDecimal getValorTotalMovimentadoProduto() {
		return valorTotalMovimentadoProduto;
	}

	public void setValorTotalMovimentadoProduto(
			BigDecimal valorTotalMovimentadoProduto) {
		this.valorTotalMovimentadoProduto = valorTotalMovimentadoProduto;
	}

	public FluxoEstoqueEntity getTipoFluxoEstoque() {
		return tipoFluxoEstoque;
	}

	public void setTipoFluxoEstoque(FluxoEstoqueEntity tipoFluxoEstoque) {
		this.tipoFluxoEstoque = tipoFluxoEstoque;
	}

	public PercentDescontoEnum getPercDesconto() {
		return percDesconto;
	}

	public void setPercDesconto(PercentDescontoEnum percDesconto) {
		this.percDesconto = percDesconto;
	}

	public Date getDtMovimentacao() {
		return dtMovimentacao;
	}

	public void setDtMovimentacao(Date dtMovimentacao) {
		this.dtMovimentacao = dtMovimentacao;
	}

	public PedidoEntity getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(PedidoEntity codPedido) {
		this.codPedido = codPedido;
	}

	public VendaEntity getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(VendaEntity codVenda) {
		this.codVenda = codVenda;
	}

	public CatalogoEntity getCodCatalogo() {
		return codCatalogo;
	}

	public void setCodCatalogo(CatalogoEntity codCatalogo) {
		this.codCatalogo = codCatalogo;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean dispnivel) {
		this.disponivel = dispnivel;
	}

	public Integer getQtdeEmEstoqueAtual() {
		return qtdeEmEstoqueAtual;
	}

	public void setQtdeEmEstoqueAtual(Integer qtdeEmEstoqueAtual) {
		this.qtdeEmEstoqueAtual = qtdeEmEstoqueAtual;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}
	
}
