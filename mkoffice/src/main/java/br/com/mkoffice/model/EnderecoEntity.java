package br.com.mkoffice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.mkoffice.dto.EnderecoDTO;

@Entity
@Table(name = "TB_ENDERECO")
public class EnderecoEntity implements Serializable {
	
	private static final long serialVersionUID = 9165943015275370490L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_ENDERECO")
	private Long id;
	
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
	
	public static EnderecoEntity convertDTOToEntity(EnderecoDTO dto){
		EnderecoEntity entity = new EnderecoEntity();
		entity.setBairro(dto.getBairro());
		entity.setCep(dto.getCep());
		entity.setCidade(dto.getCidade());
		entity.setComplemento(dto.getComplemento());
		entity.setEstado(dto.getEstado());
		entity.setLogradouro(dto.getLogradouro());
		entity.setNumero(dto.getNumero());
		entity.setTipoLogradouro(dto.getTipoLogradouro());
		
		return entity;
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
