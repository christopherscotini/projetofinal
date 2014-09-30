package br.com.mkoffice.dao.jpa.cadastro;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.CatalogoDTO;
import br.com.mkoffice.model.admin.CategoriaEntity;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.MkmtsUtil;

public class CatalogoRepository extends JpaGenericDao<CatalogoEntity, Long> {

//	final String SELECTQUERY_PRODUTO_DISP_QTDE = 
//			  "	 SELECT p" 
//			+ ", COALESCE(e.disponivel,false)" 
//			+ ", sum(COALESCE(e.qtdeEmEstoqueAtual,0)) " 
//			+ "  FROM EstoqueEntity e " 
//			+ "  RIGHT JOIN e.codCatalogo p ";

	final String SELECT_JPA = "SELECT p FROM CatalogoEntity p";
	final String ORDER_BY_NATIVE = "ORDER BY c.NM_PRODUTO ASC";
	final String ORDER_BY_JPA = "ORDER BY p.descProduto ASC";
	final String GROUP_BY_NATIVE = "GROUP BY c.ID_CATALOGO";
	final String GROUP_BY_JPA = "GROUP BY p.codProduto";
	
	final String SELECTQUERY_PRODUTO_DISP_QTDE = 
			"SELECT c.*, COALESCE(e.FL_DISPONIVEL, 0), COALESCE(SUM(e.NU_QTDE_PRODUTO_ESTOQUE_ATUAL), 0) from tb_catalogo AS c "
		    +"LEFT JOIN "
				+"(SELECT est.TB_CATALOGO_TB_ESTOQUE_FK, est.FL_DISPONIVEL, est.NU_QTDE_PRODUTO_ESTOQUE_ATUAL FROM tb_estoque AS est WHERE est.FL_DISPONIVEL = 1) " 
			+"AS e "
			+"ON c.ID_CATALOGO = e.TB_CATALOGO_TB_ESTOQUE_FK ";
	
	
	public List<CatalogoDTO>findCatalogoByFiltros(Long codMk, String nomeProduto, List<Integer> codCategoria, boolean estoqueFiltro) {
		boolean flag = false;
		StringBuilder query = new StringBuilder();
		
		
		if(estoqueFiltro){
//			query.append(SELECTQUERY_PRODUTO_DISP_QTDE+_ESPACE+"WHERE p.codCatalogo = e.codCatalogo");
			query.append(SELECTQUERY_PRODUTO_DISP_QTDE+_ESPACE+"WHERE c.ID_CATALOGO = e.TB_CATALOGO_TB_ESTOQUE_FK");
			query.append(_ESPACE);
			flag = true;
		}else{
			query.append(SELECTQUERY_PRODUTO_DISP_QTDE);
			query.append(_ESPACE);
		}
		
		if(codMk > 0){
			if(flag){
				query.append("AND c.CD_PRODUTO = "+codMk+"");
				query.append(_ESPACE);
			}else{
				query.append("WHERE c.CD_PRODUTO = "+codMk+"");
				query.append(_ESPACE);
				flag = true;
			}
		}

		if(!nomeProduto.equals("")){
			if(flag){
				query.append("AND c.NM_PRODUTO LIKE '%"+nomeProduto+"%'");
				query.append(_ESPACE);
			}else{
				query.append("WHERE c.NM_PRODUTO LIKE '%"+nomeProduto+"%'");
				query.append(_ESPACE);
				flag = true;
			}
		}	
		
		if(!codCategoria.isEmpty()){
			String c = codCategoria.toString().replace("[", "").replace("]", "");
			
			if(flag){
				query.append("AND c.CATEGORIA_CATALOGO_FK IN("+c+")");
				query.append(_ESPACE);
				
			}else{
				query.append("WHERE c.CATEGORIA_CATALOGO_FK IN("+c+")");
				query.append(_ESPACE);
				flag = true;
			}
		}	
		
		query.append(GROUP_BY_NATIVE);
		query.append(_ESPACE);
		query.append(ORDER_BY_NATIVE);
		
//		List<Object[]> listaRetornada = (List<Object[]>) getEntityManager().createQuery(query.toString(), Object[].class).getResultList();
		List<Object[]> listaRetornada = (List<Object[]>) getEntityManager().createNativeQuery(query.toString()).getResultList();
				
		return carregaOcorrencias(listaRetornada);
	}
	
	
	public List<CatalogoDTO> buscarItensExistentesNoCatalogo(List<String> array) {
		String c = array.toString().replace("[", "").replace("]", "");
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT_JPA);
		builder.append(_ESPACE);
		builder.append("WHERE p.codProduto IN ("+c+")");
		builder.append(_ESPACE);
		builder.append("AND p.ativo = true");
		
		return carregaOcorrenciasCatalogo(getEntityManager().createQuery(builder.toString(), CatalogoEntity.class).getResultList());
	}
	
	
	public boolean existsProduct(Long codProduto) {
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT_JPA);
		builder.append(_ESPACE);
		builder.append("WHERE p.codProduto = "+codProduto);
		builder.append(_ESPACE);
		builder.append("AND p.ativo = true");
		builder.append(_ESPACE);
		builder.append(ORDER_BY_JPA);
		
		try{
			if(getEntityManager().createQuery(builder.toString()).getSingleResult() == null){
				return false;
			}
			return true;
		}catch(NoResultException nre){
			return false;
		}
	}
	
	
	private List<CatalogoDTO> carregaOcorrencias(List<Object[]>listaRetornada){
		List<CatalogoDTO>listaSaida = new ArrayList<CatalogoDTO>();
		if(!MkmtsUtil.isListNullOrEmpty(listaRetornada)){
			List<CategoriaEntity> categorias =  getEntityManager().createQuery("SELECT c FROM CategoriaEntity c ").getResultList();
			CategoriaEntity categoria = categorias.get(0);
			for (int i = 0; i < listaRetornada.size(); i++) {
				
				if(!categoria.getId().equals((Long.parseLong(listaRetornada.get(i)[6].toString())))){
					for (int j = 0; j < categorias.size(); j++) {
						if(categorias.get(j).getId().equals(Long.valueOf((listaRetornada.get(i)[6]).toString()))){
							categoria = categorias.get(j);
							break;
						}
					}
				}

				CatalogoDTO dto = new CatalogoDTO
						(((BigInteger)listaRetornada.get(i)[0]).longValue()//ID_CATALOGO[0]
						, ((BigInteger)listaRetornada.get(i)[2]).longValue()//CD_PRODUTO[2]
						, (String)listaRetornada.get(i)[3]//NM_PRODUTO[3]
						, (BigDecimal)listaRetornada.get(i)[5]//VL_PRODUTO[5]
						, (Integer)listaRetornada.get(i)[4]//NU_PONTOS[4]
						, categoria//TB_CATEGORIA_CATALOGO[6]
						, (Boolean)listaRetornada.get(i)[1]);//FL_ATIVO [1]
			
				dto.setQuantidadeEmEstoque(Integer.valueOf(String.valueOf(listaRetornada.get(i)[8])));
				dto.setDisponivel(dto.getQuantidadeEmEstoque()>0?true:false);
				
				
		  /***  TRECHO COMENTADO PARA QUANDO ENCONTRARMOS UMA SOLU��O PARA O SELECT COM JPQL ***/
//				dto = Adapter.entityToDto(((CatalogoEntity) listaRetornada.get(i)[0]));
//				dto.setQuantidadeEmEstoque(Integer.valueOf(String.valueOf(listaRetornada.get(i)[2])));
//				dto.setDisponivel(dto.getQuantidadeEmEstoque() > 0 ? true : false);
				listaSaida.add(dto);
			}
		}
		return listaSaida;
	}
	
	private List<CatalogoDTO> carregaOcorrenciasCatalogo(List<CatalogoEntity>listaRetornada){
		List<CatalogoDTO>listaSaida = new ArrayList<CatalogoDTO>();
		if(!MkmtsUtil.isListNullOrEmpty(listaRetornada)){
			for (int i = 0; i < listaRetornada.size(); i++) {
				CatalogoDTO dto = new CatalogoDTO();
				dto = Adapter.entityToDto(listaRetornada.get(i));
				listaSaida.add(dto);
			}
		}
		return listaSaida;
	}
	
	
	public boolean carregaPlanilhaCatalogoAtualizado(
			List<CatalogoEntity> catalogo) {

		for (int i = 0; i < catalogo.size(); i++) {
			if(existsProduct(catalogo.get(i).getCodProduto())){
				continue;
			}else{
				catalogo.get(i).setAtivo(true);
				insert(catalogo.get(i));
			}
		}
	
		return true;
	}
	
}
