/**
 * 
 */
package br.com.mkoffice.business.bo.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.VendaBO;
import br.com.mkoffice.dao.jpa.cadastro.AgendaRepository;
import br.com.mkoffice.dao.jpa.cadastro.ClienteRepository;
import br.com.mkoffice.dao.jpa.cadastro.EstoqueRepository;
import br.com.mkoffice.dao.jpa.cadastro.FluxoEstoqueRepository;
import br.com.mkoffice.dao.jpa.cadastro.ParcelaRepository;
import br.com.mkoffice.dao.jpa.cadastro.TipoAgendaRepository;
import br.com.mkoffice.dao.jpa.cadastro.VendaRepository;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.EstoqueDTO;
import br.com.mkoffice.dto.VendaDTO;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.model.agenda.AgendaEntity;
import br.com.mkoffice.model.agenda.TipoAgendaEntity;
import br.com.mkoffice.model.constants.PercentDescontoEnum;
import br.com.mkoffice.model.constants.TipoAgendaEnum;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.CalculadoraUtils;
import br.com.mkoffice.utils.MkmtsUtil;
import br.com.mkoffice.view.constants.TipoFluxoEstoqueEnum;

/**
 * @author christopher.rozario
 */
@Stateless
public class VendaBOImpl implements VendaBO {

	@Inject
	private VendaRepository dao = null;

	@Inject
	private EstoqueRepository daoEstoque = null;

	@Inject
	private ClienteRepository daoCliente = null;
	
	@Inject
	private ParcelaRepository parcelaDao = null;

	@Inject
	private FluxoEstoqueRepository fluxoEstoqueDao = null;

	@Inject
	private AgendaRepository agendaDao = null;

	@Inject
	private TipoAgendaRepository tipoAgendaDao = null;

	@Override
	public List<VendaDTO> listarVendas(Long idUsuario) {
		return filtrarVenda(null, null, null, idUsuario);
	}

	@Override
	public VendaDTO efetuarVenda(VendaDTO venda) {
		venda.setValorVenda(venda.getValorVenda());
	
		for (int i = 0; i < venda.getListaDeProdutos().size(); i++) {
			venda.getListaDeProdutos().get(i).setDtMovimentado(venda.getDataVenda());
		}
		
		if(!daoCliente.existsSaleForClient(Adapter.dtoToEntity(venda.getCliente()))){
			venda.getCliente().setDataPrimeiraVenda(venda.getDataVenda());
			daoCliente.update(Adapter.dtoToEntity(venda.getCliente()));
		}
		
		venda.getCliente().setDataUltimaVenda(venda.getDataVenda());
		venda.setQtdeTotalPontosVendidos(CalculadoraUtils.somarTotalPontosPedidoVenda(venda.getListaDeProdutos()));
		
		VendaDTO retorno  = Adapter.entityToDto(dao.insert(Adapter.dtoToEntity(venda)));//registro a venda;

		if (venda.isAcompanhamento222()) {
			adicionarAcompanhamento(venda);
		}
		
		FluxoEstoqueEntity fluxo = (FluxoEstoqueEntity) fluxoEstoqueDao.findByFilter(TipoFluxoEstoqueEnum.SAIDA_VENDA.toString()).get(0);
		venda.setCodVenda(retorno.getCodVenda());
		
		for (int i = 0; i < venda.getListaDeProdutos().size(); i++) {
			venda.getListaDeProdutos().get(i).setCodVenda(Adapter.dtoToEntity(venda));
			venda.getListaDeProdutos().get(i).setTipoFluxoEstoque(fluxo);
			venda.getListaDeProdutos().get(i).setPercentDesconto(PercentDescontoEnum._0);
			venda.getListaDeProdutos().get(i).setUsuario(venda.getUsuario());
		}
		
		for (int i = 0; i < retorno.getParcelas().size(); i++) {
			retorno.getParcelas().get(i).setCodVenda(Adapter.dtoToEntity(retorno));
			parcelaDao.insert(Adapter.dtoToEntity(retorno.getParcelas().get(i)));
		}
		
		daoEstoque.updateProductsStock(venda.getListaDeProdutos());//atualizo o estoque;
		
		return retorno;
	}
	
	@Override
	public List<VendaDTO> filtrarVenda(String codVendaFiltro,
			String nomeClienteFiltro, DataFilter dataFiltro, Long idUsuario) {

		List<VendaDTO> retorno = dao.salesFilter(MkmtsUtil.verificaStringNulaInteger(codVendaFiltro)
				, MkmtsUtil.verificaStringNula(nomeClienteFiltro)
				, dataFiltro
				, idUsuario);
		
		return retorno;
	}
	
	@Override
	public BigDecimal calculaValorLucroVenda(VendaDTO venda){
		BigDecimal returnzz = BigDecimal.ZERO;
		for (int i = 0; i < venda.getListaDeProdutos().size(); i++) {
			List<EstoqueDTO>estoqueDTOs = daoEstoque.findAllByCodeProduct(venda.getListaDeProdutos().get(i).getProduto().getCodCatalogo(), venda.getUsuario().getId());
			int produtosSomados = 0;
			for (int j = 0; j < estoqueDTOs.size(); j++) {
				if (venda.getListaDeProdutos().get(i).getQtdeProdutoCarrinho() <= estoqueDTOs.get(j).getQtdeEmEstoque()) {
					int count = 0;
					while (count++ < venda.getListaDeProdutos().get(i).getQtdeProdutoCarrinho()) {
						if (produtosSomados == venda.getListaDeProdutos().get(i).getQtdeProdutoCarrinho()) {
							break;
						}
						returnzz = returnzz.add(CalculadoraUtils.calcularValorLucroPorProdutoVenda(estoqueDTOs.get(j).getCodCatalogo().getPreco(), estoqueDTOs.get(j).getPercDesconto()));
						produtosSomados++;
					}
					break;
				}else{
					int count = 0;
					while (count++ < estoqueDTOs.get(j).getQtdeEmEstoque()) {
						if (produtosSomados == venda.getListaDeProdutos().get(i).getQtdeProdutoCarrinho()) {
							break;
						}
						returnzz = returnzz.add(CalculadoraUtils.calcularValorLucroPorProdutoVenda(estoqueDTOs.get(j).getCodCatalogo().getPreco(), estoqueDTOs.get(j).getPercDesconto()));
						produtosSomados++;
					}
				}

			}
			
			
			
		}
		return returnzz;
	}
	
	@SuppressWarnings("static-access")
	private void adicionarAcompanhamento(VendaDTO dto){
	    Calendar dtInicio = new GregorianCalendar();  
	    Calendar dtPrimeiraFase = new GregorianCalendar();  
	    Calendar dtSegundaFase = new GregorianCalendar();  
	    Calendar dtTerceiraFase = new GregorianCalendar();  
	    
	    
	    
	    TipoAgendaEntity tipoAgenda = tipoAgendaDao.findByFilter(TipoAgendaEnum.AGENDA_ACOMPANHAMENTO.toString()).get(0);
	    
	    dtInicio.setTime(dto.getDataVenda()); 
	    dtPrimeiraFase.setTime(dto.getDataVenda());
	    dtSegundaFase.setTime(dto.getDataVenda());
	    dtTerceiraFase.setTime(dto.getDataVenda());
	    
	    dtPrimeiraFase.add(dtInicio.DAY_OF_MONTH, 3);  
	    dtSegundaFase.add(dtInicio.DAY_OF_MONTH, 15);  
	    dtTerceiraFase.add(dtInicio.MONTH, 2);  
		
		AgendaEntity agenda = new AgendaEntity();
		agenda.setUsuario(dto.getUsuario());
		agenda.setDataInicio(dtPrimeiraFase.getTime()); 
		agenda.setDataFim(dtPrimeiraFase.getTime()); 
		agenda.setDescricao("1� FA da cliente "+dto.getCliente().getDadosPessoa().getNome()); 
		agenda.setDiaTodo(true); 
		agenda.setTipoAgenda(tipoAgenda);
		
		AgendaEntity agenda2 = new AgendaEntity();
		agenda2.setUsuario(dto.getUsuario());
		agenda2.setDataInicio(dtSegundaFase.getTime()); 
		agenda2.setDataFim(dtSegundaFase.getTime()); 
		agenda2.setDescricao("2� FA da cliente "+dto.getCliente().getDadosPessoa().getNome()); 
		agenda2.setTipoAgenda(tipoAgenda);
		agenda2.setDiaTodo(true); 

		AgendaEntity agenda3 = new AgendaEntity();
		agenda3.setUsuario(dto.getUsuario());
		agenda3.setDataInicio(dtTerceiraFase.getTime()); 
		agenda3.setDataFim(dtTerceiraFase.getTime()); 
		agenda3.setDescricao("3� FA da cliente "+dto.getCliente().getDadosPessoa().getNome()); 
		agenda3.setDiaTodo(true); 
		agenda3.setTipoAgenda(tipoAgenda);
		
		agendaDao.insert(agenda);
		agendaDao.insert(agenda2);
		agendaDao.insert(agenda3);
	}

		
}
