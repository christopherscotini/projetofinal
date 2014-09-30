package br.com.mkoffice.model.venda;

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
@Table(name="TB_VENDA_JOIN_TB_CATALOGO")
public class HistoricoVendasEntity implements Serializable{

	private static final long serialVersionUID = 1981222972220871359L;

	@ManyToOne
	@JoinColumn(name = "ID_VENDA_FK")
	private VendaEntity codVenda;
	
	@ManyToOne
	@JoinColumn(name="ID_CATALOGO_FK")
	private CatalogoEntity codCatalogo;
	
	@Column(name="NU_QTDE_VENDIDA")
	private Integer qdteVendidaProduto;

	@Column(name="NU_SOMA_PONTOS_VENDIDOS")
	private Integer qtdeTotalPontosVendidosProduto;

	@Column(name="VL_VLR_TOTAL_VENDIDO")
	private BigDecimal valorTotalVendidoProduto;

	@Column(name="DT_VENDA")
	private Date dataVendaProduto;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_JOIN")
	private Long ID;

	public HistoricoVendasEntity() {

	}
	
	public HistoricoVendasEntity(VendaEntity codVenda,
			CatalogoEntity codCatalogo, Integer qdteVendidaProduto,
			Integer qtdeTotalPontosVendidosProduto,
			BigDecimal valorTotalVendidoProduto, Date dataVendaProduto) {
		super();
		this.codVenda = codVenda;
		this.codCatalogo = codCatalogo;
		this.qdteVendidaProduto = qdteVendidaProduto;
		this.qtdeTotalPontosVendidosProduto = qtdeTotalPontosVendidosProduto;
		this.valorTotalVendidoProduto = valorTotalVendidoProduto;
		this.dataVendaProduto = dataVendaProduto;
	}



	public Integer getQdteVendidaProduto() {
		return qdteVendidaProduto;
	}

	public void setQdteVendidaProduto(Integer qdteVendidaProduto) {
		this.qdteVendidaProduto = qdteVendidaProduto;
	}

	public Integer getQtdeTotalPontosVendidosProduto() {
		return qtdeTotalPontosVendidosProduto;
	}

	public void setQtdeTotalPontosVendidosProduto(
			Integer qtdeTotalPontosVendidosProduto) {
		this.qtdeTotalPontosVendidosProduto = qtdeTotalPontosVendidosProduto;
	}

	public BigDecimal getValorTotalVendidoProduto() {
		return valorTotalVendidoProduto;
	}

	public void setValorTotalVendidoProduto(BigDecimal valorTotalVendidoProduto) {
		this.valorTotalVendidoProduto = valorTotalVendidoProduto;
	}

	public Date getDataVendaProduto() {
		return dataVendaProduto;
	}

	public void setDataVendaProduto(Date dataVendaProduto) {
		this.dataVendaProduto = dataVendaProduto;
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

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
	
}
