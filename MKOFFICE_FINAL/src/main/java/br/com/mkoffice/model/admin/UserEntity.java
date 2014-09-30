package br.com.mkoffice.model.admin;

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

import br.com.mkoffice.model.embeddable.Pessoa;


@Entity
@Table(name = "TB_USER")
public class UserEntity implements Serializable{
	
	private static final long serialVersionUID = 6651000214536467904L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private Long id;

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
	
	private Pessoa dadosPessoa;
	
	public UserEntity() {

	}

	public UserEntity(Long id, String username, String password,
			PermissaoEntity permissao, Pessoa dadosPessoa) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.permissao = permissao;
		this.dadosPessoa = dadosPessoa;
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

	public Pessoa getDadosPessoa() {
		return dadosPessoa;
	}

	public void setDadosPessoa(Pessoa dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

}
