package br.com.mkoffice.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.model.ConsultoraEntity;
import br.com.mkoffice.model.constants.StatusConsultoraEnum;
import br.com.mkoffice.utils.MkmtsUtil;


public class ConsultoraDTO {

	private Long id;
	private Date dataInicio;
	private Date dataDesativacao;
	private PessoaDTO dadosPessoa;
	private StatusConsultoraEnum status;
	private String infAdicional;
	private UserDTO usuario;

	public static ConsultoraDTO entityToDTO(ConsultoraEntity entity){
		ConsultoraDTO dto = new ConsultoraDTO();
		dto.setId(entity.getId());
		dto.setDadosPessoa(PessoaDTO.entityToDTO(entity.getDadosPessoa()));
		dto.setDataDesativacao(entity.getDataDesativacao());
		dto.setDataInicio(entity.getDataInicio());
		dto.setInfAdicional(entity.getInfAdicional());
		dto.setStatus(entity.getStatus());
		dto.setUsuario(UserDTO.entityToDTO(entity.getUsuario()));
		
		return dto;
	}
	public static List<ConsultoraDTO> listEntityToListDTO(List<ConsultoraEntity> entity){
		List<ConsultoraDTO> returnzz = new ArrayList<ConsultoraDTO>();
		for (ConsultoraEntity obj : entity) {
			returnzz.add(ConsultoraDTO.entityToDTO(obj));
		}
		
		return returnzz;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataDesativacao() {
		return dataDesativacao;
	}
	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}
	public PessoaDTO getDadosPessoa() {
		return dadosPessoa;
	}
	public void setDadosPessoa(PessoaDTO dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
	}
	public StatusConsultoraEnum getStatus() {
		return status;
	}
	public void setStatus(StatusConsultoraEnum status) {
		this.status = status;
	}
	public String getInfAdicional() {
		return infAdicional;
	}
	public void setInfAdicional(String infAdicional) {
		this.infAdicional = infAdicional;
	}

	private String numTelefoneString;
	private String numCelularString;
	public String getNumTelefoneString() {
		if((numTelefoneString == null || numTelefoneString.equals("")) && !(dadosPessoa.getNumTelefone() == null)){
			return MkmtsUtil.formatarNumTelefoneCelularString(dadosPessoa.getNumTelefone());
		}
		return numTelefoneString;
	}

	public String getNumCelularString() {
		if((numCelularString == null || numCelularString.equals("")) && !(dadosPessoa.getNumCelular() == null)){
			return MkmtsUtil.formatarNumTelefoneCelularString(dadosPessoa.getNumCelular());
		}
		return numCelularString;
	}
	public void setNumTelefoneString(String numTelefoneString) {
		this.numTelefoneString = numTelefoneString;
	}

	public void setNumCelularString(String numCelularString) {
		this.numCelularString = numCelularString;
	}
	
	private Long numTelefone;
	private Long numCelular;
	public Long getNumTelefone() {
		removerMascaras();
		return numTelefone;
	}
	public void setNumTelefone(Long numTelefone) {
		this.numTelefone = numTelefone;
	}
	public Long getNumCelular() {
		removerMascaras();
		return numCelular;
	}
	public void setNumCelular(Long numCelular) {
		this.numCelular = numCelular;
	}
	
	public String getDataNascimentoFormatado() {
		return dadosPessoa.getDataNascimento() == null ? "" : MkmtsUtil.converterDataString(dadosPessoa.getDataNascimento(), "dd/MM/yyyy");
	}
	
	public String getDataInicioFormatado() {
		return dataInicio == null ? "" : MkmtsUtil.converterDataString(dataInicio, "dd/MM/yyyy");
	}
	
	public String getDataDesativacaoFormatado() {
		return dataDesativacao == null ? "" : MkmtsUtil.converterDataString(dataDesativacao, "dd/MM/yyyy");
	}
	
	public void removerMascaras() {

		if (!(getNumTelefoneString() == null || getNumTelefoneString().equals(""))) {
			setNumTelefone(	MkmtsUtil.removerFormatacaoTelefone(getNumTelefoneString()));
		}
		
		if (!(getNumCelularString() == null || getNumCelularString().equals(""))) {
			setNumCelular(MkmtsUtil.removerFormatacaoTelefone(getNumCelularString()));
		}

	}
	public UserDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UserDTO usuario) {
		this.usuario = usuario;
	}
	
}
