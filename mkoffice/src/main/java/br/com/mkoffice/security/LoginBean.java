package br.com.mkoffice.security;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.mkoffice.business.bo.UserBO;
import br.com.mkoffice.dto.UserDTO;
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
	
	public static final String LOGIN_FALHA = "login_falha";
	public static final String SESSAO_INEXISTENTE = "sessao_invalida";
	public static final String USUARIO_SESSAO = "usuario";
	private boolean erro;

	private UserDTO usuario;
	private ControladorAcesso controladorAcesso;

	public LoginBean() {
		
	}

	@PostConstruct
	public void inicializar() {
		usuario = new UserDTO();
		controladorAcesso = new ControladorAcesso();
	}
	
	@PreDestroy
	public void destruirSessao(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		UserDTO usuarioSessao = (UserDTO) sessao.getAttribute(USUARIO_SESSAO);
		
		if (usuarioSessao != null) {
			sessao.setAttribute(SessionContants.SESSION_USER, null);
		}
		
		context.getExternalContext().invalidateSession();
	}

	/**
	 * * Utilizado para tentativas de login no sistema, confrontando dados
	 * fornecidos * pelo usurio com registros de usurios cadastrados. * * @return
	 * Outcome associado a fracasso ou sucesso na tentativa de login.
	 */
	
	public String doLogin() {
		if (camposPreenchidos()){
			UserDTO userTentandoLogar = userBO.verificarLogin(usuario);
			if (userTentandoLogar != null) { 
				
				// Descobrindo o tipo de usuário que está tentando acessar o sistema.
				UserDTO usuarioLogado = userTentandoLogar;
				
				HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				sessao.setAttribute(SessionContants.SESSION_USER, usuarioLogado);
				controladorAcesso.configurarAcesso(); 
				
				erro = false;
				usuario = usuarioLogado;
				return PagesContants.PAGINA_INDEX+"?faces-redirect=true";
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
		UserDTO usuarioSessao = (UserDTO) sessao.getAttribute(SessionContants.SESSION_USER);
		
		if (usuarioSessao != null) {
			sessao.setAttribute(SessionContants.SESSION_USER, null);
		}
		
//		context.getExternalContext().invalidateSession();
		
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
		this.usuario = new UserDTO();
	}

	public UserDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UserDTO usuario) {
		this.usuario = usuario;
	}

	public ControladorAcesso getControladorAcesso() {
		return controladorAcesso;
	}

	public UserBO getUserBO() {
		return userBO;
	}

	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
	}

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}
}
