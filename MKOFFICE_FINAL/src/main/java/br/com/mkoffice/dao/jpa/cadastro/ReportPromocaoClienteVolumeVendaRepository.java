package br.com.mkoffice.dao.jpa.cadastro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.model.ClienteEntity;

public class ReportPromocaoClienteVolumeVendaRepository extends JpaGenericDao<ReportPromocaoClientePorVolumeVendaDTO, Long>{
	
	public List<ReportPromocaoClientePorVolumeVendaDTO> gerarRelatorioPorVolumeVenda(BigDecimal valorCorte, Integer anoFiltro){
		List<ReportPromocaoClientePorVolumeVendaDTO> ret = new ArrayList<ReportPromocaoClientePorVolumeVendaDTO>();
		StringBuilder query = new StringBuilder();
		query.append("select c as CLIENTE, sum(v.valorVenda) as SOMA_VALOR_VENDAS, max(v.dataVenda) as DATA_ULTIMA_VENDA").append(" ");
		query.append("from ClienteEntity c left outer join c.listaVendas v").append(" ");
		query.append("group by c.id").append(" "); 
		query.append("having  sum(v.valorVenda) >= :valorCorte").append(" ");
		query.append("order by v.valorVenda desc").append(" ");
		
		List<Object[]> list = getEntityManager().createQuery(query.toString()).setParameter("valorCorte", valorCorte).getResultList();
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i)[1] = list.get(i)[1]==null?BigDecimal.ZERO:list.get(i)[1];
			
			ReportPromocaoClientePorVolumeVendaDTO dto = new ReportPromocaoClientePorVolumeVendaDTO();
			dto.setCliente((ClienteEntity) list.get(i)[0]);
			dto.setQuantidadeVendas(dto.getCliente().getListaVendas().size());
			dto.setValorTotalVendas(new BigDecimal(list.get(i)[1].toString()));
			dto.setDataUltimaVenda(null);
			ret.add(dto);
		}
		
		return ret;
	}


	
}
