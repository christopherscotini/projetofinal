package br.com.mkoffice.dao.jpa.cadastro;

import java.util.ArrayList;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.reports.estoque.ProdutoMovimentadoDTO;
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
	
}
