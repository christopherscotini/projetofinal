package br.com.mkoffice.utils;

import java.text.NumberFormat;

import br.com.mkoffice.utils.constants.LocaleConstants;

public abstract class CpfCnpjUtils {
	
	private static final String FILIAL = "0000";
	private static final String CONTROLE = "00";
	private static final String PRINCIPAL_CPF = "000,000,000";
	private static final String PRINCIPAL_CNPJ = "#00,000,000";

	private CpfCnpjUtils(){
	}
	
	public static String formatarCpfCnpj(Long cpfCpnj, Integer cpfCpnjFilial, Integer cpfCpnjControle) {

		if(cpfCpnj == null || cpfCpnj == 0 || cpfCpnjFilial == null || cpfCpnjControle == null){
			return "";
		}
		
		
		StringBuilder cStr = new StringBuilder();

		if (cpfCpnjFilial == 0) { // CPF
			cStr.append(NumberUtils.format(cpfCpnj, PRINCIPAL_CPF));
			cStr.append("-");
			cStr.append(NumberUtils.format(cpfCpnjControle, CONTROLE));
			
		} else { // CNPJ
			
			cStr.append(NumberUtils.format(cpfCpnj, PRINCIPAL_CNPJ));
			cStr.append("/");
			cStr.append(NumberUtils.format(cpfCpnjFilial, FILIAL));
			cStr.append("-");
			cStr.append(NumberUtils.format(cpfCpnjControle, CONTROLE));
		}
		
		return cStr.toString();
	}
	
	public static Long extraiCpfCnpjPrincipal(String sdocumento){

		if(sdocumento == null){
			return 0L;
		}
		
		String documento = sdocumento;
		StringBuilder sb = new StringBuilder("0");
		StringBuilder resultado;
		
		// remove a mascara
		documento = MkmtsUtil.replaceAll(documento,".", "");
		documento = MkmtsUtil.replaceAll(documento,"/", "");
		documento = MkmtsUtil.replaceAll(documento,"-", "");

		// Deve - se validar o tamanho da string pois, caso ela seje referente ao CNPJ seu tamanho ser de 14, sabendo que a string que contm o CPF ter 9 campos e a string que contm
		// o CNPJ contm 8 campos, devemos atribuir o numero zero ao inicio do CNPJ.
		
		if(documento.length() == 14){
			
			resultado = sb.append(documento); 
			
			documento = resultado.toString();
			
		}
		
		
		// pega somente as 9 primeiras posioes.
		return Long.parseLong(documento.substring(0, 9));
	}

	public static Integer extraiCnpjFilial(String sdocumento){

		if(sdocumento == null){
			return 0;
		}
		
		String documento = sdocumento;
		documento = MkmtsUtil.replaceAll(documento,".", "");
		documento = MkmtsUtil.replaceAll(documento,"/", "");
		documento = MkmtsUtil.replaceAll(documento,"-", "");
		
		if (documento.length() == 11) {
			return 0;
		}
		else
		{
			return Integer.parseInt(documento.substring(8, 12));
		}
	}

	public static Integer extraiCpfCnpjDigito(String sdocumento){

		if(sdocumento == null){
			return 0;
		}
		
		String documento = sdocumento;
		// remove a mascara
		documento = MkmtsUtil.replaceAll(documento,".", "");
		documento = MkmtsUtil.replaceAll(documento,"/", "");
		documento = MkmtsUtil.replaceAll(documento,"-", "");
		
		return Integer.parseInt(documento.substring(documento.length()-2, documento.length()));
	}
	
	
	public static String formatCpfCnpj(Long identificador, Integer codigo) {
		
		if(identificador == null || codigo == null){
			return "";
		}
		
		StringBuffer cnpjOuCpf = new StringBuffer();

		NumberFormat numberFormat = NumberFormat.getIntegerInstance(LocaleConstants.PT_BR);
		
		if (codigo.equals(1)) {
			String cpf = identificador.toString().substring(0, 9);
			String controle = identificador.toString().substring(9, 11);
			
			cnpjOuCpf.append(numberFormat.format(new Long(cpf)));
			cnpjOuCpf.append("-");
			cnpjOuCpf.append(controle);

			return cnpjOuCpf.toString();
			
		} else {
			String cnpj = identificador.toString().substring(0, 9);
			String filial = identificador.toString().substring(9, 13);
			String controle = identificador.toString().substring(13, 15);
			
			cnpjOuCpf.append(numberFormat.format(new Long(cnpj)));
			cnpjOuCpf.append("/");
			cnpjOuCpf.append(filial);
			cnpjOuCpf.append("-");
			cnpjOuCpf.append(controle);
			
			return cnpjOuCpf.toString();
		}
	}
	
	public static String formatCpfCnpjCompleto(Long cnpjCpf, Integer filial, Integer controle) {
		
		
		if(cnpjCpf == null || filial == null || controle == null){
			return "";
		}
		
		StringBuffer cnpjOuCpf = new StringBuffer();
		NumberFormat numberFormat = NumberFormat.getIntegerInstance(LocaleConstants.PT_BR);
		String cnpjCpfAux = numberFormat.format(cnpjCpf);
		String[] partesCnpj = cnpjCpfAux.split("\\.");
		
		if (partesCnpj.length >= 1 && partesCnpj[0] != null){
			cnpjCpfAux = String.format("%03d",new Long(partesCnpj[0].trim()));
		}else{
			cnpjCpfAux = "000";
		}
		
		if (partesCnpj.length >= 2 &&  partesCnpj[1] != null){
			cnpjCpfAux = cnpjCpfAux.concat(".").concat(String.format("%03d",new Long(partesCnpj[1].trim())));
		}else{
			cnpjCpfAux = cnpjCpfAux.concat(".").concat("000");
		}
		
		if (partesCnpj.length >= 3 && partesCnpj[2] != null){
			cnpjCpfAux = cnpjCpfAux.concat(".").concat(String.format("%03d",new Long(partesCnpj[2].trim())));
		}else{
			cnpjCpfAux = cnpjCpfAux.concat(".").concat("000");
		}
		
		if (filial.equals(0)) {
			cnpjOuCpf.append(cnpjCpfAux);
			cnpjOuCpf.append("-");
			cnpjOuCpf.append(String.format("%02d", controle));
			return cnpjOuCpf.toString();
		} else {
			cnpjOuCpf.append(cnpjCpfAux);
			cnpjOuCpf.append("/");
			cnpjOuCpf.append(String.format("%04d", filial));
			cnpjOuCpf.append("-");
			cnpjOuCpf.append(String.format("%02d", controle));
			return cnpjOuCpf.toString();
		}
	}
	
	public static Long extrairCpfCnpjPrincipalLong(Long cpfCnpj){
		
		if(cpfCnpj == null){
			return null;
		}
		
		if (cpfCnpj.toString().length() < 7) {
			return null;
		}
		
		String valorFormatado = String.valueOf(cpfCnpj);
		
		String corpoPrincipal = valorFormatado.substring(0,valorFormatado.length() - 6);
		
		return !corpoPrincipal.equals("") ? Long.parseLong(corpoPrincipal) : 0L;
				
	}
	
	/**
	 * 
	 * @param cpfCnpj Este campo pode ser tanto um CPF quanto um CNPJ.
	 * @return Terá como retorno o número filial do CPF/CNPJ. Caso seja CPF este valor retornado será 0000.
	 *
	 * entrada: 131073898000073   - CPF vindo da base PIC 9(15)
	 * 			046189718000179   - CNPJ vindo da base PIC 9(15)
	 * 
	 * Retorno:  0000 como integer.
	 * 			 0001 como integer
	 * o tamanho da variavel de entrada poder variar	    
	 */
	
	public static Integer extrairCpfCnpjFilialInteiro(Long cpfCnpj){
		
		
		if(cpfCnpj == null){
			
			return null;
		}
		
		String valorFormatado = String.valueOf(cpfCnpj);
		
		//Sempre utilizamos como referencia o digito de controle e o nmero filial que sempre existiro, portanto o filial ser o ndice entre o final -6 e o final -2, onde final  o tamanho do campo.
		
		String numFilial = valorFormatado.substring(valorFormatado.length() - 6,valorFormatado.length() - 2);
			
		
		return Integer.parseInt(numFilial);
	}
	
	/**
	 * 
	 * @param cpfCnpj Este campo pode ser tanto um CPF quanto um CNPJ.
	 * @return Ter como retorno o dgito de controle do CPF/CNPJ.
	 * 
	 *
	 * entrada: 131073898000073   - CPF vindo da base PIC 9(15)
	 * 			046189718000179   - CNPJ vindo da base PIC 9(15)
	 * 
	 * Retorno:  73 como integer.
	 * 			 79 como integer
     *
	 * o tamanho da variavel de entrada poder variar 
	 * 	    
	 */
	
	public static Integer extrairCpfCnpjDigitoControleInteiro(Long cpfCnpj){
		
		if(cpfCnpj == null){
			
			return null;
		}
		
		String valorFormatado = String.valueOf(cpfCnpj);

		//Sempre utilizamos como referencia o digito de controle e o nmero filial que sempre existiro, portanto o digito de controle ser o ndice entre o final -2 e o final, onde final  o tamanho do campo.
			
		String digitoControle = valorFormatado.substring(valorFormatado.length()-2,valorFormatado.length());
			
		return Integer.parseInt(digitoControle);
		
	}
	
	/**
	 * 
	 * @param cpfCnpj Este campo pode ser tanto um CPF quanto um CNPJ.
	 * @return Ter como retorno o campo CPF/CNPJ no formato Long.
	 * 
	 * Entrada: campo formatado como String:
	 * 			CPF: 123.456.789-09
	 * 			CNPJ: 64.481.427/0001-31
	 * 
	 * Retorno: campo com tamanhos de 11 ou 14:
	 * 			CPF:  12345678909
	 * 			CNPJ: 64481427000131	
	 */
	public static Long removerFormatacaoCpfCnpj(String cpfCnpj){
		if((cpfCnpj == null) || (cpfCnpj.equals(""))){
			return null;
		}
		
		String documentoAux = cpfCnpj;
		
		documentoAux = documentoAux.replace(".", "");
		documentoAux = documentoAux.replace("/", "");
		documentoAux = documentoAux.replace("-", "");
		
		return Long.parseLong(documentoAux);
	}
	
	/**
	 * 
	 * @param cpfCnpj Este campo pode ser tanto um CPF quanto um CNPJ.
	 * @return Ter como retorno o campo CPF/CNPJ no formato String.
	 * 
	 * Entrada: campo formatado como String:
	 * 			CPF: 123.456.789-09
	 * 			CNPJ: 64.481.427/0001-31
	 * 
	 * Retorno: campo com tamanhos de 11 ou 14:
	 * 			CPF:  12345678909
	 * 			CNPJ: 64481427000131	
	 */
	public static String removerFormatacaoCpfCnpjString(String cpfCnpj){
		if((cpfCnpj == null) || (cpfCnpj.equals(""))){
			return "";
		}
		
		String documentoAux = cpfCnpj;
		
		documentoAux = documentoAux.replace(".", "");
		documentoAux = documentoAux.replace("/", "");
		documentoAux = documentoAux.replace("-", "");
		
		return documentoAux;
	}
	
	/**
	 * 
	 * @param cpfCnpj Este campo pode ser tanto um CPF quanto um CNPJ.
	 * @return Ter como retorno o campo CPF/CNPJ no formato Long.
	 * 
	 * Entrada: campo formatado como String:
	 * 			CPF: 131.073.898-03
	 * 			CNPJ: 46.189.718/0001-79
	 * 
	 * Retorno: campo com tamanhos de 11 ou 14, adicionando 0  filial caso CPF.
	 * 			CPF:  131073898000003
	 * 			CNPJ: 461897180000179	
	 */
	public static Long formatarCpfCnpjLong(String cpfCnpj){
		
		if(cpfCnpj == null){
			
			return null;
		}
		
		String valorFormatado = null;
		String cpfCnpjAux = cpfCnpj;
		
		//Caso ocorra uma barra na formatao, ento poderemos tratar este campo como CNPJ
		if(cpfCnpjAux.indexOf('/') > -1){
			
			if(cpfCnpjAux.length() == 18){
				
			    cpfCnpjAux = MkmtsUtil.replaceAll(cpfCnpjAux,".","");
			    cpfCnpjAux = MkmtsUtil.replaceAll(cpfCnpjAux,"/","");
			    cpfCnpjAux = MkmtsUtil.replaceAll(cpfCnpjAux,"-","");
			    valorFormatado = cpfCnpjAux;
			}
			
			
		}//Caso NO ocorra uma barra na formatao, ento poderemos tratar este campo como CPF
		else{
			
			if(cpfCnpjAux.length() == 14){
				
			    cpfCnpjAux = MkmtsUtil.replaceAll(cpfCnpjAux,".","");
			    cpfCnpjAux = MkmtsUtil.replaceAll(cpfCnpjAux,"-","");
				
				
				StringBuilder sb = new StringBuilder(cpfCnpjAux);
				
				sb.insert(9,"0");
				sb.insert(10,"0");
				sb.insert(11,"0");
				sb.insert(12,"0");
				
				valorFormatado = sb.toString();
			}
			
		}
		
		
		return valorFormatado != null ? Long.parseLong(valorFormatado) : null;
	}
	
	public static String formatarCpfCnpjString(Long cpfCnpj){
		
		
		
		if(cpfCnpj == null || (cpfCnpj.toString().trim().length() != 11 && cpfCnpj.toString().trim().length() != 14 && cpfCnpj.toString().trim().length() != 15)){
			
			return "";
		}
		
		String cpfCnpjFormatado = cpfCnpj.toString();		
		
		
		if(cpfCnpjFormatado.trim().length() == 11 || cpfCnpjFormatado.trim().length() == 15){
			
			StringBuilder builderCpf = new StringBuilder(cpfCnpjFormatado);
			
			if (builderCpf.length() == 15) {
				builderCpf.delete(9, 13);
			}
			
			builderCpf.insert(3,".");
			builderCpf.insert(7,".");
			builderCpf.insert(11,"-");
			
			cpfCnpjFormatado = builderCpf.toString();
			
		}
		else{
			
				
			if(cpfCnpjFormatado.trim().substring(cpfCnpjFormatado.trim().length() - 3, cpfCnpjFormatado.trim().length() - 2).equals("0")){
				
				StringBuilder builderCpf = new StringBuilder("");
				
				String corpoCpf = cpfCnpjFormatado.trim().substring(0,9);
				String digitoCpf = cpfCnpjFormatado.trim().substring(cpfCnpjFormatado.trim().length() - 2,cpfCnpjFormatado.trim().length());
				
				builderCpf.append(corpoCpf);
				builderCpf.append(digitoCpf);
				
				
				builderCpf.insert(3,".");
				builderCpf.insert(7,".");
				builderCpf.insert(11,"-");
				
				cpfCnpjFormatado = builderCpf.toString();
				
			}
			else{
				
				StringBuilder builderCnpj = new StringBuilder(cpfCnpjFormatado);
				
				builderCnpj.insert(2,".");
				builderCnpj.insert(6,".");
				builderCnpj.insert(10,"/");
				builderCnpj.insert(15,"-");
				
				cpfCnpjFormatado = builderCnpj.toString();
			}
			
		}
		
		return cpfCnpjFormatado;
	}
	
}
