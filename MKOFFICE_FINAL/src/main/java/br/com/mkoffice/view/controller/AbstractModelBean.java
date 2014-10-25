package br.com.mkoffice.view.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.mkoffice.business.bo.AgendaBO;
import br.com.mkoffice.business.bo.CatalogoBO;
import br.com.mkoffice.business.bo.CategoriaBO;
import br.com.mkoffice.business.bo.ClienteBO;
import br.com.mkoffice.business.bo.ConsultoraBO;
import br.com.mkoffice.business.bo.ContasPagarBO;
import br.com.mkoffice.business.bo.ContasReceberBO;
import br.com.mkoffice.business.bo.DefaultConfigurationBO;
import br.com.mkoffice.business.bo.EstoqueBO;
import br.com.mkoffice.business.bo.EstoqueHistoricoBO;
import br.com.mkoffice.business.bo.FluxoEstoqueBO;
import br.com.mkoffice.business.bo.FormaPagamentoBO;
import br.com.mkoffice.business.bo.ParcelaBO;
import br.com.mkoffice.business.bo.PedidoBO;
import br.com.mkoffice.business.bo.PermissaoBO;
import br.com.mkoffice.business.bo.ReportBO;
import br.com.mkoffice.business.bo.SituacaoBO;
import br.com.mkoffice.business.bo.TipoAgendaBO;
import br.com.mkoffice.business.bo.UserBO;
import br.com.mkoffice.business.bo.VendaBO;
import br.com.mkoffice.model.admin.FluxoEstoqueEntity;
import br.com.mkoffice.model.admin.FormaPagamentoEntity;
import br.com.mkoffice.model.admin.SituacaoEntity;
import br.com.mkoffice.model.constants.PercentDescontoEnum;
import br.com.mkoffice.model.constants.StatusConsultoraEnum;
import br.com.mkoffice.security.LoginBean;
import br.com.mkoffice.view.constants.EstadoEnum;
import br.com.mkoffice.view.constants.GenderEnum;

public abstract class AbstractModelBean {

	@ManagedProperty(value = "#{loginBean}")
	protected LoginBean loginBean = null;

	@Inject
	protected AgendaBO agendaBO = null;
	
	
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

	@Inject
	protected EstoqueBO estoqueBO = null;

	@Inject
	protected EstoqueHistoricoBO estoqueHistoricoBO = null;

	@Inject
	protected FluxoEstoqueBO fluxoEstoqueBO = null;

	@Inject
	protected FormaPagamentoBO formaPagamentoBO = null;

	@Inject
	protected ParcelaBO parcelaBO = null;

	@Inject
	protected PedidoBO pedidoBO = null;

	@Inject
	protected PermissaoBO permissaoBO = null;

	@Inject
	protected SituacaoBO situacaoBO = null;
	
	@Inject
	protected TipoAgendaBO tipoAgendaBO = null;

	@Inject
	protected UserBO userBO = null;
	
	@Inject
	protected VendaBO vendaBO = null;
	 
	@Inject
	protected ReportBO reportBO = null;

	@Inject
	protected ContasReceberBO contasReceberBO = null;
	
	@Inject
	protected ContasPagarBO contasPagarBO = null;
	
	public static String getMsgs(String messageId) {    
	        FacesContext facesContext = FacesContext.getCurrentInstance();    
	        String msg = "";    
	        Locale locale = facesContext.getViewRoot().getLocale();    
	        ResourceBundle bundle = ResourceBundle.getBundle("br.com.mkoffice.view.bundle.Messages", locale);    
	        try {    
	            msg = bundle.getString(messageId);    
	        } catch (Exception e) {    
	        }    
	        return msg;    
	    }  

	public abstract String iniciarTela();

	public abstract void limparCamposFiltro();

	public abstract String pesquisarFiltro();

	public ScheduleController getScheduleBean() {
		return (ScheduleController) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("scheduleController");
	}

	public List<PercentDescontoEnum> getCboPercentDesconto() {
		return new ArrayList<PercentDescontoEnum>(
				Arrays.asList(PercentDescontoEnum.values()));
	}

	public List<FormaPagamentoEntity> getCboFormaPagamento() {
		return formaPagamentoBO.listarTodos();
	}

	public List<FluxoEstoqueEntity> getTipoFluxoEstoque() {
		return fluxoEstoqueBO.listarTodos();
	}

	public List<SituacaoEntity> getSituacaoPagamentoTodos() {
		return situacaoBO.listarTodos();
	}

	public List<GenderEnum> getComboSexualidade() {
		return new ArrayList<GenderEnum>(Arrays.asList(GenderEnum.values()));
	}

	public List<StatusConsultoraEnum> getStatusConsultora() {
		return new ArrayList<StatusConsultoraEnum>(
				Arrays.asList(StatusConsultoraEnum.values()));
	}

	public List<EstadoEnum> getComboUf() {
		return new ArrayList<EstadoEnum>(
				Arrays.asList(EstadoEnum.values()));
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

}
