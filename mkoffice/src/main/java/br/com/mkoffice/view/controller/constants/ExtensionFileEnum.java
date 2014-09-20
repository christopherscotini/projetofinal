package br.com.mkoffice.view.controller.constants;

public enum ExtensionFileEnum {

	PDF(".pdf"),
	XLS(".xls"),
	XLSX(".xlsx");
	
	private String label;
	
	private ExtensionFileEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
