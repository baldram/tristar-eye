package pl.org.epf.client.local.view.polymer.widget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import pl.org.epf.client.local.view.polymer.paperelements.PaperToggleButtonElement;

public class PaperToggleButtonWidget extends PaperButtonWidget {

	public PaperToggleButtonWidget() {
		this(Document.get().createElement(PaperToggleButtonElement.TAG));
	}

	protected PaperToggleButtonWidget(Element elem) {
		super(elem);
	}
}
