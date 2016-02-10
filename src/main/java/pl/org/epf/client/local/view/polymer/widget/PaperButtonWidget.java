package pl.org.epf.client.local.view.polymer.widget;

import pl.org.epf.client.local.view.polymer.paperelements.PaperButtonElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ButtonBase;

public class PaperButtonWidget extends ButtonBase {

	public PaperButtonWidget() {
		this(Document.get().createElement(PaperButtonElement.TAG));
	}

	protected PaperButtonWidget(Element elem) {
		super(elem);
	}

	/* more code */

	public PaperButtonElement getButtonElement() {
		return getElement().cast();
	}

	public void click() {
		getButtonElement().click();
	}

	public void setRaised(boolean raised) {
		getButtonElement().raised = raised;
	}

	public boolean isRaised() {
		return getButtonElement().raised;
	}
	
	public String getLabel() {
		return getButtonElement().label;
	}

	public void setLabel(String lbl){
		getButtonElement().label = lbl;
	}
	
}
