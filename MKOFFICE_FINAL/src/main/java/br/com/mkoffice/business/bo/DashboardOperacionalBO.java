package br.com.mkoffice.business.bo;

import br.com.mkoffice.dto.dashboard.DashboardOperacionalDTO;


public interface DashboardOperacionalBO {

	DashboardOperacionalDTO gerarDashBoard(Long idUsuario);
	
}
