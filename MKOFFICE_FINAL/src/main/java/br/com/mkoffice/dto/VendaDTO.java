/**
 * 
 */
package br.com.mkoffice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.model.admin.FormaPagamentoEntity;
import br.com.mkoffice.model.admin.UserEntity;

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
	private FormaPagamentoEntity formaPagamento;
	private UserEntity usuario;
	
	private String descSituacaoPagamento;
	private boolean acompanhamento222;
	
	public VendaDTO() {

	}
	
	public VendaDTO(Long codVenda, ClienteDTO cliente, Date dataVenda,
			BigDecimal valorVenda, Integer qtdeTotalPontosVendidos,
			List<ItemMovimentadoCarrinhoDTO> listaDeProdutos,
		List<ParcelasDTO> parcelas, FormaPagamentoEntity formaPagamento, BigDecimal valorLucroVenda){
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

	public FormaPagamentoEntity getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamentoEntity formaPagamento) {
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

	public UserEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UserEntity usuario) {
		this.usuario = usuario;
	}
	
}
