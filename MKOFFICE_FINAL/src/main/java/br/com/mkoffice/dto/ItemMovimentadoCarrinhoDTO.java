package br.com.mkoffice.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.constants.PercentDescontoEnum;
import br.com.mkoffice.model.pedido.PedidoEntity;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.model.venda.VendaEntity;

public class ItemMovimentadoCarrinhoDTO {

	private CatalogoEntity produto;
	private Date dtMovimentado;
	private Integer qtdeProdutoCarrinho;
	private Integer somaPontosProdutoCarrinho;
	private BigDecimal valorTotalProdutoCarrinho;
	private FluxoEstoqueEntity tipoFluxoEstoque;
	private UserEntity usuario;
	
	private PercentDescontoEnum percentDesconto;

	// Campo para a conversao de HistoricoPedidosEntity
	private BigDecimal valorBonusUtilizado;
	private PedidoEntity codPedido;
	// Campo para a conversao de HistoricoVendasEntity
	private VendaEntity codVenda;

	// Campo para verificar se o produto não existe no catalogo
	// (ImportarPedidoBean)
	private boolean faltaNoCatalogo;

	private boolean disponivel;
	private Integer quantidadeEmEstoque;
	private List<SelectItem> quantidadeDisponivelEstoque;// para gerar o combo
															// de qtde
															// disponivel
	public ItemMovimentadoCarrinhoDTO() {

	}

	public ItemMovimentadoCarrinhoDTO(CatalogoEntity produto, Date dtMovimentado,
			Integer qtdeProdutoCarrinho, Integer somaPontosProdutoCarrinho,
			BigDecimal valorTotalProdutoCarrinho,
			FluxoEstoqueEntity tipoFluxoEstoque, PedidoEntity codPedido,
			VendaEntity codVenda, boolean faltaNoCatalogo, boolean disponivel,
			Integer quantidadeEmEstoque) {
		super();
		this.produto = produto;
		this.dtMovimentado = dtMovimentado;
		this.qtdeProdutoCarrinho = qtdeProdutoCarrinho;
		this.somaPontosProdutoCarrinho = somaPontosProdutoCarrinho;
		this.valorTotalProdutoCarrinho = valorTotalProdutoCarrinho;
		this.tipoFluxoEstoque = tipoFluxoEstoque;
		this.codPedido = codPedido;
		this.codVenda = codVenda;
		this.faltaNoCatalogo = faltaNoCatalogo;
		this.disponivel = disponivel;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public String getDisponivelDesc() {
		return disponivel == true ? "Em Estoque" : "Não Disponivel";
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

	public CatalogoEntity getProduto() {
		return produto;
	}

	public void setProduto(CatalogoEntity produto) {
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

	public boolean isFaltaNoCatalogo() {
		return faltaNoCatalogo;
	}

	public void setFaltaNoCatalogo(boolean faltaNoCatalogo) {
		this.faltaNoCatalogo = faltaNoCatalogo;
	}

	public FluxoEstoqueEntity getTipoFluxoEstoque() {
		return tipoFluxoEstoque;
	}

	public void setTipoFluxoEstoque(FluxoEstoqueEntity tipoFluxoEstoque) {
		this.tipoFluxoEstoque = tipoFluxoEstoque;
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

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}

}
