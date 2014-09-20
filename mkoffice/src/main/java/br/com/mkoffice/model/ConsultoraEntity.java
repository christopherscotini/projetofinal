package br.com.mkoffice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.mkoffice.dto.ConsultoraDTO;
import br.com.mkoffice.model.constants.StatusConsultoraEnum;

@Entity
@Table(name="TB_CONSULTORA")
public class ConsultoraEntity implements Serializable{

	private static final long serialVersionUID = -434540608337187556L;

	@Id
	@Column(name = "ID_CONSULTORA")
	private Long id;
	
	@Column(name = "DT_INICIACAO")
	private Date dataInicio;
	
	@Column(name = "DT_DESATIVACAO")
	private Date dataDesativacao;
		
	@Column(name = "DS_INF_ADICIONAL")
	private String infAdicional;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "DS_STATUS")
	private StatusConsultoraEnum status;
	
	@ManyToOne
	@JoinColumn(name = "TB_USUARIO_FK")
	private UserEntity usuario;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name= "TB_PESSOA_FK")
	private PessoaEntity dadosPessoa;

	public static ConsultoraEntity dtoToEntity(ConsultoraDTO dto){
		ConsultoraEntity entity = new ConsultoraEntity();
		entity.setId(dto.getId());
		entity.setDadosPessoa(PessoaEntity.convertDTOToEntity(dto.getDadosPessoa()));
		entity.setDataDesativacao(dto.getDataDesativacao());
		entity.setDataInicio(dto.getDataInicio());
		entity.setInfAdicional(dto.getInfAdicional());
		entity.setStatus(dto.getStatus());
		entity.setUsuario(UserEntity.dtoToEntity(dto.getUsuario()));
		
		
		return entity;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public PessoaEntity getDadosPessoa() {
		return dadosPessoa;
	}

	public void setDadosPessoa(PessoaEntity dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}

}
