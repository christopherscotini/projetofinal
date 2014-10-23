package br.com.mkoffice.view.controller.menu.clientes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.mkoffice.business.exception.NoDataFoundException;
import br.com.mkoffice.dto.ClienteDTO;
import br.com.mkoffice.dto.VendaDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.embeddable.Endereco;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.view.constants.CodeBeanConstants;
import br.com.mkoffice.view.constants.GenderEnum;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.controller.menu.clientes.vo.ClienteVO;
import br.com.mkoffice.view.controller.menu.vendas.HistoricoVendaBean;
import br.com.mkoffice.view.utils.FacesUtils;
import br.com.mkoffice.ws.BuscaCEP;
import br.com.mkoffice.ws.CEP;

@ManagedBean
@SessionScoped
public class ClienteBean extends AbstractModelBean implements Serializable{
	
	private static final long serialVersionUID = 1748990606630191510L;
	
	@ManagedProperty(value = "#{historicoVendaBean}")
	private HistoricoVendaBean vendaBean = null;
	
	final String TELA_CONSULTAR = "/content/m-cliente/conClientes";
	final String TELA_CADASTRAR = "/content/m-cliente/incCliente";
	final String TELA_ALTERAR = "/content/m-cliente/altCliente";
	final String TELA_DETALHAR = "/content/m-cliente/detCliente";
	
	private ClienteVO vo;
	private boolean cadastrar;
	
	/*** Filtros tela de pesquisa ***/
	private String codClienteFiltro;
	private String emailFiltro;
	private String nomeFiltro;
	private String celularFiltro;

	private List<ClienteDTO>filteredValues = null;
	private List<ClienteDTO>listaClientes = null;
	private ClienteDTO clienteSelecionado = null;
	private VendaDTO vendaSelecionada = null;
	
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		pesquisarFiltro();
		clienteSelecionado = null;
		
		return TELA_CONSULTAR;
	}
	
	@Override
	public void limparCamposFiltro() {
		filteredValues = null;
		codClienteFiltro = null;
		emailFiltro = null;
		nomeFiltro = null;
		celularFiltro = null;
		clienteSelecionado = null;
		cadastrar = false;
		
	}

	@Override
	public String pesquisarFiltro() {
		clienteSelecionado = null;
		
		try{
			listaClientes = clienteBO.buscarEntidadePorFiltro(codClienteFiltro, emailFiltro, nomeFiltro, celularFiltro, getLoginBean().getUsuario().getId());
		}catch(NoDataFoundException ndf){
			listaClientes = null;
			FacesUtils.addErrorMessage(ndf.getMessage());
		}catch (BusinessException b) {
			FacesUtils.addErrorMessage(b.getMessage());
		}
		
		return TELA_CONSULTAR;
	}

	public String prepareInsert(){
		vo = new ClienteVO();
		vo.setUsuario(getLoginBean().getUsuario());
		vo.setEndereco(new Endereco());
		cadastrar = true;
		
		return TELA_CADASTRAR;
	}

	public String prepareEdit(){
		vo = Adapter.dtoToVo(clienteSelecionado);
		cadastrar = false;
		
		return TELA_CADASTRAR;
	}
	
	public void limparFormulario(){
		vo = new ClienteVO();
		vo.setEndereco(new Endereco());
		
//		return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
	}
	
	public String executeSave(){
		
		try{
			if(cadastrar){
				clienteBO.adicionarEntidade(Adapter.voToDto(vo));
				FacesUtils.addMessageInclusaoSucesso();
			}else{
				clienteBO.editarEntidade(Adapter.voToDto(vo));
				FacesUtils.addMessageAlteracaoSucesso();
			}
		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
			return "";
		}
		
		return pesquisarFiltro();
	}
	
	public String detalharCliente(){
		vo = Adapter.dtoToVo(clienteSelecionado);
		vendaSelecionada = null;
		return TELA_DETALHAR;
	}
	
	public void limparCompraClienteSelecionada(){
		vendaSelecionada = null;
	}
	
	public String navegarHistoricoCompraCliente(){
		vendaBean.setChamadaExterna(true);
		vendaBean.setCodeBeanExterno(CodeBeanConstants.CLIENTE_BEAN);
		vendaBean.setVendaSelecionada(vendaSelecionada);
		
		return vendaBean.navegarDetalharVenda();
	}
	
	public String voltarInclusao(){
		
		return TELA_CONSULTAR;
	}
	
	public void limparRegistroSelecionado() {
		clienteSelecionado = null;
	}
	
	public void buscarEnderecoViaWebService(){
		BuscaCEP busca = new BuscaCEP();
		try{
			CEP cepRetorno = busca.obtemPorNumeroCEP(vo.getEndereco().getCep());
			vo.getEndereco().setBairro(cepRetorno.getBairro());
			vo.getEndereco().setCidade(cepRetorno.getLocalidade());
			vo.getEndereco().setLogradouro(cepRetorno.getLogradouro());
			vo.getEndereco().setEstado(cepRetorno.getUf());
		}catch(RuntimeException r){
			FacesUtils.addErrorMessage(r.getMessage());
		}
	}
	
//	=============================== M�TODOS PRIVATES =============================== 

	
	
//	=============================== M�TODOS GETTERS | SETTERS =============================== 
	
	public List<GenderEnum> getComboSexualidade(){
		return new ArrayList<GenderEnum>(Arrays.asList(GenderEnum.values()));
	}
	
	public ClienteVO getVo() {
		return vo;
	}

	public void setVo(ClienteVO vo) {
		this.vo = vo;
	}

	public boolean isCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public String getCodClienteFiltro() {
		return codClienteFiltro;
	}

	public void setCodClienteFiltro(String codClienteFiltro) {
		this.codClienteFiltro = codClienteFiltro;
	}

	public String getEmailFiltro() {
		return emailFiltro;
	}

	public void setEmailFiltro(String emailFiltro) {
		this.emailFiltro = emailFiltro;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public String getCelularFiltro() {
		return celularFiltro;
	}

	public void setCelularFiltro(String celularFiltro) {
		this.celularFiltro = celularFiltro;
	}

	public List<ClienteDTO> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<ClienteDTO> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ClienteDTO getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(ClienteDTO clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public VendaDTO getVendaSelecionada() {
		return vendaSelecionada;
	}

	public void setVendaSelecionada(VendaDTO vendaSelecionada) {
		this.vendaSelecionada = vendaSelecionada;
	}

	public HistoricoVendaBean getVendaBean() {
		return vendaBean;
	}

	public void setVendaBean(HistoricoVendaBean vendaBean) {
		this.vendaBean = vendaBean;
	}

	public List<ClienteDTO> getFilteredValues() {
		return filteredValues;
	}

	public void setFilteredValues(List<ClienteDTO> filteredValues) {
		this.filteredValues = filteredValues;
	}

}
