package br.com.mkoffice.model.constants;

public enum TipoAgendaEnum {
	AGENDA_ACOMPANHAMENTO(1, "Agenda de Acompanhamento"), PESSOAL(2, "Agenda Pessoal"), PROFISSIONAL(3, "Agenda Profissional");
	
	private Integer codigo = null;

	private String descricao = null;

	private TipoAgendaEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static TipoAgendaEnum getTipoAgendaByCodigo(Integer codigo) {
		for (TipoAgendaEnum tipoAgendaEnum : values()) {
			if (tipoAgendaEnum.getCodigo() == codigo) {
				return tipoAgendaEnum;
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
