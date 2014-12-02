/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.math.BigDecimal;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.UserEntity;
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
	public List<ParcelasEntity> filterByDateSituacaoPagoContasPagar(DataFilter dataFiltro, Long idUsuario) {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM ParcelasEntity p").append(" ");
		query.append("where p.codPedido.codPedido IS NOT NULL").append(" ");
		query.append("AND p.usuario.id = :idUsuario").append(" ");
		if(dataFiltro.getDataInicio() != null){
			query.append("AND p.dtPagamento BETWEEN '"+MkmtsUtil.converterDataString(dataFiltro.getDataInicio(), "yyyy-MM-dd")+"'");
			query.append("AND '"+MkmtsUtil.converterDataString(dataFiltro.getDataFinal(), "yyyy-MM-dd")+"'").append(" ");
		}
		query.append("AND p.codSituacaoParcela = 1").append(" ");
		
		return getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario).getResultList();
		
	}
	
	public List<ParcelasEntity> filterByDateSituacaoPendenteContasPagar(DataFilter dataFiltro, Long idUsuario) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM ParcelasEntity p").append(" ");
		query.append("where p.codPedido.codPedido IS NOT NULL").append(" ");
		query.append("AND p.usuario.id = :idUsuario").append(" ");
		if(dataFiltro.getDataInicio() != null){
			query.append("AND p.dtVencimento BETWEEN '"+MkmtsUtil.converterDataString(dataFiltro.getDataInicio(), "yyyy-MM-dd")+"'");
			query.append("AND '"+MkmtsUtil.converterDataString(dataFiltro.getDataFinal(), "yyyy-MM-dd")+"'").append(" ");
		}
		
		query.append("AND p.codSituacaoParcela = 2").append(" ");

		return getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario).getResultList();
	}
	/*** FIM CONTAS A PAGAR ***/

	/*** INICIO CONTAS A RECEBER ***/
	public List<ParcelasEntity> filterByDateSituacaoPagoContasReceber(DataFilter dataFiltro, Long idUsuario) {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM ParcelasEntity p").append(" ");
		query.append("where p.codVenda.codVenda IS NOT NULL").append(" ");
		query.append("AND p.usuario.id = :idUsuario").append(" ");
		if(dataFiltro.getDataInicio() != null){
			query.append("AND p.dtPagamento BETWEEN '"+MkmtsUtil.converterDataString(dataFiltro.getDataInicio(), "yyyy-MM-dd")+"'");
			query.append("AND '"+MkmtsUtil.converterDataString(dataFiltro.getDataFinal(), "yyyy-MM-dd")+"'").append(" ");
		}
		query.append("AND p.codSituacaoParcela = 1").append(" ");
		
		return getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario).getResultList();
		
	}
	
	public List<ParcelasEntity> filterByDateSituacaoPendenteContasReceber(DataFilter dataFiltro, Long idUsuario) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM ParcelasEntity p").append(" ");
		query.append("where p.codVenda.codVenda IS NOT NULL").append(" ");
		query.append("AND p.usuario.id = :idUsuario").append(" ");
		if(dataFiltro.getDataInicio() != null){
			query.append("AND p.dtVencimento BETWEEN '"+MkmtsUtil.converterDataString(dataFiltro.getDataInicio(), "yyyy-MM-dd")+"'");
			query.append("AND '"+MkmtsUtil.converterDataString(dataFiltro.getDataFinal(), "yyyy-MM-dd")+"'").append(" ");
		}
		
		query.append("AND p.codSituacaoParcela = 2").append(" ");

		return getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario).getResultList();
	}
	/*** FIM CONTAS A RECEBER ***/
	
	public BigDecimal getSaldoUsuario(Long idUsuario) {
		StringBuilder q1 = new StringBuilder("select sum(p.valorPago) from ParcelasEntity p where p.codVenda is not null and p.usuario.id = :idUsuario");
		StringBuilder q2 = new StringBuilder("select sum(p.valorPago) from ParcelasEntity p where p.codPedido is not null and p.usuario.id = :idUsuario");
		BigDecimal faturamento = (BigDecimal) getEntityManager().createQuery(q1.toString()).setParameter("idUsuario", idUsuario).getSingleResult();
		BigDecimal gasto = (BigDecimal) getEntityManager().createQuery(q2.toString()).setParameter("idUsuario", idUsuario).getSingleResult();
		faturamento = faturamento==null?BigDecimal.ZERO:faturamento;
		gasto = gasto==null?BigDecimal.ZERO:gasto;
		return faturamento.subtract(gasto);
	}

}
