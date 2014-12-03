package br.com.mkoffice.business.bo;

import java.util.Date;
import java.util.List;

import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.PedidoDTO;


public interface PedidoBO {
	
	public List<PedidoDTO> consultarPedidos(String codPedido, DataFilter dtFiltro, Long idUsuario);
	public PedidoDTO efetuarPedido(PedidoDTO dto);
	public PedidoDTO efetuarPedidoImportado(PedidoDTO dto);
	public List<PedidoDTO> listarPedidosRealizados(Long idUsuario);
	public boolean existePedido(PedidoDTO dto) ;
	
}
