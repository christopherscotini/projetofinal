package br.com.mkoffice.view.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.embeddable.Endereco;
import br.com.mkoffice.model.embeddable.Pessoa;
import br.com.mkoffice.utils.StringUtil;
import br.com.mkoffice.view.controller.menu.clientes.vo.ClienteVO;
import br.com.mkoffice.view.utils.FacesUtils;
import br.com.mkoffice.ws.BuscaCEP;
import br.com.mkoffice.ws.CEP;

/**
 * @author christopher.rozario
 *
 */

@ManagedBean
@SessionScoped
public class FormularioUsuarioBean extends AbstractModelBean{

		
		private final String TELA_CADASTRO = "/content/cadastro-usuarios/formularioNovosUsuarios.xhtml";
		private ClienteVO vo = null;
		private String password = null;
		
		@Override
		public String iniciarTela() {
			resetCadastro();
			
			return TELA_CADASTRO;
		}

		@Override
		public void limparCamposFiltro() {
			resetCadastro();
		}

		@Override
		public String pesquisarFiltro() {
			
			return null;
		}
		
		public String executeSave(){
			UserEntity usuarioCadastro = extrairUsuario(vo);
			try{
				if(!StringUtil.isNotBlank(password)){
					FacesUtils.addErrorMessage("Senha: Campo obrigat�rio!");
					return "";
				}else{
					if(password.equals(vo.getUsuario().getPasswordConfirm())){
						usuarioCadastro.setPassword(password.trim());
					}else{
						FacesUtils.addErrorMessage("As senhas n�o conferem.");
						return "";
					}
				}
				userBO.adicionarEntidade(usuarioCadastro);
				FacesUtils.addMessageInclusaoSucesso();
			}catch(BusinessException b){
				
				FacesUtils.addErrorMessage(b.getMessage());
				return "";
			}
			
			return "/login.xhtml";
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

//		=============================== M�TODOS PRIVATES =============================== 
		
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
			password = "";
		}

		/**		**************************		  GETs E SETs      ****************************/

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

}
