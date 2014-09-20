package br.com.mkoffice.dto;

import br.com.mkoffice.model.EnderecoEntity;


public class EnderecoDTO {
	
	private Long id;
	private String estado;
    private String cidade;
    private String bairro;
    private String cep;
    private String numero;
    private String tipoLogradouro;
    private String logradouro;
    private String complemento;

    public static EnderecoDTO convertEntityToDTO(EnderecoEntity entity){
    	EnderecoDTO dto = new EnderecoDTO();
    	dto.setBairro(entity.getBairro());
    	dto.setCep(entity.getCep());
    	dto.setCidade(entity.getCidade());
    	dto.setComplemento(entity.getComplemento());
    	dto.setEstado(entity.getEstado());
    	dto.setLogradouro(entity.getLogradouro());
    	dto.setNumero(entity.getNumero());
    	dto.setTipoLogradouro(entity.getTipoLogradouro());
    	
		return dto;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
    
}
