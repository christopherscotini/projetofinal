package br.com.mkoffice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.mkoffice.dto.CatalogoDTO;
import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.dto.PedidoDTO;
import br.com.mkoffice.dto.VendaDTO;

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

	public static List<ItemMovimentadoCarrinhoDTO> historicoVendaToItemMovimentado(List<HistoricoVendasEntity> historico) {
		List<ItemMovimentadoCarrinhoDTO> itens = new ArrayList<ItemMovimentadoCarrinhoDTO>();
		for (int i = 0; i < historico.size(); i++) {
			ItemMovimentadoCarrinhoDTO item = new ItemMovimentadoCarrinhoDTO(
					CatalogoDTO.entityToDto(historico.get(i).getCodCatalogo())
					, historico.get(i).getDataVendaProduto()
					, historico.get(i).getQdteVendidaProduto()
					, historico.get(i).getQtdeTotalPontosVendidosProduto()
					, historico.get(i).getValorTotalVendidoProduto()
					, null
					, VendaDTO.entityToDto(historico.get(i).getCodVenda())
					, false
					, false
					, null);
					
			itens.add(item);
		}
		
		return itens;
	}
	
	public static List<HistoricoPedidosEntity> itemMovimentadoToHistoricoPedido(PedidoDTO pedido, PedidoEntity entity) {
		List<ItemMovimentadoCarrinhoDTO> itensMovimentados = pedido.getListaProdutosComprados();
		List<HistoricoPedidosEntity> historico = new ArrayList<HistoricoPedidosEntity>();
		for (int i = 0; i < itensMovimentados.size(); i++) {
			HistoricoPedidosEntity item = new HistoricoPedidosEntity(
					entity
					, CatalogoEntity.dtoToEntity(itensMovimentados.get(i).getProduto())
					, itensMovimentados.get(i).getQtdeProdutoCarrinho()
					, itensMovimentados.get(i).getSomaPontosProdutoCarrinho()
					, itensMovimentados.get(i).getValorTotalProdutoCarrinho()
					, pedido.getDtPedido());
				
					historico.add(item);
		}
		
		return historico;
	}
	
	public static List<HistoricoVendasEntity> itemMovimentadoToHistoricoVenda(List<ItemMovimentadoCarrinhoDTO> itensMovimentados, VendaEntity entity) {
		List<HistoricoVendasEntity> historico = new ArrayList<HistoricoVendasEntity>();
		for (int i = 0; i < itensMovimentados.size(); i++) {
			HistoricoVendasEntity item = new HistoricoVendasEntity(
					entity
					, CatalogoEntity.dtoToEntity(itensMovimentados.get(i).getProduto())
					, itensMovimentados.get(i).getQtdeProdutoCarrinho()
					, itensMovimentados.get(i).getSomaPontosProdutoCarrinho()
					, itensMovimentados.get(i).getValorTotalProdutoCarrinho()
					, entity.getDataVenda());
			
					historico.add(item);
		}
		
		return historico;
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
