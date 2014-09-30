package br.com.mkoffice.view.constants;

public enum GenderEnum {

	MALE("Masculino"), FEMALE("Feminino");

	private final String label;

	private GenderEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

}
