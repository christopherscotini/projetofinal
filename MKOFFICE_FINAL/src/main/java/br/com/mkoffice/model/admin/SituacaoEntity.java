package br.com.mkoffice.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_SITUACAO_PAGTO")
public class SituacaoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SITUACAO_PAGTO")
	private Long id;

	@Column(name = "DS_SITUACAO_PAGTO")
	private String descSituacao;
	
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
