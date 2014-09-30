/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.model.agenda.TipoAgendaEntity;

/**
 * @author christopher.rozario
 *
 */

public class TipoAgendaRepository extends JpaGenericDao<TipoAgendaEntity, Long> {

	@SuppressWarnings("unchecked")
	
	public List<TipoAgendaEntity> findByFilter(String param) {
		StringBuilder query = new StringBuilder();
		query.append("FROM TipoAgendaEntity t");
		query.append(_ESPACE);
		if(param != null){
			query.append("WHERE t.descTipoAgenda LIKE '"+param+"%'");
			query.append(_ESPACE);
		}
		query.append("ORDER BY t.descTipoAgenda ASC");
		
		return getEntityManager().createQuery(query.toString()).getResultList();
	}
	
	
	public boolean existsTipoAgenda(TipoAgendaEntity agenda) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("FROM TipoAgendaEntity t WHERE t.descTipoAgenda = '"+agenda.getDescTipoAgenda()+"'");
			if(agenda.getId() != null){
				query.append(" AND t.id <> "+agenda.getId());
			}
			
			getEntityManager().createQuery(query.toString()).getSingleResult();
			
			return true;
		}catch(NoResultException nre){
			
			return false;
		}
	}

}
