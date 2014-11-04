package br.com.mkoffice.view.converter;

import java.lang.reflect.Field;
import java.util.Collection;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "genericEntityConverter")
public class GenericEntityConverter implements Converter{

	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals(""))
            return null;

        try{
        	Long id = Long.valueOf(value);
        	Collection items =  (Collection) component.getChildren().get(0).getAttributes().get("value");
        	return findById(items, id);
        }catch(Exception ex){
        	throw new ConverterException("Nao foi possivel aplicar convers�o de item com valor ["+value+"] no componente ["+component.getId()+"]", ex);
        }
    }
	
    @Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null)
			return "";
		
		return getIdByReflection(value).toString();
	}
    
    private Object findById(Collection collection, Long idToFind){
    	for (Object obj : collection){
    		 Long id = getIdByReflection(obj);
    		 if (id.longValue() == idToFind.longValue())
    			 return obj;
    	}
    	
    	return null;
    }
    
    private Long getIdByReflection(Object bean){
    	try{
    		Field idField = bean.getClass().getDeclaredField("id");
    		idField.setAccessible(true);
    		return (Long) idField.get(bean);
    	}catch(Exception ex){
    		throw new RuntimeException("Nao foi possivel obter a propriedade 'id' do item",ex);
    	}
    }
	
}
