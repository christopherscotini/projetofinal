package br.com.mkoffice.view.controller.menu.clientes.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.mkoffice.model.ClienteEntity;
import br.com.mkoffice.model.admin.UserEntity;
import br.com.mkoffice.model.embeddable.Endereco;
import br.com.mkoffice.utils.CpfCnpjUtils;
import br.com.mkoffice.utils.MkmtsUtil;

public class ClienteVO implements Serializable{

	private static final long serialVersionUID = -6857610049199890661L;

	private Long id;
	private Date dataPrimeiraVenda;
	private Date dataUltimaVenda;
	private String nome;
	private Long numTelefone;
	private Long numCelular;
	private String email;
	private Date dataNascimento;
	private Date dataInicioAcompanhamento;
	private String sexo;
	private Endereco endereco;
	private String infAdicional;
	private String numTelefoneString;
	private String numCelularString;

	private Long cpf;
	private String cpfString;
	
	private UserEntity usuario;
	
	public ClienteVO() {
	}

	public ClienteVO(ClienteEntity clienteEntity) {
		this.id = clienteEntity.getId();
		this.dataPrimeiraVenda = clienteEntity.getDataPrimeiraVenda();
		this.dataUltimaVenda = clienteEntity.getDataUltimaVenda();
		this.nome = clienteEntity.getDadosPessoa().getNome();
		this.numTelefone = clienteEntity.getDadosPessoa().getNumTelefone();
		this.numCelular = clienteEntity.getDadosPessoa().getNumCelular();
		this.email = clienteEntity.getDadosPessoa().getEmail();
		this.dataNascimento = clienteEntity.getDadosPessoa().getDataNascimento();
		this.sexo = clienteEntity.getDadosPessoa().getSexo();
		this.endereco = clienteEntity.getDadosPessoa().getEndereco();
		this.infAdicional = clienteEntity.getInfAdicional();
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getInfAdicional() {
		return infAdicional;
	}

	public void setInfAdicional(String infAdicional) {
		this.infAdicional = infAdicional;
	}

	public String getNumTelefoneString() {
		if((numTelefoneString == null || numTelefoneString.equals("")) && !(numTelefone == null)){
			return MkmtsUtil.formatarNumTelefoneCelularString(numTelefone);
		}
		return numTelefoneString;
	}

	public String getNumCelularString() {
		if((numCelularString == null || numCelularString.equals("")) && !(numCelular == null)){
			return MkmtsUtil.formatarNumTelefoneCelularString(numCelular);
		}
		return numCelularString;
	}

	public void setNumTelefoneString(String numTelefoneString) {
		this.numTelefoneString = numTelefoneString;
	}

	public void setNumCelularString(String numCelularString) {
		this.numCelularString = numCelularString;
	}
	public String getDataNascimentoFormatado() {
		return dataNascimento == null ? "" : MkmtsUtil.converterDataString(dataNascimento, "dd/MM/yyyy");
	}
	public String getDataPrimeiraVendaFormatado() {
		return dataPrimeiraVenda == null ? "" : MkmtsUtil.converterDataString(dataPrimeiraVenda, "dd/MM/yyyy");
	}
	public String getDataUltimaVendaFormatado() {
		return dataUltimaVenda == null ? "" : MkmtsUtil.converterDataString(dataUltimaVenda, "dd/MM/yyyy");
	}
	
	
	public void removerMascaras() {

		if (!(getNumTelefoneString() == null || getNumTelefoneString().equals(""))) {
			setNumTelefone(	MkmtsUtil.removerFormatacaoTelefone(getNumTelefoneString()));
		}
		
		if (!(getNumCelularString() == null || getNumCelularString().equals(""))) {
			setNumCelular(MkmtsUtil.removerFormatacaoTelefone(getNumCelularString()));
		}

	}

	public Date getDataInicioAcompanhamento() {
		return dataInicioAcompanhamento;
	}

	public void setDataInicioAcompanhamento(Date dataInicioAcompanhamento) {
		this.dataInicioAcompanhamento = dataInicioAcompanhamento;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}

	public String getCpfString() {
		if((cpfString == null || cpfString.equals("")) && !(cpf == null)){
			return CpfCnpjUtils.formatarCpfCnpjString(cpf);
		}
		return cpfString;
	}

	public void setCpfString(String cpfString) {
		this.cpfString = cpfString;
	}

	public Long getCpf() {
		return CpfCnpjUtils.removerFormatacaoCpfCnpj(cpfString);
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
}
