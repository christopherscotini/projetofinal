package br.com.mkoffice.business.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		dto.setRankingClientes(dashboardOperacionalRepository.selectRankingVendaClientes(idUsuario, 5));
		dto.setValorFaturamentoMesAnterior(dashboardOperacionalRepository.selectValorFaturamentoMesAnterior(idUsuario));
		dto.setValorFaturamentoMesAtual(dashboardOperacionalRepository.selectValorFaturamentoMesAtual(idUsuario));
		dto.calcularPercentualDiferencaFaturamentoMesAtualMesAnterior();
		dto.setValorLucroMesAnterior(dashboardOperacionalRepository.selectValorLucroMesAnterior(idUsuario));
		dto.setValorLucroMesAtual(dashboardOperacionalRepository.selectValorLucroMesAtual(idUsuario));
		dto.calcularPercentualDiferencaLucroMesAtualMesAnterior();
		dto.setBalanco(new BalancoDTO(parcelaRepository.getSaldoUsuario(idUsuario), Calendar.getInstance()));
		List<BalancoDTO>historicoBalanco = dashboardOperacionalRepository.selectHistoricoBalanco(idUsuario);
		List<BalancoDTO>historicoFaturamento = dashboardOperacionalRepository.selectHistoricoFaturamento(idUsuario);
		List<BalancoDTO>historicoGasto = (dashboardOperacionalRepository).selectHistoricoGasto(idUsuario);
		dto.setReportEstoqueDashboard(dashboardOperacionalRepository.gerarDashboardReportEstoqueDashboard(idUsuario));
		
		dto.setHistoricoBalanco(montarhistoricoBalanco(historicoBalanco));		
		dto.setHistoricoFaturamento(montarhistoricoBalanco(historicoFaturamento));		
		dto.setHistoricoGasto(montarhistoricoBalanco(historicoGasto));		
		
		return dto;
	}
	
	
	private List<BalancoDTO> montarhistoricoBalanco(List<BalancoDTO> historicoBalanco){
		List<BalancoDTO> aux = new ArrayList<BalancoDTO>();
		
		try{
			if (historicoBalanco.size() < 11) {
				for (int i = historicoBalanco.size()-1; i <= 11 ; i--) {
					if(aux.size() <= 12){
					int diffy = historicoBalanco.get(i).getData().get(Calendar.YEAR) - historicoBalanco.get(i-1).getData().get(Calendar.YEAR);
						if(diffy == 0){
							int diffm = historicoBalanco.get(i).getData().get(Calendar.MONTH) - historicoBalanco.get(i-1).getData().get(Calendar.MONTH);
							if(diffm == 1){
								System.out.println(historicoBalanco.get(i).getMes());
								aux.add(new BalancoDTO(historicoBalanco.get(i).getValorBalanco(), historicoBalanco.get(i).getData()));
							}else{
								aux.add(new BalancoDTO(historicoBalanco.get(i).getValorBalanco(), historicoBalanco.get(i).getData()));
								for (int j = 1; j < diffm; j++) {
									if(aux.size() <= 12){
										Calendar c1 = Calendar.getInstance();
										c1.set(Calendar.YEAR, historicoBalanco.get(i).getData().get(Calendar.YEAR));
										c1.set(Calendar.MONTH, historicoBalanco.get(i).getData().get(Calendar.MONTH)-j);
										c1.set(Calendar.DAY_OF_MONTH, historicoBalanco.get(i).getData().get(Calendar.DAY_OF_MONTH));
										aux.add(new BalancoDTO(BigDecimal.ZERO, c1));
										System.out.println(aux.get(i).getMes());
									}
								}	
							}
						}	
					}
				}
			}
			
		}catch(IndexOutOfBoundsException e){
			if(historicoBalanco.isEmpty()){
				aux.add(new BalancoDTO(BigDecimal.ZERO, Calendar.getInstance()));
			}else{
				aux.add(new BalancoDTO(historicoBalanco.get(0).getValorBalanco(), historicoBalanco.get(0).getData()));
			}
			if(aux.size() <12){
				for (int i = 0; i < 12; i++) {
					if(aux.size() <12){
						Calendar c1 = Calendar.getInstance();
						c1.set(Calendar.YEAR, aux.get(aux.size()-1).getData().get(Calendar.YEAR));
						c1.set(Calendar.MONTH, aux.get(aux.size()-1).getData().get(Calendar.MONTH)-1);
						c1.set(Calendar.DAY_OF_MONTH, aux.get(aux.size()-1).getData().get(Calendar.DAY_OF_MONTH));
						aux.add(new BalancoDTO(BigDecimal.ZERO, c1));
					}
				}
			}
		}
		
		historicoBalanco.removeAll(historicoBalanco);
		
		for (int i = aux.size()-1; i >= 0; i--) {
			historicoBalanco.add(aux.get(i));
		}
		
		return historicoBalanco;
	}
	
}
