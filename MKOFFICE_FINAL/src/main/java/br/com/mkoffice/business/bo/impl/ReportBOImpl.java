package br.com.mkoffice.business.bo.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.ReportBO;
import br.com.mkoffice.business.exception.ValidationFormRequiredException;
import br.com.mkoffice.dao.jpa.cadastro.ReportDashboardCaixaRepository;
import br.com.mkoffice.dao.jpa.cadastro.ReportPromocaoClienteVolumeVendaRepository;
import br.com.mkoffice.dao.jpa.cadastro.ReportRetencaoClientesRepository;
import br.com.mkoffice.dto.reports.DashboardCaixaDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDTO;
import br.com.mkoffice.dto.reports.cliente.ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO;
import br.com.mkoffice.dto.reports.cliente.ReportRetencaoClientesDTO;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.utils.MkmtsUtil;

@Stateless
public class ReportBOImpl implements ReportBO{

	@Inject
	private ReportPromocaoClienteVolumeVendaRepository repoPromocaoClientePorVolumeVenda = null;

	@Inject
	private ReportRetencaoClientesRepository repoRetencaoClientesRepository = null;

	@Inject
	private ReportDashboardCaixaRepository repoDashboardCaixaRepository = null;
	
	@Override
	public List<ReportPromocaoClientePorVolumeVendaDTO> getReportPromocaoClientePorVolume(BigDecimal valorCorte, Integer anoFiltro) {
		validadeFiltroPromocaoClientePorVolumeVenda(valorCorte, anoFiltro);
			
		return repoPromocaoClientePorVolumeVenda.gerarRelatorioPorVolumeVenda(valorCorte, anoFiltro);
	}

	@Override
	public List<ReportPromocaoClientePorVolumeVendaDetalhadoPorClienteDTO> getReportPromocaoClientePorVolumeDetalhado(BigDecimal valorCorteFiltro, Integer comboAnosFiltroSelecionado) {
		validadeFiltroPromocaoClientePorVolumeVenda(valorCorteFiltro, comboAnosFiltroSelecionado);
		return repoPromocaoClientePorVolumeVenda.gerarRelatorioPorVolumeVendaDetalhado(valorCorteFiltro, comboAnosFiltroSelecionado);
	}

	private void validadeFiltroPromocaoClientePorVolumeVenda(BigDecimal valorCorte, Integer anoFiltro) {
		if(MkmtsUtil.verificaIntegerNulo(anoFiltro) == 0){
			throw new ValidationFormRequiredException("ANO");
		}
	}

	@Override
	public List<ReportRetencaoClientesDTO> getReportRetencaoClientes(Date dataCorteFiltro) {
		validateReportRetencaoClientes(dataCorteFiltro);
		
		return repoRetencaoClientesRepository.gerarRelatorioRetencaoClientes(dataCorteFiltro);
	}

	private void validateReportRetencaoClientes(Date dataCorteFiltro){
		if(dataCorteFiltro == null){
			throw new ValidationFormRequiredException("DATA DE CORTE");
		}
	}

	@Override
	public DashboardCaixaDTO getReportVisaoGeralCaixa(Date ano) {
		Calendar c = Calendar.getInstance();
		c.setTime(ano);
		Integer anoFiltro = c.get(Calendar.YEAR);
		return repoDashboardCaixaRepository.gerarRelatorioDashboardCaixa(anoFiltro);
	}
	
	@Override
	public BigDecimal getSaldoUsuario(UserEntity usuario) {
		StringBuilder q1 = new StringBuilder("select sum(p.valorPago) from ParcelasEntity p where p.codVenda is not null");
		StringBuilder q2 = new StringBuilder("select sum(p.valorPago) from ParcelasEntity p where p.codPedido is not null");
		BigDecimal faturamento = new BigDecimal(repoDashboardCaixaRepository.getEntityManager().createQuery(q1.toString()).getSingleResult().toString());
		BigDecimal gasto = new BigDecimal(repoDashboardCaixaRepository.getEntityManager().createQuery(q2.toString()).getSingleResult().toString());
		return faturamento.subtract(gasto);
	}
	
}
