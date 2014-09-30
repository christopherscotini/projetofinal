package br.com.mkoffice.model.agenda;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.model.admin.UserEntity;

@Entity
@Table(name = "TB_AGENDA")
public class AgendaEntity implements Serializable {

	private static final long serialVersionUID = -2717085774486544842L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AGENDA")
	private Long id;
	
	@Column(name = "DS_AGENDA")
	private String descricao;
	
	@Column(name = "DT_INICIO")
	private Date dataInicio;
	
	@Column(name = "DT_FIM")
	private Date dataFim;
	
	@Column(name = "FL_ALLDAY")
	private boolean diaTodo;

	@ManyToOne
	@JoinColumn(name = "TB_TIPO_AGENDA_FK")
	private TipoAgendaEntity tipoAgenda;

	@ManyToOne
	@JoinColumn(name = "TB_CLIENTE_FK")
	private ClienteEntity cliente;

	@ManyToOne
	@JoinColumn(name = "TB_USUARIO_FK")
	private UserEntity usuario;

	// getters and setters
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date datafim) {
		this.dataFim = datafim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isDiaTodo() {
		return diaTodo;
	}

	public void setDiaTodo(boolean diaTodo) {
		this.diaTodo = diaTodo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoAgendaEntity getTipoAgenda() {
		return tipoAgenda;
	}

	public void setTipoAgenda(TipoAgendaEntity tipoAgenda) {
		this.tipoAgenda = tipoAgenda;
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
		AgendaEntity other = (AgendaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}
	
}