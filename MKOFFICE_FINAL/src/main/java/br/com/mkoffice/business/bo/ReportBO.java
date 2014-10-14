package br.com.mkoffice.business.bo;

import java.math.BigDecimal;
import java.util.List;

import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;

public interface ReportBO {

	List<ReportPromocaoClientePorVolumeVendaDTO> getReportPromocaoClientePorVolume(BigDecimal valorCorte, Integer anoFiltro);
	
	
}
