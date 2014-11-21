/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.reports.DashboardCaixaDTO;
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
	
	public DashboardCaixaDTO gerarRelatorioDashboardCaixa(Integer anoFiltro, Long idUsuario){
		DashboardCaixaDTO dto = new DashboardCaixaDTO();
		Object[]query1Result = (Object[]) getEntityManager().createQuery(getQuery1(anoFiltro)).setParameter("idUsuario", idUsuario).getSingleResult();
		dto.setValorFaturamento((BigDecimal) query1Result[0]);
		dto.setNumVendas(MkmtsUtil.castObjectToInteger(query1Result[1]));
		dto.setNumProdutosVendidos(MkmtsUtil.castObjectToInteger(getEntityManager().createQuery(getQuery2(anoFiltro)).setParameter("idUsuario", idUsuario).getSingleResult()));
		dto.setClienteMaisComprou(extractClienteMaisComprou(getEntityManager().createQuery(getQuery3(anoFiltro)).setMaxResults(1).setParameter("idUsuario", idUsuario).getResultList()));
		
		Object[]query4Result = (Object[]) getEntityManager().createQuery(getQuery4(anoFiltro)).setParameter("idUsuario", idUsuario).getSingleResult();
		dto.setValorGasto((BigDecimal) query4Result[0]);
		dto.setNumPedidos(MkmtsUtil.castObjectToInteger(query4Result[1]));
		dto.setNumProdutosComprados(MkmtsUtil.castObjectToInteger(getEntityManager().createQuery(getQuery5(anoFiltro)).setParameter("idUsuario", idUsuario).getSingleResult()));
		
		return dto;
	}

	
	private String getQuery1(Integer anoFiltro){
		StringBuilder query1 = new StringBuilder();
		query1.append("select sum(p.valorPago) AS FATURAMENTO, count(p) AS NUM_VENDA").append(" ");
		query1.append("from ParcelasEntity p").append(" ");
		query1.append("WHERE p.dtPagamento BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query1.append("AND p.codVenda IS NOT NULL").append(" ");
		query1.append("AND p.usuario.id = :idUsuario").append(" ");
		return query1.toString(); 
	}

	private String getQuery2(Integer anoFiltro){
		StringBuilder query2 = new StringBuilder();
		query2.append("select sum(v.qdteVendidaProduto)").append(" ");
		query2.append("from ParcelasEntity p").append(" ");
		query2.append("join p.codVenda.vendaProdutosList v").append(" ");
		query2.append("WHERE p.dtPagamento BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query2.append("AND p.codVenda IS NOT NULL").append(" ");
		query2.append("AND p.usuario.id = :idUsuario").append(" ");
		return query2.toString(); 
	}
	
	private String getQuery3(Integer anoFiltro){
		StringBuilder query3 = new StringBuilder();
		query3.append("select c as CLIENTE, sum(v.valorVenda) as SOMA_VALOR_VENDAS, max(v.dataVenda) as DATA_ULTIMA_VENDA").append(" ");
		query3.append("from ClienteEntity c left outer join c.listaVendas v").append(" ");
		query3.append("WHERE v.dataVenda BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query3.append("AND c.usuario.id = :idUsuario").append(" ");;
		query3.append("group by c.id").append(" "); 
		query3.append("order by v.valorVenda desc").append(" ");
		return query3.toString();
	}
	
	private ReportPromocaoClientePorVolumeVendaDTO extractClienteMaisComprou(List<Object[]> list){
		ReportPromocaoClientePorVolumeVendaDTO dto = new ReportPromocaoClientePorVolumeVendaDTO();
		if(list.size()>0){
			dto.setCliente((ClienteEntity) list.get(0)[0]);
			dto.setQuantidadeVendas(dto.getCliente().getListaVendas().isEmpty()==true?0:dto.getCliente().getListaVendas().size());
			dto.setValorTotalVendas((BigDecimal) list.get(0)[1]);
			dto.setDataUltimaVenda(null);
		}
		return dto;
	}
	
	private String getQuery4(Integer anoFiltro){
		StringBuilder query4 = new StringBuilder();
		query4.append("select sum(p.valorPago) AS FATURAMENTO, count(p) AS NUM_PEDIDOS").append(" ");
		query4.append("from ParcelasEntity p").append(" ");
		query4.append("WHERE p.dtPagamento BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query4.append("AND p.codPedido IS NOT NULL").append(" ");
		query4.append("AND p.usuario.id = :idUsuario").append(" ");
		
		return query4.toString();
	}
	private String getQuery5(Integer anoFiltro){
		StringBuilder query5 = new StringBuilder();
		query5.append("select sum(v.qdteCompradaProduto)").append(" ");
		query5.append("from ParcelasEntity p").append(" ");
		query5.append("join p.codPedido.pedidoProdutosList v").append(" ");
		query5.append("WHERE p.dtPagamento BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query5.append("AND p.codPedido IS NOT NULL").append(" ");
		query5.append("AND p.usuario.id = :idUsuario").append(" ");
		return query5.toString();
	}
	
	private String getQuery6(Integer anoFiltro){
		StringBuilder query6 = new StringBuilder();
		
		return query6.toString();
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
