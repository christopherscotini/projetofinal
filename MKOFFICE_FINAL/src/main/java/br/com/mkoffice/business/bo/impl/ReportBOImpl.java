package br.com.mkoffice.business.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.ReportBO;
import br.com.mkoffice.dao.jpa.cadastro.ReportPromocaoClienteVolumeVendaRepository;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;

@Stateless
public class ReportBOImpl implements ReportBO{

	@Inject
	private ReportPromocaoClienteVolumeVendaRepository repo = null;
	
	@Override
	public List<ReportPromocaoClientePorVolumeVendaDTO> getReportPromocaoClientePorVolume(BigDecimal valorCorte, Integer anoFiltro) {

		return repo.gerarRelatorioPorVolumeVenda(valorCorte, anoFiltro);
	}

	
	
}
