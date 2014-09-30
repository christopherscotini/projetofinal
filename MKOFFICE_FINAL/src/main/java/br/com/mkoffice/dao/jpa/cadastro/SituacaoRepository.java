/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.model.admin.SituacaoEntity;

/**
 * @author christopher.rozario
 *
 */

public class SituacaoRepository extends JpaGenericDao<SituacaoEntity, Long> {

	@SuppressWarnings("unchecked")
	
	public List<SituacaoEntity> findByFilter(String param) {
		StringBuilder query = new StringBuilder();
		query.append("FROM SituacaoEntity s");
		query.append(_ESPACE);
		if(param != null){
			query.append("WHERE s.descSituacao LIKE '"+param+"%'");
			query.append(_ESPACE);
		}
		query.append("ORDER BY s.descSituacao ASC");
		
		return getEntityManager().createQuery(query.toString()).getResultList();
	}
	
	
	public boolean existsSituacao(SituacaoEntity situacao) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("FROM SituacaoEntity s WHERE s.descSituacao = '"+situacao.getDescSituacao()+"'");
			if(situacao.getId() != null){
				query.append(" AND s.id <> "+situacao.getId());
			}
			
			getEntityManager().createQuery(query.toString()).getSingleResult();
			
			return true;
		}catch(NoResultException nre){
			
			return false;
		}
	}
}
