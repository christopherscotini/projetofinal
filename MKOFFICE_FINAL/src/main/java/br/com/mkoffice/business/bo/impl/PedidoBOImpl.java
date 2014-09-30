package br.com.mkoffice.business.bo.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.PedidoBO;
import br.com.mkoffice.business.exception.RegistroJaCadastradoException;
import br.com.mkoffice.dao.jpa.cadastro.EstoqueRepository;
import br.com.mkoffice.dao.jpa.cadastro.FluxoEstoqueRepository;
import br.com.mkoffice.dao.jpa.cadastro.ParcelaRepository;
import br.com.mkoffice.dao.jpa.cadastro.PedidoRepository;
import br.com.mkoffice.dto.PedidoDTO;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.model.constants.PercentDescontoEnum;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.MkmtsUtil;
import br.com.mkoffice.view.constants.TipoFluxoEstoqueEnum;

@Stateless
public class PedidoBOImpl implements PedidoBO {

	@Inject
	private PedidoRepository dao = null;
	
	@Inject
	private EstoqueRepository estoqueDao = null;

	@Inject
	private ParcelaRepository parcelaDao = null;

	@Inject
	private FluxoEstoqueRepository fluxoEstoqueDao = null;
	
	private final boolean EXISTE_EM_ESTOQUE = true;
	private final boolean NAO_EXISTE_EM_ESTOQUE = false;

	@Override
	public PedidoDTO efetuarPedido(PedidoDTO dto) {
		if(dao.existsPedido(dto)){
			throw new RegistroJaCadastradoException("Pedido N�"+dto.getCodPedido());
		}
		
		for (int i = 0; i < dto.getListaProdutosComprados().size(); i++) {
			dto.getListaProdutosComprados().get(i).setDtMovimentado(dto.getDtPedido());
			dto.getListaProdutosComprados().get(i).setDisponivel(true);
			dto.getListaProdutosComprados().get(i).setPercentDesconto(PercentDescontoEnum.getDescontoByCodigo(dto.getPorcDesconto()));
		}

		PedidoDTO retorno = Adapter.entityToDto(dao.insert(Adapter.dtoToEntity(dto)));
		
		FluxoEstoqueEntity fluxo = fluxoEstoqueDao.findByFilter(TipoFluxoEstoqueEnum.ENTRADA_PEDIDO.toString()).get(0);
		for (int i = 0; i < dto.getListaProdutosComprados().size(); i++) {
			retorno.getListaProdutosComprados().get(i).setTipoFluxoEstoque(fluxo);
			retorno.getListaProdutosComprados().get(i).setPercentDesconto(PercentDescontoEnum.getDescontoByCodigo(dto.getPorcDesconto()));
		}
		
		for (int i = 0; i < retorno.getParcelas().size(); i++) {
			retorno.getParcelas().get(i).setCodPedido(Adapter.dtoToEntity(retorno));
			parcelaDao.insert(Adapter.dtoToEntity(retorno.getParcelas().get(i)));
		}
		
		atualizaEstoque(retorno);
		
		return retorno;
	}
	
	@Override
	public PedidoDTO efetuarPedidoImportado(PedidoDTO dto) {
		if(dao.existsPedido(dto)){
			throw new RegistroJaCadastradoException("Pedido N�"+dto.getCodPedido());
		}
		
		for (int i = 0; i < dto.getListaProdutosComprados().size(); i++) {
			dto.getListaProdutosComprados().get(i).setDtMovimentado(dto.getDtPedido());
			dto.getListaProdutosComprados().get(i).setDisponivel(true);
			dto.getListaProdutosComprados().get(i).getProduto().setAtivo(true);
		}
		
		PedidoDTO retorno = Adapter.entityToDto(dao.insertUploadedOrder(dto));

		FluxoEstoqueEntity fluxo = fluxoEstoqueDao.findByFilter(TipoFluxoEstoqueEnum.ENTRADA_PEDIDO.toString()).get(0);
		for (int i = 0; i < dto.getListaProdutosComprados().size(); i++) {
			retorno.getListaProdutosComprados().get(i).setTipoFluxoEstoque(fluxo);
			retorno.getListaProdutosComprados().get(i).setPercentDesconto(PercentDescontoEnum.getDescontoByCodigo(dto.getPorcDesconto()));
		}
		
		for (int i = 0; i < retorno.getParcelas().size(); i++) {
			retorno.getParcelas().get(i).setCodPedido(Adapter.dtoToEntity(retorno));
			parcelaDao.insert(Adapter.dtoToEntity(retorno.getParcelas().get(i)));
		}
		
		atualizaEstoque(retorno);
		
		return retorno;
	}
	
	@Override
	public boolean existePedido(PedidoDTO dto) {
		if(dao.existsPedido(dto)){
			throw new RegistroJaCadastradoException("Pedido N�"+dto.getCodPedido());
		}
		
		return NAO_EXISTE_EM_ESTOQUE;
	}
	
	@Override
	public List<PedidoDTO> consultarPedidos(String codPedido, Date dtInicio,
			Date dtFim, Long idUsuario) {
		
		return dao.findByFilter(MkmtsUtil.verificaStringNulaLong(codPedido), dtInicio, dtFim, idUsuario);
	}
	
	@Override
	public List<PedidoDTO> listarPedidosRealizados(Long idUsuario) {
		return dao.findAllOrders(idUsuario);
	}
	
	private void atualizaEstoque(PedidoDTO pedDTO){
		for (int i = 0; i < pedDTO.getListaProdutosComprados().size(); i++) {
			pedDTO.getListaProdutosComprados().get(i).setUsuario(pedDTO.getUsuario());
		}
		estoqueDao.updateProductsStock(pedDTO.getListaProdutosComprados());
	}
	
	
}
