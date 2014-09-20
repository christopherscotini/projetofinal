/**
 * 
 */
package br.com.mkoffice.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.model.ClienteEntity;
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
	private PessoaDTO dadosPessoa;
	private Date dataInicioAcompanhamento;
	private List<VendaDTO> listaVendas;
	private UserDTO usuario;

	public ClienteDTO()  {
	}

	public ClienteDTO(Long id, Date dataPrimeiraVenda,
			Date dataUltimaVenda, String infAdicional, PessoaDTO dadosPessoa,
			Date dataInicioAcompanhamento) {
		super();
		this.id = id;
		this.dataPrimeiraVenda = dataPrimeiraVenda;
		this.dataUltimaVenda = dataUltimaVenda;
		this.infAdicional = infAdicional;
		this.dadosPessoa = dadosPessoa;
		this.dataInicioAcompanhamento = dataInicioAcompanhamento;
	}

	public static ClienteDTO entityToDTO(ClienteEntity entity){
		ClienteDTO dto = new ClienteDTO();
		
		dto.setId(entity.getId());
		dto.setDadosPessoa(PessoaDTO.entityToDTO(entity.getDadosPessoa()));
		dto.setDataPrimeiraVenda(entity.getDataPrimeiraVenda());
		dto.setDataUltimaVenda(entity.getDataUltimaVenda());
		dto.setDataInicioAcompanhamento(entity.getDataInicioAcompanhamento());
		dto.setInfAdicional(entity.getInfAdicional());
		dto.setUsuario(UserDTO.entityToDTO(entity.getUsuario()));
		
		return dto;
	}
	
	public static List<ClienteDTO> listEntityToListDTO(
			List<ClienteEntity> findByFiltroPesquisa) {
		List<ClienteDTO> list = new ArrayList<ClienteDTO>();
		for (ClienteEntity entity : findByFiltroPesquisa) {
			list.add(entityToDTO(entity));
		}
		
		return list;
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

	public PessoaDTO getDadosPessoa() {
		return dadosPessoa;
	}

	public void setDadosPessoa(PessoaDTO dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
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

	public UserDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UserDTO usuario) {
		this.usuario = usuario;
	}
	
	public String getDataPrimeiraVendaFormatado() {
		return dataPrimeiraVenda == null ? "" : MkmtsUtil.converterDataString(dataPrimeiraVenda, "dd/MM/yyyy");
	}
	public String getDataUltimaVendaFormatado() {
		return dataUltimaVenda == null ? "" : MkmtsUtil.converterDataString(dataUltimaVenda, "dd/MM/yyyy");
	}

}
