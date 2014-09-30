package br.com.mkoffice.dao.jpa.cadastro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.ClienteDTO;
import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.MkmtsUtil;

public class ClienteRepository extends JpaGenericDao<ClienteEntity, Integer>{

	final String SELECT = "SELECT c FROM ClienteEntity c WHERE c.usuario.id = :idUsuario";
	final String ORDER_BY = "ORDER BY c.dadosPessoa.nome";
	
	
	public List<ClienteDTO> findByFiltroPesquisa(Long codClienteFiltro,
			String email, String nomeFiltro, Long celularFiltro, Long idUsuario) {
		
		StringBuilder query = new StringBuilder();
		query.append(SELECT);
		query.append(_ESPACE);
		
		if(codClienteFiltro > 0){
			query.append("AND c.id = "+codClienteFiltro);
			query.append(_ESPACE);
		}
		
		if(!email.equals("")){
			query.append("AND c.dadosPessoa.email = '"+email+"'");
			query.append(_ESPACE);
		}

		if(!nomeFiltro.equals("")){
			query.append("AND c.dadosPessoa.nome LIKE '"+nomeFiltro+"%'");
			query.append(_ESPACE);
		}

		if(celularFiltro > 0){
			query.append("AND c.dadosPessoa.numCelular = '"+celularFiltro+"'");
			query.append(_ESPACE);
		}
		
		query.append(ORDER_BY);
		
		List<ClienteEntity>listaRetornada = getEntityManager().createQuery(query.toString(), ClienteEntity.class).setParameter("idUsuario", idUsuario).getResultList();
		return recuperarOcorrencias(listaRetornada);
	}

	
	public boolean existCellPhoneClient(ClienteEntity cliente) {
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT);
		builder.append(_ESPACE);
		builder.append("AND c.dadosPessoa.numCelular = "+cliente.getDadosPessoa().getNumCelular());
		builder.append(_ESPACE);
		if(MkmtsUtil.verificaLongNulo(cliente.getId()) > 0 ){
			builder.append("AND c.id <> "+cliente.getId()+"");
			builder.append(_ESPACE);
		}
		builder.append(ORDER_BY);
		
		try{
			if(getEntityManager().createQuery(builder.toString()).setParameter("idUsuario", cliente.getUsuario().getId()).getSingleResult() == null){
				return false;
			}
			return true;
		}catch(NoResultException nre){
			return false;
		}
	}
	
	
	public boolean existEmailClient(ClienteEntity cliente) {
		
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT);
		builder.append(_ESPACE);
		builder.append("AND c.dadosPessoa.email = '"+cliente.getDadosPessoa().getEmail()+"'");
		builder.append(_ESPACE);
		if(MkmtsUtil.verificaLongNulo(cliente.getId()) > 0 ){
			builder.append("AND c.id <> "+cliente.getId()+"");
		}
		builder.append(_ESPACE);
		builder.append(ORDER_BY);
		
		try{
			if(getEntityManager().createQuery(builder.toString()).setParameter("idUsuario", cliente.getUsuario().getId()).getSingleResult() == null){
				return false;
			}
			return true;
		}catch(NoResultException nre){
			return false;
		}
		
	}
	
	
	public boolean existsSaleForClient(ClienteEntity cliente) {
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT);
		builder.append(_ESPACE);
		builder.append("AND c.id = "+cliente.getId());
		builder.append(_ESPACE);
		ClienteEntity c = getEntityManager().createQuery(builder.toString(), ClienteEntity.class).setParameter("idUsuario", cliente.getUsuario().getId()).getSingleResult();
		try{
			if(c.getDataPrimeiraVenda() == null){
				return false;
			}
			return true;
		}catch(NoResultException nre){
			return false;
		}
	}

	private List<ClienteDTO> recuperarOcorrencias(List<ClienteEntity>listaRetornada){
		List<ClienteDTO> listaSaida = new ArrayList<ClienteDTO>();
		if(!MkmtsUtil.isListNullOrEmpty(listaRetornada)){
			for (int i = 0; i < listaRetornada.size(); i++) {
				ClienteDTO dto = new ClienteDTO();
				dto.setId(listaRetornada.get(i).getId());
				dto.setDadosPessoa(listaRetornada.get(i).getDadosPessoa());
				dto.setDataPrimeiraVenda(listaRetornada.get(i).getDataPrimeiraVenda());
				dto.setDataUltimaVenda(listaRetornada.get(i).getDataUltimaVenda());
				dto.setInfAdicional(listaRetornada.get(i).getInfAdicional());
				dto.setListaVendas(Adapter.listaVendaEntityToDto(listaRetornada.get(i).getListaVendas()));
				dto.setUsuario(listaRetornada.get(i).getUsuario());
				listaSaida.add(dto);
			}
		}
		return listaSaida;
	}
	
}
