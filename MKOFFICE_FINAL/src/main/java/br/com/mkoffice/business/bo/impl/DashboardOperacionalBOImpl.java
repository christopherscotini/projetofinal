package br.com.mkoffice.business.bo.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.DashboardOperacionalBO;
import br.com.mkoffice.dao.jpa.cadastro.DashboardOperacionalRepository;
import br.com.mkoffice.dto.dashboard.DashboardOperacionalDTO;

@Stateless
public class DashboardOperacionalBOImpl implements DashboardOperacionalBO {

	@Inject
	private DashboardOperacionalRepository dashboardOperacionalRepository = null;
	
	@Override
	public DashboardOperacionalDTO gerarDashBoard(Long idUsuario) {
		DashboardOperacionalDTO dto = new DashboardOperacionalDTO();
		dto.setListaUltimasVendas(dashboardOperacionalRepository.selectTop5UltimasVendas(idUsuario));
		dto.setRankingClientes(dashboardOperacionalRepository.selectTop10RankingVendaClientes(idUsuario));
		dto.setValorFaturamentoMesAnterior(dashboardOperacionalRepository.selectValorFaturamentoMesAnterior(idUsuario));
		dto.setValorFaturamentoMesAtual(dashboardOperacionalRepository.selectValorFaturamentoMesAtual(idUsuario));
		dto.calcularPercentualDiferencaFaturamentoMesAtualMesAnterior();
		dto.setValorLucroMesAnterior(dashboardOperacionalRepository.selectValorLucroMesAnterior(idUsuario));
		dto.setValorLucroMesAtual(dashboardOperacionalRepository.selectValorLucroMesAtual(idUsuario));
		dto.calcularPercentualDiferencaLucroMesAtualMesAnterior();
		
		
		return dto;
	}

	
}
