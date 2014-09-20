package br.com.mkoffice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.mkoffice.dto.PessoaDTO;

@Entity
@Table(name = "TB_PESSOA")
public class PessoaEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_PESSOA")
	private Long id;

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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="TB_ENDERECO_PESSOA_FK")
	private EnderecoEntity endereco;

	public PessoaEntity() {
	
	}

	public PessoaEntity(String nome, EnderecoEntity endereco, Long numTelefone,
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
	
	public static PessoaEntity convertDTOToEntity(PessoaDTO dto){
		PessoaEntity entity = new PessoaEntity();
		entity.setDataNascimento(dto.getDataNascimento());
		entity.setEmail(dto.getEmail());
		entity.setEndereco(EnderecoEntity.convertDTOToEntity(dto.getEndereco()));
		entity.setNome(dto.getNome());
		entity.setNumCelular(dto.getNumCelular());
		entity.setNumTelefone(dto.getNumTelefone());
		entity.setSexo(dto.getSexo());
		
		return entity;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnderecoEntity getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEntity endereco) {
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
