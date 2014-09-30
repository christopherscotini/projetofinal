package br.com.mkoffice.model.embeddable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Pessoa {

	@Column(name = "DS_NOME")
	private String nome;

	@Column(name = "NU_TELEFONE")
	private Long numTelefone;

	@Column(name = "NU_CELULAR")
	private Long numCelular;

	@Column(name = "DS_EMAIL")
	private String email;

	@Column(name = "DT_NASCIMENTO")
	private Date dataNascimento;

	@Column(name = "DS_SEXO")
	private String sexo;

	private Endereco endereco;

	public Pessoa() {
	}

	public Pessoa(String nome, Endereco endereco, Long numTelefone,
			Long numCelular, String email, Date dataNascimento, String sexo) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.numTelefone = numTelefone;
		this.numCelular = numCelular;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getNumTelefone() {
		return numTelefone;
	}

	public void setNumTelefone(Long numTelefone) {
		this.numTelefone = numTelefone;
	}

	public Long getNumCelular() {
		return numCelular;
	}

	public void setNumCelular(Long numCelular) {
		this.numCelular = numCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
