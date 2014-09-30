package br.com.mkoffice.dto;

import java.util.Date;

import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.agenda.TipoAgendaEntity;

public class AgendaDTO {

	private Long id;
	private String descricao;
	private Date dataInicio;
	private Date dataFim;
	private boolean diaTodo;
	private TipoAgendaEntity tipoAgenda;
	private ClienteEntity cliente;
	private UserEntity usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public boolean isDiaTodo() {
		return diaTodo;
	}
	public void setDiaTodo(boolean diaTodo) {
		this.diaTodo = diaTodo;
	}
	public TipoAgendaEntity getTipoAgenda() {
		return tipoAgenda;
	}
	public void setTipoAgenda(TipoAgendaEntity tipoAgenda) {
		this.tipoAgenda = tipoAgenda;
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
