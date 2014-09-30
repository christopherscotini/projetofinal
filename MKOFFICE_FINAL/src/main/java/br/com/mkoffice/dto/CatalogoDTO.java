/**
 * 
 */
package br.com.mkoffice.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.mkoffice.model.admin.CategoriaEntity;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;

/**
 * @author christopher.rozario
 *
 */
public class CatalogoDTO {

	private Long codCatalogo;
	
	private Long codProduto;
	
	private String descProduto;
	
	private BigDecimal preco;

	private Integer pontosPorUnidade;
	
	private CategoriaEntity codCategoria;
	
	private boolean ativo;

	private boolean disponivel;
	private Integer quantidadeEmEstoque;
	private List<SelectItem> quantidadeDisponivelEstoque;//para gerar o combo de qtde disponivel
	private FluxoEstoqueEntity tipoFluxoEstoque;
	
	//recursos importacao
	private boolean faltaNoCatalogo;
	
	
	public CatalogoDTO() {
	}

	public CatalogoDTO(Long codCatalogo, Long codProduto, String descProduto,
			BigDecimal preco, Integer pontosPorUnidade,
			CategoriaEntity codCategoria,
			boolean ativo) {
		super();
		this.codCatalogo = codCatalogo;
		this.codProduto = codProduto;
		this.descProduto = descProduto;
		this.preco = preco;
		this.pontosPorUnidade = pontosPorUnidade;
		this.codCategoria = codCategoria;
		this.ativo = ativo;
	}



	public String getDisponivelDesc() {
		return disponivel == true ? "Em Estoque" : "Não Disponivel";
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}
	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}
	public List<SelectItem> getQuantidadeDisponivelEstoque() {
		if(quantidadeEmEstoque != null){
			quantidadeDisponivelEstoque = new ArrayList<SelectItem>();	
			for (int i = 0; i < quantidadeEmEstoque; i++) {
				quantidadeDisponivelEstoque.add(new SelectItem((i+1), (i+1)+""));
			}
			return quantidadeDisponivelEstoque;
		}else{
			return null;
		}
	}

	public Long getCodCatalogo() {
		return codCatalogo;
	}

	public void setCodCatalogo(Long codCatalogo) {
		this.codCatalogo = codCatalogo;
	}

	public Long getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Long codProduto) {
		this.codProduto = codProduto;
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

	public FluxoEstoqueEntity getTipoFluxoEstoque() {
		return tipoFluxoEstoque;
	}

	public void setTipoFluxoEstoque(FluxoEstoqueEntity tipoFluxoEstoque) {
		this.tipoFluxoEstoque = tipoFluxoEstoque;
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

	public void setQuantidadeDisponivelEstoque(
			List<SelectItem> quantidadeDisponivelEstoque) {
		this.quantidadeDisponivelEstoque = quantidadeDisponivelEstoque;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
//	public boolean isFluxoSaida(){
//		return getTipoFluxoEstoque().equals(TipoFluxoEstoqueEnum.SAIDA_DEMONSTRACAO)
//					|| getTipoFluxoEstoque().equals(TipoFluxoEstoqueEnum.SAIDA_EMPRESTIMO)
//					|| getTipoFluxoEstoque().equals(TipoFluxoEstoqueEnum.SAIDA_MANUAL)
//					|| getTipoFluxoEstoque().equals(TipoFluxoEstoqueEnum.SAIDA_PRESENTE)
//					|| getTipoFluxoEstoque().equals(TipoFluxoEstoqueEnum.SAIDA_VENDA);
//	}
}
