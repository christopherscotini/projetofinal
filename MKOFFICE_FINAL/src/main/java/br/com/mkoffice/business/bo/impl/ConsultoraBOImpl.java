package br.com.mkoffice.business.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.ConsultoraBO;
import br.com.mkoffice.business.exception.NoDataFoundException;
import br.com.mkoffice.business.exception.RegistroJaCadastradoException;
import br.com.mkoffice.business.exception.ValidationFormInvalidException;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.ConsultoraRepository;
import br.com.mkoffice.dto.ConsultoraDTO;
import br.com.mkoffice.model.ConsultoraEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.MkmtsUtil;
import br.com.mkoffice.utils.StringUtil;

@Stateless
public class ConsultoraBOImpl implements ConsultoraBO {

	@Inject
	private ConsultoraRepository dao = null;

	
	@Override
	public List<ConsultoraDTO> buscarEntidadePorFiltro(String codConsultoraFiltro, String email,
			String nomeFiltro, String celularFiltro, Long idConsultora) {
		
		List<ConsultoraDTO> retorno = dao.findByFiltroPesquisa(
				  MkmtsUtil.verificaIntegerNulo(codConsultoraFiltro == null || codConsultoraFiltro == "" ? 0 : Integer.valueOf(codConsultoraFiltro))
				, MkmtsUtil.verificaStringNula(email)
				, MkmtsUtil.verificaStringNula(nomeFiltro)
				, Long.valueOf(celularFiltro  == null || celularFiltro == "" ? 0 : MkmtsUtil.removerFormatacaoTelefone(celularFiltro))
				, MkmtsUtil.verificaLongNulo(idConsultora)
				);
		if(retorno.isEmpty()){
			throw new NoDataFoundException("Consultoras");
		}
		
		return retorno;
	}

	@Override
	public ConsultoraDTO adicionarEntidade(ConsultoraDTO dto) {
		validateForm(dto);
		if(dao.existIDConsulter(dto)){
			throw new RegistroJaCadastradoException("Consultora");
		}else{
			if(dao.existCellPhoneConsulter(dto)){
				throw new RegistroJaCadastradoException("N� de Celular");
			}else{
				if(dao.existEmailConsulter(dto)){
					throw new RegistroJaCadastradoException("E-MAIL");
				}else{
					return Adapter.entityToDto(dao.insert(Adapter.dtoToEntity(dto)));
				}
			}
		}
	}
	
	@Override
	public ConsultoraDTO editarEntidade(ConsultoraDTO dto) {
		validateForm(dto);
		if(dao.existCellPhoneConsulter(dto)){
			throw new RegistroJaCadastradoException("N� de Celular");
		}else{
			if(dao.existEmailConsulter(dto)){
				throw new RegistroJaCadastradoException("E-MAIL");
			}else{
				return Adapter.entityToDto(dao.update(Adapter.dtoToEntity(dto)));
			}
		}
	}
	
	@Override
	public List<ConsultoraDTO> listarTodos() {
		List<ConsultoraEntity> entities = dao.findAll();
		List<ConsultoraDTO> returnzz = new ArrayList<ConsultoraDTO>();
		for (int i = 0; i < entities.size(); i++) {
			returnzz.add(Adapter.entityToDto(entities.get(i)));
		}
		return returnzz;
	}


	@Override
	public void validateForm(ConsultoraDTO entidade)
			throws ValidationFormRequiredException {
		
		if(!StringUtil.isNotBlank(entidade.getCodConsultora().toString())){
			throw new ValidationFormRequiredException("CÓD. CONSULTORA");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getNome())){
			throw new ValidationFormRequiredException("NOME");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getSexo())){
			throw new ValidationFormRequiredException("SEXO");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getLogradouro())){
			throw new ValidationFormRequiredException("ENDERE�O");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco().getNumero())){
			throw new ValidationFormRequiredException("NÚMERO");
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
		
		if(entidade.getNumCelularString() == null){
			//!StringUtil.isNotBlank(entidade.getNumCelularString()) || 
			throw new ValidationFormRequiredException("CELULAR");
		}
		
		if(!StringUtil.isNotBlank(entidade.getDataInicioFormatado())){
			throw new ValidationFormRequiredException("DATA DE INICIO");
		}
		
//		if(!StringUtil.isNotBlank(entidade.getStatus()))
	}

	
}
