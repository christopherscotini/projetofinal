package br.com.mkoffice.model.constants;


public enum StatusConsultoraEnum {

	INICIADA(0, "Iniciada Não Ativa"),
	ATIVO(1, "Iniciada Ativa");

	private Integer codigo = null;

	private String descricao = null;

	private StatusConsultoraEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static StatusConsultoraEnum getDescStatusByCodigo(Integer codigo) {
		for (StatusConsultoraEnum statusConsultoraEnum : values()) {
			if (statusConsultoraEnum.getCodigo() == codigo) {
				return statusConsultoraEnum;
			}
		}

		return null;
	}

	public Integer getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
