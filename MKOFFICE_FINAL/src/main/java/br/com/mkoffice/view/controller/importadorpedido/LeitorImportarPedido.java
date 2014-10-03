package br.com.mkoffice.view.controller.importadorpedido;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.faces.context.FacesContext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.exceptions.FormatException;

public class LeitorImportarPedido {

	private static LeitorImportarPedido instance;
	
	public static LeitorImportarPedido getInstance(){
		if(instance == null){
			instance = new LeitorImportarPedido();
		}
		
		return instance;
	}
	
	private LeitorImportarPedido(){}
	
	

	public DadosPedido read(byte[] contents, String fileName) {
		String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("" + "\\WEB-INF\\"+fileName);
		DadosPedido dadosPedido = null;
		try {
			gerarArquivoTemp(contents, realPath);
			Document doc = Jsoup.parse(new File(realPath), "UTF-8", realPath);
			dadosPedido = LeitorImportarUtils.ler(doc);
		} catch (MalformedURLException e) {
			throw new FormatException(e.getMessage(), e);
		} catch (IOException e) {
			throw new FormatException(e.getMessage(), e);
		}
		
		File delete = new File(realPath);
		delete.delete();
		
		return dadosPedido;
	}  
	
	private void gerarArquivoTemp(byte[]contents, String path){
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(contents);  
			fos.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
    
	
}
