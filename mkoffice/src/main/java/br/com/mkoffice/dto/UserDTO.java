package br.com.mkoffice.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.mkoffice.model.UserEntity;
import br.com.mkoffice.utils.CpfCnpjUtils;


public class UserDTO {
	
private Long id;
	
	private PessoaDTO dadosPessoa;
	private String username;
	private String password;
	private Long cpf;
	private String passwordConfirm;
	private PermissaoDTO permissao;

	public static UserDTO entityToDTO(UserEntity entity){
		UserDTO dto = null;
		if(entity != null){
			dto = new UserDTO();
			dto.setCpf(entity.getCpf());
			dto.setDadosPessoa(PessoaDTO.entityToDTO(entity.getDadosPessoa()));
			dto.setId(entity.getId());
			dto.setPassword(entity.getPassword());
			dto.setPasswordConfirm(entity.getPasswordConfirm());
			dto.setUsername(entity.getUsername());
			dto.setPermissao(PermissaoDTO.entityToDTO(entity.getPermissao()));
		}
		return dto;
	}
	
	public static List<UserDTO> listEntityToListDTO(List<UserEntity> list) {
		List<UserDTO>dtos = new ArrayList<UserDTO>();
		for (UserEntity entity : list) {
			dtos.add(entityToDTO(entity));
		}
		
		return dtos;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaDTO getDadosPessoa() {
		return dadosPessoa;
	}

	public void setDadosPessoa(PessoaDTO dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	private String cpfString;
	public String getCpfString() {
		if((cpfString == null || cpfString.equals("")) && !(cpf == null)){
			return CpfCnpjUtils.formatarCpfCnpjString(cpf);
		}
		return cpfString;
	}

	public void setCpfString(String cpfString) {
		this.cpfString = cpfString;
	}

	public Long getCpf() {
		return CpfCnpjUtils.removerFormatacaoCpfCnpj(cpfString);
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public PermissaoDTO getPermissao() {
		return permissao;
	}

	public void setPermissao(PermissaoDTO permissao) {
		this.permissao = permissao;
	}


}
