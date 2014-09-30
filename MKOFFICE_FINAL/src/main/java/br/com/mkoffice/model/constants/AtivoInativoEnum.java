package br.com.mkoffice.model.constants;


public enum AtivoInativoEnum {

	INATIVO(0, "Inativo(a)"),
	ATIVO(1, "Ativo(a)");

	private Integer codigo = null;

	private String descricao = null;

	private AtivoInativoEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static AtivoInativoEnum getAtivoInativoByCodigo(Integer codigo) {
		for (AtivoInativoEnum ativoInativolEnum : values()) {
			if (ativoInativolEnum.getCodigo() == codigo) {
				return ativoInativolEnum;
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
