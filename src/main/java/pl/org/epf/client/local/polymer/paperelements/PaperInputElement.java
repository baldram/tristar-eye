package pl.org.epf.client.local.polymer.paperelements;

import com.google.gwt.core.client.js.JsType;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(PaperInputElement.TAG)
@JsType
public class PaperInputElement extends CoreInputElement {
	
	public static final String TAG = "paper-input";
	
	/**
	 * Assert that the given {@link Element} is compatible with this class and
	 * automatically typecast it.
	 */
	public static PaperInputElement as(Element elem) {
		assert elem.getTagName().equalsIgnoreCase(TAG);
		return (PaperInputElement) elem;
	}
	
	protected PaperInputElement(){}
	
	public String label;// automatically treated as @JsProperty
	public int maxRows;
	public boolean floatingLabel;
}
