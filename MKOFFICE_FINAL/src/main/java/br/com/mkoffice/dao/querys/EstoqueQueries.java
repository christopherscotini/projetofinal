package br.com.mkoffice.dao.querys;

public interface EstoqueQueries {

	final String ORDER_BY = "ORDER BY e.codCatalogo.descProduto ASC";
	final String GROUP_BY = "GROUP BY e.codCatalogo.codCatalogo";

	final String SELECT = 
			 "  SELECT"
			+"  e.codCatalogo AS COD_PRODUTO"
			+", e.disponivel"
			+", sum(e.qtdeEmEstoqueAtual) AS QTDE_ESTOQUE "
			+", sum(e.qtdeTotalPontosMovimentadoProduto) AS PONTOS_TOTAL"
			+", sum(e.valorTotalMovimentadoProduto) AS SOMA_VALOR_TOTAL"
			+" FROM EstoqueEntity e"
			+" WHERE e.disponivel = 1"
			+"  AND e.usuario.id = :idUsuario";
	

	final String SELECT_AGRUPADO_WHERE_COD_PRODUCT = 
			 "  SELECT"
			+"  e.codCatalogo AS COD_PRODUTO"
			+", e.disponivel"
			+", sum(e.qtdeEmEstoqueAtual) AS QTDE_ESTOQUE"
			+", sum(e.qtdeTotalPontosMovimentadoProduto) AS PONTOS_TOTAL"
			+", sum(e.valorTotalMovimentadoProduto) AS SOMA_VALOR_TOTAL"
			+", e.usuario AS USUARIO"
			+"  FROM EstoqueEntity e"
			+"  WHERE e.codCatalogo.codCatalogo = :codCatalogo"
			+"  AND e.disponivel = 1"
			+"  AND e.usuario.id = :idUsuario"
			+" " + GROUP_BY;
	
	final String SELECT_AGRUPADO = 
			 "  SELECT"
			+"  e.codCatalogo AS COD_PRODUTO"
			+", e.disponivel"
			+", sum(e.qtdeEmEstoqueAtual) AS QTDE_ESTOQUE "
			+", sum(e.qtdeTotalPontosMovimentadoProduto) AS PONTOS_TOTAL"
			+", sum(e.valorTotalMovimentadoProduto) AS SOMA_VALOR_TOTAL"
			+", e.usuario AS USUARIO"
			+" FROM EstoqueEntity e"
			+" WHERE e.disponivel = 1" 
			+" AND e.usuario.id = :idUsuario"
			+" " + GROUP_BY;
	
	final String SELECT_CALCULOS_LABEL_LISTAR_ESTOQUE = 
			 "  SELECT   SUM(e.qtdeEmEstoqueAtual) AS QTDE_ESTOQUE"
		    +", SUM(e.qtdeTotalPontosMovimentadoProduto) AS TOTAL_PONTOS"
		    +", SUM(e.valorTotalMovimentadoProduto) AS VALOR_VENDA"
		    +", e.usuario AS USUARIO"
		    +" FROM EstoqueEntity e"
			+" WHERE e.disponivel = 1" 
		    +" AND e.usuario.id = :idUsuario";
	
}
