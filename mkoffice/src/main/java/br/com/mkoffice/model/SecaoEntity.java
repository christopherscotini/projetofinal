package br.com.mkoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.mkoffice.dto.SecaoDTO;

@Entity
@Table(name="TB_SECAO")
public class SecaoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SECAO")
	private Integer id;
	
	@Column(name="NM_SECAO")
	private String descSecao;

	public static SecaoEntity convertDTOToEntity(SecaoDTO dto){
		SecaoEntity entity = new SecaoEntity();
		entity.setId(dto.getCodSecao());
		entity.setDescSecao(dto.getDescSecao());
		
		return entity;
	}
	
	public SecaoEntity() {
	}
	
	public SecaoEntity(Integer codSecao, String descSecao) {
		super();
		this.id = codSecao;
		this.descSecao = descSecao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer codSecao) {
		this.id = codSecao;
	}

	public String getDescSecao() {
		return descSecao;
	}

	public void setDescSecao(String descSecao) {
		this.descSecao = descSecao;
	}
	
	

}
