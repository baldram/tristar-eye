package pl.org.epf.client.local.polymer.widget;

import pl.org.epf.client.local.polymer.paperelements.PaperInputElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.TextBoxBase;

public class PaperInputWidget extends TextBoxBase {

	public PaperInputWidget() {
		this(Document.get().createElement(PaperInputElement.TAG));
	}

	protected PaperInputWidget(Element elem) {
		super(elem);
		// TODO Auto-generated constructor stub
	}

	/* more code */

	public PaperInputElement getButtonElement() {
		return getElement().cast();
	}

	public String getLabel() {
		return getPaperElement().label;
	}

	public void setLabel(String lbl) {
		getPaperElement().label = lbl;
	}

	public int getMaxRows() {
		return getPaperElement().maxRows;
	}

	public void setMaxRows(int rows) {
		getPaperElement().maxRows = rows;
	}

	public boolean isFloatingLabel() {
		return getPaperElement().floatingLabel;
	}

	public void setFloatingLabel(boolean status) {
		getPaperElement().floatingLabel = status;
	}

	private PaperInputElement getPaperElement() {
		return getElement().cast();
	}

}
