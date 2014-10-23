/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.utils.MkmtsUtil;

/**
 * @author christopher.rozario
 *
 */

public class ContasReceberRepository extends JpaGenericDao<ParcelasEntity, Long> {

	public List<ParcelasEntity> filterByDateSituacaoPago(Date dataInicial, Date dataFinal) {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM ParcelasEntity p").append(" ");
		query.append("where p.codVenda.codVenda IS NOT NULL").append(" ");
		if(dataInicial != null){
			query.append("AND p.dtPagamento BETWEEN '"+MkmtsUtil.converterDataString(dataInicial, "yyyy-MM-dd")+"'");
			query.append("AND '"+MkmtsUtil.converterDataString(dataFinal, "yyyy-MM-dd")+"'").append(" ");
		}
		query.append("AND p.codSituacaoParcela = 1").append(" ");
		
		return getEntityManager().createQuery(query.toString()).getResultList();
		
	}
	
	public List<ParcelasEntity> filterByDateSituacaoPendente(Date dataInicial, Date dataFinal) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM ParcelasEntity p").append(" ");
		query.append("where p.codVenda.codVenda IS NOT NULL").append(" ");
		if(dataInicial != null){
			query.append("AND p.dtVencimento BETWEEN '"+MkmtsUtil.converterDataString(dataInicial, "yyyy-MM-dd")+"'");
			query.append("AND '"+MkmtsUtil.converterDataString(dataFinal, "yyyy-MM-dd")+"'").append(" ");
		}
		
		query.append("AND p.codSituacaoParcela = 2").append(" ");

		return getEntityManager().createQuery(query.toString()).getResultList();
	}
	
}
