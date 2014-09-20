package br.com.mkoffice.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.mkoffice.model.HistoricoPedidosEntity;
import br.com.mkoffice.model.HistoricoVendasEntity;
import br.com.mkoffice.model.constants.PercentDescontoEnum;

public class ItemMovimentadoCarrinhoDTO {

	private CatalogoDTO produto;
	private Date dtMovimentado;
	private Integer qtdeProdutoCarrinho;
	private Integer somaPontosProdutoCarrinho;
	private BigDecimal valorTotalProdutoCarrinho;
	private UserDTO usuario;
	
	private PercentDescontoEnum percentDesconto;

	// Campo para a conversao de HistoricoPedidosEntity
	private BigDecimal valorBonusUtilizado;
	private PedidoDTO codPedido;
	// Campo para a conversao de HistoricoVendasEntity
	private VendaDTO codVenda;

	// Campo para verificar se o produto n�o existe no catalogo
	// (ImportarPedidoBean)
	private boolean faltaNoCatalogo;

	private boolean disponivel;
	private Integer quantidadeEmEstoque;
	private List<SelectItem> quantidadeDisponivelEstoque;// para gerar o combo
															// de qtde
															// disponivel
	public ItemMovimentadoCarrinhoDTO() {

	}

	public ItemMovimentadoCarrinhoDTO(CatalogoDTO produto, Date dtMovimentado,
			Integer qtdeProdutoCarrinho, Integer somaPontosProdutoCarrinho,
			BigDecimal valorTotalProdutoCarrinho,
			PedidoDTO codPedido,
			VendaDTO codVenda, boolean faltaNoCatalogo, boolean disponivel,
			Integer quantidadeEmEstoque) {
		super();
		this.produto = produto;
		this.dtMovimentado = dtMovimentado;
		this.qtdeProdutoCarrinho = qtdeProdutoCarrinho;
		this.somaPontosProdutoCarrinho = somaPontosProdutoCarrinho;
		this.valorTotalProdutoCarrinho = valorTotalProdutoCarrinho;
		this.codPedido = codPedido;
		this.codVenda = codVenda;
		this.faltaNoCatalogo = faltaNoCatalogo;
		this.disponivel = disponivel;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
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
	
	public static List<ItemMovimentadoCarrinhoDTO> historicoPedidoToItemMovimentado(List<HistoricoPedidosEntity> historico) {
		List<ItemMovimentadoCarrinhoDTO> itens = new ArrayList<ItemMovimentadoCarrinhoDTO>();
		for (int i = 0; i < historico.size(); i++) {
			ItemMovimentadoCarrinhoDTO item = new ItemMovimentadoCarrinhoDTO(
					CatalogoDTO.entityToDto(historico.get(i).getCodCatalogo())
					, historico.get(i).getDataCompraProduto()
					, historico.get(i).getQdteCompradaProduto()
					, historico.get(i).getQtdeTotalPontosCompradosProduto()
					, historico.get(i).getValorTotalCompradoProduto()
					, PedidoDTO.entityToDto(historico.get(i).getCodPedido())
					, null
					, false
					, false
					, null);
					
			itens.add(item);
		}
		
		return itens;
	}
	
	public String getDisponivelDesc() {
		return disponivel == true ? "Em Estoque" : "N�o Disponivel";
	}
	
	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public List<SelectItem> getQuantidadeDisponivelEstoque() {
		if (quantidadeEmEstoque != null) {
			quantidadeDisponivelEstoque = new ArrayList<SelectItem>();
			for (int i = 0; i < quantidadeEmEstoque; i++) {
				quantidadeDisponivelEstoque.add(new SelectItem((i + 1), (i + 1)
						+ ""));
			}
			return quantidadeDisponivelEstoque;
		} else {
			return null;
		}
	}

	public CatalogoDTO getProduto() {
		return produto;
	}

	public void setProduto(CatalogoDTO produto) {
		this.produto = produto;
	}

	public Date getDtMovimentado() {
		return dtMovimentado;
	}

	public void setDtMovimentado(Date dtMovimentado) {
		this.dtMovimentado = dtMovimentado;
	}

	public Integer getQtdeProdutoCarrinho() {
		return qtdeProdutoCarrinho;
	}

	public void setQtdeProdutoCarrinho(Integer qtdeProdutoCarrinho) {
		this.qtdeProdutoCarrinho = qtdeProdutoCarrinho;
	}

	public Integer getSomaPontosProdutoCarrinho() {
		return somaPontosProdutoCarrinho;
	}

	public void setSomaPontosProdutoCarrinho(Integer somaPontosProdutoCarrinho) {
		this.somaPontosProdutoCarrinho = somaPontosProdutoCarrinho;
	}

	public BigDecimal getValorTotalProdutoCarrinho() {
		return valorTotalProdutoCarrinho;
	}

	public void setValorTotalProdutoCarrinho(
			BigDecimal valorTotalProdutoCarrinho) {
		this.valorTotalProdutoCarrinho = valorTotalProdutoCarrinho;
	}

	public PedidoDTO getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(PedidoDTO codPedido) {
		this.codPedido = codPedido;
	}

	public VendaDTO getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(VendaDTO codVenda) {
		this.codVenda = codVenda;
	}

	public boolean isFaltaNoCatalogo() {
		return faltaNoCatalogo;
	}

	public void setFaltaNoCatalogo(boolean faltaNoCatalogo) {
		this.faltaNoCatalogo = faltaNoCatalogo;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public BigDecimal getValorBonusUtilizado() {
		return valorBonusUtilizado;
	}

	public void setValorBonusUtilizado(BigDecimal valorBonusUtilizado) {
		this.valorBonusUtilizado = valorBonusUtilizado;
	}

	public PercentDescontoEnum getPercentDesconto() {
		return percentDesconto;
	}

	public void setPercentDesconto(PercentDescontoEnum percentDesconto) {
		this.percentDesconto = percentDesconto;
	}

	public UserDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UserDTO usuario) {
		this.usuario = usuario;
	}

}
