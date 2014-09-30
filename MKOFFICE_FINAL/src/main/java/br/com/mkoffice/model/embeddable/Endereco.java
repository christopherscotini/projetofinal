package br.com.mkoffice.model.embeddable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 9165943015275370490L;
	
	@Column(name = "DS_ESTADO")
	private String estado;
	
	@Column(name = "DS_CIDADE")
    private String cidade;
	
	@Column(name = "DS_BAIRRO")
    private String bairro;
	
	@Column(name = "DS_CEP")
    private String cep;
    
	@Column(name = "DS_NUMERO")
    private String numero;
    
	@Column(name = "DS_TP_LOGRADOURO")
    private String tipoLogradouro;
    
	@Column(name = "DS_LOGRADOURO")
    private String logradouro;
    
	@Column(name = "DS_COMPLEMENTO")
    private String complemento;
	
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
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
    
}
