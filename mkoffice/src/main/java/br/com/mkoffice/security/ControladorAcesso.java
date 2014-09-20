package br.com.mkoffice.security;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.mkoffice.dto.UserDTO;
import br.com.mkoffice.security.util.SessionContants;

/**
 * * Controlador utilizado especificamente para verifica��o de permiss�es de *
 * acesso a funcionalidades oferecidas pela aplica��o.
 */
public class ControladorAcesso {
	
	private boolean permissaoAdministrador;
	private boolean permissaoUsuarioComum;

	public boolean isPermissaoAdministrador() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		UserDTO usuarioSessao = (UserDTO) sessao
				.getAttribute(SessionContants.SESSION_USER);
		if (usuarioSessao != null) {
			permissaoAdministrador = (usuarioSessao.getPermissao().getDescPermissao().equals("ADMINISTRADOR"));
		} else {
			permissaoAdministrador = false;
		}
		return permissaoAdministrador;
	}

	public boolean isPermissaoUsuarioComum() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		UserDTO usuarioSessao = (UserDTO) sessao
				.getAttribute(SessionContants.SESSION_USER);
		if (usuarioSessao != null) {
			permissaoAdministrador = (usuarioSessao.getPermissao().getDescPermissao().equals("ADMINISTRADOR"));
			if (permissaoAdministrador) {
				permissaoUsuarioComum = true;
			} else {
				permissaoUsuarioComum = (usuarioSessao.getPermissao().getDescPermissao().equals("USUARIO_COMUM"));
			}
		} else {
			permissaoUsuarioComum = false;
		}
		return permissaoUsuarioComum;
	}

	/**
	 * * M�todo utilizado para configurar o perfil de acesso do usu�rio logado
	 * �s * funcionalidades da aplica��o.
	 */
	public void configurarAcesso() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		UserDTO usuarioSessao = (UserDTO) sessao
				.getAttribute(SessionContants.SESSION_USER);
		if (usuarioSessao != null) {
			System.out.println(">>>>>>>>>>>>>> Usuário da sessão tem tipo: "+ usuarioSessao.getPermissao().getDescPermissao());
			permissaoAdministrador = (usuarioSessao.getPermissao().getDescPermissao().equals("ADMINISTRADOR"));
			
			if (permissaoAdministrador) {
				permissaoUsuarioComum = true;
			} else {
				permissaoUsuarioComum = (usuarioSessao.getPermissao().getDescPermissao().equals("USUARIO_COMUM"));
			}
			
		}
	}
}
