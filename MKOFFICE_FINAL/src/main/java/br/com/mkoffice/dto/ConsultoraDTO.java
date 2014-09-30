package br.com.mkoffice.dto;

import java.util.Date;

import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.constants.StatusConsultoraEnum;
import br.com.mkoffice.model.embeddable.Pessoa;
import br.com.mkoffice.utils.MkmtsUtil;


public class ConsultoraDTO {

	private Long codConsultora;
	private Date dataInicio;
	private Date dataDesativacao;
	private Pessoa dadosPessoa;
	private StatusConsultoraEnum status;
	private String infAdicional;
	private UserEntity usuario;

	
	public Long getCodConsultora() {
		return codConsultora;
	}
	public void setCodConsultora(Long codConsultora) {
		this.codConsultora = codConsultora;
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
	public Pessoa getDadosPessoa() {
		return dadosPessoa;
	}
	public void setDadosPessoa(Pessoa dadosPessoa) {
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
	public UserEntity getUsuario() {
		return usuario;
	}
	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}
	
}
