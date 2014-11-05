package br.com.mkoffice.business.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.dto.reports.DashboardCaixaDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO;
import br.com.mkoffice.dto.reports.cliente.ReportRetencaoClientesDTO;
import br.com.mkoffice.model.admin.UserEntity;

public interface ReportBO {

	List<ReportPromocaoClientePorVolumeVendaDTO> getReportPromocaoClientePorVolume(BigDecimal valorCorte, Integer anoFiltro);

	List<ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO> getReportPromocaoClientePorVolumeDetalhado(BigDecimal valorCorteFiltro, Integer comboAnosFiltroSelecionado);

	List<ReportRetencaoClientesDTO> getReportRetencaoClientes(Date dataCorteFiltro);
	
	DashboardCaixaDTO getReportVisaoGeralCaixa(Date ano);

	BigDecimal getSaldoUsuario(UserEntity usuario);
}
