package br.com.mkoffice.view.constants;

public enum FormaDePagamentoEnum {

	DINHEIRO("Dinheiro"), DEBITO("Cartão de Débito"), CREDITO("Cartão de Crédito");

	private final String label;

	private FormaDePagamentoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
