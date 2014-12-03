package br.com.mkoffice.dao.jpa.cadastro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO;
import br.com.mkoffice.dto.reports.cliente.ReportRetencaoClientesDTO;
import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class ReportClienteRepository extends JpaGenericDao<ReportPromocaoClientePorVolumeVendaDTO, Long>{
	
	public List<ReportPromocaoClientePorVolumeVendaDTO> gerarRelatorioPorVolumeVenda(BigDecimal valorCorte, Integer anoFiltro, Long idUsuario){
		List<ReportPromocaoClientePorVolumeVendaDTO> ret = new ArrayList<ReportPromocaoClientePorVolumeVendaDTO>();
		StringBuilder query = new StringBuilder();
		query.append("select c as CLIENTE, sum(v.valorVenda) as SOMA_VALOR_VENDAS, max(v.dataVenda) as DATA_ULTIMA_VENDA").append(" ");
		query.append("from ClienteEntity c left outer join c.listaVendas v").append(" ");
		query.append("WHERE v.dataVenda BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query.append("AND c.usuario.id = :idUsuario").append(" ");
		query.append("group by c.id").append(" "); 
		query.append("having  sum(v.valorVenda) >= :valorCorte").append(" ");
		query.append("order by v.valorVenda desc").append(" ");
		
		
		List<Object[]> list = getEntityManager().createQuery(query.toString()).setParameter("valorCorte", valorCorte).setParameter("idUsuario", idUsuario).getResultList();
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i)[1] = list.get(i)[1]==null?BigDecimal.ZERO:list.get(i)[1];
			
			ReportPromocaoClientePorVolumeVendaDTO dto = new ReportPromocaoClientePorVolumeVendaDTO();
			dto.setCliente((ClienteEntity) list.get(i)[0]);
			dto.setQuantidadeVendas(dto.getCliente().getListaVendas().size());
			dto.setValorTotalVendas(new BigDecimal(list.get(i)[1].toString()));
			dto.setDataUltimaVenda((Date) list.get(i)[2]);
			ret.add(dto);
		}
		
		return ret;
	}


	public List<ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO> gerarRelatorioPorVolumeVendaDetalhado(BigDecimal valorCorte, Integer anoFiltro, Long idUsuario){
		List<ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO> ret = new ArrayList<ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO>();

		StringBuilder query = new StringBuilder();
		query.append("select").append(" ");
		query.append("v.cliente AS CLIENTE,").append(" ");
		query.append("sum(case when month(v.dataVenda)=1 then v.valorVenda else 0 end) AS Jan,").append(" ");
		query.append("sum(case when month(v.dataVenda)=2 then v.valorVenda else 0 end) AS Fev,").append(" ");
		query.append("sum(case when month(v.dataVenda)=3 then v.valorVenda else 0 end) AS Mar,").append(" ");
		query.append("sum(case when month(v.dataVenda)=4 then v.valorVenda else 0 end) AS Abr,").append(" ");
		query.append("sum(case when month(v.dataVenda)=5 then v.valorVenda else 0 end) AS Mai,").append(" ");
		query.append("sum(case when month(v.dataVenda)=6 then v.valorVenda else 0 end) AS Jun,").append(" ");
		query.append("sum(case when month(v.dataVenda)=7 then v.valorVenda else 0 end) AS Jul,").append(" ");
		query.append("sum(case when month(v.dataVenda)=8 then v.valorVenda else 0 end) AS Ago,").append(" ");
		query.append("sum(case when month(v.dataVenda)=9 then v.valorVenda else 0 end) AS Sete,").append(" ");
		query.append("sum(case when month(v.dataVenda)=10 then v.valorVenda else 0 end) AS Outu,").append(" ");
		query.append("sum(case when month(v.dataVenda)=11 then v.valorVenda else 0 end) AS Nov,").append(" ");
		query.append("sum(case when month(v.dataVenda)=12 then v.valorVenda else 0 end) AS Dez").append(" ");
		query.append("from VendaEntity v").append(" "); 
		query.append("WHERE v.dataVenda BETWEEN '"+anoFiltro+"/01/01 00:00:00' AND '"+anoFiltro+"/12/31 23:59:59'").append(" ");
		query.append("AND v.usuario.id = :idUsuario").append(" ");
		query.append("group by v.cliente").append(" ");
		query.append("having  sum(v.valorVenda) >= :valorCorte").append(" ");
		query.append("order by v.valorVenda desc").append(" ");
		
		List<Object[]> list = getEntityManager().createQuery(query.toString()).setParameter("valorCorte", valorCorte).setParameter("idUsuario", idUsuario).getResultList();
		
		for (int i = 0; i < list.size(); i++) {
			ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO dto = new ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO();
			dto.setCliente((ClienteEntity) list.get(i)[0]);
			dto.setValorMontanteJan(new BigDecimal(list.get(i)[1].toString()));
			dto.setValorMontanteFev(new BigDecimal(list.get(i)[2].toString()));
			dto.setValorMontanteMar(new BigDecimal(list.get(i)[3].toString()));
			dto.setValorMontanteAbr(new BigDecimal(list.get(i)[4].toString()));
			dto.setValorMontanteMai(new BigDecimal(list.get(i)[5].toString()));
			dto.setValorMontanteJun(new BigDecimal(list.get(i)[6].toString()));
			dto.setValorMontanteJul(new BigDecimal(list.get(i)[7].toString()));
			dto.setValorMontanteAgo(new BigDecimal(list.get(i)[8].toString()));
			dto.setValorMontanteSet(new BigDecimal(list.get(i)[9].toString()));
			dto.setValorMontanteOut(new BigDecimal(list.get(i)[10].toString()));
			dto.setValorMontanteNov(new BigDecimal(list.get(i)[11].toString()));
			dto.setValorMontanteDez(new BigDecimal(list.get(i)[12].toString()));
			
			ret.add(dto);
		}
		
		
		return ret;
	}
	
	public List<ReportRetencaoClientesDTO> gerarRelatorioRetencaoClientes(Date dataCorteFiltro, Long idUsuario){
		List<ReportRetencaoClientesDTO> ret = new ArrayList<ReportRetencaoClientesDTO>();
		StringBuilder query = new StringBuilder();
		
		query.append("select c as CLIENTE, max(v.dataVenda) as ULTIMA_VENDA").append(" ");
		query.append("from ClienteEntity c left outer join c.listaVendas v").append(" ");
		query.append("WHERE v.dataVenda <= '"+MkmtsUtil.converterDataString(dataCorteFiltro, "yyyy/MM/dd")+" 23:59:59'").append(" ");
		query.append("AND c.usuario.id = :idUsuario").append(" "); 
		query.append("group by c.id").append(" "); 
		query.append("order by 2").append(" ");
		
		List<Object[]> list = getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario).getResultList();
		
		for (int i = 0; i < list.size(); i++) {
			
			ReportRetencaoClientesDTO dto = new ReportRetencaoClientesDTO();
			dto.setCliente((ClienteEntity) list.get(i)[0]);
			if(list.get(i)[1]!=null){
				dto.setDataUltimaCompra((Date) list.get(i)[1]);
			}
			ret.add(dto);
		}
		
		return ret;
	}
	
}
