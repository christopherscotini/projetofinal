package br.com.mkoffice.dao.jpa.cadastro;

import java.util.Date;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.model.estoque.EstoqueEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class EstoqueHistoricoRepository extends
		JpaGenericDao<EstoqueEntity, Long> {

	final String SELECT = "SELECT e FROM EstoqueEntity e WHERE e.usuario.id = :idUsuario";
	
	
	public List<EstoqueEntity> findhistoricalStockByProductCode(
			Long code) {

		return null;
	}

	
	public List<EstoqueEntity> generateMovimentacaoEstoqueReport(
			FluxoEstoqueEntity tipoFluxoEstoque, Date dtInicio, Date dtFinal, Long idUsuario) {

		StringBuilder query = new StringBuilder();

		query.append(SELECT);
		query.append(_ESPACE);
		query.append("AND e.tipoFluxoEstoque = :tipoFluxoEstoque");
		query.append(_ESPACE);
		query.append("AND e.dtMovimentacao BETWEEN '"
				+ MkmtsUtil.converterDataString(dtInicio, "dd/MM/yyyy")
				+ " 00:00:00' AND '"
				+ MkmtsUtil.converterDataString(dtFinal, "yyyy-MM-dd")
				+ " 23:59:59'");
		query.append(_ESPACE);

		return getEntityManager()
				.createQuery(query.toString(), EstoqueEntity.class)
				.setParameter("tipoFluxoEstoque", tipoFluxoEstoque)
				.setParameter("idUsuario", idUsuario)
				.getResultList();
	}

}