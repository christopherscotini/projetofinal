package br.com.mkoffice.view.controller.menu.pedidos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import jxl.read.biff.BiffException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.mkoffice.dto.CatalogoDTO;
import br.com.mkoffice.dto.CategoriaDTO;
import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.dto.ParcelasDTO;
import br.com.mkoffice.dto.PedidoDTO;
import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.exceptions.FormatException;
import br.com.mkoffice.model.ParcelasEntity;
import br.com.mkoffice.model.admin.CategoriaEntity;
import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.CalculadoraUtils;
import br.com.mkoffice.utils.DecimalUtils;
import br.com.mkoffice.utils.MkmtsUtil;
import br.com.mkoffice.view.constants.SituacaoPagamentoEnum;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.controller.importadorpedido.DadosPedido;
import br.com.mkoffice.view.controller.importadorpedido.LeitorImportarPedido;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class ImportarPedidoBean extends AbstractModelBean implements
		Serializable {

	private static final long serialVersionUID = 2141967316958457526L;

	private final String TELA_IMPORTAR_PEDIDO = "/content/m-pedidos/sm-importarpedido/importarPedido";
	private final String TELA_DADOS_PEDIDO = "/content/m-pedidos/sm-importarpedido/dadosPedidoImportado";
	private final String TELA_CONCLUIR_PEDIDO = "/content/m-pedidos/sm-importarpedido/concluirPedidoImportado";
	private final String TELA_ADD_CAT_PRODS_PEDIDO = "/content/m-pedidos/sm-importarpedido/adicionarCategoriaProdutosPedidoImportar";

	private boolean faltouCategoria;

	private PedidoDTO pedidoDTO;
	private UploadedFile fileUpload;
	private List<CategoriaDTO> listaCategorias;
	private List<CatalogoDTO> listaProdutosEmCatalogo;
	private DadosPedido pedidoImportado;
	private List<ItemMovimentadoCarrinhoDTO> listaProdutosInclusosPedido;

	private Integer totalPontosVendidos;
	private BigDecimal totalAPagar;
	private boolean habilitaPainelRevisaoPedido;
	

	private List<ParcelasEntity> listaParcelamento;
	private List<SelectItem> listaParcelamentoSelectItem;
	private Integer lblNumeroProdutosPedido;
	private Integer lblNumeroItensPedido;
	private Integer numParcelasPagamento;
	private Date dtVctoPrimeiraParcela;
	

	@Override
	public String iniciarTela() {
		limparCamposFiltro();
		return TELA_IMPORTAR_PEDIDO;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		try {
			pedidoImportado = LeitorImportarPedido.getInstance().read(event.getFile().getContents(), event.getFile().getFileName());
			System.out.println("NUMERO PEDIDO: "+pedidoImportado.getNumeroPedido());
			listaProdutosInclusosPedido = pedidoImportado.getProdutos();
			habilitaPainelRevisaoPedido = true;
			FacesUtils.addInfoMessage("Arquivo " + event.getFile().getFileName()+ " carregado com sucesso.\nVerifique se a lista de produtos esta completa.");
			faltouCategoria = false;
		}catch(FormatException f){
			FacesUtils.addInfoMessage(f.getMessage());
		}

	}
	
	public String navegarAddCategoriaProdutoFaltanteCatalogo() {// melhorar: criar uma lista com
											// produtos nao existentes em
											// catalogo e passar como parametro
											// quando for persistir o PEDIDO
		pedidoDTO = new PedidoDTO();
		pedidoDTO.setUsuario(getLoginBean().getUsuario());
		pedidoDTO.setListaProdutosComprados(listaProdutosInclusosPedido);
		pedidoDTO.setArrayIDsProdutos(new ArrayList<String>());
		for (int i = 0; i < pedidoDTO.getListaProdutosComprados().size(); i++) {
			pedidoDTO.getArrayIDsProdutos().add(pedidoDTO.getListaProdutosComprados().get(i).getProduto().getCodProduto().toString());
		}
		
		final CategoriaEntity CATEGORIA_OUTROS = Adapter.dtoToEntity(categoriaBO.buscarPeloCodigo(15L));
		
		if (!faltouCategoria) {
			List<CatalogoDTO> existentes = catalogoBO.buscarItensExistentesNoCatalogo(pedidoDTO.getArrayIDsProdutos());
			
			for (int i = 0; i < pedidoDTO.getListaProdutosComprados().size(); i++) {
				int count = 0;
				int pos = 0;
				
				for (int j = 0; j < existentes.size(); j++) {
					if (pedidoDTO.getListaProdutosComprados()
							.get(i)
							.getProduto()
							.getCodProduto()
							.equals(existentes.get(j).getCodProduto())) {
						pos = j;
						break;
					}
					count++;
				}
				
				// Nï¿½O ESTA CONTIDO NO CATALOGO
				if (count == existentes.size()) {
					// CATEGORIA = OUTROS
					pedidoDTO.getListaProdutosComprados().get(i).getProduto().setCodCategoria(CATEGORIA_OUTROS);
					pedidoDTO.getListaProdutosComprados().get(i).setFaltaNoCatalogo(true);
					faltouCategoria = true;
				} else {
					// CATEGORIA = OUTROS
					pedidoDTO.getListaProdutosComprados().get(i).getProduto().setCodCategoria(existentes.get(pos).getCodCategoria());
				}
				
			}
			
			if (faltouCategoria) {
				listaCategorias = categoriaBO.listarTodos();
				return TELA_ADD_CAT_PRODS_PEDIDO;
			}
			
		}
		
		return navegarDadosPedido();
	}

	public String navegarDadosPedido() {
		pedidoDTO.setDtPedido(new Date());
		pedidoDTO.setValorFrete(BigDecimal.ZERO);
		pedidoDTO.setValorBonusUtilizado(BigDecimal.ZERO);
		pedidoDTO.setPontosTotalPedido(CalculadoraUtils.somarTotalPontosPedidoVenda(pedidoDTO.getListaProdutosComprados()));
		pedidoDTO.setValorTotalEmProdutos(CalculadoraUtils.valorTotalProdutosPedido(listaProdutosInclusosPedido));
		pedidoDTO.setPorcDesconto(CalculadoraUtils.calcularPorcentagemDescontoPedido(pedidoDTO.getValorTotalEmProdutos()));
		pedidoDTO.setValorTotalAtacado(CalculadoraUtils.calcularValorTotalAtacado(pedidoDTO.getValorTotalEmProdutos(),pedidoDTO.getPorcDesconto()));
		pedidoDTO.setValorFinalTotalPago(CalculadoraUtils.calcularValorFinalTotalPago(pedidoDTO.getValorTotalAtacado(),pedidoDTO.getValorFrete(), pedidoDTO.getValorBonusUtilizado()));
		pedidoDTO.setLucroTotal(CalculadoraUtils.calcularLucroTotalPedido(pedidoDTO.getValorTotalAtacado(), pedidoDTO.getValorTotalEmProdutos()));
		lblNumeroProdutosPedido = getListaProdutosInclusosPedido().size();
		lblNumeroItensPedido = 0;
		for (int i = 0; i < getListaProdutosInclusosPedido().size(); i++) {
			lblNumeroItensPedido+= getListaProdutosInclusosPedido().get(i).getQtdeProdutoCarrinho();
		}
		
		
		return TELA_DADOS_PEDIDO;
	}
	
	public String navegarConcluirPedidoImportado(){
		try {
			if(validarFormDadosPedido()){
				pedidoBO.existePedido(pedidoDTO);
			}else{
				FacesUtils.addErrorMessage("Insira o número do pedido");
				return "";
			}
		} catch (BusinessException b) {
			FacesUtils.addErrorMessage(b.getMessage());
			return "";
		}
		
		numParcelasPagamento = null;
		dtVctoPrimeiraParcela = new Date();
		
		pedidoDTO.setFormaDePagamento(getCboFormaPagamento().get(0));
		onChangeFormaPagamento();
		
		return TELA_CONCLUIR_PEDIDO;
	}
	
	private boolean validarFormDadosPedido() {
		if(pedidoDTO.getCodPedido() == null){
			return false;
		}else if(pedidoDTO.getCodPedido().equals(0L)){
			return false;
		}
		return true;
	}

	public String executePedido() {

		pedidoDTO.setParcelas(prepararParcelas());
		
		try {
			FacesUtils.addInfoMessage("Pedido Nï¿½ "
					+ pedidoBO.efetuarPedidoImportado(pedidoDTO).getCodPedido()
					+ " foi realizado com sucesso.");
		} catch (BusinessException b) {
			FacesUtils.addErrorMessage(b.getMessage());
			return "";
		}

		return iniciarTela();
	}

	public void onChangePercDesconto() {
		pedidoDTO.setValorTotalAtacado(CalculadoraUtils
				.calcularValorTotalAtacado(pedidoDTO.getValorTotalEmProdutos(),
						pedidoDTO.getPorcDesconto()));
		pedidoDTO.setValorFinalTotalPago(CalculadoraUtils
				.calcularValorFinalTotalPago(pedidoDTO.getValorTotalAtacado(),
						pedidoDTO.getValorFrete(),
						pedidoDTO.getValorBonusUtilizado()));
		pedidoDTO.setLucroTotal(CalculadoraUtils.calcularLucroTotalPedido(
				pedidoDTO.getValorTotalAtacado(),
				pedidoDTO.getValorTotalEmProdutos()));
	}

	public void onBlurValorFreteAndBonusUtilizado() {
		pedidoDTO.setValorFinalTotalPago(CalculadoraUtils.calcularValorFinalTotalPago(pedidoDTO.getValorTotalAtacado(),pedidoDTO.getValorFrete(),pedidoDTO.getValorBonusUtilizado()));
		pedidoDTO.setLucroTotal(CalculadoraUtils.calcularLucroTotalPedido(pedidoDTO.getValorFinalTotalPago(), pedidoDTO.getValorTotalEmProdutos()));
	}

	private CategoriaDTO catAnterior;
	public void onEditCell(Integer index, Long codCat) {
		try {
			if (codCat == catAnterior.getCodCategoria()) {
				listaProdutosInclusosPedido.get(index).getProduto()
						.setCodCategoria(Adapter.dtoToEntity(catAnterior));
			} else {
				catAnterior = categoriaBO.buscarPeloCodigo(codCat);
				listaProdutosInclusosPedido.get(index).getProduto()
						.setCodCategoria(Adapter.dtoToEntity(catAnterior));
			}
		} catch (Exception e) {
			catAnterior = categoriaBO.buscarPeloCodigo(codCat);
			listaProdutosInclusosPedido.get(index).getProduto()
					.setCodCategoria(Adapter.dtoToEntity(catAnterior));
		}
	}

	public void onChangeFormaPagamento(){
		listaParcelamento = new ArrayList<ParcelasEntity>();
		listaParcelamentoSelectItem = new ArrayList<SelectItem>();
		double preco = pedidoDTO.getValorFinalTotalPago().doubleValue();
		DecimalFormat decimalFormat = new DecimalFormat();   
		decimalFormat.setMaximumFractionDigits(2);
		
		for (int i = 0; i < pedidoDTO.getFormaDePagamento().getNumeroParcelas(); i++) {//personalizar a qtde de parcelas
			
			String lblAVista = "R$ "+DecimalUtils.format(BigDecimal.valueOf((preco/(i+1))))+" ï¿½ vista";
			String lbl = (i+1)+"X sem juros R$ "+DecimalUtils.format(BigDecimal.valueOf((preco/(i+1))));
			
			ParcelasEntity parcela = new ParcelasEntity(null, i== 0 ? lblAVista : lbl, (i+1), BigDecimal.valueOf((preco/(i+1))), null, null, i==0?BigDecimal.valueOf((preco/(i+1))):null);
			
			listaParcelamento.add(parcela);
			listaParcelamentoSelectItem.add(new SelectItem(i, i== 0 ? lblAVista : lbl));
		}
	}

	private List<ParcelasDTO> prepararParcelas(){
		List<ParcelasDTO>returnzz = new ArrayList<ParcelasDTO>();
		ParcelasEntity parcelaSelecionada = listaParcelamento.get(numParcelasPagamento);
		for (int i = 0; i < numParcelasPagamento+1; i++) {
			SituacaoEntity situacaoPagamento = new SituacaoEntity();
			List<SituacaoEntity>situacaoEntities = getSituacaoPagamentoTodos();
			for (int j = 0; j < situacaoEntities.size(); j++) {
				if(i==0){
					if(situacaoEntities.get(j).getDescSituacao().equalsIgnoreCase(SituacaoPagamentoEnum.PAGO.getLabel())){
						situacaoPagamento = situacaoEntities.get(j);
						break;
					}
				}else{
					if(situacaoEntities.get(j).getDescSituacao().equalsIgnoreCase(SituacaoPagamentoEnum.PENDENTE.getLabel())){
						situacaoPagamento = situacaoEntities.get(j);
						break;
					}
				}
			}
			Calendar c = new GregorianCalendar(MkmtsUtil.extrairAnoDataInteiro(dtVctoPrimeiraParcela), MkmtsUtil.extrairMesDataInteiro(dtVctoPrimeiraParcela), MkmtsUtil.extrairDiaDataInteiro(dtVctoPrimeiraParcela));
			c.set(Calendar.MONTH, (MkmtsUtil.extrairMesDataInteiro(dtVctoPrimeiraParcela)-1) + i);
			ParcelasDTO entity = new ParcelasDTO(null, null, null, c.getTime(), (i+1)+"/"+(numParcelasPagamento+1), (i+1), parcelaSelecionada.getValorParcela(), situacaoPagamento , i==0?c.getTime():null, i==0?parcelaSelecionada.getValorParcela():BigDecimal.ZERO);
			entity.setUsuario(getLoginBean().getUsuario());
			returnzz.add(entity);
		}
		
		return returnzz;
	}
	
	@Override
	public String pesquisarFiltro() {
		return TELA_IMPORTAR_PEDIDO;
	}
	
	// aï¿½ï¿½es limpar algo da pagina
	@Override
	public void limparCamposFiltro() {
		pedidoDTO = new PedidoDTO();
		setHabilitaPainelRevisaoPedido(false);
		listaProdutosInclusosPedido = null;
		faltouCategoria = false;
	}
	
	public void limparProdutosSelecionados() {
		listaProdutosInclusosPedido = null;
	}
	
	// aï¿½ï¿½es retorno de pagina
	public String navegarVoltarAddCategoriaPedido() {
		faltouCategoria = false;
		return TELA_IMPORTAR_PEDIDO;
	}

	public String navegarVoltarDadosPedidoImportado() {
		if (faltouCategoria) {
			faltouCategoria = false;
			return TELA_ADD_CAT_PRODS_PEDIDO;
		} else {
			return TELA_DADOS_PEDIDO;
		}
	}

	// GETTERS AND SETTERS
	public List<CatalogoDTO> getListaProdutosEmCatalogo() {
		return listaProdutosEmCatalogo;
	}

	public void setListaProdutosEmCatalogo(
			List<CatalogoDTO> listaProdutosEmCatalogo) {
		this.listaProdutosEmCatalogo = listaProdutosEmCatalogo;
	}

	public List<ItemMovimentadoCarrinhoDTO> getListaProdutosInclusosPedido() {
		return listaProdutosInclusosPedido;
	}

	public void setListaProdutosInclusosPedido(
			List<ItemMovimentadoCarrinhoDTO> listaProdutosInclusosPedido) {
		this.listaProdutosInclusosPedido = listaProdutosInclusosPedido;
	}

	public Integer getTotalPontosVendidos() {
		return totalPontosVendidos;
	}

	public void setTotalPontosVendidos(Integer totalPontosVendidos) {
		this.totalPontosVendidos = totalPontosVendidos;
	}

	public BigDecimal getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(BigDecimal totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	public PedidoDTO getPedidoDTO() {
		return pedidoDTO;
	}

	public void setPedidoDTO(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}

	public boolean isHabilitaPainelRevisaoPedido() {
		return habilitaPainelRevisaoPedido;
	}

	public void setHabilitaPainelRevisaoPedido(
			boolean habilitaPainelRevisaoPedido) {
		this.habilitaPainelRevisaoPedido = habilitaPainelRevisaoPedido;
	}

	public boolean isFaltouCategoria() {
		return faltouCategoria;
	}

	public void setFaltouCategoria(boolean faltouCategoria) {
		this.faltouCategoria = faltouCategoria;
	}

	public List<CategoriaDTO> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<CategoriaDTO> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public UploadedFile getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(UploadedFile fileUpload) {
		this.fileUpload = fileUpload;
	}

	public Integer getLblNumeroProdutosPedido() {
		return lblNumeroProdutosPedido;
	}

	public void setLblNumeroProdutosPedido(Integer lblNumeroProdutosPedido) {
		this.lblNumeroProdutosPedido = lblNumeroProdutosPedido;
	}

	public Integer getLblNumeroItensPedido() {
		return lblNumeroItensPedido;
	}

	public void setLblNumeroItensPedido(Integer lblNumeroItensPedido) {
		this.lblNumeroItensPedido = lblNumeroItensPedido;
	}

	public List<ParcelasEntity> getListaParcelamento() {
		return listaParcelamento;
	}

	public void setListaParcelamento(List<ParcelasEntity> listaParcelamento) {
		this.listaParcelamento = listaParcelamento;
	}

	public List<SelectItem> getListaParcelamentoSelectItem() {
		return listaParcelamentoSelectItem;
	}

	public void setListaParcelamentoSelectItem(
			List<SelectItem> listaParcelamentoSelectItem) {
		this.listaParcelamentoSelectItem = listaParcelamentoSelectItem;
	}

	public Integer getNumParcelasPagamento() {
		return numParcelasPagamento;
	}

	public void setNumParcelasPagamento(Integer numParcelasPagamento) {
		this.numParcelasPagamento = numParcelasPagamento;
	}

	public Date getDtVctoPrimeiraParcela() {
		return dtVctoPrimeiraParcela;
	}

	public void setDtVctoPrimeiraParcela(Date dtVctoPrimeiraParcela) {
		this.dtVctoPrimeiraParcela = dtVctoPrimeiraParcela;
	}

	public DadosPedido getPedidoImportado() {
		return pedidoImportado;
	}

	public void setPedidoImportado(DadosPedido pedidoImportado) {
		this.pedidoImportado = pedidoImportado;
	}

}
