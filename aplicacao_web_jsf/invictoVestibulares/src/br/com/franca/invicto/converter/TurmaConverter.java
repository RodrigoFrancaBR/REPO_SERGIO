package br.com.franca.invicto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.franca.invicto.dao.TurmaDAO;
import br.com.franca.invicto.model.Turma;

@FacesConverter(forClass = Turma.class)
public class TurmaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if (string == null || string.isEmpty())
			return null;
		Integer id = Integer.valueOf(string);
		Turma turma = new TurmaDAO().buscarPorId(id);
		return turma;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Turma turma = (Turma) object;
		if (turma == null || turma.getId() == null)
			return null;
		return String.valueOf(turma.getId());
	}

}
