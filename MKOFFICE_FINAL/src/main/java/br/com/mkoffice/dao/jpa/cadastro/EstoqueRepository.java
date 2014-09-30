package br.com.mkoffice.dao.jpa.cadastro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dao.querys.EstoqueQueries;
import br.com.mkoffice.dto.EstoqueDTO;
import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.dto.metricas.EstoqueMetricasDTO;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.estoque.EstoqueEntity;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.CalculadoraUtils;

public class EstoqueRepository extends JpaGenericDao<EstoqueEntity, Integer> implements EstoqueQueries {

	private final String UPDATE_BAIXA_ESTOQUE = 
			 " UPDATE EstoqueEntity e SET"
			+"  e.qtdeTotalPontosDoProduto = e.qtdeTotalPontosDoProduto - :qtdeTotalPontosDoProdutoCarrinho"
			+", e.valorTotalEmEstoqueDoProduto = e.valorTotalEmEstoqueDoProduto - :valorTotalEmEstoqueDoProduto"
			+", e.qtdeProdutoEmEstoque = e.qtdeProdutoEmEstoque - :qtdeProdutoEmEstoque"
			+"  WHERE e.id = :idEstoque";
	
	
	public List<EstoqueDTO> findAllProductsInStock(Long idUsuario) {
		
		@SuppressWarnings("unchecked")
		List<Object[]>listaRetornada = getEntityManager().createQuery(SELECT_AGRUPADO).setParameter("idUsuario", idUsuario).getResultList();
		
		return carregaOcorrencias(listaRetornada);
	}
	
	
	public void updateProductsStock(List<ItemMovimentadoCarrinhoDTO> produtos) {
		String updateEstoque = "UPDATE EstoqueEntity e SET";
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < produtos.size(); i++) {
			EstoqueDTO produto = Adapter.convertItemMovimentadoCarrinhoToEstoqueDto(produtos.get(i));
			if(produto.isDisponivel()){
				if(produto.getQtdeMovimentadoProduto() > 0 || produto.getQtdeMovimentadoProduto() != null){//VERIFICA SE EXISTE PRODUTO NO CARRINHO
					builder.append(updateEstoque);builder.append(_ESPACE);
					if(produto.getTipoFluxoEstoque().isFluxoSaida()){
						efetuarBaixaEstoqueProduto(produto);//fazer uma inteligencia que remova do produto mais antigo para o novo
					}else{
						efetuarEntradaDeEstoque(produto);
					}
				
				}else{
					return;
				}
			}else{
				if(produto.getQtdeMovimentadoProduto() <= 0 || produto.getQtdeMovimentadoProduto() == null){
					return;
				}
				efetuarEntradaDeEstoque(produto);
			}
		}
	}
	
	private void efetuarEntradaDeEstoque(EstoqueDTO produto){
		EstoqueEntity entity = new EstoqueEntity(
				  null
				, produto.getQtdeMovimentadoProduto()
				, CalculadoraUtils.somarTotalPontosCarrinho(produto.getQtdeMovimentadoProduto(), produto.getCodCatalogo().getPontosPorUnidade())
				, CalculadoraUtils.valorTotalProdutosCarrinho(produto.getQtdeMovimentadoProduto(), produto.getCodCatalogo().getPreco())
				, produto.getTipoFluxoEstoque()
				, produto.getPercDesconto()
				, produto.getDtMovimentacao()
				, produto.getCodPedido()
				, produto.getCodVenda()
				, produto.getCodCatalogo()
				, Boolean.TRUE
				, produto.getQtdeMovimentadoProduto());
		entity.setUsuario(produto.getUsuario());
		insert(entity);
				
	}
	
	
	public EstoqueDTO findProductByCode(Long codCatalogo, Long idUsuario) {
		EstoqueDTO dto = new EstoqueDTO();
		try{
			Object[] returnzz = (Object[]) getEntityManager().createQuery(SELECT_AGRUPADO_WHERE_COD_PRODUCT).
					setParameter("codCatalogo", codCatalogo).
					setParameter("idUsuario", idUsuario).
					getSingleResult();
			List<Object[]> listaRetornada = new ArrayList<Object[]>();
			listaRetornada.add(returnzz);
			dto = carregaOcorrencias(listaRetornada).get(0);
			
		}catch(NoResultException nre){
			return null;
		}
		
		return dto;
	}

	
	public List<EstoqueDTO> findByFilter(String codMk,
			String nomeProduto, List<Integer> codCategoria, Long idUsuario) {
		
		if(codMk.equals("") && nomeProduto.equals("") && codCategoria.isEmpty()){
			return carregaOcorrencias(getEntityManager().createQuery(SELECT_AGRUPADO).setParameter("idUsuario", idUsuario).getResultList());
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT);
		builder.append(_ESPACE);
		
		if(!codMk.equals("")){
			builder.append("AND e.codCatalogo.codProduto = "+codMk+"");
			builder.append(_ESPACE);
		}
		
		if(!nomeProduto.equals("")){
			builder.append("AND e.codCatalogo.descProduto LIKE '%"+nomeProduto+"%'");
			builder.append(_ESPACE);
		}	
		
		if(!codCategoria.isEmpty()){
			String c = codCategoria.toString().replace("[", "").replace("]", "");
			builder.append("AND e.codCatalogo.codCategoria IN("+c+")");
			builder.append(_ESPACE);
		}	
		
		builder.append(GROUP_BY);
		
		return carregaOcorrencias(getEntityManager().createQuery(builder.toString()).setParameter("idUsuario", idUsuario).getResultList());
	}
	
	
	public EstoqueMetricasDTO calculatedMetricsListStockView(String codMk,
			String nomeProduto, List<Integer> codCategoria, Long idUsuario) {
		
		if(codMk.equals("") && nomeProduto.equals("") && codCategoria.isEmpty()){
			return carregaMetricasCalculadas((Object[]) getEntityManager().createQuery(SELECT_CALCULOS_LABEL_LISTAR_ESTOQUE).setParameter("idUsuario", idUsuario).getSingleResult());
		}
		
		
		StringBuilder clausulas = new StringBuilder();
		clausulas.append(_ESPACE);

		if(!codMk.equals("")){
			clausulas.append("AND e.codCatalogo.codProduto = "+codMk+"");
			clausulas.append(_ESPACE);
		}
		
		if(!nomeProduto.equals("")){
			clausulas.append("AND e.codCatalogo.descProduto LIKE '%"+nomeProduto+"%'");
			clausulas.append(_ESPACE);
		}	
		
		if(!codCategoria.isEmpty()){
			String c = codCategoria.toString().replace("[", "").replace("]", "");
			clausulas.append("AND e.codCatalogo.codCategoria IN("+c+")");
			clausulas.append(_ESPACE);
		}	
		
		return carregaMetricasCalculadas((Object[]) getEntityManager().createQuery(SELECT_CALCULOS_LABEL_LISTAR_ESTOQUE+clausulas.toString()).setParameter("idUsuario", idUsuario).getSingleResult());
		
	}
	
	
	public List<EstoqueDTO> findAllByCodeProduct(Long codCatalogo, Long idUsuario) {
		List<EstoqueEntity>entities = getEntityManager().createQuery("FROM EstoqueEntity e WHERE e.codCatalogo.codCatalogo = "+codCatalogo+" AND e.disponivel = 1 AND e.usuario.id = "+idUsuario+" ORDER BY e.dtMovimentacao ASC").getResultList();
		List<EstoqueDTO>dtos = new ArrayList<EstoqueDTO>();
		for (int i = 0; i < entities.size(); i++) {
			dtos.add(Adapter.entityToDto(entities.get(i)));
		}
		
		return dtos;
	}
	
//	Efetuo a baixa de acordo com a regra citada no doc do projeto
	private void efetuarBaixaEstoqueProduto(EstoqueDTO produto){
		if(produto.getQtdeEmEstoque() == produto.getQtdeMovimentadoProduto()){
//			//seto disponivel = false do estoque todos os registros referentes ao produto
//			
			List<EstoqueEntity> produtos = (List<EstoqueEntity>) getEntityManager().createQuery("FROM EstoqueEntity e WHERE e.codCatalogo.codCatalogo ="+produto.getCodCatalogo().getCodCatalogo()+" AND e.disponivel = 1 AND e.usuario.id = "+produto.getUsuario().getId()).getResultList();
			//setando false na fl_disponivel

			for (int i = 0; i < produtos.size(); i++) {
				getEntityManager().createQuery("UPDATE EstoqueEntity e SET e.disponivel = 0, e.qtdeEmEstoqueAtual = 0  WHERE e.id = "+produtos.get(i).getId()).executeUpdate();
				EstoqueEntity entity = new EstoqueEntity(
						null
						, produtos.get(i).getQtdeMovimentadoProduto()
						, produto.getQtdeTotalPontosMovimentadoProduto()
						, produto.getValorTotalMovimentadoProduto()
						, produto.getTipoFluxoEstoque()
						, produto.getPercDesconto()
						, produto.getDtMovimentacao()
						, produtos.get(i).getCodPedido()
						, produto.getCodVenda()
						, produto.getCodCatalogo()
						, Boolean.FALSE
						, new Integer(0));
				entity.setUsuario(produto.getUsuario());
				insert(entity);
				
			}
			
		
		}
		else{
			Integer qtdeBaixadoDoEstoque = 0;
			for (int i = 0; i < produto.getQtdeMovimentadoProduto(); i++) {
				if(qtdeBaixadoDoEstoque == produto.getQtdeMovimentadoProduto()){
					break;
				}
				
				String selectEstoqueMaisAntigo = "SELECT e FROM EstoqueEntity e WHERE e.codCatalogo.codCatalogo = "+produto.getCodCatalogo().getCodCatalogo()+ " AND e.disponivel = 1 AND e.usuario.id = "+produto.getUsuario().getId();
				EstoqueEntity estoqueMaisAntigo = getEntityManager().createQuery(selectEstoqueMaisAntigo, EstoqueEntity.class).setMaxResults(1).getSingleResult();

				if(produto.getQtdeMovimentadoProduto() - qtdeBaixadoDoEstoque >= estoqueMaisAntigo.getQtdeEmEstoqueAtual()){
					qtdeBaixadoDoEstoque += estoqueMaisAntigo.getQtdeEmEstoqueAtual();
					getEntityManager().createQuery("UPDATE EstoqueEntity e SET e.disponivel = 0, e.qtdeEmEstoqueAtual = 0  WHERE e.id = "+estoqueMaisAntigo.getId()).executeUpdate();
					
					insert(new EstoqueEntity(
							null
							, estoqueMaisAntigo.getQtdeEmEstoqueAtual()
							, produto.getQtdeTotalPontosMovimentadoProduto()
							, produto.getValorTotalMovimentadoProduto()
							, produto.getTipoFluxoEstoque()
							, produto.getPercDesconto()
							, produto.getDtMovimentacao()
							, estoqueMaisAntigo.getCodPedido()
							, produto.getCodVenda()
							, produto.getCodCatalogo()
							, Boolean.FALSE
							, new Integer(0)));
					
				}
				else{
					Integer qtdeEstoqueAtual = estoqueMaisAntigo.getQtdeEmEstoqueAtual() - (produto.getQtdeMovimentadoProduto() - qtdeBaixadoDoEstoque);
					getEntityManager().createQuery("UPDATE EstoqueEntity e SET e.qtdeEmEstoqueAtual = :qtdeEmEstoqueAtual  WHERE e.id = "+estoqueMaisAntigo.getId()).setParameter("qtdeEmEstoqueAtual", qtdeEstoqueAtual).executeUpdate();
					insert(new EstoqueEntity(
							null
							, qtdeEstoqueAtual
							, produto.getQtdeTotalPontosMovimentadoProduto()
							, produto.getValorTotalMovimentadoProduto()
							, produto.getTipoFluxoEstoque()
							, produto.getPercDesconto()
							, produto.getDtMovimentacao()
							, estoqueMaisAntigo.getCodPedido()
							, produto.getCodVenda()
							, produto.getCodCatalogo()
							, Boolean.FALSE
							, new Integer(produto.getQtdeMovimentadoProduto() - qtdeBaixadoDoEstoque)));
					
					qtdeBaixadoDoEstoque+=(produto.getQtdeMovimentadoProduto() - qtdeBaixadoDoEstoque);
				}
			}
		}
	}
	
	private List<EstoqueDTO> carregaOcorrencias(List<Object[]> listaRetornada){
		List<EstoqueDTO>listagem = new ArrayList<EstoqueDTO>();
		for (int i = 0; i < listaRetornada.size(); i++) {
			EstoqueEntity entity = new EstoqueEntity();
			entity.setCodCatalogo((CatalogoEntity) listaRetornada.get(i)[0]);
			entity.setDisponivel((Boolean) listaRetornada.get(i)[1]);
			entity.setQtdeEmEstoqueAtual(((Long) listaRetornada.get(i)[2]).intValue());
			entity.setQtdeTotalPontosMovimentadoProduto(((Long) listaRetornada.get(i)[3]).intValue());
			entity.setValorTotalMovimentadoProduto((BigDecimal) listaRetornada.get(i)[4]);
			entity.setUsuario((UserEntity) listaRetornada.get(i)[5]);
			
			listagem.add(Adapter.entityToDto(entity));
		}
		
		return listagem;
	}
	
	private EstoqueMetricasDTO carregaMetricasCalculadas(Object[] metricas){
		if(metricas[0] != null){
			EstoqueMetricasDTO returnzz = new EstoqueMetricasDTO();
			returnzz.setSomaTodosProdutosEmEstoque(Integer.valueOf(metricas[0].toString()));
			returnzz.setSomaToTalPontosEmEstoque(Integer.valueOf(metricas[1].toString()));
			returnzz.setValorToTalRevendaProdutosEmEstoque((BigDecimal) metricas[2]);
			return returnzz;
		}
		return null;
	}
}


