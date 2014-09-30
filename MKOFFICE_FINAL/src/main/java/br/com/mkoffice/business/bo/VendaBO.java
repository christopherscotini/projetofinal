/**
 * 
 */
package br.com.mkoffice.business.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.dto.VendaDTO;

/**
 * @author christopher.rozario
 */

public interface VendaBO {

	List<VendaDTO>listarVendas(Long idUsuario);
	VendaDTO efetuarVenda(VendaDTO dto);
	List<VendaDTO>filtrarVenda(String codVendaFiltro, String nomeClienteFiltro, Date dataInicioFiltro, Date dataFinalFiltro, Long idUsuario);
	BigDecimal calculaValorLucroVenda(VendaDTO venda);
}
