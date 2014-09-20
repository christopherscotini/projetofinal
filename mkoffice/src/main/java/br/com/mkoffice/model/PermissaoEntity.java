package br.com.mkoffice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.mkoffice.dto.PermissaoDTO;


@Entity
@Table(name = "TB_PERMISSAO")
public class PermissaoEntity implements Serializable {
	
	private static final long serialVersionUID = -7315905366603156964L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_PERMISSAO")
	private Long id;

	@Column(name = "DS_PERMISSAO")
	private String descPermissao;

	public PermissaoEntity() {

	}
	
	public PermissaoEntity(Long id, String descPermissao) {
		super();
		this.id = id;
		this.descPermissao = descPermissao;
	}
	
	public static PermissaoEntity dtoToEntity(PermissaoDTO dto){
		PermissaoEntity entity = new PermissaoEntity();
		entity.setId(dto.getId());
		entity.setDescPermissao(dto.getDescPermissao());
		
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescPermissao() {
		return descPermissao;
	}

	public void setDescPermissao(String descPermissao) {
		this.descPermissao = descPermissao;
	}
	
}
