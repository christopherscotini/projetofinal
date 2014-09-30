package br.com.mkoffice.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_SECAO")
public class SecaoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SECAO")
	private Integer codSecao;
	
	@Column(name="NM_SECAO")
	private String descSecao;

	public SecaoEntity() {
	}
	
	public SecaoEntity(Integer codSecao, String descSecao) {
		super();
		this.codSecao = codSecao;
		this.descSecao = descSecao;
	}

	public Integer getCodSecao() {
		return codSecao;
	}

	public void setCodSecao(Integer codSecao) {
		this.codSecao = codSecao;
	}

	public String getDescSecao() {
		return descSecao;
	}

	public void setDescSecao(String descSecao) {
		this.descSecao = descSecao;
	}
	
	

}
