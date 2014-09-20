package br.com.mkoffice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CONFIGURACAO_SISTEMA")
public class DefaultConfigurationEntity implements Serializable{
	
	private static final long serialVersionUID = 3509513055661119609L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONFIGURACAO_SISTEMA")
	private Long id;
	
	@Column(name = "DS_IMG_FUNDO_TELA_LOGIN")
	private String imgFundoTelaLogin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgFundoTelaLogin() {
		return imgFundoTelaLogin;
	}

	public void setImgFundoTelaLogin(String imgFundoTelaLogin) {
		this.imgFundoTelaLogin = imgFundoTelaLogin;
	}
	
}
