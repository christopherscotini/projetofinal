package br.com.mkoffice.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.primefaces.event.FileUploadEvent;

import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.utils.exception.FormatReadXLSException;

public class UploadXLSTabelaProdutos {
	private static final String PRIMEIRA_LINHA = "primeiraLinha";
	private static final String PRIMEIRA_COLUNA = "primeiraColuna";

	public static List<CatalogoEntity> doUpload(FileUploadEvent event)  
            throws FileNotFoundException, IOException, BiffException {  
        String caminho = FacesContext.getCurrentInstance().getExternalContext()  
                .getRealPath("" + "\\WEB-INF\\" + event.getFile().getFileName());  
        byte[] conteudo = event.getFile().getContents();  
        FileOutputStream fos = new FileOutputStream(caminho);  
        fos.write(conteudo);  
        fos.close();	
    	List<CatalogoEntity> returnzz = lerXLS(caminho); 
    	new File(caminho).delete(); 
    	return returnzz;
}  
	
	private static Map<String, Integer> verificaPrimeiraColunaAndLinhaDoArquivo(
			Sheet sheet) {
		int linhasDoc = sheet.getRows();
		int limiteColunasVerificacao = sheet.getColumns();
		int colunasDoc = 6;
		Map<String, Integer> retorno = new HashMap<String, Integer>();

		int countColumn = 0;
		for (int linha = 0; linha < linhasDoc; linha++) {
			for (int coluna = 0; coluna < limiteColunasVerificacao; coluna++) {
				if (!sheet.getCell(coluna, linha).getContents().equals("")) {
					if (countColumn == 0) {
						retorno.put(PRIMEIRA_COLUNA, coluna);
						retorno.put(PRIMEIRA_LINHA, (linha + 1));
					}
					countColumn++;
				}
			}
			if (countColumn == colunasDoc) {
				break;
			}
		}

		return retorno;
	}

	public static List<CatalogoEntity> lerXLS(String caminhoArquivo) throws BiffException, IOException {
		List<CatalogoEntity> returnzz = new ArrayList<CatalogoEntity>();
			Workbook workbook = Workbook.getWorkbook(new File(caminhoArquivo));

			// Agora vamos indicar a abertura da primeira planilha e fazer uma
			// contagem para saber qual a quantidade total de linhas tem a
			// planilha:
			Sheet sheet = workbook.getSheet(0);
			Map<String, Integer> primeiraColunaAndLinhaDoArquivo = verificaPrimeiraColunaAndLinhaDoArquivo(sheet);
			int linhas = sheet.getRows();
			int primeiraColuna = primeiraColunaAndLinhaDoArquivo
					.get(PRIMEIRA_COLUNA);
			int primeiraLinha = primeiraColunaAndLinhaDoArquivo
					.get(PRIMEIRA_LINHA);

			// Agora vamos exibir uma mensagem para indicar o inicio do
			// processamento de leitura e iniciar um la�o for para varrer todas
			// as linhas da planilha j� selecionada:
			System.out.println("Iniciando a leitura da planilha XLS:");

			for (int i = primeiraLinha; i < linhas; i++) {
				try{
					CatalogoEntity e = new CatalogoEntity();
					
					String preco = sheet.getCell(primeiraColuna + 2, i).getContents().replace("R$", "");
					preco = preco.replace(",", ".").replaceAll(" ", "");
					e.setPontosPorUnidade(Integer.valueOf(MkmtsUtil.extrairNumerosString(preco.split("/")[1])));
					preco = preco.split("/")[0];
					
					String descProduto = sheet.getCell(primeiraColuna + 1, i).getContents().trim();
					e.setCodProduto(Long.valueOf(MkmtsUtil.extrairNumerosString(sheet.getCell(primeiraColuna + 0, i).getContents())));
					e.setDescProduto(descProduto);
					e.setPreco(new BigDecimal(preco));
					
					returnzz.add(e);
				}catch(NumberFormatException n){
					if(MkmtsUtil.isListNullOrEmpty(returnzz)){
						throw new FormatReadXLSException("A linha "+(i+1)+" est� vazia ou mal formatada, ela deve conter n�meros", n);
					}
				}
			}
			workbook.close();
			return returnzz;
	}
	
	
}
