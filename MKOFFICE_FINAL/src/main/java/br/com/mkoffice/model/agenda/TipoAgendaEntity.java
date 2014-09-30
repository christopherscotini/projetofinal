package br.com.mkoffice.model.agenda;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TIPO_AGENDA")
public class TipoAgendaEntity implements Serializable {

	private static final long serialVersionUID = 4126362532882462250L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AGENDA")
	private Long id;
	
	@Column(name = "DS_TIPO_AGENDA")
	private String descTipoAgenda;
	
	@Column(name = "FL_CONFIG_PADRAO")
	private boolean configuracaoPadrao;


	public String getDescTipoAgenda() {
		return descTipoAgenda;
	}

	public void setDescTipoAgenda(String descTipoAgenda) {
		this.descTipoAgenda = descTipoAgenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TipoAgendaEntity other = (TipoAgendaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isConfiguracaoPadrao() {
		return configuracaoPadrao;
	}

	public void setConfiguracaoPadrao(boolean configuracaoPadrao) {
		this.configuracaoPadrao = configuracaoPadrao;
	}

}