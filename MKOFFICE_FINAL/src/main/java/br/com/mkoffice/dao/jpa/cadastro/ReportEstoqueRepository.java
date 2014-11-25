package br.com.mkoffice.dao.jpa.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.reports.estoque.ProdutoMovimentadoDTO;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.model.estoque.EstoqueEntity;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class ReportEstoqueRepository extends JpaGenericDao<ProdutoMovimentadoDTO, Long>{

	
	public List<ProdutoMovimentadoDTO> gerarProdutosMaisVendidos(Integer anoFiltro, Long idUsuario) {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT e.codCatalogo, sum(e.qtdeMovimentadoProduto) AS COMPRADOS FROM EstoqueEntity e").append(" ");
		query.append("WHERE e.tipoFluxoEstoque.fluxoSaida = 0").append(" ");
		query.append("AND e.usuario = :idUsuario").append(" ");
		if(!anoFiltro.equals(9999)){query.append("AND e.dtMovimentacao BETWEEN '"+anoFiltro+"-01-01 00:00:00' AND '"+anoFiltro+"-12-31 23:59:59'").append(" ");}
		query.append("GROUP BY e.codCatalogo").append(" ");
		query.append("ORDER BY count(*) desc, e.codCatalogo").append(" ");
		
		return extract((List<Object[]>)getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario).setMaxResults(10).getResultList());
		
	}

	public List<ProdutoMovimentadoDTO> gerarProdutosMenosVendidos(Integer anoFiltro, Long idUsuario) {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT e.codCatalogo, sum(e.qtdeMovimentadoProduto) AS COMPRADOS FROM EstoqueEntity e").append(" ");
		query.append("WHERE e.tipoFluxoEstoque.fluxoSaida = 1").append(" ");
		query.append("AND e.usuario = :idUsuario").append(" ");
		if(!anoFiltro.equals(9999)){query.append("AND e.dtMovimentacao BETWEEN '"+anoFiltro+"-01-01 00:00:00' AND '"+anoFiltro+"-12-31 23:59:59'").append(" ");}
		query.append("GROUP BY e.codCatalogo").append(" ");
		query.append("ORDER BY count(*) desc, e.codCatalogo").append(" ");
		
		return extract((List<Object[]>)getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario).setMaxResults(10).getResultList());
	}

	
	private List<ProdutoMovimentadoDTO> extract(List<Object[]> resultSet){
		List<ProdutoMovimentadoDTO> list = new ArrayList<ProdutoMovimentadoDTO>();
		for (int i = 0; i < resultSet.size(); i++) {
			ProdutoMovimentadoDTO dto = new ProdutoMovimentadoDTO();
			dto.setProduto((CatalogoEntity)resultSet.get(i)[0]);
			dto.setNumProdutosMovimentados(MkmtsUtil.castObjectToInteger(resultSet.get(i)[1]));
			list.add(dto);
		}
		
		return list;
	}

	public List<EstoqueEntity> gerarMovimentacaoEstoque(DataFilter dataFiltro, Long fluxoEstoqueFiltro, Long idUsuario) {
		StringBuilder query = new StringBuilder();
		query.append("select e from EstoqueEntity e").append(" ");
		query.append("where e.usuario.id = :idUsuario").append(" ");
		if(!fluxoEstoqueFiltro.equals(99999999L)){
			query.append("AND e.tipoFluxoEstoque = "+fluxoEstoqueFiltro).append(" ");
		}
		if(dataFiltro.getDataInicio() != null){
			query.append("AND e.dtMovimentacao BETWEEN '"+MkmtsUtil.converterDataString(dataFiltro.getDataInicio(), _DATE_MASK)+" 00:00:00' AND '"+MkmtsUtil.converterDataString(dataFiltro.getDataFinal(), _DATE_MASK)+" 23:59:59'").append(" ");
		}
		query.append("order by e.dtMovimentacao, e.codCatalogo.descProduto, e.qtdeMovimentadoProduto").append(" ");
		
		return getEntityManager().createQuery(query.toString())
				.setParameter("idUsuario", idUsuario)
				.getResultList();
	}
	
}
