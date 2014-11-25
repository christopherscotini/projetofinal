/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.math.BigDecimal;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.model.ParcelasEntity;
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
	
	
	
	
	/*** INICIO CONTAS A PAGAR ***/
	public List<ParcelasEntity> filterByDateSituacaoPagoContasPagar(DataFilter dataFiltro) {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM ParcelasEntity p").append(" ");
		query.append("where p.codPedido.codPedido IS NOT NULL").append(" ");
		if(dataFiltro.getDataInicio() != null){
			query.append("AND p.dtPagamento BETWEEN '"+MkmtsUtil.converterDataString(dataFiltro.getDataInicio(), "yyyy-MM-dd")+"'");
			query.append("AND '"+MkmtsUtil.converterDataString(dataFiltro.getDataFinal(), "yyyy-MM-dd")+"'").append(" ");
		}
		query.append("AND p.codSituacaoParcela = 1").append(" ");
		
		return getEntityManager().createQuery(query.toString()).getResultList();
		
	}
	
	public List<ParcelasEntity> filterByDateSituacaoPendenteContasPagar(DataFilter dataFiltro) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM ParcelasEntity p").append(" ");
		query.append("where p.codPedido.codPedido IS NOT NULL").append(" ");
		if(dataFiltro.getDataInicio() != null){
			query.append("AND p.dtVencimento BETWEEN '"+MkmtsUtil.converterDataString(dataFiltro.getDataInicio(), "yyyy-MM-dd")+"'");
			query.append("AND '"+MkmtsUtil.converterDataString(dataFiltro.getDataFinal(), "yyyy-MM-dd")+"'").append(" ");
		}
		
		query.append("AND p.codSituacaoParcela = 2").append(" ");

		return getEntityManager().createQuery(query.toString()).getResultList();
	}
	/*** FIM CONTAS A PAGAR ***/

	/*** INICIO CONTAS A RECEBER ***/
	public List<ParcelasEntity> filterByDateSituacaoPagoContasReceber(DataFilter dataFiltro) {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM ParcelasEntity p").append(" ");
		query.append("where p.codVenda.codVenda IS NOT NULL").append(" ");
		if(dataFiltro.getDataInicio() != null){
			query.append("AND p.dtPagamento BETWEEN '"+MkmtsUtil.converterDataString(dataFiltro.getDataInicio(), "yyyy-MM-dd")+"'");
			query.append("AND '"+MkmtsUtil.converterDataString(dataFiltro.getDataFinal(), "yyyy-MM-dd")+"'").append(" ");
		}
		query.append("AND p.codSituacaoParcela = 1").append(" ");
		
		return getEntityManager().createQuery(query.toString()).getResultList();
		
	}
	
	public List<ParcelasEntity> filterByDateSituacaoPendenteContasReceber(DataFilter dataFiltro) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM ParcelasEntity p").append(" ");
		query.append("where p.codVenda.codVenda IS NOT NULL").append(" ");
		if(dataFiltro.getDataInicio() != null){
			query.append("AND p.dtVencimento BETWEEN '"+MkmtsUtil.converterDataString(dataFiltro.getDataInicio(), "yyyy-MM-dd")+"'");
			query.append("AND '"+MkmtsUtil.converterDataString(dataFiltro.getDataFinal(), "yyyy-MM-dd")+"'").append(" ");
		}
		
		query.append("AND p.codSituacaoParcela = 2").append(" ");

		return getEntityManager().createQuery(query.toString()).getResultList();
	}
	/*** FIM CONTAS A RECEBER ***/
}
