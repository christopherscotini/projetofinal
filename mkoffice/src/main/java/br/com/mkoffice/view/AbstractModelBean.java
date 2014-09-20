package br.com.mkoffice.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.CatalogoBO;
import br.com.mkoffice.business.bo.CategoriaBO;
import br.com.mkoffice.business.bo.ClienteBO;
import br.com.mkoffice.business.bo.ConsultoraBO;
import br.com.mkoffice.business.bo.DefaultConfigurationBO;
import br.com.mkoffice.business.bo.PermissaoBO;
import br.com.mkoffice.business.bo.UserBO;
import br.com.mkoffice.model.constants.PercentDescontoEnum;
import br.com.mkoffice.model.constants.StatusConsultoraEnum;
import br.com.mkoffice.security.LoginBean;
import br.com.mkoffice.view.controller.constants.GenderEnum;

public abstract class AbstractModelBean {

	@ManagedProperty(value = "#{loginBean}")
	protected LoginBean loginBean = null;
	
	@Inject
	protected CatalogoBO catalogoBO = null;

	@Inject
	protected CategoriaBO categoriaBO = null;

	@Inject
	protected ClienteBO clienteBO = null;
	
	@Inject
	protected ConsultoraBO consultoraBO = null;

	@Inject
	protected DefaultConfigurationBO defaultConfigurationBO = null;

//	@Inject
//	protected EstoqueBO estoqueBO = null;
//
//	@Inject
//	protected EstoqueHistoricoBO estoqueHistoricoBO = null;
//
//	@Inject
//	@ManagedProperty( value = "#{fluxoEstoqueBOImpl}")
//	protected FluxoEstoqueBO fluxoEstoqueBO = null;
//
//	@Inject
//	protected FormaPagamentoBO formaPagamentoBO = null;

//	@Inject
//	protected ParcelaBO parcelaBO = null;
//
//	@Inject
//	protected PedidoBO pedidoBO = null;

	@Inject
	protected PermissaoBO permissaoBO = null;

//	@Inject
//	protected SituacaoBO situacaoBO = null;
//	
//	@Inject
//	protected TipoAgendaBO tipoAgendaBO = null;

	@Inject
	protected UserBO userBO = null;
	
//	@Inject
//	protected VendaBO vendaBO = null;
	
	

	public abstract String iniciarTela();

	public abstract void limparCamposFiltro();

	public abstract String pesquisarFiltro();

//	public ScheduleController getScheduleBean() {
//		return (ScheduleController) FacesContext.getCurrentInstance()
//				.getExternalContext().getSessionMap().get("scheduleController");
//	}

	public List<PercentDescontoEnum> getCboPercentDesconto() {
		return new ArrayList<PercentDescontoEnum>(
				Arrays.asList(PercentDescontoEnum.values()));
	}

//	public List<FormaPagamentoEntity> getCboFormaPagamento() {
//		return formaPagamentoBO().listarTodos();
//	}

//	public List<SituacaoEntity> getSituacaoPagamentoTodos() {
//		return getSituacaoBO().listarTodos();
//	}

	public List<GenderEnum> getComboSexualidade() {
		return new ArrayList<GenderEnum>(Arrays.asList(GenderEnum.values()));
	}

	public List<StatusConsultoraEnum> getStatusConsultora() {
		return new ArrayList<StatusConsultoraEnum>(
				Arrays.asList(StatusConsultoraEnum.values()));
	}

	public List<SelectItem> getComboUf() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();
		retorno.add(new SelectItem("AC"));
		retorno.add(new SelectItem("AL"));
		retorno.add(new SelectItem("AM"));
		retorno.add(new SelectItem("AP"));
		retorno.add(new SelectItem("BA"));
		retorno.add(new SelectItem("CE"));
		retorno.add(new SelectItem("DF"));
		retorno.add(new SelectItem("ES"));
		retorno.add(new SelectItem("GO"));
		retorno.add(new SelectItem("MA"));
		retorno.add(new SelectItem("MG"));
		retorno.add(new SelectItem("MS"));
		retorno.add(new SelectItem("MT"));
		retorno.add(new SelectItem("PA"));
		retorno.add(new SelectItem("PB"));
		retorno.add(new SelectItem("PE"));
		retorno.add(new SelectItem("PI"));
		retorno.add(new SelectItem("PR"));
		retorno.add(new SelectItem("RJ"));
		retorno.add(new SelectItem("RN"));
		retorno.add(new SelectItem("RO"));
		retorno.add(new SelectItem("RR"));
		retorno.add(new SelectItem("RS"));
		retorno.add(new SelectItem("SC"));
		retorno.add(new SelectItem("SE"));
		retorno.add(new SelectItem("SP"));
		retorno.add(new SelectItem("TO"));
		return retorno;
	}
	
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
}
