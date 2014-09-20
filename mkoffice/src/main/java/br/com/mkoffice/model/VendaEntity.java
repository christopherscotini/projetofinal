package br.com.mkoffice.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;

import br.com.mkoffice.dto.VendaDTO;

@Entity
@Table(name = "TB_VENDA")
public class VendaEntity implements Serializable {

	private static final long serialVersionUID = 8829568145770105114L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VENDA")
	private Long codVenda;

	@Column(name = "DT_VENDA", nullable = false)
	private Date dataVenda;

	@Column(name = "VL_VLR_VENDA", nullable = false)
	private BigDecimal valorVenda;

	@Column(name = "VL_VLR_DESCONTO_VENDA", nullable = true)
	private BigDecimal valorDescontoVenda;

	@Column(name = "VL_VLR_LUCRO", nullable = false)
	private BigDecimal valorLucroVenda;

	@Column(name = "NU_QTDE_TOTAL_PONTOS_VENDIDOS", nullable = false)
	private Integer qtdeTotalPontosVendidos;

	@ManyToOne
	@JoinColumn(name = "TB_CLIENTE_TB_VENDA_FK", nullable = false)
	private ClienteEntity cliente;

	@ManyToOne
	@JoinColumn(name = "TB_FORMA_PAGTO_VENDA_FK")
	private FormaPagamentoEntity formaDePagamento;
	
	@OneToMany(mappedBy = "codVenda", fetch = FetchType.LAZY)
	private List<ParcelasEntity> parcelas;

	@OneToMany(mappedBy = "codVenda", fetch = FetchType.LAZY, cascade= CascadeType.PERSIST)
	private List<HistoricoVendasEntity> vendaProdutosList;
	
	@ManyToOne
	@JoinColumn(name = "TB_USUARIO_FK")
	private UserEntity usuario;

	public VendaEntity() {

	}

	public VendaEntity(Long codVenda, Date dataVenda, BigDecimal valorVenda,
			Integer qtdeTotalPontosVendidos,
			ClienteEntity cliente, List<ParcelasEntity> parcelas, FormaPagamentoEntity formaDePagamento, BigDecimal valorLucroVenda) {
		super();
		this.codVenda = codVenda;
		this.dataVenda = dataVenda;
		this.valorVenda = valorVenda;
		this.qtdeTotalPontosVendidos = qtdeTotalPontosVendidos;
		this.cliente = cliente;
		this.parcelas = parcelas;
		this.formaDePagamento = formaDePagamento;
		this.valorLucroVenda = valorLucroVenda;
	}

	public static VendaEntity dtoToEntity(VendaDTO dto) {
		VendaEntity entity = new VendaEntity(
				dto.getCodVenda()
				, dto.getDataVenda()
				, dto.getValorVenda()
				, dto.getQtdeTotalPontosVendidos()
				, ClienteEntity.dtoToEntity(dto.getCliente())
				, ParcelasEntity.listDtoToListEntity(dto.getParcelas())
				, FormaPagamentoEntity.convertDTOToEntity(dto.getFormaPagamento())
				, dto.getValorDescontoVenda());//lista de parcelas
		
		entity.setVendaProdutosList(HistoricoPedidosEntity.itemMovimentadoToHistoricoVenda(dto.getListaDeProdutos(), entity));
		entity.setUsuario(UserEntity.dtoToEntity(dto.getUsuario()));
		
		return entity;
	}

	public List<ParcelasEntity> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelasEntity> parcelas) {
		this.parcelas = parcelas;
	}

	public Long getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(Long codVenda) {
		this.codVenda = codVenda;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}


	public Integer getQtdeTotalPontosVendidos() {
		return qtdeTotalPontosVendidos;
	}

	public void setQtdeTotalPontosVendidos(Integer qtdeTotalPontosVendidos) {
		this.qtdeTotalPontosVendidos = qtdeTotalPontosVendidos;
	}

	public List<HistoricoVendasEntity> getVendaProdutosList() {
		return vendaProdutosList;
	}

	public void setVendaProdutosList(List<HistoricoVendasEntity> vendaProdutosList) {
		this.vendaProdutosList = vendaProdutosList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codVenda == null) ? 0 : codVenda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendaEntity other = (VendaEntity) obj;
		if (codVenda == null) {
			if (other.codVenda != null)
				return false;
		} else if (!codVenda.equals(other.codVenda))
			return false;
		return true;
	}

	public FormaPagamentoEntity getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaPagamentoEntity formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public BigDecimal getValorDescontoVenda() {
		return valorDescontoVenda;
	}

	public void setValorDescontoVenda(BigDecimal valorDescontoVenda) {
		this.valorDescontoVenda = valorDescontoVenda;
	}

	public BigDecimal getValorLucroVenda() {
		return valorLucroVenda;
	}

	public void setValorLucroVenda(BigDecimal valorLucroVenda) {
		this.valorLucroVenda = valorLucroVenda;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}
}
