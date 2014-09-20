/**
 * 
 */
package br.com.mkoffice.repository.dao;

import java.util.List;

import br.com.mkoffice.model.AgendaEntity;
import br.com.mkoffice.repository.jpa.JpaGenericDao;

/**
 * @author christopher.rozario
 *
 */

public class AgendaRepository extends JpaGenericDao<AgendaEntity, Long> {

	public List<AgendaEntity> findByFilter(Long idUsuario) {
		return getEntityManager().createQuery("FROM AgendaEntity a WHERE a.usuario.id = "+idUsuario).getResultList();
	}
	
}
