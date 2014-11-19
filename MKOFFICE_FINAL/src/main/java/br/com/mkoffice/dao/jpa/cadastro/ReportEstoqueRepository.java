package br.com.mkoffice.dao.jpa.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.reports.estoque.ProdutoMovimentadoDTO;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.model.estoque.EstoqueEntity;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class ReportEstoqueRepository extends JpaGenericDao<ProdutoMovimentadoDTO, Long>{

	
	public List<ProdutoMovimentadoDTO> gerarProdutosMaisVendidos(Integer anoFiltro, Long idUsuario) {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT h.codCatalogo AS PRODUTO, count(*) AS NUM_MOVIMENTADO FROM HistoricoVendasEntity h").append(" ");
		query.append("WHERE h.codVenda.usuario.id = :idUsuario").append(" ");
		query.append("GROUP BY h.codCatalogo").append(" ");
		query.append("ORDER BY count(*) desc, h.codCatalogo").append(" ");
		
		return extract((List<Object[]>)getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario).getResultList());
		
	}

	public List<ProdutoMovimentadoDTO> gerarProdutosMenosVendidos(Integer anoFiltro, Long idUsuario) {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT h.codCatalogo AS PRODUTO, count(*) AS NUM_MOVIMENTADO FROM HistoricoVendasEntity h").append(" ");
		query.append("WHERE h.codVenda.usuario.id = :idUsuario").append(" ");
		query.append("GROUP BY h.codCatalogo").append(" ");
		query.append("ORDER BY count(*), h.codCatalogo").append(" ");
		
		return extract((List<Object[]>)getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario).getResultList());
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

	public List<EstoqueEntity> gerarMovimentacaoEstoque(Date dataInicioFiltro, Date dataFimFiltro, Long fluxoEstoqueFiltro, Long idUsuario) {
		StringBuilder query = new StringBuilder();
		query.append("select e from EstoqueEntity e").append(" ");
		query.append("where e.usuario.id = :idUsuario").append(" ");
		if(!fluxoEstoqueFiltro.equals(99999999L)){
			query.append("AND e.tipoFluxoEstoque = "+fluxoEstoqueFiltro).append(" ");
		}
		if(dataInicioFiltro != null){
			query.append("AND e.dtMovimentacao BETWEEN '"+MkmtsUtil.converterDataString(dataInicioFiltro, _DATE_MASK)+" 00:00:00' AND '"+MkmtsUtil.converterDataString(dataFimFiltro, _DATE_MASK)+" 23:59:59'").append(" ");
		}
		query.append("order by e.dtMovimentacao, e.codCatalogo.descProduto, e.qtdeMovimentadoProduto").append(" ");
		
		return getEntityManager().createQuery(query.toString())
				.setParameter("idUsuario", idUsuario)
				.getResultList();
	}
	
}
