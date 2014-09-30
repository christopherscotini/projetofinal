package br.com.mkoffice.view.controller.menu.vendas.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.mkoffice.dto.ClienteDTO;
import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.dto.ParcelasDTO;
import br.com.mkoffice.model.admin.FormaPagamentoEntity;
import br.com.mkoffice.utils.MkmtsUtil;

public class VendaVO implements Serializable{

	private static final long serialVersionUID = -6857610049199890661L;

	private Long codVenda;
	private ClienteDTO cliente;
	private Date dataVenda;
	private BigDecimal valorVenda;
	private FormaPagamentoEntity formaDePagamento;
	private Integer qtdeTotalPontosVendidos;
	private List<ItemMovimentadoCarrinhoDTO>listaProdutos;
	private List<ParcelasDTO>parcelas;
	

	public VendaVO() {
		// TODO Auto-generated constructor stub
	}
	
	public VendaVO(Long codVenda, ClienteDTO cliente, Date dataVenda,
			BigDecimal valorVenda, FormaPagamentoEntity formaDePagamento,
			boolean possuiParcelas, Integer numeroParcelas,
			Integer qtdeTotalPontosVendidos, BigDecimal valorParcelas,
			List<ItemMovimentadoCarrinhoDTO> listaProdutos,
			List<ParcelasDTO>parcelas) {
		super();
		this.codVenda = codVenda;
		this.cliente = cliente;
		this.dataVenda = dataVenda;
		this.valorVenda = valorVenda;
		this.formaDePagamento = formaDePagamento;
		this.qtdeTotalPontosVendidos = qtdeTotalPontosVendidos;
		this.listaProdutos = listaProdutos;
		this.parcelas = parcelas;
	}

	public String getDataVendaFormatado() {
		return dataVenda == null ? "" : MkmtsUtil.converterDataString(dataVenda, "dd/MM/yyyy");
	}
	
	public Long getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(Long codVenda) {
		this.codVenda = codVenda;
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

	public FormaPagamentoEntity getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaPagamentoEntity formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public Integer getQtdeTotalPontosVendidos() {
		return qtdeTotalPontosVendidos;
	}

	public void setQtdeTotalPontosVendidos(Integer qtdeTotalPontosVendidos) {
		this.qtdeTotalPontosVendidos = qtdeTotalPontosVendidos;
	}

	public List<ItemMovimentadoCarrinhoDTO> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<ItemMovimentadoCarrinhoDTO> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<ParcelasDTO> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelasDTO> parcelas) {
		this.parcelas = parcelas;
	}
	
}
