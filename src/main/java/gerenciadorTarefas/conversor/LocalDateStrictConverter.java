package gerenciadorTarefas.conversor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("localDateStrictConverter")
public class LocalDateStrictConverter implements Converter{

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(value, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ConverterException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Formato de data inválido. Use dd/MM/aaaa (ex: 05/07/2023)", null)
            );
        }
    }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
	    if (value == null) {
	        return "";
	    }
	    return ((LocalDate) value).format(FORMATTER);
	}

}

