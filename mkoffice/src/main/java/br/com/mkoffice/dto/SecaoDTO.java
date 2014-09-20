package br.com.mkoffice.dto;

import br.com.mkoffice.model.SecaoEntity;

public class SecaoDTO {

	private Integer codSecao;
	private String descSecao;

	
	public static SecaoDTO convertEntityToDTO(SecaoEntity entity){
		SecaoDTO dto = new SecaoDTO();
		dto.setCodSecao(entity.getId());
		dto.setDescSecao(entity.getDescSecao());
		
		return dto;
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
