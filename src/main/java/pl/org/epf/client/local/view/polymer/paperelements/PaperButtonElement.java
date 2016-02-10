package pl.org.epf.client.local.view.polymer.paperelements;

import com.google.gwt.core.client.js.JsType;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(PaperButtonElement.TAG)
@JsType
public class PaperButtonElement extends PaperFocusableElement{
	
	public static final String TAG = "paper-button";
	
	/**
	 * Assert that the given {@link Element} is compatible with this class and
	 * automatically typecast it.
	 */
	public static PaperButtonElement as(Element elem) {
		assert elem.getTagName().equalsIgnoreCase(TAG);
		return (PaperButtonElement) elem;
	}
	
	protected PaperButtonElement(){}
	
	public String label;// automatically treated as @JsProperty
	public String iconSrc;
	public String icon;
	public boolean raised;	
}