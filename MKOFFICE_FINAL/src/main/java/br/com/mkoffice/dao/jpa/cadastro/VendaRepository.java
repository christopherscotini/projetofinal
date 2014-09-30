/**
 * 
 */
package br.com.mkoffice.dao.jpa.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.dao.jpa.JpaGenericDao;
import br.com.mkoffice.dto.VendaDTO;
import br.com.mkoffice.model.venda.VendaEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.MkmtsUtil;

/**
 * @author christopher.rozario
 */
public class VendaRepository extends JpaGenericDao<VendaEntity, Long> {

	final String SELECT = "SELECT v FROM VendaEntity v WHERE v.usuario.id = :idUsuario";
	final String ORDER_BY = "ORDER BY v.dataVenda";

	private StringBuilder builder;
	
	public List<VendaDTO> salesFilter(Integer codVendaFiltro,
			String nomeClienteFiltro, Date dataInicioFiltro,
			Date dataFinalFiltro, Long idUsuario) {

		builder = new StringBuilder();
		builder.append(SELECT);
		builder.append(_ESPACE);
		
		if(codVendaFiltro > 0){
			builder.append("AND v.codVenda = "+codVendaFiltro);
		}
		if(!nomeClienteFiltro.equals("")){
			builder.append("AND v.cliente.dadosPessoa.nome LIKE '"+nomeClienteFiltro+"%'");
			builder.append(_ESPACE);
		}
		if(null != dataInicioFiltro){
			builder.append("AND v.dataVenda BETWEEN '"+MkmtsUtil.converterDataString(dataInicioFiltro, "dd/MM/yyyy")+" 00:00:00' AND '"+MkmtsUtil.converterDataString(dataFinalFiltro, "yyyy-MM-dd")+" 23:59:59'");
			builder.append(_ESPACE);
		}
		builder.append(ORDER_BY);
		
		List<VendaEntity>listaRetornadaEntities = getEntityManager().createQuery(builder.toString(), VendaEntity.class).setParameter("idUsuario", idUsuario).getResultList();
		List<VendaDTO>retorno = new ArrayList<VendaDTO>();
		for (int i = 0; i < listaRetornadaEntities.size(); i++) {
			VendaDTO dto = new VendaDTO(
					listaRetornadaEntities.get(i).getCodVenda()
					, Adapter.entityToDto(listaRetornadaEntities.get(i).getCliente())
					, listaRetornadaEntities.get(i).getDataVenda()
					, listaRetornadaEntities.get(i).getValorVenda()
					, listaRetornadaEntities.get(i).getQtdeTotalPontosVendidos()
					, Adapter.historicoVendaToItemMovimentado(listaRetornadaEntities.get(i).getVendaProdutosList())
					, Adapter.listEntityToListDto(listaRetornadaEntities.get(i).getParcelas())
					, listaRetornadaEntities.get(i).getFormaDePagamento()
					, listaRetornadaEntities.get(i).getValorLucroVenda());
			
			dto.setDescSituacaoPagamento(listaRetornadaEntities.get(i).getParcelas().get(listaRetornadaEntities.get(i).getParcelas().size()-1).getCodSituacaoParcela().getDescSituacao());
			dto.setUsuario(listaRetornadaEntities.get(i).getUsuario());
			retorno.add(dto);
		}
		
		
		return retorno;
		
	}
	
}
