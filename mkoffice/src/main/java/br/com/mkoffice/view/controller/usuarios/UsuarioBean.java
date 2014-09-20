package br.com.mkoffice.view.controller.usuarios;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.dto.ClienteDTO;
import br.com.mkoffice.dto.EnderecoDTO;
import br.com.mkoffice.dto.PermissaoDTO;
import br.com.mkoffice.dto.PessoaDTO;
import br.com.mkoffice.dto.UserDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.utils.StringUtil;
import br.com.mkoffice.view.AbstractModelBean;
import br.com.mkoffice.view.controller.utils.FacesUtils;
import br.com.mkoffice.ws.BuscaCEP;
import br.com.mkoffice.ws.CEP;

@ManagedBean
@SessionScoped
public class UsuarioBean extends AbstractModelBean{

	private final String TELA_MANTER_USUARIOS = "/content/m-admin/usuario/manterUsuario.xhtml";
	private final String TELA_ALTERAR_CADASTRO_USUARIO = "/content/cadastro-usuarios/formularioAlterarNovosUsuarios.xhtml";
	private final String TELA_DETALHAR_CADASTRO_USUARIO = "/content/m-admin/usuario/detalharformularioUsuarios.xhtml";
	
	private List<UserDTO> usuarios;
	private List<PermissaoDTO> permissoes;
	private List<UserDTO> filteredUsuarios;
	private UserDTO usuario;
	private UserDTO usuarioSelecionado;
	private boolean cadastrar;
	
	private ClienteDTO dto = null;
	private String password = null;
	
	
	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		limparSelecaoTabela();
		carregarListaUsuarios();
		
		return TELA_MANTER_USUARIOS;
	}

	@Override
	public void limparCamposFiltro() {
	}

	public void limparSelecaoTabela() {
		setUsuarioSelecionado(null);
	}

	@Override
	public String pesquisarFiltro() {
		
		return "";
	}

	public void buscarEnderecoViaWebService(){
		BuscaCEP busca = new BuscaCEP();
		try{
			CEP cepRetorno = busca.obtemPorNumeroCEP(dto.getDadosPessoa().getEndereco().getCep());
			dto.getDadosPessoa().getEndereco().setBairro(cepRetorno.getBairro());
			dto.getDadosPessoa().getEndereco().setCidade(cepRetorno.getLocalidade());
			dto.getDadosPessoa().getEndereco().setLogradouro(cepRetorno.getLogradouro());
			dto.getDadosPessoa().getEndereco().setEstado(cepRetorno.getUf());
		}catch(RuntimeException r){
			FacesUtils.addErrorMessage(r.getMessage());
		}
	}
	
	public void navegarIncluirUsuario(){
		cadastrar = true;
	}

	public String navegarDetalharUsuario(){
		if(dto != null){
			dto = converterUserDTOToClienteDTO(usuarioSelecionado);
		}else{
			dto = converterUserDTOToClienteDTO(loginBean.getUsuario());
		}
		
		return TELA_DETALHAR_CADASTRO_USUARIO;
	}
	
	public String navegarVoltarTelaDetalhe(){
		
		return TELA_MANTER_USUARIOS;
	}

	public void navegarAlterarUsuario(){
		usuario = usuarioSelecionado;
		cadastrar = false;
	}

	public String executeSave(){
		UserDTO usuarioCadastro = extrairUsuario(dto);

		try{
			
			if(!StringUtil.isNotBlank(password) && !StringUtil.isNotBlank(usuarioCadastro.getPassword())){
				FacesUtils.addErrorMessage("Senha: Campo obrigatário!");
				return "";
			}else{
				if(!password.equals("")){
					if(password.equals(dto.getUsuario().getPasswordConfirm())){
						usuarioCadastro.setPassword(password.trim());
					}else{
						FacesUtils.addErrorMessage("As senhas não conferem.");
						return "";
					}
					usuarioCadastro.setPassword(password.trim());
				}else{
					if(!dto.getUsuario().getPasswordConfirm().equals("")){
						FacesUtils.addErrorMessage("As senhas não conferem.");
						return "";
					}
				}
			}
			
			if (cadastrar) {
				userBO.adicionarEntidade(usuarioCadastro);
				FacesUtils.addMessageInclusaoSucesso();
			}else{
				userBO.editarEntidade(usuarioCadastro);
				FacesUtils.addMessageAlteracaoSucesso();
			}
			pesquisarFiltro();

		}catch(BusinessException b){
			FacesUtils.addErrorMessage(b.getMessage());
			return "";
		}
		
		return "/index.xhtml";
	}
	
	
	public String iniciarTelaAlterarCadastro(){
		resetCadastro();
		dto = converterUserDTOToClienteDTO(getLoginBean().getUsuario());
		cadastrar = false;
		password = "";
		
		return TELA_ALTERAR_CADASTRO_USUARIO;
	}
	
//	=============================== M�TODOS PRIVATES =============================== 
	
	private void carregarListaUsuarios(){
		usuarios = userBO.buscarEntidadePorFiltro(null);
	}
	
	private UserDTO extrairUsuario(ClienteDTO dto) {
		UserDTO userDTO = dto.getUsuario();
		userDTO.setDadosPessoa(dto.getDadosPessoa());
		
		return userDTO;
	}
	
	private void resetCadastro(){
		dto = new ClienteDTO();
		dto.setUsuario(new UserDTO());
		dto.setDadosPessoa(new PessoaDTO());
		dto.getDadosPessoa().setEndereco(new EnderecoDTO());
		setPassword("");
	}
	
	private ClienteDTO converterUserDTOToClienteDTO(UserDTO userDTO) {
		ClienteDTO dto = new ClienteDTO();
		dto.setDadosPessoa(userDTO.getDadosPessoa());
		dto.setUsuario(userDTO);
		
		return dto;
	}
	
//	=============================== M�TODOS GET E SET =============================== 
	
	public UserDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UserDTO usuario) {
		this.usuario = usuario;
	}

	public List<UserDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UserDTO> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public UserDTO getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(UserDTO usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<PermissaoDTO> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoDTO> permissoes) {
		this.permissoes = permissoes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserDTO> getFilteredUsuarios() {
		return filteredUsuarios;
	}

	public void setFilteredUsuarios(List<UserDTO> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}

	public ClienteDTO getDto() {
		return dto;
	}

	public void setDto(ClienteDTO dto) {
		this.dto = dto;
	}

}
