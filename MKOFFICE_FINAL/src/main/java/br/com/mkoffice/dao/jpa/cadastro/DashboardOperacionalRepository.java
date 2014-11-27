package br.com.mkoffice.dao.jpa.cadastro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.dashboard.ClientePorMontanteCompradoDTO;
import br.com.mkoffice.dto.dashboard.DashboardOperacionalDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.venda.VendaEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class DashboardOperacionalRepository extends JpaGenericDao<ParcelasEntity, Long> {

	public DashboardOperacionalDTO gerarRelatorioDashboardCaixa(Integer anoFiltro, Long idUsuario){
		DashboardOperacionalDTO dto = new DashboardOperacionalDTO();
		Object[]query1Result = (Object[]) getEntityManager().createQuery(getQuery1(anoFiltro)).setParameter("idUsuario", idUsuario).getSingleResult();
//		dto.setValorFaturamento((BigDecimal) query1Result[0]);
//		dto.setNumVendas(MkmtsUtil.castObjectToInteger(query1Result[1]));
//		dto.setNumProdutosVendidos(MkmtsUtil.castObjectToInteger(getEntityManager().createQuery(getQuery2(anoFiltro)).setParameter("idUsuario", idUsuario).getSingleResult()));
//		dto.setClienteMaisComprou(extractClienteMaisComprou(getEntityManager().createQuery(getQuery3(anoFiltro)).setMaxResults(1).setParameter("idUsuario", idUsuario).getResultList()));
		
		Object[]query4Result = (Object[]) getEntityManager().createQuery(getQuery4(anoFiltro)).setParameter("idUsuario", idUsuario).getSingleResult();
		dto.setValorGasto((BigDecimal) query4Result[0]);
//		dto.setNumPedidos(MkmtsUtil.castObjectToInteger(query4Result[1]));
//		dto.setNumProdutosComprados(MkmtsUtil.castObjectToInteger(getEntityManager().createQuery(getQuery5(anoFiltro)).setParameter("idUsuario", idUsuario).getSingleResult()));
		
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


	public List<VendaEntity> selectTop5UltimasVendas(Long idUsuario) {
		StringBuilder query = new StringBuilder();
		query.append("select v FROM VendaEntity v").append(" ");
		query.append("WHERE v.usuario.id = :idUsuario").append(" ");
		query.append("ORDER BY v.dataVenda DESC").append(" ");
		
		return getEntityManager().createQuery(query.toString()).setMaxResults(5).setParameter("idUsuario", idUsuario).getResultList();
	}


	public List<ClientePorMontanteCompradoDTO> selectTop10RankingVendaClientes(Long idUsuario) {
		StringBuilder query = new StringBuilder();
		query.append("select sum(v.valorVenda), v.cliente FROM VendaEntity v").append(" ");
		query.append("WHERE v.usuario.id = :idUsuario").append(" ");
		query.append("GROUP BY v.cliente").append(" ");
		query.append("ORDER BY sum(v.valorVenda) DESC").append(" ");
		
		List<Object[]>ret = getEntityManager().createQuery(query.toString()).setMaxResults(10).setParameter("idUsuario", idUsuario).getResultList();
		List<ClientePorMontanteCompradoDTO> returnzz = new ArrayList<ClientePorMontanteCompradoDTO>();

		for (int i = 0; i < ret.size(); i++) {
			ClientePorMontanteCompradoDTO obj = new ClientePorMontanteCompradoDTO();
			obj.setSomaValorCompra((BigDecimal)ret.get(i)[0]);
			obj.setCliente((ClienteEntity)ret.get(i)[1]);
			returnzz.add(obj);
		}
		
		return returnzz;
	}

	public BigDecimal selectValorFaturamentoMesAtual(Long idUsuario) {
		DataFilter dataFiltro = new DataFilter(true);
		
		StringBuilder query = new StringBuilder();
		query.append("select sum(p.valorPago) FROM VendaEntity v").append(" ");
		query.append("JOIN v.parcelas p").append(" ");
		query.append("WHERE v.usuario.id = :idUsuario").append(" ");
		query.append("AND p.dtPagamento BETWEEN '")
		.append(MkmtsUtil.converterDataString(dataFiltro.getDataInicio(), _DATE_MASK)).append(" 00:00:00")
		.append("' AND '")
		.append(MkmtsUtil.converterDataString(dataFiltro.getDataFinal(), _DATE_MASK)).append(" 23:59:59").append("' ")
		.append("AND p.codVenda IS NOT NULL").append(" ");
		
		
		return (BigDecimal) getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario) .getSingleResult();
	}

	public BigDecimal selectValorFaturamentoMesAnterior(Long idUsuario) {
		DataFilter filtro = montarFiltroDataMesAnterior();
		
		StringBuilder query = new StringBuilder();
		query.append("select sum(p.valorPago) FROM VendaEntity v").append(" ");
		query.append("JOIN v.parcelas p").append(" ");
		query.append("WHERE v.usuario.id = :idUsuario").append(" ");
		query.append("AND p.dtPagamento BETWEEN '")
		.append(MkmtsUtil.converterDataString(filtro.getDataInicio(), _DATE_MASK)).append(" 00:00:00")
		.append("' AND '")
		.append(MkmtsUtil.converterDataString(filtro.getDataFinal(), _DATE_MASK)).append(" 23:59:59").append("' ")
		.append("AND p.codVenda IS NOT NULL").append(" ");
		
		return (BigDecimal) getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario) .getSingleResult();
	}


	public BigDecimal selectValorLucroMesAnterior(Long idUsuario) {
		DataFilter filtro = montarFiltroDataMesAnterior();
		
		StringBuilder query = new StringBuilder();
		query.append("select sum(v.valorLucroVenda) FROM VendaEntity v").append(" ");
		query.append("WHERE v.usuario.id = :idUsuario").append(" ");
		query.append("AND v.dataVenda BETWEEN '")
		.append(MkmtsUtil.converterDataString(filtro.getDataInicio(), _DATE_MASK)).append(" 00:00:00")
		.append("' AND '")
		.append(MkmtsUtil.converterDataString(filtro.getDataFinal(), _DATE_MASK)).append(" 23:59:59").append("' ");
		
		return (BigDecimal) getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario) .getSingleResult();
		
	}

	public BigDecimal selectValorLucroMesAtual(Long idUsuario) {
		DataFilter filtro = new DataFilter(true);
		
		StringBuilder query = new StringBuilder();
		query.append("select sum(v.valorLucroVenda) FROM VendaEntity v").append(" ");
		query.append("WHERE v.usuario.id = :idUsuario").append(" ");
		query.append("AND v.dataVenda BETWEEN '")
		.append(MkmtsUtil.converterDataString(filtro.getDataInicio(), _DATE_MASK)).append(" 00:00:00")
		.append("' AND '")
		.append(MkmtsUtil.converterDataString(filtro.getDataFinal(), _DATE_MASK)).append(" 23:59:59").append("' ");
		
		return (BigDecimal) getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario) .getSingleResult();
	}
	
	
	
	private DataFilter montarFiltroDataMesAnterior(){
		Calendar mesAtual = Calendar.getInstance();
		mesAtual.set(Calendar.DAY_OF_MONTH, 1);
		Calendar cA1 = Calendar.getInstance();
		cA1.set(Calendar.YEAR, mesAtual.get(Calendar.YEAR));
		cA1.set(Calendar.MONTH, mesAtual.get(Calendar.MONTH)-1);
		cA1.set(Calendar.DAY_OF_MONTH, mesAtual.get(Calendar.DAY_OF_MONTH));
		Calendar cA2 = Calendar.getInstance();
		cA2.set(Calendar.YEAR, cA1.get(Calendar.YEAR));
		cA2.set(Calendar.MONTH, cA1.get(Calendar.MONTH)+1);
		cA2.set(Calendar.DAY_OF_MONTH, cA1.get(Calendar.DAY_OF_MONTH)-1);
		
		return new DataFilter(cA1.getTime(), cA2.getTime());
	}
	
}



