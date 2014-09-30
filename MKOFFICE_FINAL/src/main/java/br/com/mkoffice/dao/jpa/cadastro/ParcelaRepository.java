/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.reports.financeiro.ContasReceberReportDTO;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.utils.AdapterReports;
import br.com.mkoffice.utils.MkmtsUtil;

/**
 * @author christopher.rozario
 *
 */

public class ParcelaRepository extends JpaGenericDao<ParcelasEntity, Long> {

	@SuppressWarnings("unchecked")
	
	public List<ParcelasEntity> findByFilter(String param) {
		StringBuilder query = new StringBuilder();
		query.append("FROM ParcelasEntity p");
		
		return getEntityManager().createQuery(query.toString()).getResultList();
	}
	
	
	public List<ContasReceberReportDTO> generateReportContasReceber(Date dataInicial, Date dataFinal,
			Long idUsuario) {

		StringBuilder query = new StringBuilder();
		query.append("FROM ParcelasEntity p WHERE p.usuario.id = "+idUsuario);
		query.append(" AND p.codPedido IS NULL");
		query.append(" AND p.dtVencimento BETWEEN '"
				+ MkmtsUtil.converterDataString(dataInicial, "dd/MM/yyyy")
				+ " 00:00:00' AND '"
				+ MkmtsUtil.converterDataString(dataFinal, "yyyy-MM-dd")
				+ " 23:59:59'");
		query.append(_ESPACE);		
		
		List<ContasReceberReportDTO>returnzz = new ArrayList<ContasReceberReportDTO>();
		
		AdapterReports.generateContasReceber(getEntityManager().createQuery(query.toString(), ParcelasEntity.class).getResultList(), returnzz);
		
		return returnzz;
	}

}
