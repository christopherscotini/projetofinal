package br.com.mkoffice.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author ffranco
 * 
 *         Para alterar o gabarito para este comentário do tipo gerado vá para Janela&gt;Preferências&gt;Java&gt;Geração
 *         de Códigos&gt;Código e Comentários
 */
public final class MkmtsUtil {

	static final String DEFAULT_INPUT_NUMMER_DECIMAL_SEPARATOR = ",";

	static final String DEFAULT_INPUT_NUMBER_GROUP_SEPARATOR = ".";

	static final boolean DEFAULT_INPUT_NUMBER_SELECT_ALLTEXT = true;
	
    static final Locale PT_BR = new Locale("pt", "BR");
    
    static final String DECIMAL_PATTERN = "#,##0.00";

	private MkmtsUtil() {
		// Classes de utilidades não devem possuir um construtor público.
	}

	public static String formatarCampoDeValorMonetario(Double valorini) {

		return formatNumber(valorini, 2, ",", true);

	}

	public static String formatNumber(double value, int decimals, String decimalSeparator, boolean showGroups) {

		String valueCopy = new DecimalFormat("0.00").format(value);
		valueCopy = replaceAll(valueCopy, ".", decimalSeparator);
		return formatNumber(valueCopy, decimals, decimalSeparator, showGroups);
	}

	public static String formatNumber(String value, int decimals, String decimalSeparator, boolean showGroups) {

		String ret = value;
		String integerNumbers;
		String decimalNumbers;
		int position = 0;

		if (Long.parseLong(replaceAll(replaceAll(ret, ".", ""), ",", "")) == 0) {
			if (decimals == 0) {
				return "0";
			} else {
				return "0" + decimalSeparator + preencherZerosADireita("", decimals);
			}
		}

		ret = replaceAll(ret, ".", "");
		if (decimals == 0) {
			return ret;
		}

		if (ret.indexOf(decimalSeparator) == -1) {
			integerNumbers = ret.substring(0, ret.length());
			decimalNumbers = preencherZerosADireita("", decimals);
		} else {
			integerNumbers = ret.substring(0, ret.indexOf(decimalSeparator));
			decimalNumbers = preencherZerosADireita(ret.substring(ret.indexOf(decimalSeparator) + 1, ret.length()),
							decimals).substring(0, decimals);
		}
		ret = integerNumbers + decimalSeparator + decimalNumbers;

		// ret = roundNumber(ret, decimals, decimalSeparator);

		if (ret.indexOf(decimalSeparator) == -1) {
			integerNumbers = ret.substring(0, ret.length());
			decimalNumbers = preencherZerosADireita("", decimals);
		} else {
			integerNumbers = ret.substring(0, ret.indexOf(decimalSeparator));
			if (integerNumbers.equals("")) {
				integerNumbers = "0";
			}

			decimalNumbers = preencherZerosADireita(ret.substring(ret.indexOf(decimalSeparator) + 1, ret.length()),
							decimals);
		}

		int count = 0;
		for (position = integerNumbers.length(); position > 0; position--) {
			if (count == 3) {
				integerNumbers = integerNumbers.substring(0, position) + DEFAULT_INPUT_NUMBER_GROUP_SEPARATOR
								+ integerNumbers.substring(position, integerNumbers.length());
				count = 1;
				position--;
			}
			count += 1;
		}
		ret = integerNumbers + decimalSeparator + decimalNumbers;

		// Complets the String
		// if (showGroups) {
		//
		// }

		return ret;
	}

	/*
	 * completa um valor com zeros a Direita até completar o tamanho máximo
	 */
	public static String preencherZerosADireita(String valor, int tamanhoMaximo) {
		return insereCaracterADireita(valor, "0", tamanhoMaximo);
	}

	public static String retirarZerosAEsquerda(String valor) {

		StringBuffer recebeValor = new StringBuffer(valor);

		while (recebeValor.toString().startsWith("0")) {

			String remover = "0";

			int pos = recebeValor.indexOf(remover);

			recebeValor.delete(pos, pos + remover.length());

		}

		return recebeValor.toString();
	}

	/**
	 * Preenche a string com o caracter passado
	 * 
	 * @param str
	 *            String a ser modificada
	 * @param caracter
	 *            caracer a ser apendado na string
	 * @param tamanho
	 *            maximo da string apos a insercao
	 * @return string preenchida com os caracters out/2010
	 */
	public static String insereCaracterADireita(String str, String caracter, int tamanho) {
		StringBuilder sb = new StringBuilder(str);
		for (int i = 0; i < tamanho - str.length(); i++) {
			sb.append(caracter);
		}
		return sb.toString();
	}

	/* Preenche o campo passado com 0 a esquerda até o tamanho maximo. */
	public static String preencherZerosAEsquerda(String campo, int tamanhoMaximo) {
		StringBuilder sb = new StringBuilder(addZero(campo, tamanhoMaximo));
		return sb.toString();

	}

	public static String replaceAll(String sourceText, String find, String replacement) {
		String value = sourceText;
		while (value.indexOf(find) != -1) {
			value = value.replace(find, replacement);
		}
		return value;
	}

	private static String addZero(String vr, int size) {
		StringBuilder resultado = new StringBuilder();
		int vrLength = vr.length();
		if (vrLength <= size) {
			int dif = size - vrLength;
			for (int i = 0; i < dif; i++) {
				resultado.append("0");
			}
			return resultado.append(vr).toString();
		} else {
			return vr;
			// throw new IllegalArgumentException("Valor maior que o intervalo informado.");
		}
	}

	public static String verificaStringNula(String str) {
		if (str == null) {
			return "";
		} else {
			return StringUtil.trim(str, ' ');
		}

	}
	
	public static Integer verificaStringNulaInteger(String str) {
		if (str == null || "".equals(str)) {
			return 0;
		} else {
			return Integer.valueOf(StringUtil.trim(str, ' '));
		}
	}
	
	public static Long verificaStringNulaLong(String str) {
		if (str == null || "".equals(str)) {
			return new Long(0);
		} else {
			return Long.valueOf(StringUtil.trim(str, ' '));
		}
	}
	
	public static Long verificaStringCpfCnpjToLong(String str) {
		if (str == null || "".equals(str)) {
			return new Long(0);
		} else {
			str = str.replace(".", "");
			str = str.replace("-", "");
			return Long.valueOf(StringUtil.trim(str, ' '));
		}
	}

	public static int verificaIntegerNulo(Integer nro) {
		if (nro == null) {
			return 0;
		} else {
			return nro.intValue();
		}

	}

	public static BigDecimal verificaBigDecimalNulo(BigDecimal nro) {
		if (nro == null) {
			return BigDecimal.ZERO;
		} else {
			return nro;
		}

	}

	public static long verificaLongNulo(Long nro) {
		if (nro == null) {
			return 0l;
		} else {
			return nro.longValue();
		}

	}

	/**
	 * Converte Data para string no formato definido na mascara.
	 * 
	 * @param data
	 *            Data a Ser formatada
	 * @param mascara
	 *            Mascara a ser aplicada na mascara: Ex: dd.MM.yyyy
	 * @return data formatada
	 */

	public static String converterDataString(Date data, String mascara) {

		String dataFormatada;
		SimpleDateFormat df = new SimpleDateFormat(mascara);
		dataFormatada = df.format(data);

		return dataFormatada;
	}

	// Devolve a data passada como Integer no formato da mascara
	// ex: 26/01/2011, yyyyMMdd -> 20110126
	public static Integer converterDataInteger(Date data, String mascara) {

		String dt = converterDataString(data, mascara);

		return Integer.parseInt(dt);
	}

	// Devolve a data passada como Long no formato de mascara
	// ex: 26/01/2011, yyyyMMdd -> 20110126
	public static Long converterDataLong(Date data, String mascara) {

		String dt = converterDataString(data, mascara);

		return Long.parseLong(dt);

	}

	// Retorna o dia da data em formato String
	public static String extrairDiaData(Date data) {

		Calendar date = Calendar.getInstance();

		if (data != null) {

			date.setTime(data);

			return preencherZerosAEsquerda(String.valueOf(date.get(Calendar.DATE)), 2);
		} else {

			return "";
		}

	}

	// Retorna o mês da data em formato String
	public static String extrairMesData(Date data) {

		Calendar date = Calendar.getInstance();

		if (data != null) {

			date.setTime(data);

			return preencherZerosAEsquerda(String.valueOf(date.get(Calendar.MONTH) + 1), 2);
		} else {
			return "";
		}

	}

	// Retorna o ano da data em formato String
	public static String extrairAnoData(Date data) {

		Calendar date = Calendar.getInstance();

		if (data != null) {

			date.setTime(data);

			return String.valueOf(date.get(Calendar.YEAR));
		} else {
			return "";
		}

	}

	// Retorna o dia da data em formato Integer
	public static Integer extrairDiaDataInteiro(Date data) {

		Calendar date = Calendar.getInstance();

		if (data != null) {
			date.setTime(data);
			return date.get(Calendar.DATE);
		} else {

			return 0;

		}

	}

	// Retorna o mês da data em formato Integer
	public static Integer extrairMesDataInteiro(Date data) {

		Calendar date = Calendar.getInstance();

		if (data != null) {
			date.setTime(data);
			return date.get(Calendar.MONTH) + 1;
		} else {

			return 0;

		}

	}

	// Retorna o ano da data em formato Integer
	public static Integer extrairAnoDataInteiro(Date data) {

		Calendar date = Calendar.getInstance();

		if (data != null) {
			date.setTime(data);
			return date.get(Calendar.YEAR);
		} else {

			return 0;

		}

	}

	/**
	 * Concatena dia(Integer), mês(Integer) e ano (Integer) e tem como retono uma String.
	 * 
	 * @param dia
	 *            Deve ser passado o dia em inteiro para a formatação
	 * @param mes
	 *            Deve ser passado o mês em inteiro para a formatação
	 * @param ano
	 *            Deve ser passado o ano em inteiro para a formatação
	 * @param formatacao
	 *            Deve ser passado a String que será utilizada como formatação da data
	 * @return data formatada em String
	 */

	public static String concatenaDatas(Integer dia, Integer mes, Integer ano, String formatacao) {

		if (dia == null || mes == null || ano == null) {
			return "";

		} else {

			String diaString = MkmtsUtil.preencherZerosAEsquerda(String.valueOf(dia), 2);
			String mesString = MkmtsUtil.preencherZerosAEsquerda(String.valueOf(mes), 2);
			String anoString = MkmtsUtil.preencherZerosAEsquerda(String.valueOf(ano), 4);

			StringBuilder concatena = new StringBuilder(diaString);

			concatena.append(mesString);
			concatena.append(anoString);

			concatena.insert(2, formatacao);
			concatena.insert(5, formatacao);

			return String.valueOf(concatena);
		}
	}

	/**
	 * Concatena dia, mês e ano do formato DATE e converte para inteiro.
	 * 
	 * @param data
	 *            Deve ser passado a data no formato DATE para a formatação.
	 * @return data formatada em inteiro.
	 */

	public static Integer extrairDataInteiro(Date data) {

		Calendar calendario = Calendar.getInstance();

		calendario.setTime(data);

		int dia = calendario.get(Calendar.DATE);
		int mes = calendario.get(Calendar.MONTH) + 1;
		int ano = calendario.get(Calendar.YEAR);

		StringBuilder stringBuilder = new StringBuilder(preencherZerosAEsquerda(String.valueOf(dia), 2));
		stringBuilder.append(preencherZerosAEsquerda(String.valueOf(mes), 2));
		stringBuilder.append(preencherZerosAEsquerda(String.valueOf(ano), 4));

		return Integer.parseInt(stringBuilder.toString());
	}

	/**
	 * Concatena dia, mês e ano do formato STRING e converte para inteiro.
	 * 
	 * @param data
	 *            Deve ser passado uma data no formato String.
	 * @param formatador
	 *            Deve ser passado o formatador da data.
	 * @return data formatada em Inteiro.
	 */

	public static Integer extrairDataString(String data, String formatador) {

		if (data == null) {
			return 0;
		}

		if (data.indexOf(formatador) == -1) {
			return 0;
		}

		String[] dataFormatada = data.split(formatador);

		StringBuilder stringBuilder = new StringBuilder(preencherZerosAEsquerda(dataFormatada[0], 2));
		stringBuilder.append(preencherZerosAEsquerda(dataFormatada[1], 2));
		stringBuilder.append(preencherZerosAEsquerda(dataFormatada[2], 4));

		return Integer.parseInt(stringBuilder.toString());
	}

	/**
	 * 
	 * @param data
	 *            Deve ser passado uma data no formato Integer.
	 * @param mascara
	 *            Deve ser passado a máscara utilizada para formatar a data.
	 * @return data formatada em String.
	 */
	public static String extrairDataFormatada(Integer data, String mascara) {

		if (data == null || data.toString().length() > 8) {
			return "";
		}

		String dataFinal = preencherZerosAEsquerda(data.toString(), 8);

		StringBuilder calendario = new StringBuilder(dataFinal);

		calendario.insert(2, mascara);
		calendario.insert(5, mascara);

		return calendario.toString();
	}

	/**
	 * 
	 * @param hora
	 *            Deve ser passada uma hora no formato Integer.
	 * @param mascara
	 *            Deve ser passado a máscara utilizada para formatar a hora, ex: ":".
	 * @return hora formatada em String.
	 */
	public static String extrairHoraFormatada(Integer hora, String mascara) {
		if (hora == null || hora.toString().length() > 6) {
			return "";
		}

		StringBuilder horaFormatada = new StringBuilder(hora.toString());

		horaFormatada.insert(2, mascara);
		horaFormatada.insert(5, mascara);

		return horaFormatada.toString();
	}

	/**
	 * Função criada para formatar o campo de Id de Documento para fins de apresentação visual.
	 * 
	 * @param idDocumento
	 *            Deve ser uma String , seu tamanho deve ser 44 ou 47 caracteres e não pode haver formatação.
	 * @return idDocumento formatado em formato String.
	 */

	public static String formatarIdDocumento(String idDocumento) {

		if ((idDocumento == null || idDocumento.indexOf('.') > -1)
						|| (idDocumento.trim().length() != 47 && idDocumento.trim().length() != 44)) {

			return "";
		}

		StringBuilder idBuilder = new StringBuilder(idDocumento);

		idBuilder.insert(5, ".");
		idBuilder.insert(11, " ");
		idBuilder.insert(17, ".");
		idBuilder.insert(24, " ");
		idBuilder.insert(30, ".");
		idBuilder.insert(37, " ");
		idBuilder.insert(39, " ");

		return idBuilder.toString();
	}
	
	public static String extrairNumerosString(String string) {
		
		return string.replaceAll("[^0-9]*", "");
	}

    public static BigDecimal formatValoresToBigDecimal(String valor) {
        if (valor != null) {
            DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(PT_BR);
            format.applyPattern(DECIMAL_PATTERN);
            BigDecimal valorBD = new BigDecimal(valor);

            return valorBD;
        }
        return null;
    }

    public static Long removerFormatacaoTelefone(String telefone){
    	try{
	    	if(!telefone.equalsIgnoreCase("")){
	    		telefone = telefone.replace("(", "");
	    		telefone = telefone.replace(")", "");
	    		telefone = telefone.replace("-", "");
	    		telefone = telefone.replace(" ", "");
	    		return Long.valueOf(telefone);
	    	}
    	}catch(NullPointerException np){
    		return null;
    	}
		return null;
    }
    
    public static String formatarNumRgString(Long numRg){
		
		if(numRg == null || numRg.toString().trim().length() != 9 ){
			
			return "";
		}
		
		String numRgFormatado = numRg.toString();		
		String tmp = "";
		
		for (int i = 0; i < numRgFormatado.length(); i++) {
			if(i == 2 || i == 5){
				tmp+=".";
				tmp+=numRgFormatado.charAt(i);
			}else
				if(i == (numRgFormatado.length() -1)){
					tmp+="-";
					tmp+=numRgFormatado.charAt(i);
				}else
					tmp+=numRgFormatado.charAt(i);
		}
		numRgFormatado = tmp;
		return numRgFormatado;
	}	

    public static String formatarNumTelefoneCelularString(Long numTelefoneCelular){
    	String numTelefoneCelularFormatado = numTelefoneCelular.toString();

    	if(numTelefoneCelular == null || (numTelefoneCelularFormatado.trim().length() != 10 && numTelefoneCelularFormatado.trim().length() != 11)){
			return "";
		}
    	
    	String tmp = "";
		
		for (int i = 0; i < numTelefoneCelularFormatado.length(); i++) {

			if(i == 0){
				tmp+="(";
			}
			if(i == 2){
				tmp+=")";
			}
			if(numTelefoneCelularFormatado.length() == 10){
				if(i == 6){
					tmp+="-";
				}
			}else{
				if(i == 7){
					tmp+="-";
				}
			}
			tmp+=numTelefoneCelularFormatado.charAt(i);
			
		}
		numTelefoneCelularFormatado = tmp;
    	return numTelefoneCelularFormatado;
    }
    
    public static <E> boolean isListNullOrEmpty(List<E> lista){
		if(lista == null){
			return true;
		}else{
			if(lista.isEmpty()){
				return true;
			}else{
				return false;
			}
		}
    }
    
}



