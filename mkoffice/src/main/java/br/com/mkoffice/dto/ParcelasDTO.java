package br.com.mkoffice.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.model.ParcelasEntity;

public class ParcelasDTO {
	
	private Long id;
	private VendaDTO codVenda;
	private PedidoDTO codPedido;
	private Date dtVencimento;
	private String descricao;
	private Integer numParcela;
	private BigDecimal valorParcela;
	private SituacaoDTO codSituacaoParcela;
	private Date dtPagamento;
	private BigDecimal valorPago;
	private UserDTO usuario;
	
	public ParcelasDTO() {

	}

	public ParcelasDTO(Date dtVencimento, String descricao,
			Integer numParcela, BigDecimal valorParcela,
			SituacaoDTO codSituacaoParcela, Date dtPagamento,
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



	public ParcelasDTO(Long id, VendaDTO codVenda,
			PedidoDTO codPedido, Date dtVencimento, String descricao,
			Integer numParcela, BigDecimal valorParcela,
			SituacaoDTO codSituacaoParcela, Date dtPagamento,
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
	
	public static ParcelasDTO entityToDto(ParcelasEntity entity){
		
		ParcelasDTO dto = new ParcelasDTO(
				  entity.getId()
				, VendaDTO.entityToDto(entity.getCodVenda())
				, PedidoDTO.entityToDto(entity.getCodPedido())
				, entity.getDtVencimento()
				, entity.getDescricao()
				, entity.getNumParcela()
				, entity.getValorParcela()
				, SituacaoDTO.entityToDTO(entity.getCodSituacaoParcela())
				, entity.getDtPagamento()
				, entity.getValorPago());
		dto.setUsuario(UserDTO.entityToDTO(entity.getUsuario()));
		
		return dto;
	}

	public static List<ParcelasDTO> listEntityToListDto(List<ParcelasEntity> lista){
		List<ParcelasDTO>parcelasDTOs = new ArrayList<ParcelasDTO>();
		for (int i = 0; i < lista.size(); i++) {
			parcelasDTOs.add(entityToDto(lista.get(i)));
		}
		return parcelasDTOs;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VendaDTO getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(VendaDTO codVenda) {
		this.codVenda = codVenda;
	}

	public PedidoDTO getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(PedidoDTO codPedido) {
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

	public SituacaoDTO getCodSituacaoParcela() {
		return codSituacaoParcela;
	}

	public void setCodSituacaoParcela(SituacaoDTO codSituacaoParcela) {
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

	public UserDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UserDTO usuario) {
		this.usuario = usuario;
	}

}
