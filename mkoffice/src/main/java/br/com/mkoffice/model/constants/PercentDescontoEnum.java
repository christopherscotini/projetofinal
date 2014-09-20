package br.com.mkoffice.model.constants;


public enum PercentDescontoEnum {

	_0(0,"0%"), _25(25,"25%"), _30(30,"30%"), _35(35,"35%"), _40(40,"40%"), _100(100,"100%");
	
	private Integer codigo = null;

	private String descricao = null;

	private PercentDescontoEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static PercentDescontoEnum getDescontoByCodigo(Integer codigo) {
		for (PercentDescontoEnum percentDescontoEnum : values()) {
			if (percentDescontoEnum.getCodigo() == codigo) {
				return percentDescontoEnum;
			}
		}

		return null;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
