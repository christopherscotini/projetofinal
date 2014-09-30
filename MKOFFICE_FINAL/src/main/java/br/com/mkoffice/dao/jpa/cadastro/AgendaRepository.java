/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.util.ArrayList;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.AgendaDTO;
import br.com.mkoffice.model.agenda.AgendaEntity;
import br.com.mkoffice.utils.Adapter;

/**
 * @author christopher.rozario
 *
 */

public class AgendaRepository extends JpaGenericDao<AgendaEntity, Long>{

	public List<AgendaDTO> findByFilter(Long idUsuario) {
		List<AgendaEntity>agendaEntities = getEntityManager().createQuery("FROM AgendaEntity a WHERE a.usuario.id = "+idUsuario).getResultList();
		List<AgendaDTO>returnzz = new ArrayList<AgendaDTO>();
		for (AgendaEntity entity : agendaEntities) {
			returnzz.add(Adapter.entityToDto(entity));
		}
		return returnzz;
	}
	
}
