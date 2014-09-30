/**
 * 
 */
package br.com.mkoffice.dto;

import java.util.Date;
import java.util.List;

import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.embeddable.Pessoa;
import br.com.mkoffice.utils.MkmtsUtil;

/**
 * @author christopher.rozario
 * 
 */
public class ClienteDTO {

	private Long id;
	private Date dataPrimeiraVenda;
	private Date dataUltimaVenda;
	private String infAdicional;
	private Pessoa dadosPessoa;
	private Date dataInicioAcompanhamento;
	private List<VendaDTO> listaVendas;
	private UserEntity usuario;

	public ClienteDTO()  {
	}

	public ClienteDTO(Long id, Date dataPrimeiraVenda,
			Date dataUltimaVenda, String infAdicional, Pessoa dadosPessoa,
			Date dataInicioAcompanhamento, String numTelefoneString,
			String numCelularString) {
		super();
		this.id = id;
		this.dataPrimeiraVenda = dataPrimeiraVenda;
		this.dataUltimaVenda = dataUltimaVenda;
		this.infAdicional = infAdicional;
		this.dadosPessoa = dadosPessoa;
		this.dataInicioAcompanhamento = dataInicioAcompanhamento;
		this.numTelefoneString = numTelefoneString;
		this.numCelularString = numCelularString;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataPrimeiraVenda() {
		return dataPrimeiraVenda;
	}

	public void setDataPrimeiraVenda(Date dataPrimeiraVenda) {
		this.dataPrimeiraVenda = dataPrimeiraVenda;
	}

	public Date getDataUltimaVenda() {
		return dataUltimaVenda;
	}

	public void setDataUltimaVenda(Date dataUltimaVenda) {
		this.dataUltimaVenda = dataUltimaVenda;
	}

	public String getInfAdicional() {
		return infAdicional;
	}

	public void setInfAdicional(String infAdicional) {
		this.infAdicional = infAdicional;
	}

	public Pessoa getDadosPessoa() {
		return dadosPessoa;
	}

	public void setDadosPessoa(Pessoa dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
	}

	private String numTelefoneString;
	private String numCelularString;

	public String getNumTelefoneString() {
		if(dadosPessoa != null){
			if ((numTelefoneString == null || numTelefoneString.equals(""))
					&& !(dadosPessoa.getNumTelefone() == null)) {
				return MkmtsUtil.formatarNumTelefoneCelularString(dadosPessoa
						.getNumTelefone());
			}
		}
		return numTelefoneString;
	}

	public String getNumCelularString() {
		if(dadosPessoa != null){
			if ((numCelularString == null || numCelularString.equals("")) && !(dadosPessoa.getNumCelular() == null)) {
				return MkmtsUtil.formatarNumTelefoneCelularString(dadosPessoa
						.getNumCelular());
			}
		}
		return numCelularString;
	}

	public Date getDataInicioAcompanhamento() {
		return dataInicioAcompanhamento;
	}

	public void setDataInicioAcompanhamento(Date dataInicioAcompanhamento) {
		this.dataInicioAcompanhamento = dataInicioAcompanhamento;
	}

	public List<VendaDTO> getListaVendas() {
		return listaVendas;
	}

	public void setListaVendas(List<VendaDTO> listaVendas) {
		this.listaVendas = listaVendas;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}

}
