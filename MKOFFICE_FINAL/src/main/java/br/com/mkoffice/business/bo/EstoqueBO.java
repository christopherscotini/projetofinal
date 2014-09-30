package br.com.mkoffice.business.bo;

import java.util.List;

import br.com.mkoffice.dto.EstoqueDTO;
import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.dto.metricas.EstoqueMetricasDTO;

public interface EstoqueBO {

	final boolean ADICIONA_ESTOQUE = true;
	final boolean REMOVE_ESTOQUE = false;

	public List<EstoqueDTO> listarEstoqueCompleto(Long idUsuario);

	public List<EstoqueDTO> filtrarEstoque(String codMk,
			String nomeProduto, List<Integer> codCategoria, Long idUsuario);
	
	public EstoqueDTO buscarProdutoEstoque(Long idProduto, Long idUsuario);
	
	public void atualizarEstoquePorProduto(EstoqueDTO dto);
	
	public void atualizarEstoque(List<ItemMovimentadoCarrinhoDTO> lista);
	
	public EstoqueMetricasDTO calcularMetricasListagemEstoque(String codMk,
			String nomeProduto, List<Integer> codCategoria, Long idUsuario);
	
	public List<EstoqueDTO> filtrarTodosProdutosPeloCodigo(Long codCatalogo, Long idUsuario);
	

}
