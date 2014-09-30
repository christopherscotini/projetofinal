package br.com.mkoffice.business.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.EstoqueBO;
import br.com.mkoffice.dao.jpa.cadastro.EstoqueRepository;
import br.com.mkoffice.dto.EstoqueDTO;
import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.dto.metricas.EstoqueMetricasDTO;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.MkmtsUtil;

@Stateless
public class EstoqueBOImpl implements EstoqueBO {

	@Inject
	private EstoqueRepository dao = null;

	@Override
	public List<EstoqueDTO>listarEstoqueCompleto(Long idUsuario) {

		return dao.findAllProductsInStock(idUsuario);
	}
	
	@Override
	public List<EstoqueDTO> filtrarEstoque(String codMk,
			String nomeProduto, List<Integer> codCategoria, Long idUsuario) {

		if(MkmtsUtil.isListNullOrEmpty(codCategoria)){
			codCategoria = new ArrayList<Integer>();
		}
		
		return dao.findByFilter(MkmtsUtil.verificaStringNula(codMk) ,MkmtsUtil.verificaStringNula(nomeProduto), codCategoria, idUsuario);
	}
	
	@Override
	public EstoqueDTO buscarProdutoEstoque(Long codCatalogo, Long idUsuario) {
		return dao.findProductByCode(codCatalogo, idUsuario);
	}
	
	@Override
	public void atualizarEstoquePorProduto(EstoqueDTO dto) {
		List<ItemMovimentadoCarrinhoDTO>produtos = new ArrayList<ItemMovimentadoCarrinhoDTO>();
		produtos.add(Adapter.convertEstoqueDtoToItemMovimentadoCarrinho(dto));
		dao.updateProductsStock(produtos);
	}
	
	@Override
	public void atualizarEstoque(List<ItemMovimentadoCarrinhoDTO> lista) {
		
		dao.updateProductsStock(lista);
	}
	
	@Override
	public EstoqueMetricasDTO calcularMetricasListagemEstoque(String codMk,
			String nomeProduto, List<Integer> codCategoria, Long idUsuario) {

		if(MkmtsUtil.isListNullOrEmpty(codCategoria)){
			codCategoria = new ArrayList<Integer>();
		}
		
		return dao.calculatedMetricsListStockView(MkmtsUtil.verificaStringNula(codMk) ,MkmtsUtil.verificaStringNula(nomeProduto), codCategoria, idUsuario);
	
	}
	
	@Override
	public List<EstoqueDTO> filtrarTodosProdutosPeloCodigo(Long codCatalogo, Long idUsuario){
		return dao.findAllByCodeProduct(codCatalogo, idUsuario);
	}
	

}
