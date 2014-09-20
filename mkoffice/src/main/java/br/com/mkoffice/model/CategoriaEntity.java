package br.com.mkoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.mkoffice.dto.CategoriaDTO;

@Entity
@Table(name="TB_CATEGORIA")
public class CategoriaEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CATEGORIA")
	private Long id;
	
	@Column(name="NM_CATEGORIA")
	private String descCategoria;
	
	@JoinColumn(name="SECAO_CATEGORIA_FK")
	@ManyToOne
	private SecaoEntity codSecao;

	public CategoriaEntity() {
	}

	public static CategoriaEntity dtoToEntity(CategoriaDTO dto){
		CategoriaEntity entity = new CategoriaEntity();
		entity.setId(dto.getId());
		entity.setDescCategoria(dto.getDescCategoria());
		entity.setCodSecao(SecaoEntity.convertDTOToEntity(dto.getCodSecao()));		
		return entity;
	}
	
	public CategoriaEntity(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescCategoria() {
		return descCategoria;
	}


	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}


	public SecaoEntity getCodSecao() {
		return codSecao;
	}


	public void setCodSecao(SecaoEntity codSecao) {
		this.codSecao = codSecao;
	}
	
}
