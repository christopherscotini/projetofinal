package br.com.mkoffice.dto;

import br.com.mkoffice.model.FormaPagamentoEntity;


public class FormaPagamentoDTO {
	
	private Long id;
	private String descFormaPgto;
	private boolean possuiParcelas;
	private Integer numeroParcelas;

	public FormaPagamentoDTO() {

	}

	public FormaPagamentoDTO(Long id, String descFormaPgto,
			boolean possuiParcelas, Integer numeroParcelas) {
		super();
		this.id = id;
		this.descFormaPgto = descFormaPgto;
		this.possuiParcelas = possuiParcelas;
		this.numeroParcelas = numeroParcelas;
	}

	public static FormaPagamentoDTO convertEntotyToDTO(FormaPagamentoEntity entity){
		FormaPagamentoDTO dto = new FormaPagamentoDTO(
				  entity.getId()
				, entity.getDescFormaPgto()
				, entity.isPossuiParcelas()
				, entity.getNumeroParcelas());
		
		return dto;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescFormaPgto() {
		return descFormaPgto;
	}

	public void setDescFormaPgto(String descFormaPgto) {
		this.descFormaPgto = descFormaPgto;
	}

	public boolean isPossuiParcelas() {
		return possuiParcelas;
	}

	public void setPossuiParcelas(boolean possuiParcelas) {
		this.possuiParcelas = possuiParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descFormaPgto == null) ? 0 : descFormaPgto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (possuiParcelas ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormaPagamentoDTO other = (FormaPagamentoDTO) obj;
		if (descFormaPgto == null) {
			if (other.descFormaPgto != null)
				return false;
		} else if (!descFormaPgto.equals(other.descFormaPgto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (possuiParcelas != other.possuiParcelas)
			return false;
		return true;
	}

}
