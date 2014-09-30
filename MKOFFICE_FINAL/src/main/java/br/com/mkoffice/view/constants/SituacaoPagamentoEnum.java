package br.com.mkoffice.view.constants;

public enum SituacaoPagamentoEnum {

	PAGO("Pago"), PENDENTE("Pendente de Pagamento");

	private final String label;

	private SituacaoPagamentoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
