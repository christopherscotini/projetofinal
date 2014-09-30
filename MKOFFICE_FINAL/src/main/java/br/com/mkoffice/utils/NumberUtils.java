package br.com.mkoffice.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import br.com.mkoffice.utils.constants.LocaleConstants;

public abstract class NumberUtils {

	public static final String DEFAULT_DECIMAL_PATTERN = "#,##0.00";

	public static final String INDICE_PATTERN = "#,########0.00000000";

	public static final String INDICE_PATTERN_ESCALA_5 = "#,########0.00000";
	
	private NumberUtils(){
	    //Classes de utilidades não devem possuir um construtor público.
	}
	
	public static String format(Number number, String pattern) {
		if (number == null) {
			return "";
		}
		DecimalFormat formatter = getBrazilianNumberFormat();
		formatter.applyPattern(pattern);
		return formatter.format(number);
	}

	public static String format(Number number) {
		return format(number, DEFAULT_DECIMAL_PATTERN);
	}

	public static BigDecimal convert(String number) throws ParseException {
		return convert(number, DEFAULT_DECIMAL_PATTERN);
	}

	public static BigDecimal convert(String number, String pattern) throws ParseException {
		if (number == null || number.trim().equals("")) {
			return null;
		}
		DecimalFormat formatter = getBrazilianNumberFormat();
		formatter.applyPattern(pattern);
		formatter.setParseBigDecimal(true);
		return (BigDecimal) formatter.parse(number);
	}

	public static boolean validateHora(String hora) {
		if (hora == null || hora.trim().equals("")) {
			return false;
		}

		return hora.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
	}
	
	private static DecimalFormat getBrazilianNumberFormat() {
		return (DecimalFormat) NumberFormat.getNumberInstance(LocaleConstants.PT_BR);
	}
	
	public static String formatarFone(Integer area, String fone) {
		
		if (area.equals(0) || fone.equals("")) {
			return "";	
		} else {
			StringBuffer stringBuffer = new StringBuffer();
			
			stringBuffer.append("(");
			stringBuffer.append(area.toString());
			stringBuffer.append(") ");
			stringBuffer.append(fone.substring(0,4));
			stringBuffer.append("-");
			stringBuffer.append(fone.substring(4,8));

			return stringBuffer.toString();
		}
	}
	
	public static String formatarFone(String fone) {
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(fone.substring(0,4));
		stringBuffer.append("-");
		stringBuffer.append(fone.substring(4,8));

		return stringBuffer.toString();
		
	}
	
}
