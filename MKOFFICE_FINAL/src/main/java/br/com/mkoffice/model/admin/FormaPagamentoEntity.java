package br.com.mkoffice.model.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_FORMA_PGTO")
public class FormaPagamentoEntity implements Serializable{
	
	private static final long serialVersionUID = -4635510230736077709L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FORMA_PGTO")
	private Long id;

	@Column(name = "DS_FORMA_PGTO")
	private String descFormaPgto;

	@Column(name = "FL_POSSUI_PARCELAS")
	private boolean possuiParcelas;

	@Column(name = "NU_PARCELAS")
	private Integer numeroParcelas;

	public FormaPagamentoEntity() {

	}

	public FormaPagamentoEntity(Long id, String descFormaPgto,
			boolean possuiParcelas, Integer numeroParcelas) {
		super();
		this.id = id;
		this.descFormaPgto = descFormaPgto;
		this.possuiParcelas = possuiParcelas;
		this.numeroParcelas = numeroParcelas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescFormaPgto() {
		return descFormaPgto;
	}

	public void setDescFormaPgto(String descFormaPgto) {
		this.descFormaPgto = descFormaPgto;
	}

	public boolean isPossuiParcelas() {
		return possuiParcelas;
	}

	public void setPossuiParcelas(boolean possuiParcelas) {
		this.possuiParcelas = possuiParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descFormaPgto == null) ? 0 : descFormaPgto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (possuiParcelas ? 1231 : 1237);
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
		FormaPagamentoEntity other = (FormaPagamentoEntity) obj;
		if (descFormaPgto == null) {
			if (other.descFormaPgto != null)
				return false;
		} else if (!descFormaPgto.equals(other.descFormaPgto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (possuiParcelas != other.possuiParcelas)
			return false;
		return true;
	}

}
