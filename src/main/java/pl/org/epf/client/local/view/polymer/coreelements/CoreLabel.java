package pl.org.epf.client.local.view.polymer.coreelements;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Label;

public class CoreLabel extends Label {

	public static final String STYLE = "errai-core-label";

	protected CoreLabel(Element elem) {
		super(elem);
	}

	public CoreLabel() {
		this(Document.get().createElement(CoreLabelElement.TAG), STYLE);
	}

	public CoreLabel(Element element, String styleName) {
		this(element);
		if (styleName != null && !styleName.equalsIgnoreCase(STYLE)) {
			styleName = STYLE + " " + styleName;
		}
		setStyleName(styleName);
	}

	@Override
	public void setDirection(Direction direction) {
		// TODO Auto-generated method stub
		super.setDirection(direction);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Direction getTextDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text, Direction dir) {
		// TODO Auto-generated method stub
		
	}
	
	
}
