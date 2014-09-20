package br.com.mkoffice.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.mkoffice.dto.ClienteDTO;

@Entity
@Table(name = "TB_CLIENTE")
public class ClienteEntity implements Serializable  {

	private static final long serialVersionUID = 6104454123345027201L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private Long id;

	@Column(name = "DT_PRIM_VENDA")
	private Date dataPrimeiraVenda;

	@Column(name = "DT_ULTI_VENDA")
	private Date dataUltimaVenda;

	@Column(name = "DT_INIC_ACOM")
	private Date dataInicioAcompanhamento;

	@Column(name = "DS_INFO_ADIC")
	private String infAdicional;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<VendaEntity> listaVendas;

	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name= "TB_PESSOA_FK")
	private PessoaEntity dadosPessoa;
	
	@ManyToOne
	@JoinColumn(name = "TB_USUARIO_FK")
	private UserEntity usuario;

	public ClienteEntity() {
	
	}

	public ClienteEntity(Long id, Date dataPrimeiraVenda,
			Date dataUltimaVenda, String infAdicional, PessoaEntity dadosPessoa) {
		super();
		this.id = id;
		this.dataPrimeiraVenda = dataPrimeiraVenda;
		this.dataUltimaVenda = dataUltimaVenda;
		this.infAdicional = infAdicional;
		this.dadosPessoa = dadosPessoa;
	}

	public static ClienteEntity dtoToEntity(ClienteDTO dto){
		ClienteEntity entity = new ClienteEntity();
		entity.setId(dto.getId());
		entity.setDadosPessoa(PessoaEntity.convertDTOToEntity(dto.getDadosPessoa()));
		entity.setDataPrimeiraVenda(dto.getDataPrimeiraVenda());
		entity.setDataUltimaVenda(dto.getDataUltimaVenda());
		entity.setDataInicioAcompanhamento(dto.getDataInicioAcompanhamento());
		entity.setInfAdicional(dto.getInfAdicional());
		entity.setUsuario(UserEntity.dtoToEntity(dto.getUsuario()));
		
		return entity;
	}
	
	public ClienteEntity(Long id) {
		super();
		this.id = id;
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

	public PessoaEntity getDadosPessoa() {
		return dadosPessoa;
	}

	public void setDadosPessoa(PessoaEntity dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
	}

	public String getInfAdicional() {
		return infAdicional;
	}

	public void setInfAdicional(String infAdicional) {
		this.infAdicional = infAdicional;
	}

	public Date getDataInicioAcompanhamento() {
		return dataInicioAcompanhamento;
	}

	public void setDataInicioAcompanhamento(Date dataInicioAcompanhamento) {
		this.dataInicioAcompanhamento = dataInicioAcompanhamento;
	}
	
	public List<VendaEntity> getListaVendas() {
		return listaVendas;
	}
	
	public void setListaVendas(List<VendaEntity> listaVendas) {
		this.listaVendas = listaVendas;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}

}
