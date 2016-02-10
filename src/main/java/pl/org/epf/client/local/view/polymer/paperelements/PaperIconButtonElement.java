package pl.org.epf.client.local.view.polymer.paperelements;

import com.google.gwt.core.client.js.JsType;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(PaperIconButtonElement.TAG)
@JsType
public class PaperIconButtonElement extends PaperFocusableElement{
	
	public static final String TAG = "paper-icon-button";
	
	/**
	 * Assert that the given {@link Element} is compatible with this class and
	 * automatically typecast it.
	 */
	public static PaperIconButtonElement as(Element elem) {
		assert elem.getTagName().equalsIgnoreCase(TAG);
		return (PaperIconButtonElement) elem;
	}
	
	protected PaperIconButtonElement(){}
}