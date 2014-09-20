package br.com.mkoffice.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.mkoffice.model.PermissaoEntity;


public class PermissaoDTO {
	
	private Long id;
	private String descPermissao;

	public static PermissaoDTO entityToDTO(PermissaoEntity entity){
		PermissaoDTO dto = new PermissaoDTO();
		dto.setId(entity.getId());
		dto.setDescPermissao(entity.getDescPermissao());
		
		return dto;
	}
	
	public static List<PermissaoDTO> listEntityToListDTO(List<PermissaoEntity> findByFilter) {
		
		List<PermissaoDTO> dtos =new ArrayList<PermissaoDTO>();
		for (PermissaoEntity permissaoEntity : findByFilter) {
			dtos.add(entityToDTO(permissaoEntity));
		}
		
		return dtos;
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
