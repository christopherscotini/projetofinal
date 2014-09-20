package br.com.mkoffice.security.listener;

import java.util.HashSet;
import java.util.Set;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.mkoffice.security.util.SessionContants;
import br.com.mkoffice.utils.constants.PagesContants;

/**
 * * Respons�vel por manipular requisi��es de usu�rio, permitindo acesso ao *
 * conte�do da aplica��o somente no caso do usu�rio j� ter se autenticado.
 */

public class ListenerAutenticacao implements PhaseListener {

	private static final long serialVersionUID = -8137966371401373854L;
	
	private static final Set<String> listaIdTelasDesprotegidas = new HashSet<String>();
	
	static{
		listaIdTelasDesprotegidas.add("/content/cadastro-usuarios/formularioNovosUsuarios.xhtml");
	}
	
	@Override
	public void afterPhase(PhaseEvent event) {
		
//		FacesContext facesContext = event.getFacesContext();
//		String currentPage = facesContext.getViewRoot().getViewId();
//		 
//		boolean isLoginPage = (currentPage.lastIndexOf(PagesContants.PAGINA_LOGIN) > -1);
//		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
//		Object currentUser = session.getAttribute(SessionContants.SESSION_USER);
//		 
//		if (!isLoginPage && (currentUser == null || currentUser == "")) {
//			if(!listaIdTelasDesprotegidas.contains(currentPage)){
//				redirectLoginPage(facesContext);
//			}
//		}
	}
	
	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
	private void redirectLoginPage(FacesContext facesContext){
		NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
		nh.handleNavigation(facesContext, null, PagesContants.PAGINA_LOGIN);
	}

	private void redirectActualPage(FacesContext facesContext, String page){
		NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
		nh.handleNavigation(facesContext, null, page);
	}
}
