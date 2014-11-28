package br.com.mkoffice.business.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.ReportBO;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.ParcelaRepository;
import br.com.mkoffice.dao.jpa.cadastro.PedidoRepository;
import br.com.mkoffice.dao.jpa.cadastro.ReportClienteRepository;
import br.com.mkoffice.dao.jpa.cadastro.ReportEstoqueRepository;
import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO;
import br.com.mkoffice.dto.reports.cliente.ReportRetencaoClientesDTO;
import br.com.mkoffice.dto.reports.estoque.ReportProdutosMaisMenosVendidosDTO;
import br.com.mkoffice.dto.reports.pedido.ReportPedidoConsolidadoDTO;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.estoque.EstoqueEntity;
import br.com.mkoffice.utils.MkmtsUtil;

@Stateless
public class ReportBOImpl implements ReportBO{

	@Inject
	private ReportClienteRepository reportClienteRepository = null;

	@Inject
	private ParcelaRepository parcelaRepository = null;

	@Inject
	private ReportEstoqueRepository reportEstoqueRepository = null;
	
	@Inject
	private PedidoRepository pedidoRepository = null;

	@Override
	public List<ReportPromocaoClientePorVolumeVendaDTO> getReportPromocaoClientePorVolume(BigDecimal valorCorte, Integer anoFiltro, Long idUsuario) {
		validadeFiltroPromocaoClientePorVolumeVenda(valorCorte, anoFiltro);
		return reportClienteRepository.gerarRelatorioPorVolumeVenda(valorCorte, anoFiltro, idUsuario);
	}

	@Override
	public List<ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO> getReportPromocaoClientePorVolumeDetalhado(BigDecimal valorCorteFiltro, Integer comboAnosFiltroSelecionado, Long idUsuario) {
		validadeFiltroPromocaoClientePorVolumeVenda(valorCorteFiltro, comboAnosFiltroSelecionado);
		return reportClienteRepository.gerarRelatorioPorVolumeVendaDetalhado(valorCorteFiltro, comboAnosFiltroSelecionado, idUsuario);
	}

	private void validadeFiltroPromocaoClientePorVolumeVenda(BigDecimal valorCorte, Integer anoFiltro) {
		if(MkmtsUtil.verificaIntegerNulo(anoFiltro) == 0){
			throw new ValidationFormRequiredException("ANO");
		}
	}

	@Override
	public List<ReportRetencaoClientesDTO> getReportRetencaoClientes(Date dataCorteFiltro, Long idUsuario) {
		validateReportRetencaoClientes(dataCorteFiltro);
		
		return reportClienteRepository.gerarRelatorioRetencaoClientes(dataCorteFiltro, idUsuario);
	}

	private void validateReportRetencaoClientes(Date dataCorteFiltro){
		if(dataCorteFiltro == null){
			throw new ValidationFormRequiredException("DATA DE CORTE");
		}
	}

	@Override
	public ReportProdutosMaisMenosVendidosDTO getReportProdutoMaisMenosVendidos(Integer anoFiltro, Long idUsuario) {
		ReportProdutosMaisMenosVendidosDTO dto = new ReportProdutosMaisMenosVendidosDTO();
		dto.setProdutosMaisVendidos(reportEstoqueRepository.gerarProdutosMaisVendidos(anoFiltro, idUsuario));
		dto.setProdutosMenosVendidos(reportEstoqueRepository.gerarProdutosMenosVendidos(anoFiltro, idUsuario));
		return dto;
	}
	
	@Override
	public List<EstoqueEntity> getReportMovimentacaoEstoque(DataFilter dataFiltro, Long fluxoEstoqueFiltro, Long idUsuario) {
		return reportEstoqueRepository.gerarMovimentacaoEstoque(dataFiltro, fluxoEstoqueFiltro, idUsuario);
	}
	
	@Override
	public ReportPedidoConsolidadoDTO getReportPedidoConsolidado(DataFilter dataFiltro, Long idUsuario) {
		
		return pedidoRepository.gerarRelatorioPedidoConsolidado(dataFiltro, idUsuario);
	}
	
}
