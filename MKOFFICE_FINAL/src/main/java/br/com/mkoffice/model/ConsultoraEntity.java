package br.com.mkoffice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.constants.StatusConsultoraEnum;
import br.com.mkoffice.model.embeddable.Pessoa;

@Entity
@Table(name="TB_CONSULTORA")
public class ConsultoraEntity implements Serializable{

	private static final long serialVersionUID = -434540608337187556L;

	@Id
	@Column(name = "ID_CONSULTORA")
	private Long codConsultora;
	
	@Column(name = "DT_INICIACAO")
	private Date dataInicio;
	
	@Column(name = "DT_DESATIVACAO")
	private Date dataDesativacao;
		
	@Column(name = "DS_INF_ADICIONAL")
	private String infAdicional;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private StatusConsultoraEnum status;
	
	@ManyToOne
	@JoinColumn(name = "TB_USUARIO_FK")
	private UserEntity usuario;
	
	private Pessoa dadosPessoa;

	public Long getCodConsultora() {
		return codConsultora;
	}

	public void setCodConsultora(Long codConsultora) {
		this.codConsultora = codConsultora;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public String getInfAdicional() {
		return infAdicional;
	}

	public void setInfAdicional(String infAdicional) {
		this.infAdicional = infAdicional;
	}

	public StatusConsultoraEnum getStatus() {
		return status;
	}

	public void setStatus(StatusConsultoraEnum status) {
		this.status = status;
	}

	public Pessoa getDadosPessoa() {
		return dadosPessoa;
	}

	public void setDadosPessoa(Pessoa dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}

}
