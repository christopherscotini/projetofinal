package br.com.mkoffice.model;

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

import br.com.mkoffice.model.admin.UserEntity;

@Entity
@Table(name = "TB_PARAMETRO_DASHBOARD")
public class ParametrosDashboardEntity implements Serializable{

	private static final long serialVersionUID = -6439006817942670765L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PARAMETRO")
	private Long id;

	@Column(name = "VL_META_FATURAMENTO")
	private BigDecimal valorMetaFaturamento;
	
	@Column(name = "VL_LIMITE_GASTO")
	private BigDecimal valorLimiteGasto;
	
	@ManyToOne
	@JoinColumn(name = "TB_USUARIO_FK")
	private UserEntity usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorMetaFaturamento() {
		return valorMetaFaturamento;
	}

	public void setValorMetaFaturamento(BigDecimal valorMetaFaturamento) {
		this.valorMetaFaturamento = valorMetaFaturamento;
	}

	public BigDecimal getValorLimiteGasto() {
		return valorLimiteGasto;
	}

	public void setValorLimiteGasto(BigDecimal valorLimiteGasto) {
		this.valorLimiteGasto = valorLimiteGasto;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}
	
}
