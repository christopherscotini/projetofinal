package br.com.mkoffice.view.constants;


public enum TipoFluxoEstoqueEnum {

	ENTRADA_DEVOLUCAO_EMPRESTIMO(0, "Entrada - Devolução de Empréstimo"),
	ENTRADA_PEDIDO(1, "Entrada - Pedido"),
	ENTRADA_MANUAL(2, "Entrada - Manual"),
	SAIDA_VENDA(3, "Saida - Venda"),
	SAIDA_DEMONSTRACAO(4, "Saida - Sessão/Demonstração"),
	SAIDA_MANUAL(5, "Saida - Manual"),
	SAIDA_PRESENTE(6, "Saida - Brinde"),
	SAIDA_EMPRESTIMO(7, "Saida - Empréstimo");

	private Integer codigo = null;

	private String descricao = null;

	private TipoFluxoEstoqueEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static TipoFluxoEstoqueEnum getTipoFluxoByCodigo(Integer codigo) {
		for (TipoFluxoEstoqueEnum tipoFluxoEstoqueEnum : values()) {
			if (tipoFluxoEstoqueEnum.getCodigo() == codigo) {
				return tipoFluxoEstoqueEnum;
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
