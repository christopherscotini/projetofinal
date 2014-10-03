/**
 * 
 */
package br.com.mkoffice.view.controller.menu.equipe;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.mkoffice.dto.ConsultoraDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.embeddable.Endereco;
import br.com.mkoffice.model.embeddable.Pessoa;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;
import br.com.mkoffice.ws.BuscaCEP;
import br.com.mkoffice.ws.CEP;

/**
 * @author christopher.rozario
 *
 */
@ManagedBean
@SessionScoped
public class ConsultoraBean extends AbstractModelBean implements Serializable{

	private static final long serialVersionUID = -888336628697962999L;
	
	final String TELA_CONSULTAR = "/content/m-equipe/listarEquipe";
	final String TELA_CADASTRAR = "/content/m-equipe/incConsultora";
	final String TELA_ALTERAR = "/content/m-equipe/altConsultora";
	final String TELA_DETALHAR = "/content/m-equipe/detConsultora";
	
	private boolean cadastrar;
	private ConsultoraDTO consultora;
	private ConsultoraDTO consultoraSelecionado;
	private List<ConsultoraDTO>listaConsultoras;
	private String nomeFiltro;
	private String emailFiltro;
	private String celularFiltro;
	private String codConsultoraFiltro;
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		consultoraSelecionado = null;
		
		return pesquisarFiltro();
	}

	@Override
	public void limparCamposFiltro() {
		nomeFiltro = null;
		emailFiltro = null;
		celularFiltro = null;
		codConsultoraFiltro = null;
	}

	@Override
	public String pesquisarFiltro() {
		try{
			listaConsultoras = consultoraBO.buscarEntidadePorFiltro(codConsultoraFiltro, emailFiltro, nomeFiltro, celularFiltro, getLoginBean().getUsuario().getId());
		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
		}
		return TELA_CONSULTAR;
	}

	
	public String prepareInsert(){
		consultora = new ConsultoraDTO();
		consultora.setUsuario(getLoginBean().getUsuario());
		consultora.setDadosPessoa(new Pessoa());
		consultora.getDadosPessoa().setEndereco(new Endereco());
		setCadastrar(true);
		
		return TELA_CADASTRAR;
	}

	public String prepareEdit(){
		consultora = consultoraSelecionado;
		setCadastrar(false);
		
		return TELA_ALTERAR;
	}
	
	public String executeSave() {
		try{
			if(cadastrar){
				consultoraBO.adicionarEntidade(consultora);
				FacesUtils.addMessageInclusaoSucesso();
			}else{
				consultoraBO.editarEntidade(consultora);
				FacesUtils.addMessageAlteracaoSucesso();
			}
		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
			return "";
		}
		
		return iniciarTela();
	}
	
	public String detalharConsultora(){
		consultora = consultoraSelecionado;
		
		return TELA_DETALHAR;
	}
	
	public String limparFormulario() {
		consultora = new ConsultoraDTO();
		consultora.setDadosPessoa(new Pessoa());
		consultora.getDadosPessoa().setEndereco(new Endereco());
		
		return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
	}
	
	public String voltarInclusao() {
		return TELA_CONSULTAR;
	}

	public void limparRegistroSelecionado() {
		consultoraSelecionado = null;
	}
	
	public void buscarEnderecoViaWebService(){
		BuscaCEP busca = new BuscaCEP();
		try{
			CEP cepRetorno = busca.obtemPorNumeroCEP(consultora.getDadosPessoa().getEndereco().getCep());
			consultora.getDadosPessoa().getEndereco().setBairro(cepRetorno.getBairro());
			consultora.getDadosPessoa().getEndereco().setCidade(cepRetorno.getLocalidade());
			consultora.getDadosPessoa().getEndereco().setLogradouro(cepRetorno.getLogradouro());
			consultora.getDadosPessoa().getEndereco().setEstado(cepRetorno.getUf());
		}catch(RuntimeException r){
			FacesUtils.addErrorMessage(r.getMessage());
		}
	}
	
	

	/*****************************************************************************/	
	
	public ConsultoraDTO getConsultora() {
		return consultora;
	}

	public void setConsultora(ConsultoraDTO consultora) {
		this.consultora = consultora;
	}

	public boolean isCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public ConsultoraDTO getConsultoraSelecionado() {
		return consultoraSelecionado;
	}

	public void setConsultoraSelecionado(ConsultoraDTO consultoraSelecionado) {
		this.consultoraSelecionado = consultoraSelecionado;
	}

	public List<ConsultoraDTO> getListaConsultoras() {
		return listaConsultoras;
	}

	public void setListaConsultoras(List<ConsultoraDTO> listaConsultoras) {
		this.listaConsultoras = listaConsultoras;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public String getEmailFiltro() {
		return emailFiltro;
	}

	public void setEmailFiltro(String emailFiltro) {
		this.emailFiltro = emailFiltro;
	}

	public String getCelularFiltro() {
		return celularFiltro;
	}

	public void setCelularFiltro(String celularFiltro) {
		this.celularFiltro = celularFiltro;
	}

	public String getCodConsultoraFiltro() {
		return codConsultoraFiltro;
	}

	public void setCodConsultoraFiltro(String codConsultoraFiltro) {
		this.codConsultoraFiltro = codConsultoraFiltro;
	}

	
}
