package br.com.mkoffice.business.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.ClienteBO;
import br.com.mkoffice.business.exception.NoDataFoundException;
import br.com.mkoffice.business.exception.RegistroJaCadastradoException;
import br.com.mkoffice.business.exception.ValidationFormInvalidException;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.AgendaRepository;
import br.com.mkoffice.dao.jpa.cadastro.ClienteRepository;
import br.com.mkoffice.dto.ClienteDTO;
import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.MkmtsUtil;
import br.com.mkoffice.utils.StringUtil;

@Stateless
public class ClienteBOImpl implements ClienteBO {

	@Inject
	private ClienteRepository dao = null;

	// @Inject
	// private AgendaRepository daoAgenda = null;

	@Override
	public List<ClienteDTO> buscarEntidadePorFiltro(String codClienteFiltro,
			String email, String nomeFiltro, String celularFiltro,
			Long idUsuario) {

		List<ClienteDTO> retorno = dao.findByFiltroPesquisa(MkmtsUtil
				.verificaLongNulo(codClienteFiltro == null ? 0 : Long
						.valueOf(codClienteFiltro)), MkmtsUtil
				.verificaStringNula(email), MkmtsUtil
				.verificaStringNula(nomeFiltro), Long
				.valueOf(celularFiltro == null || celularFiltro == "" ? 0
						: MkmtsUtil.removerFormatacaoTelefone(celularFiltro)),
				idUsuario);
		if (retorno.isEmpty()) {
			throw new NoDataFoundException(MkmtsUtil.getMsgs("generico.lbl.valcliente.clientes"));
		}

		return retorno;
	}

	@Override
	public ClienteDTO adicionarEntidade(ClienteDTO dto) {
		validateForm(dto);
		ClienteEntity entity = Adapter.dtoToEntity(dto);
		return Adapter.entityToDto(dao.insert(entity));
	}

	@Override
	public ClienteDTO editarEntidade(ClienteDTO dto) {
		validateForm(dto);
		ClienteEntity entity = Adapter.dtoToEntity(dto);
		if (dao.existCellPhoneClient(entity)) {
			throw new RegistroJaCadastradoException(
					MkmtsUtil.getMsgs("generico.lbl.valcliente.numcelular"));
		} else {
			return Adapter.entityToDto(dao.update(entity));
		}
	}

	@Override
	public List<ClienteDTO> listarTodos() {
		List<ClienteEntity> entities = dao.findAll();
		List<ClienteDTO> returnzz = new ArrayList<ClienteDTO>();
		for (int i = 0; i < entities.size(); i++) {
			returnzz.add(Adapter.entityToDto(entities.get(i)));
		}
		return returnzz;
	}

	@Override
	public void validateForm(ClienteDTO entidade)
			throws ValidationFormRequiredException {
		if (!StringUtil.isNotBlank(entidade.getDadosPessoa().getNome())) {
			throw new ValidationFormRequiredException(MkmtsUtil.getMsgs("generico.lbl.valcliente.nome"));
		}

		if (!StringUtil.isNotBlank(entidade.getDadosPessoa().getSexo())) {
			throw new ValidationFormRequiredException(MkmtsUtil.getMsgs("generico.lbl.valcliente.sexo"));
		}

		if (!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco()
				.getLogradouro())) {
			throw new ValidationFormRequiredException(MkmtsUtil.getMsgs("generico.lbl.valcliente.endereco"));
		}

		if (!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco()
				.getNumero())) {
			throw new ValidationFormRequiredException(MkmtsUtil.getMsgs("generico.lbl.valcliente.numero"));
		}

		if (!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco()
				.getBairro())) {
			throw new ValidationFormRequiredException(MkmtsUtil.getMsgs("generico.lbl.valcliente.bairro"));
		}

		if (!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco()
				.getCidade())) {
			throw new ValidationFormRequiredException(MkmtsUtil.getMsgs("generico.lbl.valcliente.cidade"));
		}

		if (!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco()
				.getEstado())) {
			throw new ValidationFormRequiredException(MkmtsUtil.getMsgs("generico.lbl.valcliente.estado"));
		}

		if (!StringUtil.isNotBlank(entidade.getDadosPessoa().getEndereco()
				.getCep())) {
			throw new ValidationFormRequiredException(MkmtsUtil.getMsgs("generico.lbl.valcliente.cep"));
		}

		if (!StringUtil.isNotBlank(entidade.getDadosPessoa().getEmail())) {
			throw new ValidationFormRequiredException(MkmtsUtil.getMsgs("generico.lbl.valcliente.email"));
		}

		String regex = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";
		if (!entidade.getDadosPessoa().getEmail().matches(regex)) {
			throw new ValidationFormInvalidException(MkmtsUtil.getMsgs("generico.lbl.valcliente.email"));
		}

		if (entidade.getNumCelularString() == null) {
			// !StringUtil.isNotBlank(entidade.getNumCelularString()) ||
			throw new ValidationFormRequiredException(MkmtsUtil.getMsgs("generico.lbl.valcliente.numcel"));
		}

	}

}
