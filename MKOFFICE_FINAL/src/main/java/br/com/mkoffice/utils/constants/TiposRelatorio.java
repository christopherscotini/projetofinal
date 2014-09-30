package br.com.mkoffice.utils.constants;

public enum TiposRelatorio {

	PDF(1, "PDF"), XLS(2, "XLS");
	
	private Integer codigo = null;

	private String descricao = null;
	
	private TiposRelatorio(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return descricao;
	}
	
}
