/**
 * 
 */
package br.com.mkoffice.repository.dao;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.model.PermissaoEntity;
import br.com.mkoffice.repository.jpa.JpaGenericDao;

/**
 * @author christopher.rozario
 *
 */

public class PermissaoRepository extends JpaGenericDao<PermissaoEntity, Long> {

	public List<PermissaoEntity> findByFilter(String param) {
		StringBuilder query = new StringBuilder();
		query.append("FROM PermissaoEntity p");
		query.append(_ESPACE);
		if(param != null){
			query.append("WHERE p.descPermissao LIKE '"+param+"%'");
			query.append(_ESPACE);
		}
		query.append("ORDER BY p.descPermissao ASC");
		
		return getEntityManager().createQuery(query.toString()).getResultList();
	}
	
	public boolean existsPermissao(PermissaoEntity permissao) {
		try{
			StringBuilder query = new StringBuilder();
			query.append("FROM PermissaoEntity p WHERE p.descPermissao = '"+permissao.getDescPermissao()+"'");
			if(permissao.getId() != null){
				query.append(" AND p.codPermissao <> "+permissao.getId());
			}
			
			getEntityManager().createQuery(query.toString()).getSingleResult();
			
			return true;
		}catch(NoResultException nre){
			
			return false;
		}
	}
}
