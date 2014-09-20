package br.com.mkoffice.dto;

import java.util.Date;

import br.com.mkoffice.model.PessoaEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class PessoaDTO {
	
	private Long id;
	private String nome;
	private Long numTelefone;
	private Long numCelular;
	private String email;
	private Date dataNascimento;
	private String sexo;
	private EnderecoDTO endereco;

	public static PessoaDTO entityToDTO(PessoaEntity entity){
		PessoaDTO dto = new PessoaDTO();
		dto.setDataNascimento(entity.getDataNascimento());
		dto.setEmail(entity.getEmail());
		dto.setEndereco(EnderecoDTO.convertEntityToDTO(entity.getEndereco()));
		dto.setNome(entity.getNome());
		dto.setNumCelular(entity.getNumCelular());
		dto.setNumTelefone(entity.getNumTelefone());
		dto.setSexo(entity.getSexo());
		
		return dto;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
	
	private String numTelefoneString;
	private String numCelularString;

	public String getNumTelefoneString() {
		if ((numTelefoneString == null || numTelefoneString.equals(""))
				&& !(getNumTelefone() == null)) {
			return MkmtsUtil.formatarNumTelefoneCelularString(getNumTelefone());
		}
		
		return numTelefoneString;
	}

	public String getNumCelularString() {
		if ((numCelularString == null || numCelularString.equals("")) && !(getNumCelular() == null)) {
			return MkmtsUtil.formatarNumTelefoneCelularString(getNumCelular());
		}
		
		return numCelularString;
	}

	public String getDataNascimentoFormatado() {
		return dataNascimento == null ? "" : MkmtsUtil.converterDataString(dataNascimento, "dd/MM/yyyy");
	}
	
	
}
