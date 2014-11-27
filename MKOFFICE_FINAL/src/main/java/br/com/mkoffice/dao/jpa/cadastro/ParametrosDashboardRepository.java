/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.dashboard.DashboardOperacionalDTO;
import br.com.mkoffice.model.ParametrosDashboardEntity;

/**
 * @author christopher.rozario
 *
 */

public class ParametrosDashboardRepository extends JpaGenericDao<ParametrosDashboardEntity, Long> {


	public ParametrosDashboardEntity insert(ParametrosDashboardEntity entity) {
		
		try{
			getEntityManager().createQuery("SELECT p FROM ParametrosDashboardEntity p WHERE p.usuario = :idUsuario").setParameter("idUsuario", entity.getUsuario()) .getSingleResult();
			entity = update(entity);
		}catch(NoResultException nre){
			entity = super.insert(entity);
		}
		
		return entity;
	}

	public ParametrosDashboardEntity findByUsuario(Long idUsuario) {
		return (ParametrosDashboardEntity) getEntityManager().createQuery("SELECT p FROM ParametrosDashboardEntity p WHERE p.usuario.id = :idUsuario").setParameter("idUsuario", idUsuario) .getSingleResult();
	}

}
