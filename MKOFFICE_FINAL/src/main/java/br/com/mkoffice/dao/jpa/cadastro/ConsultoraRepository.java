package br.com.mkoffice.dao.jpa.cadastro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.ConsultoraDTO;
import br.com.mkoffice.model.ConsultoraEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.MkmtsUtil;

public class ConsultoraRepository extends JpaGenericDao<ConsultoraEntity, Integer>{

	final String SELECT = "SELECT c FROM ConsultoraEntity c WHERE c.usuario.id = :idConsultora";
	final String ORDER_BY = "ORDER BY c.dadosPessoa.nome";
	
	
	public List<ConsultoraDTO> findByFiltroPesquisa(Integer codConsultoraFiltro,
			String email, String nomeFiltro, Long celularFiltro, Long idConsultora) {
		
		StringBuilder query = new StringBuilder();
		query.append(SELECT);
		query.append(_ESPACE);
		
		if(codConsultoraFiltro > 0){
			query.append("AND c.codConsultora = "+codConsultoraFiltro);
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
		
		List<ConsultoraEntity>listaRetornada = getEntityManager().createQuery(query.toString(), ConsultoraEntity.class).setParameter("idConsultora", idConsultora).getResultList();
		return recuperarOcorrencias(listaRetornada);
	}

	
	public boolean existCellPhoneConsulter(ConsultoraDTO consultora) {
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT);
		builder.append(_ESPACE);
		builder.append("AND c.dadosPessoa.numCelular = "+consultora.getDadosPessoa().getNumCelular());
		builder.append(_ESPACE);
		if(MkmtsUtil.verificaLongNulo(consultora.getCodConsultora()) > 0 ){
			builder.append("AND c.codConsultora <> "+consultora.getCodConsultora()+"");
			builder.append(_ESPACE);
		}
		builder.append(ORDER_BY);
		
		try{
			if(getEntityManager().createQuery(builder.toString()).setParameter("idConsultora", consultora.getUsuario().getId()).getSingleResult() == null){
				return false;
			}
			return true;
		}catch(NoResultException nre){
			return false;
		}
	}
	
	
	public boolean existEmailConsulter(ConsultoraDTO consultora) {
		
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT);
		builder.append(_ESPACE);
		builder.append("AND c.dadosPessoa.email = '"+consultora.getDadosPessoa().getEmail()+"'");
		builder.append(_ESPACE);
		if(MkmtsUtil.verificaLongNulo(consultora.getCodConsultora()) > 0 ){
			builder.append("AND c.codConsultora <> "+consultora.getCodConsultora()+"");
		}
		builder.append(_ESPACE);
		builder.append(ORDER_BY);
		
		try{
			if(getEntityManager().createQuery(builder.toString()).setParameter("idConsultora", consultora.getUsuario().getId()).getSingleResult() == null){
				return false;
			}
			return true;
		}catch(NoResultException nre){
			return false;
		}
		
	}
	
	
	public boolean existIDConsulter(ConsultoraDTO consultora) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT c FROM ConsultoraEntity c");
		builder.append(_ESPACE);
		builder.append("WHERE c.codConsultora = "+consultora.getCodConsultora());
		try{
			if(getEntityManager().createQuery(builder.toString()).getSingleResult() == null){
				return false;
			}
			return true;
		}catch(NoResultException nre){
			return false;
		}
	}
	
	private List<ConsultoraDTO> recuperarOcorrencias(List<ConsultoraEntity>listaRetornada){
		List<ConsultoraDTO> listaSaida = new ArrayList<ConsultoraDTO>();
		if(!MkmtsUtil.isListNullOrEmpty(listaRetornada)){
			for (int i = 0; i < listaRetornada.size(); i++) {
				listaSaida.add(Adapter.entityToDto(listaRetornada.get(i)));
			}
		}
		return listaSaida;
	}
	
}
