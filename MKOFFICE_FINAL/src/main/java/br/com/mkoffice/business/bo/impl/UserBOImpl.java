/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.UserBO;
import br.com.mkoffice.business.exception.RegistroJaCadastradoException;
import br.com.mkoffice.business.exception.ValidationFormInvalidException;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.business.validator.CpfValidator;
import br.com.mkoffice.dao.jpa.cadastro.ParametrosDashboardRepository;
import br.com.mkoffice.dao.jpa.cadastro.UserRepository;
import br.com.mkoffice.model.ParametrosDashboardEntity;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.utils.CpfCnpjUtils;
import br.com.mkoffice.utils.CriptografiaUtil;
import br.com.mkoffice.utils.MkmtsUtil;
import br.com.mkoffice.utils.StringUtil;

/**
 * @author christopher.rozario
 *
 */

@Stateless
public class UserBOImpl implements UserBO{

	@Inject
	private UserRepository dao = null;

	@Inject
	private ParametrosDashboardRepository parametrosDashboardRepository = null;

	@Override
	public UserEntity verificarLogin(UserEntity usuario) {
		
		return dao.findUserByLogin(usuario.getUsername(), CriptografiaUtil.criptografar(usuario.getPassword()));
		
	}

	@Override
	public UserEntity adicionarEntidade(UserEntity usuario) {
		validateForm(usuario);
		
		if(dao.existsUserWithCpf(usuario)){
			throw new RegistroJaCadastradoException("Usuario com CPF "+CpfCnpjUtils.formatarCpfCnpjString(usuario.getCpf()));
		}

		if(dao.existsUserWithUsername(usuario)){
			throw new RegistroJaCadastradoException("Usuario "+usuario.getUsername());
		}
		if(usuario.getDadosPessoa().getSexo().equalsIgnoreCase("m")){
			usuario.setUrlAvatar("resources/images/avatar/man_business_avatar.png");
		}else{
			usuario.setUrlAvatar("resources/images/avatar/woman_business_avatar.png");
		}
		usuario.setUsername(usuario.getUsername().toLowerCase());
		usuario.setPassword(CriptografiaUtil.criptografar(usuario.getPassword()));
		return dao.insert(usuario);
	}

	@Override
	public ParametrosDashboardEntity atualizarParametros(ParametrosDashboardEntity parametros){
		parametros = parametrosDashboardRepository.insert(parametros);
		return parametros;
	}
	
	@Override
	public UserEntity editarEntidade(UserEntity usuario) {
		validateFormAlterar(usuario);
		if(dao.existsUserWithCpf(usuario)){
			throw new RegistroJaCadastradoException("Usuario com CPF "+CpfCnpjUtils.formatarCpfCnpjString(usuario.getCpf()));
		}

		if(dao.existsUserWithUsername(usuario)){
			throw new RegistroJaCadastradoException("Usuario "+usuario.getUsername());
		}
		if(StringUtil.isNotBlank(usuario.getPassword()) && StringUtil.isNotBlank(usuario.getPasswordConfirm())){
			usuario.setPassword(CriptografiaUtil.criptografar(usuario.getPassword()));
		}
		return dao.update(usuario);

	}
	
	@Override
	public List<UserEntity> buscarEntidadePorFiltro(String username) {
		
		return dao.findUserbyFilter(MkmtsUtil.verificaStringNula(username));
	}

	@Override
	public List<UserEntity> listarTodos() {
		return dao.findAll();
	}

	public void validateFormAlterar(UserEntity entidade) throws ValidationFormRequiredException {
		if(entidade.getCpf() == null){
			throw new ValidationFormRequiredException("CPF");
		}else{
			if(!CpfValidator.validaCPF(entidade.getCpf().toString())){
				throw new ValidationFormInvalidException("CPF");
			}
		}

		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getNome())){
			throw new ValidationFormRequiredException("NOME");
		}

		if(entidade.getDadosPessoa().getDataNascimento() == null){
			throw new ValidationFormRequiredException("DATA DE NASCIMENTO");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getSexo())){
			throw new ValidationFormRequiredException("SEXO");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getLogradouro())){
			throw new ValidationFormRequiredException("ENDERE�O");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getNumero())){
			throw new ValidationFormRequiredException("N�MERO");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getBairro())){
			throw new ValidationFormRequiredException("BAIRRO");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getCidade())){
			throw new ValidationFormRequiredException("CIDADE");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getEstado())){
			throw new ValidationFormRequiredException("ESTADO");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getCep())){
			throw new ValidationFormRequiredException("CEP");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEmail())){
			throw new ValidationFormRequiredException("EMAIL");
		}
		
		String regex = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";   
		if(!entidade.getDadosPessoa().getEmail().matches(regex)){
			throw new ValidationFormInvalidException("EMAIL");
		}
		
		if(entidade.getDadosPessoa().getNumCelular() == null){
			throw new ValidationFormRequiredException("CELULAR");
		}

		if(!(StringUtil.isNotBlank(entidade.getUsername()))){
			throw new ValidationFormRequiredException("USU�RIO");
		}

	}
	
	
	@Override
	public void validateForm(UserEntity entidade) throws ValidationFormRequiredException {
		if(entidade.getCpf() == null){
			throw new ValidationFormRequiredException("CPF");
		}else{
			if(!CpfValidator.validaCPF(entidade.getCpf().toString())){
				throw new ValidationFormInvalidException("CPF");
			}
		}

		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getNome())){
			throw new ValidationFormRequiredException("NOME");
		}

		if(entidade.getDadosPessoa().getDataNascimento() == null){
			throw new ValidationFormRequiredException("DATA DE NASCIMENTO");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getSexo())){
			throw new ValidationFormRequiredException("SEXO");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getLogradouro())){
			throw new ValidationFormRequiredException("ENDERE�O");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getNumero())){
			throw new ValidationFormRequiredException("N�MERO");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getBairro())){
			throw new ValidationFormRequiredException("BAIRRO");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getCidade())){
			throw new ValidationFormRequiredException("CIDADE");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getEstado())){
			throw new ValidationFormRequiredException("ESTADO");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getCep())){
			throw new ValidationFormRequiredException("CEP");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEmail())){
			throw new ValidationFormRequiredException("EMAIL");
		}
		
		String regex = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";   
		if(!entidade.getDadosPessoa().getEmail().matches(regex)){
			throw new ValidationFormInvalidException("EMAIL");
		}
		
		if(entidade.getDadosPessoa().getNumCelular() == null){
			throw new ValidationFormRequiredException("CELULAR");
		}

		if(!(StringUtil.isNotBlank(entidade.getUsername()))){
			throw new ValidationFormRequiredException("USU�RIO");
		}

		if(!(StringUtil.isNotBlank(entidade.getPassword()))){
			throw new ValidationFormRequiredException("SENHA");
		}
		
	}


	
}
