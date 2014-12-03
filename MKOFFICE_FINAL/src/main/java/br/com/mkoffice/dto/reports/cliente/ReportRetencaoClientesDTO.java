package br.com.mkoffice.dto.reports.cliente;

import java.util.Date;

import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class ReportRetencaoClientesDTO {

	
	private ClienteEntity cliente;
	private Date dataUltimaCompra;
	private String numCelularString;
	
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	public Date getDataUltimaCompra() {
		return dataUltimaCompra;
	}
	public void setDataUltimaCompra(Date dataUltimaCompra) {
		this.dataUltimaCompra = dataUltimaCompra;
	}
	public String getNumCelularString() {
		if(cliente.getDadosPessoa() != null){
			if ((numCelularString == null || numCelularString.equals("")) && !(cliente.getDadosPessoa().getNumCelular() == null)) {
				return MkmtsUtil.formatarNumTelefoneCelularString(cliente.getDadosPessoa()
						.getNumCelular());
			}
		}
		return numCelularString;
	}
}
