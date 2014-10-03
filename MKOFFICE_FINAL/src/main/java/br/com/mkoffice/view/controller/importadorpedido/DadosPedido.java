package br.com.mkoffice.view.controller.importadorpedido;

import java.util.List;

import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;

public class DadosPedido {

	private Long numeroPedido;
	private List<ItemMovimentadoCarrinhoDTO>produtos;
	
	public Long getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public List<ItemMovimentadoCarrinhoDTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ItemMovimentadoCarrinhoDTO> produtos) {
		this.produtos = produtos;
	}
	
	
	
}
