package br.com.mkoffice.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import br.com.mkoffice.dto.ItemMovimentadoCarrinhoDTO;
import br.com.mkoffice.model.produto.CatalogoEntity;
import br.com.mkoffice.utils.exception.FormatReadXLSException;

public class UploadXLSPedido {
	private static final String PRIMEIRA_LINHA = "primeiraLinha";
	private static final String PRIMEIRA_COLUNA = "primeiraColuna";

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

	public static List<ItemMovimentadoCarrinhoDTO> lerXLS(String caminhoArquivo) throws BiffException, IOException {
		List<ItemMovimentadoCarrinhoDTO> returnzz = new ArrayList<ItemMovimentadoCarrinhoDTO>();
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
					ItemMovimentadoCarrinhoDTO dto = new ItemMovimentadoCarrinhoDTO();
					CatalogoEntity e = new CatalogoEntity();
					e.setCodProduto(Long.valueOf(sheet.getCell(primeiraColuna + 0, i).getContents()));
					e.setDescProduto(sheet.getCell(primeiraColuna + 1, i).getContents());
					e.setPreco(new BigDecimal(sheet.getCell(primeiraColuna + 2, i).getContents().replace(",", ".")));
					dto.setQtdeProdutoCarrinho(Integer.valueOf(sheet.getCell(primeiraColuna + 3, i).getContents().split("/")[0]));
					dto.setValorTotalProdutoCarrinho(new BigDecimal(sheet.getCell(primeiraColuna + 4, i).getContents().replace(",", ".")));
					dto.setSomaPontosProdutoCarrinho(Integer.valueOf(sheet.getCell(primeiraColuna + 5, i).getContents()));
					e.setPontosPorUnidade(dto.getSomaPontosProdutoCarrinho()/dto.getQtdeProdutoCarrinho());
					dto.setProduto(e);
					returnzz.add(dto);
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
