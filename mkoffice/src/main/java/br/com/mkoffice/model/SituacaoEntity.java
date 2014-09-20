package br.com.mkoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.mkoffice.dto.SituacaoDTO;

@Entity
@Table(name = "TB_SITUACAO_PAGTO")
public class SituacaoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SITUACAO_PAGTO")
	private Long id;

	@Column(name = "DS_SITUACAO_PAGTO")
	private String descSituacao;
	
	public static SituacaoEntity dtoToEntity(SituacaoDTO dto){
		SituacaoEntity entity = new SituacaoEntity();
		entity.setId(dto.getId());
		entity.setDescSituacao(dto.getDescSituacao());
		
		return entity;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescSituacao() {
		return descSituacao;
	}
	public void setDescSituacao(String descSituacao) {
		this.descSituacao = descSituacao;
	}
	
}
