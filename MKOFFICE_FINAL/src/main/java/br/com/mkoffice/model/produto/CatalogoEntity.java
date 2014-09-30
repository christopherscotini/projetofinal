package br.com.mkoffice.model.produto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.mkoffice.model.admin.CategoriaEntity;

@Entity
@Table(name="TB_CATALOGO")
public class CatalogoEntity implements Serializable{

	private static final long serialVersionUID = 6232564107097919606L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CATALOGO")
	private Long codCatalogo;
	
	@Column(name="CD_PRODUTO")
	private Long codProduto;
	
	@Column(name="NM_PRODUTO")
	private String descProduto;
	
	@Column(name="VL_PRODUTO")
	private BigDecimal preco;

	@Column(name="NU_PONTOS")
	private Integer pontosPorUnidade;
	
	@ManyToOne
	@JoinColumn(name="CATEGORIA_CATALOGO_FK")
	private CategoriaEntity codCategoria;
	
	@Column(name="FL_ATIVO")
	private boolean ativo;

	

	public CatalogoEntity() {
		// TODO Auto-generated constructor stub
	}

	public CatalogoEntity(Long codCatalogo, Long codProduto, String descProduto, BigDecimal preco,
			Integer pontosPorUnidade, CategoriaEntity codCategoria, boolean ativo) {
		super();
		this.codCatalogo = codCatalogo;
		this.codProduto = codProduto;
		this.descProduto = descProduto;
		this.preco = preco;
		this.pontosPorUnidade = pontosPorUnidade;
		this.codCategoria = codCategoria;
		this.ativo = ativo;
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

	public Long getCodCatalogo() {
		return codCatalogo;
	}

	public void setCodCatalogo(Long codCatalogo) {
		this.codCatalogo = codCatalogo;
	}

//	public List<HistoricoVendasEntity> getVendaProdutosList() {
//		return vendaProdutosList;
//	}
//
//	public void setVendaProdutosList(List<HistoricoVendasEntity> vendaProdutosList) {
//		this.vendaProdutosList = vendaProdutosList;
//	}
//
//	public List<HistoricoPedidosEntity> getPedidoProdutosList() {
//		return pedidoProdutosList;
//	}
//
//	public void setPedidoProdutosList(
//			List<HistoricoPedidosEntity> pedidoProdutosList) {
//		this.pedidoProdutosList = pedidoProdutosList;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codCatalogo == null) ? 0 : codCatalogo.hashCode());
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
		CatalogoEntity other = (CatalogoEntity) obj;
		if (codCatalogo == null) {
			if (other.codCatalogo != null)
				return false;
		} else if (!codCatalogo.equals(other.codCatalogo))
			return false;
		return true;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
