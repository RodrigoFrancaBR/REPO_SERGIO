package br.com.franca.invicto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.franca.invicto.dao.UnidadeDAO;
import br.com.franca.invicto.model.Unidade;

@FacesConverter(forClass = Unidade.class)
public class UnidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if (string == null || string.isEmpty())
			return null;
		Integer id = Integer.valueOf(string);
		Unidade unidade = new UnidadeDAO().buscarPorId(id);
		return unidade;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Unidade unidade = (Unidade) object;
		if (unidade == null || unidade.getId() == null)
			return null;
		return String.valueOf(unidade.getId());
	}

}
