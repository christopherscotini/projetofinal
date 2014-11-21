package br.com.mkoffice.business.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.dto.DataFilter;
import br.com.mkoffice.dto.reports.DashboardCaixaDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO;
import br.com.mkoffice.dto.reports.cliente.ReportRetencaoClientesDTO;
import br.com.mkoffice.dto.reports.estoque.ReportProdutosMaisMenosVendidosDTO;
import br.com.mkoffice.dto.reports.pedido.ReportPedidoConsolidadoDTO;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.estoque.EstoqueEntity;

public interface ReportBO {

	List<ReportPromocaoClientePorVolumeVendaDTO> getReportPromocaoClientePorVolume(BigDecimal valorCorte, Integer anoFiltro, Long idUsuario);

	List<ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO> getReportPromocaoClientePorVolumeDetalhado(BigDecimal valorCorteFiltro, Integer comboAnosFiltroSelecionado, Long idUsuario);

	List<ReportRetencaoClientesDTO> getReportRetencaoClientes(Date dataCorteFiltro, Long idUsuario);
	
	DashboardCaixaDTO getReportVisaoGeralCaixa(Date ano, Long idUsuario);

	BigDecimal getSaldoUsuario(UserEntity usuario);

	ReportProdutosMaisMenosVendidosDTO getReportProdutoMaisMenosVendidos(Integer anoFiltro, Long idUsuario);

	List<EstoqueEntity> getReportMovimentacaoEstoque(DataFilter dataFiltro, Long fluxoEstoqueFiltro, Long idUsuario);

	ReportPedidoConsolidadoDTO getReportPedidoConsolidado(DataFilter dataFiltro, Long idUsuario);
}
