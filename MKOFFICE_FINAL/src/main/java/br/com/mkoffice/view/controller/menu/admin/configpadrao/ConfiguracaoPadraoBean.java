package br.com.mkoffice.view.controller.menu.admin.configpadrao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mkoffice.exceptions.BusinessException;
import br.com.mkoffice.model.admin.DefaultConfigurationEntity;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class ConfiguracaoPadraoBean extends AbstractModelBean{

	private final String TELA_CONFIGURACAO_TELA_LOGIN = "/content/m-admin/config-padrao/configPadraoTelaLogin.xhtml";
	
	private DefaultConfigurationEntity configuration;
	private List<String> images; 
	
	
	public ConfiguracaoPadraoBean() {
	
	}
	
	@PostConstruct
	public void init(){
		configuration = defaultConfigurationBO.carregarConfiguracao();
	}
	
	@Override
	public String iniciarTela() {return null;}
	
	@Override
	public void limparCamposFiltro() {}

	@Override
	public String pesquisarFiltro() {return null;}

	public String iniciarTelaConfigLogin() {
		configuration = defaultConfigurationBO.carregarConfiguracao();
		images = new ArrayList<String>();
		images.add("ferramentas_suas ferramentas_fundos de tela_ credite!.jpg");
		images.add("ferramentas_suas ferramentas_fundos de tela_ Entusiasmo.jpg");
		images.add("ferramentas_suas ferramentas_fundos de tela_ Sinta-se especial!.jpg");
		images.add("ferramentas_suas ferramentas_fundos de tela_ sonhe!.jpg");
		images.add("ferramentas_suas ferramentas_fundos de tela_ Ultrapasse seus limites!.jpg");
		
		return TELA_CONFIGURACAO_TELA_LOGIN;
	}
	
	public void atualizarImgFundoLogin(){
		try{
			defaultConfigurationBO.atualizarConfiguracoes(configuration);
			FacesUtils.addInfoMessage("Imagem alterada com sucesso.");
		}catch(BusinessException b){
			FacesUtils.addErrorMessage("Erro ao alterar a imagem.");
		}
	}
	
	
	public DefaultConfigurationEntity getConfiguration() {
		return configuration;
	}

	public void setConfiguration(DefaultConfigurationEntity configuration) {
		this.configuration = configuration;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
