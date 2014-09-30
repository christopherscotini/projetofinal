package br.com.mkoffice.view.controller.menu.admin.cargacatalogo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import jxl.read.biff.BiffException;

import org.primefaces.event.FileUploadEvent;

import br.com.mkoffice.dto.CategoriaDTO;
import br.com.mkoffice.exceptions.FormatException;
import br.com.mkoffice.model.admin.CategoriaEntity;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.utils.Adapter;
import br.com.mkoffice.utils.UploadXLSTabelaProdutos;
import br.com.mkoffice.view.controller.AbstractModelBean;
import br.com.mkoffice.view.utils.FacesUtils;

@ManagedBean
@SessionScoped
public class CargaCatalogoBean extends AbstractModelBean{

	
	private List<CatalogoEntity>listaProdutosInclusos;
	private List<CategoriaEntity>comboCategorias;
	private CategoriaEntity categoriaSelecionada;
	
	@Override
	public void limparCamposFiltro() {
	}
	
	@Override
	public String pesquisarFiltro() {
		return null;
	}
	
	@Override
	public String iniciarTela(){
		comboCategorias = new ArrayList<CategoriaEntity>();
		List<CategoriaDTO> listaCategoriaDTOs = categoriaBO.listarTodos();
		for (int i = 0; i < listaCategoriaDTOs.size(); i++) {
			getComboCategorias().add(Adapter.dtoToEntity(listaCategoriaDTOs.get(i)));
		}
		categoriaSelecionada = getComboCategorias().get(0);
		
		return "/content/cargaCatalogo/importarCatalogo.xhtml";
	}
	
	public String carregarBanco(){
		for (int i = 0; i < listaProdutosInclusos.size(); i++) {
			listaProdutosInclusos.get(i).setCodCategoria(categoriaSelecionada);
		}
		
		if(catalogoBO.carregaPlanilhaCatalogoAtualizado(listaProdutosInclusos)){
			FacesUtils.addInfoMessage("Catalogo Atualizado com sucesso.");
		}else
			FacesUtils.addErrorMessage("Erro ao atualizarCatalogo.");
		
		return "/index";
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			listaProdutosInclusos = UploadXLSTabelaProdutos.doUpload(event);
			FacesUtils
					.addInfoMessage("Arquivo " + event.getFile().getFileName()
							+ " carregado com sucesso.\nVerifique se a lista de produtos esta completa.");

		} catch (FileNotFoundException e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		} catch (BiffException e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}catch(FormatException f){
			FacesUtils.addInfoMessage(f.getMessage());
		}

	}
	
	
	public List<CategoriaEntity> getComboCategorias() {
		return comboCategorias;
	}
	public List<CatalogoEntity> getListaProdutosInclusos() {
		return listaProdutosInclusos;
	}

	public void setListaProdutosInclusos(List<CatalogoEntity> listaProdutosInclusos) {
		this.listaProdutosInclusos = listaProdutosInclusos;
	}

	public CategoriaEntity getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(CategoriaEntity categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public void setComboCategorias(List<CategoriaEntity> comboCategorias) {
		this.comboCategorias = comboCategorias;
	}

}
