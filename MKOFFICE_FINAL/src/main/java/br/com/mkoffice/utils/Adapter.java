package br.com.mkoffice.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.mkoffice.dto.AgendaDTO;
import br.com.mkoffice.dto.CatalogoDTO;
import br.com.mkoffice.dto.CategoriaDTO;
import br.com.mkoffice.dto.ClienteDTO;
import br.com.mkoffice.dto.ConsultoraDTO;
import br.com.mkoffice.dto.EstoqueDTO;
import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.dto.ParcelasDTO;
import br.com.mkoffice.dto.PedidoDTO;
import br.com.mkoffice.dto.VendaDTO;
import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.model.ConsultoraEntity;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.CategoriaEntity;
import br.com.mkoffice.model.agenda.AgendaEntity;
import br.com.mkoffice.model.constants.PercentDescontoEnum;
import br.com.mkoffice.model.embeddable.Pessoa;
import br.com.mkoffice.model.estoque.EstoqueEntity;
import br.com.mkoffice.model.pedido.HistoricoPedidosEntity;
import br.com.mkoffice.model.pedido.PedidoEntity;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.model.venda.HistoricoVendasEntity;
import br.com.mkoffice.model.venda.VendaEntity;
import br.com.mkoffice.view.controller.menu.clientes.vo.ClienteVO;
import br.com.mkoffice.view.controller.menu.produtos.vo.CatalogoVO;
import br.com.mkoffice.view.controller.menu.vendas.vo.VendaVO;


public class Adapter {

//CONVERSOES DA CLASSE CLIENTE 	
	public static ClienteEntity dtoToEntity(ClienteDTO dto) {
		ClienteEntity entity = new ClienteEntity();
		entity.setId(dto.getId());
		entity.setDadosPessoa(dto.getDadosPessoa());
		entity.setDataPrimeiraVenda(dto.getDataPrimeiraVenda());
		entity.setDataUltimaVenda(dto.getDataUltimaVenda());
		entity.setDataInicioAcompanhamento(dto.getDataInicioAcompanhamento());
		entity.setInfAdicional(dto.getInfAdicional());
		entity.setUsuario(dto.getUsuario());
		
		return entity;
	}

	public static ClienteDTO entityToDto(ClienteEntity entity){
		ClienteDTO dto = new ClienteDTO();
		dto.setId(entity.getId());
		dto.setDadosPessoa(entity.getDadosPessoa());
		dto.setDataPrimeiraVenda(entity.getDataPrimeiraVenda());
		dto.setDataUltimaVenda(entity.getDataUltimaVenda());
		dto.setDataInicioAcompanhamento(entity.getDataInicioAcompanhamento());
		dto.setInfAdicional(entity.getInfAdicional());
		dto.setUsuario(entity.getUsuario());
		
		return dto;
	}
	
	public static ClienteVO dtoToVo(ClienteDTO dto){
		ClienteVO vo = new ClienteVO();
		vo.setId(dto.getId());
		vo.setDataNascimento(dto.getDadosPessoa().getDataNascimento());
		vo.setDataPrimeiraVenda(dto.getDataPrimeiraVenda());
		vo.setDataUltimaVenda(dto.getDataUltimaVenda());
		vo.setEmail(dto.getDadosPessoa().getEmail());
		vo.setEndereco(dto.getDadosPessoa().getEndereco());
		vo.setInfAdicional(dto.getInfAdicional());
		vo.setNome(dto.getDadosPessoa().getNome());
		vo.setNumCelular(dto.getDadosPessoa().getNumCelular());
		vo.setNumTelefone(dto.getDadosPessoa().getNumTelefone());
		vo.setDataInicioAcompanhamento(dto.getDataInicioAcompanhamento());
		vo.setSexo(dto.getDadosPessoa().getSexo());
		vo.setUsuario(dto.getUsuario());
		
		return vo;
	}
	
	public static ClienteDTO voToDto(ClienteVO vo){
		ClienteDTO dto = new ClienteDTO();
		dto.setId(vo.getId());
		dto.setDadosPessoa(new Pessoa(vo.getNome(), vo.getEndereco(), vo.getNumTelefone(), vo.getNumCelular(), vo.getEmail(), vo.getDataNascimento(), vo.getSexo()));
		dto.setDataPrimeiraVenda(vo.getDataPrimeiraVenda());
		dto.setDataUltimaVenda(vo.getDataUltimaVenda());
		dto.setInfAdicional(vo.getInfAdicional());
		dto.setDataInicioAcompanhamento(vo.getDataInicioAcompanhamento());
		dto.setUsuario(vo.getUsuario());
		
		return dto;
	}
	
	
//CONVERSOES DA CLASSE CONSULTORA
	public static ConsultoraEntity dtoToEntity(ConsultoraDTO dto){
		ConsultoraEntity entity = new ConsultoraEntity();
		entity.setCodConsultora(dto.getCodConsultora());
		entity.setDadosPessoa(dto.getDadosPessoa());
		entity.getDadosPessoa().setNumCelular(dto.getNumCelular());
		entity.getDadosPessoa().setNumTelefone(dto.getNumTelefone());
		entity.setDataDesativacao(dto.getDataDesativacao());
		entity.setDataInicio(dto.getDataInicio());
		entity.setStatus(dto.getStatus());
		entity.setDataDesativacao(dto.getDataDesativacao());
		entity.setInfAdicional(dto.getInfAdicional());
		entity.setUsuario(dto.getUsuario());
		
		return entity;
	}
	
	public static ConsultoraDTO entityToDto(ConsultoraEntity entity) {
		ConsultoraDTO dto = new ConsultoraDTO();
		dto.setCodConsultora(entity.getCodConsultora());
		dto.setDadosPessoa(entity.getDadosPessoa());
		dto.setDataDesativacao(entity.getDataDesativacao());
		dto.setDataInicio(entity.getDataInicio());
		dto.setStatus(entity.getStatus());
		dto.setDataDesativacao(entity.getDataDesativacao());
		dto.setInfAdicional(entity.getInfAdicional());
		dto.setNumCelular(entity.getDadosPessoa().getNumCelular());
		dto.setNumTelefone(dto.getDadosPessoa().getNumTelefone());
		dto.setUsuario(entity.getUsuario());

		return dto;
	}

	
//CONVERSOES DA CLASSE CATALOGO
	public static CatalogoEntity dtoToEntity(CatalogoDTO dto) {
		CatalogoEntity entity = new CatalogoEntity(
				  dto.getCodCatalogo()
				, dto.getCodProduto()
				, dto.getDescProduto()
				, dto.getPreco()
				, dto.getPontosPorUnidade()
				, dto.getCodCategoria()
				, dto.isAtivo());
		return entity;
	}

	public static CatalogoDTO entityToDto(CatalogoEntity entity){
		CatalogoDTO dto = new CatalogoDTO(
				  entity.getCodCatalogo()
				, entity.getCodProduto()
				, entity.getDescProduto()
				, entity.getPreco()
				, entity.getPontosPorUnidade()
				, entity.getCodCategoria()
				, entity.isAtivo());
		return dto;
	}
	
	public static CatalogoDTO voToDto(CatalogoVO vo){
		CatalogoDTO dto = new CatalogoDTO(
				vo.getCodCatalogo()
				, vo.getCodProduto()
				, vo.getDescProduto()
				, vo.getPreco()
				, vo.getPontosPorUnidade()
				, vo.getCodCategoria()
				, vo.isAtivo());
		dto.setQuantidadeEmEstoque(vo.getQuantidadeEmEstoque());
		
		return dto;
	}

	public static CatalogoVO dtoToVo(CatalogoDTO dto){
		CatalogoVO vo = new CatalogoVO(
				dto.getCodCatalogo()
				, dto.getCodProduto()
				, dto.getDescProduto()
				, dto.getPreco()
				, dto.getPontosPorUnidade()
				, dto.getCodCategoria()
				, dto.isAtivo());
		return vo;
	}
	
	public static List<ItemMovimentadoCarrinhoDTO> convertCatalogoDtoToItemMovimentadoCarrinho(List<CatalogoDTO> catalogo){
		List<ItemMovimentadoCarrinhoDTO> returnzz = new ArrayList<ItemMovimentadoCarrinhoDTO>();
		for (int i = 0; i < catalogo.size(); i++) {
			ItemMovimentadoCarrinhoDTO dto = new ItemMovimentadoCarrinhoDTO();
			dto.setProduto(Adapter.dtoToEntity(catalogo.get(i)));
			dto.setQuantidadeEmEstoque(catalogo.get(i).getQuantidadeEmEstoque());
			dto.setDisponivel(catalogo.get(i).isDisponivel());
			returnzz.add(dto);
		}
		
		return returnzz;
	}
	
//CONVERSOES DA CLASSE CATEGORIA
	public static CategoriaEntity dtoToEntity(CategoriaDTO dto){
		CategoriaEntity entity = new CategoriaEntity();
		entity.setId(dto.getCodCategoria());
		entity.setCodSecao(dto.getCodSecao());
		entity.setDescCategoria(dto.getDescCategoria());
		return entity;
	}
	
	public static CategoriaDTO entityToDto(CategoriaEntity entity) {
		CategoriaDTO dto = new CategoriaDTO();
		dto.setCodCategoria(entity.getId());
		dto.setCodSecao(entity.getCodSecao());
		dto.setDescCategoria(entity.getDescCategoria());
		return dto;
	}
	

//CONVERSOES DA CLASSE PEDIDO
	public static PedidoDTO entityToDto(PedidoEntity entity){
		PedidoDTO dto = new PedidoDTO();

		dto.setCodPedido(entity.getCodPedido());
		dto.setDtPedido(entity.getDtPedido());
		dto.setLucroTotal(entity.getLucroTotal());
		dto.setPorcDesconto(entity.getPercDesconto().getCodigo());
		dto.setPontosTotalPedido(entity.getPontosTotalPedido());
		dto.setValorFrete(entity.getValorFrete());
		dto.setValorTotalEmProdutos(entity.getValorTotalEmProdutos());
		dto.setValorFinalTotalPago(entity.getValorTotalPago());
		dto.setValorTotalAtacado(entity.getVlrTotalComDesconto());
		dto.setValorBonusUtilizado(entity.getValorBonusUtilizado());
		dto.setListaProdutosComprados(historicoPedidoToItemMovimentado(entity.getPedidoProdutosList()));
		dto.setParcelas(Adapter.listEntityToListDto(entity.getParcelas()));
		dto.setFormaDePagamento(entity.getFormaDePagamento());
		dto.setUsuario(entity.getUsuario());
		
		return dto;
	}	

	public static PedidoEntity dtoToEntity(PedidoDTO dto){
		PedidoEntity entity = new PedidoEntity();
		entity.setCodPedido(dto.getCodPedido());
		entity.setDtPedido(dto.getDtPedido());
		entity.setValorTotalPago(dto.getValorFinalTotalPago());
		entity.setValorTotalEmProdutos(dto.getValorTotalEmProdutos());
		entity.setPontosTotalPedido(dto.getPontosTotalPedido());
		entity.setLucroTotal(dto.getLucroTotal());
		entity.setVlrTotalComDesconto(dto.getValorTotalAtacado());
		entity.setValorFrete(dto.getValorFrete());
		entity.setValorBonusUtilizado(dto.getValorBonusUtilizado());
		entity.setPercDesconto(PercentDescontoEnum.getDescontoByCodigo(dto.getPorcDesconto()));
		entity.setPedidoProdutosList(ItemMovimentadoToHistoricoPedido(dto, entity));
		entity.setParcelas(Adapter.listDtoToListEntity(dto.getParcelas()));
		entity.setFormaDePagamento(dto.getFormaDePagamento());
		entity.setUsuario(dto.getUsuario());
		return entity;
	}

//CONVERSOES DA CLASSE HistoricoPedidosEntity - Itens inclusos no pedido para itemMovimentado
	public static List<ItemMovimentadoCarrinhoDTO> historicoPedidoToItemMovimentado(List<HistoricoPedidosEntity> historico) {
		List<ItemMovimentadoCarrinhoDTO> itens = new ArrayList<ItemMovimentadoCarrinhoDTO>();
		for (int i = 0; i < historico.size(); i++) {
			ItemMovimentadoCarrinhoDTO item = new ItemMovimentadoCarrinhoDTO(
					historico.get(i).getCodCatalogo()
					, historico.get(i).getDataCompraProduto()
					, historico.get(i).getQdteCompradaProduto()
					, historico.get(i).getQtdeTotalPontosCompradosProduto()
					, historico.get(i).getValorTotalCompradoProduto()
					, null//tipo fluxo
					, historico.get(i).getCodPedido()
					, null
					, false
					, false
					, null);
					
			itens.add(item);
		}
		
		return itens;
	}
	
	public static List<HistoricoPedidosEntity> ItemMovimentadoToHistoricoPedido(PedidoDTO pedido, PedidoEntity entity) {
		List<ItemMovimentadoCarrinhoDTO> itensMovimentados = pedido.getListaProdutosComprados();
		List<HistoricoPedidosEntity> historico = new ArrayList<HistoricoPedidosEntity>();
		for (int i = 0; i < itensMovimentados.size(); i++) {
			HistoricoPedidosEntity item = new HistoricoPedidosEntity(
					entity
					, itensMovimentados.get(i).getProduto()
					, itensMovimentados.get(i).getQtdeProdutoCarrinho()
					, itensMovimentados.get(i).getSomaPontosProdutoCarrinho()
					, itensMovimentados.get(i).getValorTotalProdutoCarrinho()
					, pedido.getDtPedido());
				
					historico.add(item);
		}
		
		return historico;
	}
	

//CONVERSOES DA CLASSE VENDA
	public static VendaEntity dtoToEntity(VendaDTO dto) {
		VendaEntity entity = new VendaEntity(
				dto.getCodVenda()
				, dto.getDataVenda()
				, dto.getValorVenda()
				, dto.getQtdeTotalPontosVendidos()
				, Adapter.dtoToEntity(dto.getCliente())
				, listDtoToListEntity(dto.getParcelas())
				, dto.getFormaPagamento()
				, dto.getValorDescontoVenda());//lista de parcelas
		
		entity.setValorLucroVenda(dto.getValorLucroVenda());
		entity.setVendaProdutosList(ItemMovimentadoToHistoricoVenda(dto.getListaDeProdutos(), entity));
		entity.setUsuario(dto.getUsuario());
		
		return entity;
	}	

	public static VendaDTO entityToDto(VendaEntity entity) {
		VendaDTO dto = new VendaDTO(
				entity.getCodVenda()
				, Adapter.entityToDto(entity.getCliente())
				, entity.getDataVenda()
				, entity.getValorVenda()
				, entity.getQtdeTotalPontosVendidos()
				, historicoVendaToItemMovimentado(entity.getVendaProdutosList())
				, listEntityToListDto(entity.getParcelas())
				, entity.getFormaDePagamento()
				, entity.getValorDescontoVenda());//lista de parcelas
		
		dto.setUsuario(entity.getUsuario());
		
		return dto;
	}
	
	public static VendaVO dtoToVo(VendaDTO venda) {
		VendaVO vo = new VendaVO(
				venda.getCodVenda()
				, venda.getCliente()
				, venda.getDataVenda()
				, venda.getValorVenda()
				, venda.getFormaPagamento()
				, venda.getFormaPagamento().isPossuiParcelas()
				, venda.getParcelas().size()
				, venda.getQtdeTotalPontosVendidos()
				, venda.getValorVenda()
				, venda.getListaDeProdutos()
				, venda.getParcelas());
		
		return vo;
	}
	
//CONVERSOES DA CLASSE HistoricoPedidosEntity - Itens inclusos no pedido para itemMovimentado
	public static List<ItemMovimentadoCarrinhoDTO> historicoVendaToItemMovimentado(List<HistoricoVendasEntity> historico) {
			List<ItemMovimentadoCarrinhoDTO> itens = new ArrayList<ItemMovimentadoCarrinhoDTO>();
			for (int i = 0; i < historico.size(); i++) {
				ItemMovimentadoCarrinhoDTO item = new ItemMovimentadoCarrinhoDTO(
						historico.get(i).getCodCatalogo()
						, historico.get(i).getDataVendaProduto()
						, historico.get(i).getQdteVendidaProduto()
						, historico.get(i).getQtdeTotalPontosVendidosProduto()
						, historico.get(i).getValorTotalVendidoProduto()
						, null//tipo fluxo
						, null
						, historico.get(i).getCodVenda()
						, false
						, false
						, null);
						
				itens.add(item);
			}
			
			return itens;
		}
		
	public static List<HistoricoVendasEntity> ItemMovimentadoToHistoricoVenda(List<ItemMovimentadoCarrinhoDTO> itensMovimentados, VendaEntity entity) {
			List<HistoricoVendasEntity> historico = new ArrayList<HistoricoVendasEntity>();
			for (int i = 0; i < itensMovimentados.size(); i++) {
				HistoricoVendasEntity item = new HistoricoVendasEntity(
						entity
						, itensMovimentados.get(i).getProduto()
						, itensMovimentados.get(i).getQtdeProdutoCarrinho()
						, itensMovimentados.get(i).getSomaPontosProdutoCarrinho()
						, itensMovimentados.get(i).getValorTotalProdutoCarrinho()
						, entity.getDataVenda());
				
						historico.add(item);
			}
			
			return historico;
		}
		
	
//CONVERSOES DA CLASSE ESTOQUE
	public static EstoqueEntity dtoToEntity(EstoqueDTO dto){
		EstoqueEntity entity = new EstoqueEntity(
				  dto.getId()
				, dto.getQtdeMovimentadoProduto()
				, dto.getQtdeTotalPontosMovimentadoProduto()
				, dto.getValorTotalMovimentadoProduto()
				, dto.getTipoFluxoEstoque()
				, dto.getPercDesconto()
				, dto.getDtMovimentacao()
				, dto.getCodPedido()
				, dto.getCodVenda()
				, dto.getCodCatalogo()
				, dto.isDisponivel()
				, dto.getQtdeEmEstoque());
		
		entity.setUsuario(dto.getUsuario());
		
		return entity;
	}
	
	public static EstoqueDTO entityToDto(EstoqueEntity entity) {
		EstoqueDTO dto = new EstoqueDTO(
				entity.getId()
				, entity.getQtdeMovimentadoProduto()
				, entity.getQtdeTotalPontosMovimentadoProduto()
				, entity.getValorTotalMovimentadoProduto()
				, entity.getTipoFluxoEstoque()
				, entity.getPercDesconto()
				, entity.getDtMovimentacao()
				, entity.isDisponivel()
				, entity.getCodPedido()
				, entity.getCodVenda()
				, entity.getCodCatalogo()
				, entity.getQtdeEmEstoqueAtual());
		
		dto.setUsuario(entity.getUsuario());
				
		return dto;
	}

	public static EstoqueDTO convertItemMovimentadoCarrinhoToEstoqueDto(ItemMovimentadoCarrinhoDTO item){
		EstoqueDTO dto = new EstoqueDTO(
				null
				,item.getQtdeProdutoCarrinho()
				, item.getSomaPontosProdutoCarrinho()
				, item.getValorTotalProdutoCarrinho()
				, item.getTipoFluxoEstoque()
				, item.getCodPedido() != null ? item.getCodPedido().getPercDesconto() : PercentDescontoEnum._0  //setar a parte PERC_DESCONTO 
				, item.getDtMovimentado()
				, item.isDisponivel()
				, item.getCodPedido()
				, item.getCodVenda()
				, item.getProduto()
				, item.getQuantidadeEmEstoque());
		dto.setPercDesconto(item.getPercentDesconto());
		dto.setUsuario(item.getUsuario());
		
		return dto;
	}

	public static List<EstoqueDTO> convertItemMovimentadoCarrinhoToEstoqueDto(List<ItemMovimentadoCarrinhoDTO> itens){
		List<EstoqueDTO>returnzz = new ArrayList<EstoqueDTO>();
		for (int i = 0; i < itens.size(); i++) {
			EstoqueDTO dto = new EstoqueDTO(
					null
					,itens.get(i).getQtdeProdutoCarrinho()
					, itens.get(i).getSomaPontosProdutoCarrinho()
					, itens.get(i).getValorTotalProdutoCarrinho()
					, itens.get(i).getTipoFluxoEstoque()
					, itens.get(i).getCodPedido() != null ? itens.get(i).getCodPedido().getPercDesconto() : PercentDescontoEnum._0  //setar a parte PERC_DESCONTO 
					, itens.get(i).getDtMovimentado()
					, itens.get(i).isDisponivel()
					, itens.get(i).getCodPedido()
					, itens.get(i).getCodVenda()
					, itens.get(i).getProduto()
					, itens.get(i).getQuantidadeEmEstoque());
			dto.setPercDesconto(itens.get(i).getPercentDesconto());
			dto.setUsuario(itens.get(i).getUsuario());
			returnzz.add(dto);
		}
		
		return returnzz;
	}
	
	public static ItemMovimentadoCarrinhoDTO convertEstoqueDtoToItemMovimentadoCarrinho(EstoqueDTO estoque){
		ItemMovimentadoCarrinhoDTO dto = new ItemMovimentadoCarrinhoDTO(
				estoque.getCodCatalogo()
				, estoque.getDtMovimentacao()
				, estoque.getQtdeMovimentadoProduto()
				, estoque.getQtdeTotalPontosMovimentadoProduto()
				, estoque.getValorTotalMovimentadoProduto()
				, estoque.getTipoFluxoEstoque()
				, estoque.getCodPedido()
				, estoque.getCodVenda()
				, false
				, estoque.isDisponivel()
				, estoque.getQtdeEmEstoque());
		dto.setPercentDesconto(estoque.getPercDesconto());
		dto.setUsuario(estoque.getUsuario());
		return dto;
				
	}	
	
	public static List<ItemMovimentadoCarrinhoDTO> convertEstoqueDtoToItemMovimentadoCarrinho(List<EstoqueDTO> estoques){
		List<ItemMovimentadoCarrinhoDTO>returnzz = new ArrayList<ItemMovimentadoCarrinhoDTO>();
			for (int i = 0; i < estoques.size(); i++) {
				
				ItemMovimentadoCarrinhoDTO dto = new ItemMovimentadoCarrinhoDTO(
						estoques.get(i).getCodCatalogo()
					, estoques.get(i).getDtMovimentacao()
					, estoques.get(i).getQtdeMovimentadoProduto()
					, estoques.get(i).getQtdeTotalPontosMovimentadoProduto()
					, estoques.get(i).getValorTotalMovimentadoProduto()
					, estoques.get(i).getTipoFluxoEstoque()
					, estoques.get(i).getCodPedido()
					, estoques.get(i).getCodVenda()
					, false
					, estoques.get(i).isDisponivel()
					, estoques.get(i).getQtdeEmEstoque());
				
				dto.setPercentDesconto(estoques.get(i).getPercDesconto());
				dto.setUsuario(estoques.get(i).getUsuario());
				
				returnzz.add(dto);
			}
			
		return returnzz;
	}	
	
//CONVERSOES DA CLASSE PARCELA
	public static List<ParcelasEntity> listDtoToListEntity(List<ParcelasDTO> lista){
		List<ParcelasEntity>parcelasEntities = new ArrayList<ParcelasEntity>();
		for (int i = 0; i < lista.size(); i++) {
			ParcelasEntity entity = new ParcelasEntity(lista.get(i).getId(), lista.get(i).getCodVenda(), lista.get(i).getCodPedido(), lista.get(i).getDtVencimento(), lista.get(i).getDescricao(), lista.get(i).getNumParcela(), lista.get(i).getValorParcela(), lista.get(i).getCodSituacaoParcela(), lista.get(i).getDtPagamento(), lista.get(i).getValorPago());
			entity.setUsuario(lista.get(i).getUsuario());
			parcelasEntities.add(entity);
		}
		
		return parcelasEntities;
	}

	public static List<ParcelasDTO> listEntityToListDto(List<ParcelasEntity> lista){
		List<ParcelasDTO>parcelasDTOs = new ArrayList<ParcelasDTO>();
		for (int i = 0; i < lista.size(); i++) {
			ParcelasDTO dto = new ParcelasDTO(lista.get(i).getId(), lista.get(i).getCodVenda(), lista.get(i).getCodPedido(), lista.get(i).getDtVencimento(), lista.get(i).getDescricao(), lista.get(i).getNumParcela(), lista.get(i).getValorParcela(), lista.get(i).getCodSituacaoParcela(), lista.get(i).getDtPagamento(), lista.get(i).getValorPago());
			dto.setUsuario(lista.get(i).getUsuario());
			parcelasDTOs.add(dto);
		}
		return parcelasDTOs;
	}
	public static ParcelasEntity dtoToEntity(ParcelasDTO dto){
		ParcelasEntity entity = new ParcelasEntity(dto.getId(), dto.getCodVenda(), dto.getCodPedido(), dto.getDtVencimento(), dto.getDescricao(), dto.getNumParcela(), dto.getValorParcela(), dto.getCodSituacaoParcela(), dto.getDtPagamento(), dto.getValorPago());
			entity.setUsuario(dto.getUsuario());
		return entity;
	}
	
	public static ParcelasDTO entityToDto(ParcelasEntity entity){
		ParcelasDTO dto = new ParcelasDTO(entity.getId(), entity.getCodVenda(), entity.getCodPedido(), entity.getDtVencimento(), entity.getDescricao(), entity.getNumParcela(), entity.getValorParcela(), entity.getCodSituacaoParcela(), entity.getDtPagamento(), entity.getValorPago());
		dto.setUsuario(entity.getUsuario());
		
		return dto;
	}

	
//CONVERSOES DA CLASSE AGENDA
	public static AgendaEntity dtoToEntity(AgendaDTO dto){
		AgendaEntity entity = new AgendaEntity();
		entity.setDataFim(dto.getDataFim());
		entity.setDataInicio(dto.getDataInicio());
		entity.setDescricao(dto.getDescricao());
		entity.setDiaTodo(dto.isDiaTodo());
		entity.setId(dto.getId());
		entity.setTipoAgenda(dto.getTipoAgenda());
		entity.setCliente(dto.getCliente());
		entity.setUsuario(dto.getUsuario());
		return entity;
	}
	
	public static AgendaDTO entityToDto(AgendaEntity entity) {
		AgendaDTO dto = new AgendaDTO();
		dto.setDataFim(entity.getDataFim());
		dto.setDataInicio(entity.getDataInicio());
		dto.setDescricao(entity.getDescricao());
		dto.setDiaTodo(entity.isDiaTodo());
		dto.setId(entity.getId());
		dto.setTipoAgenda(entity.getTipoAgenda());
		dto.setCliente(entity.getCliente());
		dto.setUsuario(entity.getUsuario());
		return dto;
	}
	
	public static List<VendaDTO> listaVendaEntityToDto(List<VendaEntity> venda) {
		List<VendaDTO> itens = new ArrayList<VendaDTO>();
		for (int i = 0; i < venda.size(); i++) {
			
			VendaDTO item = new VendaDTO(
					venda.get(i).getCodVenda()
					, Adapter.entityToDto(venda.get(i).getCliente())
					, venda.get(i).getDataVenda()
					, venda.get(i).getValorVenda()
					, venda.get(i).getQtdeTotalPontosVendidos()
					, historicoVendaToItemMovimentado(venda.get(i).getVendaProdutosList())
					, Adapter.listEntityToListDto(venda.get(i).getParcelas())
					, venda.get(i).getFormaDePagamento()
					, venda.get(i).getValorDescontoVenda());
			
			item.setDescSituacaoPagamento(venda.get(i).getParcelas().get(venda.get(i).getParcelas().size()-1).getCodSituacaoParcela().getDescSituacao());
			item.setUsuario(venda.get(i).getUsuario());
			
			itens.add(item);
		}
		
		return itens;
	}
	
	public static List<VendaEntity> listaVendaDtoToEntity(List<VendaDTO> venda) {
		List<VendaEntity> itens = new ArrayList<VendaEntity>();
		for (int i = 0; i < venda.size(); i++) {
			
			VendaEntity item = new VendaEntity(  
					venda.get(i).getCodVenda()
					, venda.get(i).getDataVenda()
					, venda.get(i).getValorVenda()
					, venda.get(i).getQtdeTotalPontosVendidos()
					, dtoToEntity(venda.get(i).getCliente())
					, Adapter.listDtoToListEntity(venda.get(i).getParcelas())
					, venda.get(i).getFormaPagamento()
					, venda.get(i).getValorDescontoVenda());				
			
			item.setUsuario(venda.get(i).getUsuario());
			itens.add(item);
		}
		
		return itens;
	}


}
