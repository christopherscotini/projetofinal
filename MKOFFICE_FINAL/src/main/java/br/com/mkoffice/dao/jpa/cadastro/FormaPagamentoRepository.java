/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.model.admin.FormaPagamentoEntity;

/**
 * @author christopher.rozario
 *
 */

public class FormaPagamentoRepository extends JpaGenericDao<FormaPagamentoEntity, Long> {

	
	public boolean existsFormaPagamento(FormaPagamentoEntity entity) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("FROM FormaPagamentoEntity f WHERE f.descFormaPgto = '"+entity.getDescFormaPgto()+"'");
			query.append(_ESPACE);
			if(entity.getId() != null){
				query.append(" AND f.id <> "+entity.getId());
				query.append(_ESPACE);
			}
			
			query.append("ORDER BY f.descFormaPgto ASC");
			getEntityManager().createQuery(query.toString()).getSingleResult();
			
			return true;
		}catch(NoResultException nre){
			
			return false;
		}
	}

	
	public List<FormaPagamentoEntity> findByFilter(String param) {
		StringBuilder query = new StringBuilder();
		query.append("FROM FormaPagamentoEntity f");
		query.append(_ESPACE);
		if(param != null){
			query.append("WHERE f.descFormaPgto LIKE '"+param+"%'");
			query.append(_ESPACE);
		}
		query.append("ORDER BY f.descFormaPgto ASC");
		
		return getEntityManager().createQuery(query.toString()).getResultList();
	}

	
}
