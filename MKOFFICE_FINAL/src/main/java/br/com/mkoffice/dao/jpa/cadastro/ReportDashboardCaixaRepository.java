package br.com.mkoffice.dao.jpa.cadastro;

import java.math.BigDecimal;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.reports.DashboardCaixaDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.model.ClienteEntity;

public class ReportDashboardCaixaRepository extends JpaGenericDao<DashboardCaixaDTO, Long>{
	
	public DashboardCaixaDTO gerarRelatorioDashboardCaixa(Integer anoFiltro){
		DashboardCaixaDTO dto = new DashboardCaixaDTO();
		Object[]query1Result = (Object[]) getEntityManager().createQuery(getQuery1(anoFiltro)).getSingleResult();
		dto.setValorFaturamento(new BigDecimal(query1Result[0].toString()));
		dto.setNumVendas(Integer.parseInt(query1Result[1].toString()));
		dto.setNumProdutosVendidos(Integer.parseInt(getEntityManager().createQuery(getQuery2(anoFiltro)).getSingleResult().toString()));
		dto.setClienteMaisComprou(extractClienteMaisComprou(getEntityManager().createQuery(getQuery3(anoFiltro)).setMaxResults(1).getResultList()));
		
		Object[]query4Result = (Object[]) getEntityManager().createQuery(getQuery4(anoFiltro)).getSingleResult();
		dto.setValorGasto(new BigDecimal(query4Result[0].toString()));
		dto.setNumPedidos(Integer.parseInt(query4Result[1].toString()));
		dto.setNumProdutosComprados(Integer.parseInt(getEntityManager().createQuery(getQuery5(anoFiltro)).getSingleResult().toString()));
		
		return dto;
	}

	
	private String getQuery1(Integer anoFiltro){
		StringBuilder query1 = new StringBuilder();
		query1.append("select sum(p.valorPago) AS FATURAMENTO, count(p) AS NUM_VENDA").append(" ");
		query1.append("from ParcelasEntity p").append(" ");
		query1.append("WHERE p.dtPagamento BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query1.append("AND p.codVenda IS NOT NULL").append(" ");
		return query1.toString(); 
	}

	private String getQuery2(Integer anoFiltro){
		StringBuilder query2 = new StringBuilder();
		query2.append("select sum(v.qdteVendidaProduto)").append(" ");
		query2.append("from ParcelasEntity p").append(" ");
		query2.append("join p.codVenda.vendaProdutosList v").append(" ");
		query2.append("WHERE p.dtPagamento BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query2.append("AND p.codVenda IS NOT NULL").append(" ");
		return query2.toString(); 
	}
	
	private String getQuery3(Integer anoFiltro){
		StringBuilder query3 = new StringBuilder();
		query3.append("select c as CLIENTE, sum(v.valorVenda) as SOMA_VALOR_VENDAS, max(v.dataVenda) as DATA_ULTIMA_VENDA").append(" ");
		query3.append("from ClienteEntity c left outer join c.listaVendas v").append(" ");
		query3.append("WHERE v.dataVenda BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query3.append("group by c.id").append(" "); 
		query3.append("order by v.valorVenda desc").append(" ");
		return query3.toString();
	}
	
	private ReportPromocaoClientePorVolumeVendaDTO extractClienteMaisComprou(List<Object[]> list){
		list.get(0)[1] = list.get(0)[1]==null?BigDecimal.ZERO:list.get(0)[1];
		ReportPromocaoClientePorVolumeVendaDTO dto = new ReportPromocaoClientePorVolumeVendaDTO();
		dto.setCliente((ClienteEntity) list.get(0)[0]);
		dto.setQuantidadeVendas(dto.getCliente().getListaVendas().size());
		dto.setValorTotalVendas(new BigDecimal(list.get(0)[1].toString()));
		dto.setDataUltimaVenda(null);
		return dto;
	}
	
	private String getQuery4(Integer anoFiltro){
		StringBuilder query4 = new StringBuilder();
		query4.append("select sum(p.valorPago) AS FATURAMENTO, count(p) AS NUM_PEDIDOS").append(" ");
		query4.append("from ParcelasEntity p").append(" ");
		query4.append("WHERE p.dtPagamento BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query4.append("AND p.codPedido IS NOT NULL").append(" ");
		return query4.toString();
	}
	private String getQuery5(Integer anoFiltro){
		StringBuilder query5 = new StringBuilder();
		query5.append("select sum(v.qdteCompradaProduto)").append(" ");
		query5.append("from ParcelasEntity p").append(" ");
		query5.append("join p.codPedido.pedidoProdutosList v").append(" ");
		query5.append("WHERE p.dtPagamento BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query5.append("AND p.codPedido IS NOT NULL").append(" ");
		return query5.toString();
	}
	
	private String getQuery6(Integer anoFiltro){
		StringBuilder query6 = new StringBuilder();
		
		return query6.toString();
	}
}
