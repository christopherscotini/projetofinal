/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;

/**
 * @author christopher.rozario
 *
 */

public class FluxoEstoqueRepository extends JpaGenericDao<FluxoEstoqueEntity, Long> {

	
	public boolean existsFluxoEstoque(FluxoEstoqueEntity entity) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("FROM FluxoEstoqueEntity f WHERE f.descFluxoEstoque = '"+entity.getDescFluxoEstoque()+"' ORDER BY f.descFluxoEstoque ASC");
			query.append(_ESPACE);
			if(entity.getId() != null){
				query.append(" AND f.id <> "+entity.getId());
				query.append(_ESPACE);
			}
			
			query.append("ORDER BY f.descFluxoEstoque ASC");
			getEntityManager().createQuery(query.toString()).getSingleResult();
			
			return true;
		}catch(NoResultException nre){
			
			return false;
		}
	}

	
	public List<FluxoEstoqueEntity> findByFilter(String param) {
		StringBuilder query = new StringBuilder();
		query.append("FROM FluxoEstoqueEntity f");
		query.append(_ESPACE);
		if(param != null){
			query.append("WHERE f.descFluxoEstoque LIKE '"+param+"%'");
			query.append(_ESPACE);
		}
		query.append("ORDER BY f.descFluxoEstoque ASC");
		
		return getEntityManager().createQuery(query.toString()).getResultList();
	}
	
}
