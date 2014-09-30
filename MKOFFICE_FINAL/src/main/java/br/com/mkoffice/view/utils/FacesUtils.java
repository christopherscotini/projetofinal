package br.com.mkoffice.view.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;

import org.primefaces.event.FileUploadEvent;

import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.utils.UploadXLSPedido;
import br.com.mkoffice.view.constants.MessagesConstants;

public final class FacesUtils {

	private FacesUtils() {
		super();
	}

	public static void generateExcelResponse(String excelFileName) {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/vnd.ms-excel");
    	response.setHeader("Content-Disposition", "attachment;filename=" + excelFileName);

    	FacesContext.getCurrentInstance().responseComplete();
	}

	public static void generatePdfResponse(String pdfFileName) {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/pdf");
    	response.setHeader("Content-Disposition", "attachment;filename=" + pdfFileName);

    	FacesContext.getCurrentInstance().responseComplete();
	}
	
	 public static List<ItemMovimentadoCarrinhoDTO> doUpload(FileUploadEvent event)  
	            throws FileNotFoundException, IOException, BiffException {  
	        String caminho = FacesContext.getCurrentInstance().getExternalContext()  
	                .getRealPath("" + "\\WEB-INF\\" + event.getFile().getFileName());  
	        byte[] conteudo = event.getFile().getContents();  
	        FileOutputStream fos = new FileOutputStream(caminho);  
	        fos.write(conteudo);  
	        fos.close();	
        	List<ItemMovimentadoCarrinhoDTO> returnzz = UploadXLSPedido.lerXLS(caminho); 
        	new File(caminho).delete(); 
        	return returnzz;
    }  


	public static void addMessageInclusaoSucesso() {
		addInfoMessage(MessagesConstants.INCLUSAO_SUCESSO);
	}

	public static void addMessageAlteracaoSucesso() {
		addInfoMessage(MessagesConstants.ALTERACAO_SUCESSO);
	}
	
	public static void addMessageExclusaoSucesso() {
		addInfoMessage(MessagesConstants.EXCLUSAO_SUCESSO);
	}

	public static void addErrorMessage(String msg) {
		addMessage(FacesMessage.SEVERITY_ERROR, msg);
	}

	public static void addInfoMessage(String msg) {
		addMessage(FacesMessage.SEVERITY_INFO, msg);
	}

	private static void addMessage(FacesMessage.Severity severity, String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, msg));
	}
}
