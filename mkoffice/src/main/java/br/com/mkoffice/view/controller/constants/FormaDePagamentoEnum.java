package br.com.mkoffice.view.controller.constants;

public enum FormaDePagamentoEnum {

	DINHEIRO("Dinheiro"), DEBITO("Cart�o de D�bito"), CREDITO("Cart�o de Cr�dito");

	private final String label;

	private FormaDePagamentoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
