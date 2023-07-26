package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class BasicView {

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void login() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Welcome " + text));
    }
	
}
