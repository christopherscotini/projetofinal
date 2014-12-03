/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.PedidoDTO;
import br.com.mkoffice.dto.reports.pedido.ReportPedidoConsolidadoDTO;
import br.com.mkoffice.model.pedido.PedidoEntity;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.MkmtsUtil;

/**
 * @author christopher.rozario
 *
 */
public class PedidoRepository extends JpaGenericDao<PedidoEntity, Long> {
	
	
	public List<PedidoDTO> findAllOrders(Long idUsuario) {
		List<PedidoEntity>pedidoEntities = getEntityManager().createQuery("FROM PedidoEntity p WHERE p.usuario.id = "+idUsuario).getResultList();
		List<PedidoDTO>returnzz = new ArrayList<PedidoDTO>();
		
		for (int i = 0; i < pedidoEntities.size(); i++) {
			PedidoDTO dto = new PedidoDTO();
			dto = Adapter.entityToDto(pedidoEntities.get(i));
			dto.setDescSituacaoPagamento(pedidoEntities.get(i).getParcelas().get(pedidoEntities.get(i).getParcelas().size()-1).getCodSituacaoParcela().getDescSituacao());
			returnzz.add(dto);
		}
		
		return returnzz;
	}
	
	
	public PedidoEntity insertUploadedOrder(PedidoDTO dto) {
		for (int i = 0; i < dto.getListaProdutosComprados().size(); i++) {
			CatalogoEntity catalogoEntity = dto.getListaProdutosComprados().get(i).getProduto();
			
			boolean isContentCatalogo = false;
			try{
				dto.getListaProdutosComprados().get(i).setProduto((CatalogoEntity) getEntityManager().createQuery("FROM CatalogoEntity c Where c.codProduto = "+ dto.getListaProdutosComprados().get(i).getProduto().getCodProduto()+" AND c.ativo = 1").getSingleResult());
				isContentCatalogo = true;
			}catch(NoResultException nre){
				isContentCatalogo = false;
			}
				if(isContentCatalogo){
					getEntityManager().createQuery("UPDATE CatalogoEntity c SET"+_ESPACE+"c.descProduto = '"+catalogoEntity.getDescProduto() +"'"+_ESPACE+", c.pontosPorUnidade = "+catalogoEntity.getPontosPorUnidade() +_ESPACE+", c.preco = "+catalogoEntity.getPreco()+" WHERE c.codProduto = "+catalogoEntity.getCodProduto()).executeUpdate();
				}else{
					getEntityManager().persist(catalogoEntity);//ADICIONA NO CAT�LOGO !
					getEntityManager().flush();
					dto.getListaProdutosComprados().get(i).setProduto((CatalogoEntity) getEntityManager().createQuery("FROM CatalogoEntity c Where c.codProduto = "+ catalogoEntity.getCodProduto()+" AND c.ativo = 1").getSingleResult());
				}
				
		}
		
		return insert(Adapter.dtoToEntity(dto));
	}
	
	
	public boolean existsPedido(PedidoDTO dto) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT p FROM PedidoEntity p");
		builder.append(" WHERE p.codPedido = "+dto.getCodPedido());
//		builder.append(" AND p.usuario.id = "+dto.getUsuario().getId());
		try{
			getEntityManager().createQuery(builder.toString()).getSingleResult();
			return true;
		}catch(NoResultException nre){
			return false;
		}
	}
	
	
	public List<PedidoDTO> findByFilter(Long codPedido, DataFilter dtFiltro, Long idUsuario) {
		
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT p FROM PedidoEntity p WHERE p.usuario.id = "+idUsuario);
		builder.append(_ESPACE);

		if(codPedido > 0){
			builder.append("AND p.codPedido = "+codPedido);
		}

		if(null != dtFiltro){
			builder.append("AND p.dtPedido BETWEEN '"+MkmtsUtil.converterDataString(dtFiltro.getDataInicio(), "yyyy-MM-dd")+" 00:00:00' AND '"+MkmtsUtil.converterDataString(dtFiltro.getDataFinal(), "yyyy-MM-dd")+" 23:59:59'");
			builder.append(_ESPACE);
		}
		builder.append("ORDER BY p.codPedido, p.dtPedido ASC");
		List<PedidoEntity>listaRetornadaEntities = getEntityManager().createQuery(builder.toString(), PedidoEntity.class).getResultList();
		List<PedidoDTO>retorno = new ArrayList<PedidoDTO>();
		for (int i = 0; i < listaRetornadaEntities.size(); i++) {
			PedidoDTO dto = new PedidoDTO();
			dto = Adapter.entityToDto(listaRetornadaEntities.get(i));
			dto.setDescSituacaoPagamento(listaRetornadaEntities.get(i).getParcelas().get(listaRetornadaEntities.get(i).getParcelas().size()-1).getCodSituacaoParcela().getDescSituacao());
			retorno.add(dto);

		}
		
		return retorno;
	}


	public ReportPedidoConsolidadoDTO gerarRelatorioPedidoConsolidado(DataFilter dataFiltro, Long idUsuario) {
		ReportPedidoConsolidadoDTO returnzz = new ReportPedidoConsolidadoDTO();
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM PedidoEntity p").append(" ");
		query.append("WHERE p.usuario.id = :idUsuario").append(" ");

		if(null != dataFiltro.getDataInicio()){
			query.append("AND p.dtPedido BETWEEN '"+MkmtsUtil.converterDataString(dataFiltro.getDataInicio(), "yyyy-MM-dd")+" 00:00:00' AND '"+MkmtsUtil.converterDataString(dataFiltro.getDataFinal(), "yyyy-MM-dd")+" 23:59:59'").append(" ");
		}
		
		
		returnzz.setPedidos(new ArrayList<PedidoDTO>());
		List<PedidoEntity>listaRetornadaEntities = getEntityManager().createQuery(query.toString()).setParameter("idUsuario", idUsuario).getResultList();
		for (int i = 0; i < listaRetornadaEntities.size(); i++) {
			PedidoDTO dto = new PedidoDTO();
			dto = Adapter.entityToDto(listaRetornadaEntities.get(i));
			dto.setDescSituacaoPagamento(listaRetornadaEntities.get(i).getParcelas().get(listaRetornadaEntities.get(i).getParcelas().size()-1).getCodSituacaoParcela().getDescSituacao());
			returnzz.getPedidos().add(dto);
		}
		
		
		returnzz.setNumeroPedidos(returnzz.getPedidos().size());
		
		for (int i = 0; i < returnzz.getPedidos().size(); i++) {
			for (int j = 0; j < returnzz.getPedidos().get(i).getListaProdutosComprados().size(); j++) {
				returnzz.setNumeroProdutosPedidos(returnzz.getNumeroProdutosPedidos()+returnzz.getPedidos().get(i).getListaProdutosComprados().get(j).getQtdeProdutoCarrinho());
			}
			returnzz.setValorTotalPagoPedidos(returnzz.getValorTotalPagoPedidos().add(returnzz.getPedidos().get(i).getValorFinalTotalPago()));
			returnzz.setValorTotalRevendaPedidos(returnzz.getValorTotalRevendaPedidos().add(returnzz.getPedidos().get(i).getValorTotalEmProdutos()));
			returnzz.setNumeroTotalPontosPedidos(returnzz.getNumeroTotalPontosPedidos()+returnzz.getPedidos().get(i).getPontosTotalPedido());
		}
		
		
		return returnzz;
	}
	
}
