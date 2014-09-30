package br.com.mkoffice.model.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_FLUXO_ESTOQUE")
public class FluxoEstoqueEntity implements Serializable{
	
	private static final long serialVersionUID = -1864312019826679851L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FLUXO_ESTOQUE")
	private Long id;
	
	@Column(name = "DS_FLUXO_ESTOQUE")
	private String descFluxoEstoque;
	
	@Column(name = "FL_FLUXO_SAIDA")
	private boolean fluxoSaida;
	
	public FluxoEstoqueEntity() {

	}
	
	public FluxoEstoqueEntity(Long id, String descFluxoEstoque, boolean fluxoSaida) {
		super();
		this.id = id;
		this.descFluxoEstoque = descFluxoEstoque;
		this.fluxoSaida = fluxoSaida;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescFluxoEstoque() {
		return descFluxoEstoque;
	}

	public void setDescFluxoEstoque(String descFluxoEstoque) {
		this.descFluxoEstoque = descFluxoEstoque;
	}

	public boolean isFluxoSaida() {
		return fluxoSaida;
	}

	public void setFluxoSaida(boolean fluxoSaida) {
		this.fluxoSaida = fluxoSaida;
	}
	
}
