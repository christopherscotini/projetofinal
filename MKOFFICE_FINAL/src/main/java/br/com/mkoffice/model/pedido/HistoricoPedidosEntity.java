package br.com.mkoffice.model.pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.mkoffice.model.produto.CatalogoEntity;

@Entity
@Table(name="TB_PEDIDO_JOIN_TB_CATALOGO")
public class HistoricoPedidosEntity implements Serializable{

	private static final long serialVersionUID = 1981222972220871359L;

	@ManyToOne
	@JoinColumn(name = "ID_PEDIDO_FK")
	private PedidoEntity codPedido;
	
	@ManyToOne
	@JoinColumn(name="ID_CATALOGO_FK")
	private CatalogoEntity codCatalogo;

	@Column(name="NU_QTDE_COMPRADA")
	private Integer qdteCompradaProduto;

	@Column(name="NU_SOMA_PONTOS_COMPRADOS")
	private Integer qtdeTotalPontosCompradosProduto;

	@Column(name="VL_VLR_TOTAL_COMPRADO")
	private BigDecimal valorTotalCompradoProduto;

	@Column(name="DT_COMPRA")
	private Date dataCompraProduto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_JOIN")
	private Long ID;
	
	public HistoricoPedidosEntity() {

	}
	
	public HistoricoPedidosEntity(PedidoEntity codPedido,
			CatalogoEntity codCatalogo, Integer qdteCompradaProduto,
			Integer qtdeTotalPontosCompradosProduto,
			BigDecimal valorTotalCompradoProduto, Date dataCompraProduto) {
		super();
		this.codPedido = codPedido;
		this.codCatalogo = codCatalogo;
		this.qdteCompradaProduto = qdteCompradaProduto;
		this.qtdeTotalPontosCompradosProduto = qtdeTotalPontosCompradosProduto;
		this.valorTotalCompradoProduto = valorTotalCompradoProduto;
		this.dataCompraProduto = dataCompraProduto;
	}



	public PedidoEntity getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(PedidoEntity codPedido) {
		this.codPedido = codPedido;
	}

	public CatalogoEntity getCodCatalogo() {
		return codCatalogo;
	}

	public void setCodCatalogo(CatalogoEntity codCatalogo) {
		this.codCatalogo = codCatalogo;
	}

	public Integer getQdteCompradaProduto() {
		return qdteCompradaProduto;
	}

	public void setQdteCompradaProduto(Integer qdteCompradaProduto) {
		this.qdteCompradaProduto = qdteCompradaProduto;
	}

	public Integer getQtdeTotalPontosCompradosProduto() {
		return qtdeTotalPontosCompradosProduto;
	}

	public void setQtdeTotalPontosCompradosProduto(
			Integer qtdeTotalPontosCompradosProduto) {
		this.qtdeTotalPontosCompradosProduto = qtdeTotalPontosCompradosProduto;
	}

	public BigDecimal getValorTotalCompradoProduto() {
		return valorTotalCompradoProduto;
	}

	public void setValorTotalCompradoProduto(BigDecimal valorTotalCompradoProduto) {
		this.valorTotalCompradoProduto = valorTotalCompradoProduto;
	}

	public Date getDataCompraProduto() {
		return dataCompraProduto;
	}

	public void setDataCompraProduto(Date dataCompraProduto) {
		this.dataCompraProduto = dataCompraProduto;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

}
