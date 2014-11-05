package br.com.mkoffice.security;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.mkoffice.business.bo.ReportBO;
import br.com.mkoffice.business.bo.UserBO;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.security.util.SessionContants;
import br.com.mkoffice.utils.constants.PagesContants;

/**
 * * Bean responsvel por controlar operaes de login, logout e controle de *
 * sesses de usurios.
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -8081378309027791765L;

	@Inject
	private UserBO userBO = null;

	@Inject
	private ReportBO reportBO = null;
	
	public static final String LOGIN_FALHA = "login_falha";
	public static final String SESSAO_INEXISTENTE = "sessao_invalida";
	public static final String USUARIO_SESSAO = "usuario";
	private boolean erro;
	private BigDecimal saldo;

	private UserEntity usuario;
	private ControladorAcesso controladorAcesso;

	public LoginBean() {
	}

	@PostConstruct
	public void inicializar() {
		usuario = new UserEntity();
		saldo = null;
		controladorAcesso = new ControladorAcesso();
	}
	
	@PreDestroy
	public void destruirSessao(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		UserEntity usuarioSessao = (UserEntity) sessao.getAttribute(USUARIO_SESSAO);
		
		if (usuarioSessao != null) {
			sessao.setAttribute(SessionContants.SESSION_USER, null);
		}
		saldo = null;
		context.getExternalContext().invalidateSession();
	}

	/**
	 * * Utilizado para tentativas de login no sistema, confrontando dados
	 * fornecidos * pelo usurio com registros de usurios cadastrados. * * @return
	 * Outcome associado a fracasso ou sucesso na tentativa de login.
	 */
	public String doLogin() {
		if (camposPreenchidos()){
				UserEntity userTentandoLogar = userBO.verificarLogin(usuario);
				if (userTentandoLogar != null) { 
					
					// Descobrindo o tipo de usurio que est tentando acessar o sistema.
					UserEntity usuarioLogado = userTentandoLogar;
					
					HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
					sessao.setAttribute(SessionContants.SESSION_USER, usuarioLogado);
					controladorAcesso.configurarAcesso(); 
					
					erro = false;
					usuario = usuarioLogado;
					saldo = reportBO.getSaldoUsuario(usuario);
					return PagesContants.PAGINA_INDEX;
				}
		}
		
		erro = true;
		return LOGIN_FALHA;
	}

	/**
	 * * Utilizado para finalizar uma sesso de um usurio no sistema. * 
	 * * @return
	 * Outcome associado a fracasso ou sucesso na tentativa de logout.
	 */
	public String doLogout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		UserEntity usuarioSessao = (UserEntity) sessao.getAttribute(SessionContants.SESSION_USER);
		
		if (usuarioSessao != null) {
			sessao.setAttribute(SessionContants.SESSION_USER, null);
		}
		
		context.getExternalContext().invalidateSession();
		saldo = null;
		return PagesContants.PAGINA_LOGIN;
	}

	/**
	 * * Utilizado para verificar se as credenciais necessrias para realizao
	 * do * login foram preenchidas. * * @return <code>true</code> em caso de
	 * dados preenchidos. * <code>false</code> caso contrrio.
	 */
	private boolean camposPreenchidos() {
		return (usuario != null && usuario.getUsername() != null
				&& !"".equals(usuario.getUsername())
				&& usuario.getPassword() != null && !"".equals(usuario.getPassword()));
	}

	/**
	 * * Mtodo utilizado para verificar se um usurio tentando logar na
	 * aplicao * j no possui alguma sesso aberta em outro navegador ou
	 * outra aba. A * aplicao est barrando mltiplos acessos simultneos de um
	 * usurio. * * @return <code>true</code> se j existir uma sesso ativa para
	 * o usurio. * <code>false</code> caso contrrio.
	 */

	/** * Limpa todos os dados da tela de login. */
	public void limparTela() {
		this.usuario = new UserEntity();
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}

	public ControladorAcesso getControladorAcesso() {
		return controladorAcesso;
	}

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
}
