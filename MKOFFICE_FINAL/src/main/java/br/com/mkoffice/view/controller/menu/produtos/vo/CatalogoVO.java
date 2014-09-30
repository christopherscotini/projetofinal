package br.com.mkoffice.view.controller.menu.produtos.vo;

import java.math.BigDecimal;

import br.com.mkoffice.model.admin.CategoriaEntity;

public class CatalogoVO {

	private Long codCatalogo;
	private Long codProduto;
	private String descProduto;
	private BigDecimal preco;
	private Integer pontosPorUnidade;
	private CategoriaEntity codCategoria;
	private Integer quantidadeEmEstoque;
	private boolean ativo;

	public CatalogoVO() {
	}

	public CatalogoVO(Long codCatalogo, Long codProduto, String descProduto,
			BigDecimal preco, Integer pontosPorUnidade,
			CategoriaEntity codCategoria, boolean ativo) {
		super();
		this.codCatalogo = codCatalogo;
		this.codProduto = codProduto;
		this.descProduto = descProduto;
		this.preco = preco;
		this.pontosPorUnidade = pontosPorUnidade;
		this.codCategoria = codCategoria;
		this.ativo = ativo;
	}

	public Long getCodCatalogo() {
		return codCatalogo;
	}

	public void setCodCatalogo(Long codCatalogo) {
		this.codCatalogo = codCatalogo;
	}

	public String getDescProduto() {
		return descProduto;
	}

	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getPontosPorUnidade() {
		return pontosPorUnidade;
	}

	public void setPontosPorUnidade(Integer pontosPorUnidade) {
		this.pontosPorUnidade = pontosPorUnidade;
	}

	public CategoriaEntity getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(CategoriaEntity codCategoria) {
		this.codCategoria = codCategoria;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public Long getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Long codProduto) {
		this.codProduto = codProduto;
	}

}
