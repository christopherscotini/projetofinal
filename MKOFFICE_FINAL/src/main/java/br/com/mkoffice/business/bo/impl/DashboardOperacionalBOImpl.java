package br.com.mkoffice.business.bo.impl;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.DashboardOperacionalBO;
import br.com.mkoffice.dao.jpa.cadastro.DashboardOperacionalRepository;
import br.com.mkoffice.dao.jpa.cadastro.ParcelaRepository;
import br.com.mkoffice.dto.dashboard.BalancoDTO;
import br.com.mkoffice.dto.dashboard.DashboardOperacionalDTO;
import br.com.mkoffice.utils.MkmtsUtil;

@Stateless
public class DashboardOperacionalBOImpl implements DashboardOperacionalBO {

	@Inject
	private DashboardOperacionalRepository dashboardOperacionalRepository = null;

	@Inject
	private ParcelaRepository parcelaRepository = null;
	
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
		dto.setBalanco(new BalancoDTO(parcelaRepository.getSaldoUsuario(idUsuario), MkmtsUtil.extrairMesDataNome(new Date(), true)));
		dto.setHistoricoBalanco(dashboardOperacionalRepository.selectHistoricoBalanco(idUsuario));
		
		return dto;
	}
	
	
}
