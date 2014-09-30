/**
 * 
 */
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

/**
 * @author christopher.rozario
 *
 */
public class EstoqueDTO {

private Long id;
	
	private Integer qtdeMovimentadoProduto;
	private Integer qtdeTotalPontosMovimentadoProduto;
	private BigDecimal valorTotalMovimentadoProduto;
	private FluxoEstoqueEntity tipoFluxoEstoque;
	private PercentDescontoEnum percDesconto;
	private Date dtMovimentacao;
	private boolean disponivel;
	private PedidoEntity codPedido;
	private VendaEntity codVenda;
	private CatalogoEntity codCatalogo;
	private UserEntity usuario;
	
	private Integer qtdeEmEstoque;
	private List<SelectItem> quantidadeDisponivelEstoque;
	
	public EstoqueDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public EstoqueDTO(Long id, Integer qtdeMovimentadoProduto,
			Integer qtdeTotalPontosMovimentadoProduto,
			BigDecimal valorTotalMovimentadoProduto,
			FluxoEstoqueEntity tipoFluxoEstoque,
			PercentDescontoEnum percDesconto, Date dtMovimentacao,
			boolean disponivel, PedidoEntity codPedido, VendaEntity codVenda,
			CatalogoEntity codCatalogo, Integer qtdeEmEstoque) {
		super();
		this.id = id;
		this.qtdeMovimentadoProduto = qtdeMovimentadoProduto;
		this.qtdeTotalPontosMovimentadoProduto = qtdeTotalPontosMovimentadoProduto;
		this.valorTotalMovimentadoProduto = valorTotalMovimentadoProduto;
		this.tipoFluxoEstoque = tipoFluxoEstoque;
		this.percDesconto = percDesconto;
		this.dtMovimentacao = dtMovimentacao;
		this.disponivel = disponivel;
		this.codPedido = codPedido;
		this.codVenda = codVenda;
		this.codCatalogo = codCatalogo;
		this.qtdeEmEstoque = qtdeEmEstoque;
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

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean dispnivel) {
		this.disponivel = dispnivel;
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
	
	public boolean isFluxoSaida(){
		return getTipoFluxoEstoque().isFluxoSaida();
	}

	public List<SelectItem> getQuantidadeDisponivelEstoque() {
		quantidadeDisponivelEstoque = new ArrayList<SelectItem>();
		quantidadeDisponivelEstoque.add(new SelectItem(new Integer(0), "0"));
		if (qtdeEmEstoque != null) {
			for (int i = 0; i < qtdeEmEstoque; i++) {
				quantidadeDisponivelEstoque.add(new SelectItem(new Integer((i+1)), (i+1)+""));
			}
		} 
		
		return quantidadeDisponivelEstoque;
	}

	public void setQuantidadeDisponivelEstoque(
			List<SelectItem> quantidadeDisponivelEstoque) {
		this.quantidadeDisponivelEstoque = quantidadeDisponivelEstoque;
	}

	public Integer getQtdeEmEstoque() {
		return qtdeEmEstoque;
	}

	public void setQtdeEmEstoque(Integer qtdeEmEstoque) {
		this.qtdeEmEstoque = qtdeEmEstoque;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}
}
