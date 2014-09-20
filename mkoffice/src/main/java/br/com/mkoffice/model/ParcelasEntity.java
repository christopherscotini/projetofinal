package br.com.mkoffice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.mkoffice.dto.ParcelasDTO;

@Entity
@Table(name = "TB_PARCELA")
public class ParcelasEntity implements Serializable{
	
	private static final long serialVersionUID = 3594829303532783331L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PARCELA")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "TB_VENDA_PARCELA_FK")
	private VendaEntity codVenda;
	
	@ManyToOne
	@JoinColumn(name = "TB_PEDIDO_PARCELA_FK")
	private PedidoEntity codPedido;
	
	@Column(name = "DT_VENCIMENTO")
	private Date dtVencimento;
	
	@Column(name = "DS_PARCELA")
	private String descricao;
	
	@Column(name = "NU_PARCELA")
	private Integer numParcela;
	
	@Column(name = "VL_VLR_PARCELA")
	private BigDecimal valorParcela;
	
	@ManyToOne
	@JoinColumn(name = "TB_SITUACAO_PAGTO_PARCELA_FK")
	private SituacaoEntity codSituacaoParcela;
	
	@Column(name = "DT_PAGAMENTO")
	private Date dtPagamento;
	
	@Column(name = "VL_VLR_PAGO")
	private BigDecimal valorPago;
	
	@ManyToOne
	@JoinColumn(name = "TB_USUARIO_FK")
	private UserEntity usuario;
	
	public ParcelasEntity() {

	}

	public ParcelasEntity(Date dtVencimento, String descricao,
			Integer numParcela, BigDecimal valorParcela,
			SituacaoEntity codSituacaoParcela, Date dtPagamento,
			BigDecimal valorPago) {
		super();
		this.dtVencimento = dtVencimento;
		this.descricao = descricao;
		this.numParcela = numParcela;
		this.valorParcela = valorParcela;
		this.codSituacaoParcela = codSituacaoParcela;
		this.dtPagamento = dtPagamento;
		this.valorPago = valorPago;
	}



	public ParcelasEntity(Long id, VendaEntity codVenda,
			PedidoEntity codPedido, Date dtVencimento, String descricao,
			Integer numParcela, BigDecimal valorParcela,
			SituacaoEntity codSituacaoParcela, Date dtPagamento,
			BigDecimal valorPago) {
		super();
		this.id = id;
		this.codVenda = codVenda;
		this.codPedido = codPedido;
		this.dtVencimento = dtVencimento;
		this.descricao = descricao;
		this.numParcela = numParcela;
		this.valorParcela = valorParcela;
		this.codSituacaoParcela = codSituacaoParcela;
		this.dtPagamento = dtPagamento;
		this.valorPago = valorPago;
	}

	public static ParcelasEntity dtoToEntity(ParcelasDTO dto){
		ParcelasEntity entity = new ParcelasEntity(
				dto.getId()
				, VendaEntity.dtoToEntity(dto.getCodVenda())
				, PedidoEntity.dtoToEntity(dto.getCodPedido())
				, dto.getDtVencimento()
				, dto.getDescricao()
				, dto.getNumParcela()
				, dto.getValorParcela()
				, SituacaoEntity.dtoToEntity(dto.getCodSituacaoParcela())
				, dto.getDtPagamento()
				, dto.getValorPago());
			entity.setUsuario(UserEntity.dtoToEntity(dto.getUsuario()));
		return entity;
	}
	
	public static List<ParcelasEntity> listDtoToListEntity(List<ParcelasDTO> lista){
		List<ParcelasEntity>parcelasEntities = new ArrayList<ParcelasEntity>();
		for (int i = 0; i < lista.size(); i++) {
			parcelasEntities.add(dtoToEntity(lista.get(i)));
		}
		
		return parcelasEntities;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VendaEntity getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(VendaEntity codVenda) {
		this.codVenda = codVenda;
	}

	public PedidoEntity getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(PedidoEntity codPedido) {
		this.codPedido = codPedido;
	}

	public Date getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public SituacaoEntity getCodSituacaoParcela() {
		return codSituacaoParcela;
	}

	public void setCodSituacaoParcela(SituacaoEntity codSituacaoParcela) {
		this.codSituacaoParcela = codSituacaoParcela;
	}

	public Date getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}

}
