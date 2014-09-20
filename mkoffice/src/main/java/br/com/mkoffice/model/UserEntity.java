package br.com.mkoffice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.mkoffice.dto.UserDTO;


@Entity
@Table(name = "TB_USER")
public class UserEntity implements Serializable{
	
	private static final long serialVersionUID = 6651000214536467904L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "TB_PESSOA_USER_FK", nullable = false)
	private PessoaEntity dadosPessoa;

	@Column(name = "DS_USERNAME", length= 25, unique = true, nullable = false)
	private String username;
	
	@Column(name = "DS_PASSWORD", nullable = false)
	private String password;

	@Column(name = "NU_CPF", nullable = false)
	private Long cpf;

	@Transient
	private String passwordConfirm;

	@ManyToOne
	@JoinColumn(name = "TB_PERMISSAO_USER_FK", nullable = false)
	private PermissaoEntity permissao;
//	
	public UserEntity() {

	}

	public UserEntity(Long id, String username, String password,
			PermissaoEntity permissao, 
			PessoaEntity dadosPessoa) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.permissao = permissao;
		this.dadosPessoa = dadosPessoa;
	}

	public static UserEntity dtoToEntity(UserDTO dto){
		UserEntity entity = new UserEntity();
		entity.setCpf(dto.getCpf());
		entity.setDadosPessoa(PessoaEntity.convertDTOToEntity(dto.getDadosPessoa()));
		entity.setId(dto.getId());
		entity.setPassword(dto.getPassword());
		entity.setPasswordConfirm(dto.getPasswordConfirm());
		entity.setUsername(dto.getUsername());
		entity.setPermissao(PermissaoEntity.dtoToEntity(dto.getPermissao()));
		
		return entity;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public PermissaoEntity getPermissao() {
		return permissao;
	}

	public void setPermissao(PermissaoEntity permissao) {
		this.permissao = permissao;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public PessoaEntity getDadosPessoa() {
		return dadosPessoa;
	}

	public void setDadosPessoa(PessoaEntity dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

}
