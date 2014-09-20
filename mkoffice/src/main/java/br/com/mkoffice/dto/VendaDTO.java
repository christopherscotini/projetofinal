/**
 * 
 */
package br.com.mkoffice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.model.VendaEntity;

/**
 * @author christopher.rozario
 * 
 */
public class VendaDTO {

	private Long codVenda;
	private ClienteDTO cliente;
	private Date dataVenda;
	private BigDecimal valorVenda;
	private BigDecimal valorDescontoVenda;
	private BigDecimal valorLucroVenda;
	private Integer qtdeTotalPontosVendidos;
	private List<ItemMovimentadoCarrinhoDTO>listaDeProdutos;
	private List<ParcelasDTO>parcelas;
	private FormaPagamentoDTO formaPagamento;
	private UserDTO usuario;
	
	private String descSituacaoPagamento;
	private boolean acompanhamento222;
	
	public VendaDTO() {

	}
	
	public VendaDTO(Long codVenda, ClienteDTO cliente, Date dataVenda,
			BigDecimal valorVenda, Integer qtdeTotalPontosVendidos,
			List<ItemMovimentadoCarrinhoDTO> listaDeProdutos,
		List<ParcelasDTO> parcelas, FormaPagamentoDTO formaPagamento, BigDecimal valorLucroVenda){
		super();
		this.codVenda = codVenda;
		this.cliente = cliente;
		this.dataVenda = dataVenda;
		this.valorVenda = valorVenda;
		this.qtdeTotalPontosVendidos = qtdeTotalPontosVendidos;
		this.listaDeProdutos = listaDeProdutos;
		this.parcelas = parcelas;
		this.formaPagamento = formaPagamento;
		this.setValorLucroVenda(valorLucroVenda);
	}

	public static VendaDTO entityToDto(VendaEntity entity) {
		VendaDTO dto = new VendaDTO(
				entity.getCodVenda()
				, ClienteDTO.entityToDTO(entity.getCliente())
				, entity.getDataVenda()
				, entity.getValorVenda()
				, entity.getQtdeTotalPontosVendidos()
				, ItemMovimentadoCarrinhoDTO.historicoVendaToItemMovimentado(entity.getVendaProdutosList())
				, ParcelasDTO.listEntityToListDto(entity.getParcelas())
				, FormaPagamentoDTO.convertEntotyToDTO(entity.getFormaDePagamento())
				, entity.getValorDescontoVenda());//lista de parcelas
		
		dto.setUsuario(UserDTO.entityToDTO(entity.getUsuario()));
		
		return dto;
	}
	
	public static List<VendaDTO> listaVendaEntityToDto(List<VendaEntity> listaVendas) {

		return null;
	}
	
	public Long getCodVenda() {
		return codVenda;
	}
	public void setCodVenda(Long codVenda) {
		this.codVenda = codVenda;
	}
	public List<ItemMovimentadoCarrinhoDTO> getListaDeProdutos() {
		return listaDeProdutos;
	}
	public void setListaDeProdutos(List<ItemMovimentadoCarrinhoDTO> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
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

	public List<ParcelasDTO> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelasDTO> parcelas) {
		this.parcelas = parcelas;
	}

	public FormaPagamentoDTO getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamentoDTO formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getDescSituacaoPagamento() {
		return descSituacaoPagamento;
	}

	public void setDescSituacaoPagamento(String descSituacaoPagamento) {
		this.descSituacaoPagamento = descSituacaoPagamento;
	}

	public boolean isAcompanhamento222() {
		return acompanhamento222;
	}

	public void setAcompanhamento222(boolean acompanhamento222) {
		this.acompanhamento222 = acompanhamento222;
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

	public UserDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UserDTO usuario) {
		this.usuario = usuario;
	}

	
}
