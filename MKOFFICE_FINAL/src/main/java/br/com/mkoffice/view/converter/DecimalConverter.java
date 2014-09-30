package br.com.mkoffice.view.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.mkoffice.utils.DecimalUtils;

@FacesConverter(value="decimalConverter")
public class DecimalConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		return DecimalUtils.convert(value);
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		if (value instanceof BigDecimal) {
			return DecimalUtils.format((BigDecimal) value);
		}

		return null;
	}

}
