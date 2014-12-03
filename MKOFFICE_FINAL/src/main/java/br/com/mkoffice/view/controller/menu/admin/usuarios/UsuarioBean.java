package br.com.mkoffice.view.controller.menu.admin.usuarios;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.ParametrosDashboardEntity;
import br.com.mkoffice.model.admin.PermissaoEntity;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.embeddable.Endereco;
import br.com.mkoffice.model.embeddable.Pessoa;
import br.com.mkoffice.utils.StringUtil;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.controller.menu.clientes.vo.ClienteVO;
import br.com.mkoffice.view.utils.FacesUtils;
import br.com.mkoffice.ws.BuscaCEP;
import br.com.mkoffice.ws.CEP;

@ManagedBean
@SessionScoped
public class UsuarioBean extends AbstractModelBean{

	private final String TELA_MANTER_USUARIOS = "/content/m-admin/usuario/manterUsuario.xhtml";
	private final String TELA_ALTERAR_CADASTRO_USUARIO = "/content/cadastro-usuarios/formularioAlterarNovosUsuarios.xhtml";
	private final String TELA_DETALHAR_CADASTRO_USUARIO = "/content/m-admin/usuario/detalharformularioUsuarios.xhtml";
	
	private List<UserEntity> usuarios;
	private List<PermissaoEntity> permissoes;
	private List<UserEntity> filteredUsuarios;
	private UserEntity usuario;
	private UserEntity usuarioSelecionado;
	private boolean cadastrar;
	
	private ParametrosDashboardEntity parametros;
	
	private ClienteVO vo = null;
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
			CEP cepRetorno = busca.obtemPorNumeroCEP(vo.getEndereco().getCep());
			vo.getEndereco().setBairro(cepRetorno.getBairro());
			vo.getEndereco().setCidade(cepRetorno.getLocalidade());
			vo.getEndereco().setLogradouro(cepRetorno.getLogradouro());
			vo.getEndereco().setEstado(cepRetorno.getUf());
		}catch(RuntimeException r){
			FacesUtils.addErrorMessage(r.getMessage());
		}
	}
	
	public void navegarIncluirUsuario(){
		cadastrar = true;
	}

	public String navegarDetalharUsuario(){
		
		vo = converterUserEntityToClienteCo(usuarioSelecionado);
		
		parametros = userBO.buscarParametros(usuarioSelecionado);	
		
		return TELA_DETALHAR_CADASTRO_USUARIO;
	}
	
	public String navegarVoltarTelaDetalhe(){
		
		return TELA_MANTER_USUARIOS;
	}

	public String navegarAlterarUsuario(){
		usuario = usuarioSelecionado;
		cadastrar = false;
		
		return TELA_ALTERAR_CADASTRO_USUARIO;
	}

	public String executeSave(){
		UserEntity usuarioCadastro = extrairUsuario(vo);

		try{
			
			if(!StringUtil.isNotBlank(password) && !StringUtil.isNotBlank(usuarioCadastro.getPassword())){
				FacesUtils.addErrorMessage("Senha: Campo obrigat�rio!");
				return "";
			}else{
				if(!password.equals("")){
					if(password.equals(vo.getUsuario().getPasswordConfirm())){
						usuarioCadastro.setPassword(password.trim());
					}else{
						FacesUtils.addErrorMessage("As senhas n�o conferem.");
						return "";
					}
					usuarioCadastro.setPassword(password.trim());
				}else{
					if(!vo.getUsuario().getPasswordConfirm().equals("")){
						FacesUtils.addErrorMessage("As senhas n�o conferem.");
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
		vo = converterUserEntityToClienteCo(getLoginBean().getUsuario());
		cadastrar = false;
		password = "";
		
		return TELA_ALTERAR_CADASTRO_USUARIO;
	}
	
//	=============================== M�TODOS PRIVATES =============================== 
	
	private void carregarListaUsuarios(){
		usuarios = userBO.buscarEntidadePorFiltro(null);
	}
	
	private UserEntity extrairUsuario(ClienteVO vo) {
		UserEntity entity = new UserEntity();
		Pessoa p = new Pessoa();
		p.setDataNascimento(vo.getDataNascimento());
		p.setEmail(vo.getEmail());
		p.setEndereco(vo.getEndereco());
		p.setNome(vo.getNome());
		p.setNumCelular(vo.getNumCelular());
		p.setNumTelefone(vo.getNumTelefone());
		p.setSexo(vo.getSexo());
		entity.setId(vo.getUsuario().getId());
		entity.setDadosPessoa(p);
		entity.setCpf(vo.getCpf());
		entity.setUsername(vo.getUsuario().getUsername());
		entity.setPassword(vo.getUsuario().getPassword());
		entity.setPasswordConfirm(vo.getUsuario().getPasswordConfirm());
		entity.setPermissao(permissaoBO.buscarPerfilUsuarioComum());

		return entity;
	}
	
	private void resetCadastro(){
		vo = new ClienteVO();
		vo.setUsuario(new UserEntity());
		vo.setEndereco(new Endereco());
		setPassword("");
	}
	
	private ClienteVO converterUserEntityToClienteCo(UserEntity userEntity) {
		ClienteVO voClienteVO = new ClienteVO();
		voClienteVO.setDataNascimento(userEntity.getDadosPessoa().getDataNascimento());
		voClienteVO.setEmail(userEntity.getDadosPessoa().getEmail());
		voClienteVO.setEndereco(userEntity.getDadosPessoa().getEndereco());
		voClienteVO.setNome(userEntity.getDadosPessoa().getNome());
		voClienteVO.setNumCelular(userEntity.getDadosPessoa().getNumCelular());
		voClienteVO.setNumTelefone(userEntity.getDadosPessoa().getNumTelefone());
		voClienteVO.setSexo(userEntity.getDadosPessoa().getSexo());
		voClienteVO.setCpf(userEntity.getCpf());
		voClienteVO.setUsuario(userEntity);
		
		return voClienteVO;
	}
	
//	=============================== M�TODOS GET E SET =============================== 
	
	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}

	public List<UserEntity> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UserEntity> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public UserEntity getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(UserEntity usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<PermissaoEntity> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoEntity> permissoes) {
		this.permissoes = permissoes;
	}

	public ClienteVO getVo() {
		return vo;
	}

	public void setVo(ClienteVO vo) {
		this.vo = vo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserEntity> getFilteredUsuarios() {
		return filteredUsuarios;
	}

	public void setFilteredUsuarios(List<UserEntity> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}

	public ParametrosDashboardEntity getParametros() {
		return parametros;
	}
	
	public void setParametros(ParametrosDashboardEntity parametros) {
		this.parametros = parametros;
	}
}
