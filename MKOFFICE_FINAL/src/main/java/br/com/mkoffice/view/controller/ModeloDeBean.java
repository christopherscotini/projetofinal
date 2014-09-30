package br.com.mkoffice.view.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author christopher.rozario
 *
 */

@ManagedBean
@SessionScoped
public class ModeloDeBean extends AbstractModelBean{

		
		private final String TELA_CONSULTA = "";
		
		
		@Override
		public String iniciarTela() {

			return TELA_CONSULTA;
		}

		@Override
		public void limparCamposFiltro() {

		}

		@Override
		public String pesquisarFiltro() {
			
			return TELA_CONSULTA;
		}
		
		

//		=============================== MÉTODOS PRIVATES =============================== 
		
		
		
		
		
/**		**************************		  GETs E SETs      ****************************/


}
