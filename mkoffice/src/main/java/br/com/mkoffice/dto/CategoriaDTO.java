package br.com.mkoffice.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.mkoffice.model.CategoriaEntity;

public class CategoriaDTO {
	
	private Long id;
	private String descCategoria;
	private SecaoDTO codSecao;
	
	public static CategoriaDTO entityToDTO(CategoriaEntity entity){
		CategoriaDTO dto = new CategoriaDTO();
		dto.setId(entity.getId());
		dto.setDescCategoria(entity.getDescCategoria());
		dto.setCodSecao(SecaoDTO.convertEntityToDTO(entity.getCodSecao()));
		
		return dto;
	}
	
	public static List<CategoriaDTO> listEntityToListDTO(List<CategoriaEntity> findByFilter) {
		List<CategoriaDTO> returnzz = new ArrayList<CategoriaDTO>();
		for (CategoriaEntity entity : findByFilter) {
			returnzz.add(entityToDTO(entity));
		}
		
		return returnzz;
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
	public SecaoDTO getCodSecao() {
		return codSecao;
	}
	public void setCodSecao(SecaoDTO codSecao) {
		this.codSecao = codSecao;
	}

	
}
