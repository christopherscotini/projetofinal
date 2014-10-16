package br.com.mkoffice.dao.jpa.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.reports.cliente.ReportRetencaoClientesDTO;
import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class ReportRetencaoClientesRepository extends JpaGenericDao<ReportRetencaoClientesDTO, Long>{
	
	public List<ReportRetencaoClientesDTO> gerarRelatorioRetencaoClientes(Date dataCorteFiltro){
		List<ReportRetencaoClientesDTO> ret = new ArrayList<ReportRetencaoClientesDTO>();
		StringBuilder query = new StringBuilder();
		
		query.append("select c as CLIENTE, max(v.dataVenda) as ULTIMA_VENDA").append(" ");
		query.append("from ClienteEntity c left outer join c.listaVendas v").append(" ");
		query.append("WHERE v.dataVenda <= '"+MkmtsUtil.converterDataString(dataCorteFiltro, "yyyy/MM/dd")+" 23:59:59'").append(" ");
		query.append("group by c.id").append(" "); 
		query.append("order by 2").append(" ");
		
		List<Object[]> list = getEntityManager().createQuery(query.toString()).getResultList();
		
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
