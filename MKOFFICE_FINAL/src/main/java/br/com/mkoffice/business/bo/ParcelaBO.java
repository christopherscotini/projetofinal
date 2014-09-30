package br.com.mkoffice.business.bo;

import java.util.Date;
import java.util.List;

import br.com.mkoffice.business.ServiceModel;
import br.com.mkoffice.dto.ParcelasDTO;
import br.com.mkoffice.dto.reports.financeiro.ContasReceberReportDTO;

public interface ParcelaBO extends ServiceModel<ParcelasDTO> {

	public boolean efetuarPagamento(ParcelasDTO parcela);

	public List<ContasReceberReportDTO> gerarRelatorioContasReceber(Date dataInicial, Date dataFinal, Long idUsuario);
}
