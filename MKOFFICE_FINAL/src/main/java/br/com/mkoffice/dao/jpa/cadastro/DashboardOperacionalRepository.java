package br.com.mkoffice.dao.jpa.cadastro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.dashboard.BalancoDTO;
import br.com.mkoffice.dto.dashboard.ClientePorMontanteCompradoDTO;
import br.com.mkoffice.dto.dashboard.DashboardOperacionalDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.venda.VendaEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class DashboardOperacionalRepository extends JpaGenericDao<ParcelasEntity, Long> {

	
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
		
		
		return MkmtsUtil.verificaBigDecimalNulo((BigDecimal) getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario) .getSingleResult());
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
		
		return MkmtsUtil.verificaBigDecimalNulo((BigDecimal) getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario) .getSingleResult());
	}


	public BigDecimal selectValorLucroMesAnterior(Long idUsuario) {
		DataFilter filtro = montarFiltroDataMesAnterior();
		
		StringBuilder query = new StringBuilder();
		query.append("select sum(v.valorVenda) FROM VendaEntity v").append(" ");
		query.append("WHERE v.usuario.id = :idUsuario").append(" ");
		query.append("AND v.dataVenda BETWEEN '")
		.append(MkmtsUtil.converterDataString(filtro.getDataInicio(), _DATE_MASK)).append(" 00:00:00")
		.append("' AND '")
		.append(MkmtsUtil.converterDataString(filtro.getDataFinal(), _DATE_MASK)).append(" 23:59:59").append("' ");
		
		BigDecimal somaFat = (BigDecimal) getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario) .getSingleResult();

		
		StringBuilder querySomaGasto = new StringBuilder();
		querySomaGasto.append("select sum(p.valorTotalPago) FROM PedidoEntity p").append(" ");
		querySomaGasto.append("WHERE p.usuario.id = :idUsuario").append(" ");
		querySomaGasto.append("AND p.dtPedido BETWEEN '")
		.append(MkmtsUtil.converterDataString(filtro.getDataInicio(), _DATE_MASK)).append(" 00:00:00")
		.append("' AND '")
		.append(MkmtsUtil.converterDataString(filtro.getDataFinal(), _DATE_MASK)).append(" 23:59:59").append("' ");
		
		BigDecimal somaGasto = (BigDecimal) getEntityManager().createQuery(querySomaGasto.toString()).setParameter("idUsuario", idUsuario) .getSingleResult();
		
		return MkmtsUtil.verificaBigDecimalNulo(somaFat).subtract(MkmtsUtil.verificaBigDecimalNulo(somaGasto));
	}

	public BigDecimal selectValorLucroMesAtual(Long idUsuario) {
		DataFilter filtro = new DataFilter(true);
		
		StringBuilder query = new StringBuilder();
		query.append("select sum(v.valorVenda) FROM VendaEntity v").append(" ");
		query.append("WHERE v.usuario.id = :idUsuario").append(" ");
		query.append("AND v.dataVenda BETWEEN '")
		.append(MkmtsUtil.converterDataString(filtro.getDataInicio(), _DATE_MASK)).append(" 00:00:00")
		.append("' AND '")
		.append(MkmtsUtil.converterDataString(filtro.getDataFinal(), _DATE_MASK)).append(" 23:59:59").append("' ");
		
		BigDecimal somaFat = (BigDecimal) getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario) .getSingleResult();
		
		StringBuilder querySomaGasto = new StringBuilder();
		querySomaGasto.append("select sum(p.valorTotalPago) FROM PedidoEntity p").append(" ");
		querySomaGasto.append("WHERE p.usuario.id = :idUsuario").append(" ");
		querySomaGasto.append("AND p.dtPedido BETWEEN '")
		.append(MkmtsUtil.converterDataString(filtro.getDataInicio(), _DATE_MASK)).append(" 00:00:00")
		.append("' AND '")
		.append(MkmtsUtil.converterDataString(filtro.getDataFinal(), _DATE_MASK)).append(" 23:59:59").append("' ");
		
		BigDecimal somaGasto = (BigDecimal) getEntityManager().createQuery(querySomaGasto.toString()).setParameter("idUsuario", idUsuario) .getSingleResult();
		
		return MkmtsUtil.verificaBigDecimalNulo(somaFat).subtract(MkmtsUtil.verificaBigDecimalNulo(somaGasto));
	}
	
	
	public List<BalancoDTO> selectHistoricoBalanco(Long idUsuario) {
		List<BalancoDTO> retorno = new ArrayList<BalancoDTO>();
		
		StringBuilder query = new StringBuilder();
		query.append("(SELECT FATUR_MENSAL.*,GASTO_MENSAL.*, COALESCE(FATUR_MENSAL.FATUR_MENSAL,0) - COALESCE(GASTO_MENSAL.GASTO_MENSAL,0) from ( SELECT DATE_FORMAT(p.DT_PAGAMENTO,'%Y/%m') as DT_PAGAMENTO1 , SUM(p.VL_VLR_PAGO) AS FATUR_MENSAL FROM tb_parcela as p WHERE p.DT_PAGAMENTO IS NOT NULL AND p.TB_USUARIO_FK = :idUsuario AND p.TB_VENDA_PARCELA_FK IS NOT NULL GROUP BY DATE_FORMAT(p.DT_PAGAMENTO,'%Y/%m') ) as FATUR_MENSAL LEFT OUTER JOIN ( SELECT DATE_FORMAT(p.DT_PAGAMENTO,'%Y/%m')  as DT_PAGAMENTO2, SUM(p.VL_VLR_PAGO) AS GASTO_MENSAL FROM tb_parcela as p WHERE p.DT_PAGAMENTO IS NOT NULL AND p.TB_USUARIO_FK = :idUsuario AND p.TB_PEDIDO_PARCELA_FK IS NOT NULL GROUP BY DATE_FORMAT(p.DT_PAGAMENTO,'%Y/%m') )GASTO_MENSAL ON FATUR_MENSAL.DT_PAGAMENTO1 = GASTO_MENSAL.DT_PAGAMENTO2) UNION (SELECT FATUR_MENSAL.*,GASTO_MENSAL.*, COALESCE(FATUR_MENSAL.FATUR_MENSAL,0) - COALESCE(GASTO_MENSAL.GASTO_MENSAL,0) from (SELECT DATE_FORMAT(p.DT_PAGAMENTO,'%Y/%m')  as DT_PAGAMENTO1, SUM(p.VL_VLR_PAGO) AS FATUR_MENSAL FROM tb_parcela as p WHERE p.DT_PAGAMENTO IS NOT NULL AND p.TB_USUARIO_FK = :idUsuario AND p.TB_VENDA_PARCELA_FK IS NOT NULL GROUP BY DATE_FORMAT(p.DT_PAGAMENTO,'%Y/%m') ) as FATUR_MENSAL RIGHT OUTER JOIN ( SELECT DATE_FORMAT(p.DT_PAGAMENTO,'%Y/%m') as DT_PAGAMENTO2, SUM(p.VL_VLR_PAGO) AS GASTO_MENSAL FROM tb_parcela as p WHERE p.DT_PAGAMENTO IS NOT NULL AND p.TB_USUARIO_FK = :idUsuario AND p.TB_PEDIDO_PARCELA_FK IS NOT NULL GROUP BY DATE_FORMAT(p.DT_PAGAMENTO,'%Y/%m') )GASTO_MENSAL ON FATUR_MENSAL.DT_PAGAMENTO1 = GASTO_MENSAL.DT_PAGAMENTO2) ORDER BY 1 ").append(" ");
		
		List<Object[]> faturamentosEntities = getEntityManager().createNativeQuery(query.toString()).setParameter("idUsuario", idUsuario).getResultList();
		
		for (int i = 0; i < faturamentosEntities.size(); i++) {
//			[0] DT_PAGAMENTO1 - STRING
//			[1] FAT_MENSAL - BIGDECIMAL
//			[2] DT_PAGAMENTO2 - STRING
//			[3] GASTO_MENSAL - BIGDECIMAL
//			[4] BALANCO - BIGDECIMAL
			BalancoDTO dto = new BalancoDTO();
			if(faturamentosEntities.get(i)[0] == null){dto.setMes((String) faturamentosEntities.get(i)[2]);}else{dto.setMes((String) faturamentosEntities.get(i)[0]);}
			dto.setValorBalanco((BigDecimal) faturamentosEntities.get(i)[4]);
			retorno.add(dto);
		}
		
		return retorno;
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



