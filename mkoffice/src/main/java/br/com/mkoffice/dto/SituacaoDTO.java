package br.com.mkoffice.dto;

import br.com.mkoffice.model.SituacaoEntity;

public class SituacaoDTO {
	
	private Long id;
	private String descSituacao;
	
	public static SituacaoDTO entityToDTO(SituacaoEntity entity){
		SituacaoDTO dto = new SituacaoDTO();
		dto.setId(entity.getId());
		dto.setDescSituacao(entity.getDescSituacao());
		
		return dto;
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
