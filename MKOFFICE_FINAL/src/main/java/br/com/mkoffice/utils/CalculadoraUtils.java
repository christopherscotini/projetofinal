package br.com.mkoffice.utils;

import java.math.BigDecimal;
import java.util.List;

import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.model.constants.PercentDescontoEnum;

public class CalculadoraUtils {
	
	
// CALCULOS DE CARRINHO(Venda/Pedido)
	public static Integer somarTotalPontosCarrinho(Integer qtdeProdutoCarrinho, Integer pontosPorUnidade){
		return (qtdeProdutoCarrinho * pontosPorUnidade);
	}

	public static BigDecimal valorTotalProdutosCarrinho(Integer qtdeProdutoCarrinho, BigDecimal valorPorUnidade){
		return (valorPorUnidade.multiply(BigDecimal.valueOf(qtdeProdutoCarrinho)));
	}
	
//	CALCULOS GENERICOS VENDA/PEDIDO
	public static Integer somarTotalPontosPedidoVenda(List<ItemMovimentadoCarrinhoDTO> itens){
		Integer returnzz = 0;
		for (int i = 0; i < itens.size(); i++) {
			for (int j = 0; j < itens.get(i).getQtdeProdutoCarrinho(); j++) {
				returnzz+=itens.get(i).getProduto().getPontosPorUnidade();
			}
		}
		
		return returnzz;
	}

// CALCULOS DE PEDIDO
	public static BigDecimal valorTotalProdutosPedido(List<ItemMovimentadoCarrinhoDTO> itens){
		BigDecimal returnzz = BigDecimal.ZERO;
		for (int i = 0; i < itens.size(); i++) {
			returnzz = returnzz.add(itens.get(i).getValorTotalProdutoCarrinho());
		}
		
		return returnzz;
	}
	
	public static Integer calcularPorcentagemDescontoPedido(BigDecimal valorTotalProdutosPedido){
		Integer returnzz = 0;
		if(valorTotalProdutosPedido.compareTo(BigDecimal.valueOf(530)) != 1){
			returnzz = PercentDescontoEnum._0.getCodigo();
		}else{
			if(valorTotalProdutosPedido.compareTo(BigDecimal.valueOf(850)) != 1){
				returnzz = PercentDescontoEnum._25.getCodigo();
			}else{
				if(valorTotalProdutosPedido.compareTo(BigDecimal.valueOf(1340)) != 1){
					returnzz = PercentDescontoEnum._30.getCodigo();
				}else{
					if(valorTotalProdutosPedido.compareTo(BigDecimal.valueOf(2340)) != 1){
						returnzz = PercentDescontoEnum._35.getCodigo();
					}else{
						returnzz = PercentDescontoEnum._40.getCodigo();
					}
				}
			}
		}
		
		return returnzz;
	}
	
	public static BigDecimal calcularValorTotalAtacado(BigDecimal valorTotal, Integer porcDesc){
		BigDecimal result = BigDecimal.ZERO;
		if(porcDesc > 0){
			result = valorTotal.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(100 - porcDesc));
		}else{
			result = valorTotal;
		}
		return result;
	}
	
	public static BigDecimal calcularValorFinalTotalPago(BigDecimal valorAtacado, BigDecimal valorFrete, BigDecimal valorBonusUtilizado){
		BigDecimal result = valorAtacado;
		valorFrete = valorFrete == null ? BigDecimal.ZERO : valorFrete;
		valorBonusUtilizado = valorBonusUtilizado == null ? BigDecimal.ZERO : valorBonusUtilizado;
		if(valorFrete.compareTo(BigDecimal.ZERO) == 1){
			result = valorAtacado.add(valorFrete);
		}
		
		if(valorBonusUtilizado.compareTo(BigDecimal.ZERO) == 1){
			result = valorAtacado.subtract(valorBonusUtilizado);
		}
		
		return result;
	}
	
	public static BigDecimal calcularLucroTotalPedido(BigDecimal valorComDesconto, BigDecimal valorTotalSemDesconto){
		
		return valorTotalSemDesconto.subtract(valorComDesconto);
	}	
	
	//CALCULOS VENDA
	public static BigDecimal calcularValorLucroPorProdutoVenda(BigDecimal valorProduto, PercentDescontoEnum percentDesconto){
		BigDecimal returnzz = BigDecimal.ZERO;
		returnzz = returnzz.add(valorProduto.multiply(BigDecimal.valueOf(percentDesconto.getCodigo()).divide(new BigDecimal(100))));
		
		return returnzz;
	}
}
