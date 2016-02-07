package pl.org.epf.client.local.polymer.paperelements;

import com.google.gwt.core.client.js.JsType;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(PaperToggleButtonElement.TAG)
@JsType
public class PaperToggleButtonElement extends CheckablePaperElement {
	
	public static final String TAG = "paper-toggle-button";
	
	/**
	 * Assert that the given {@link Element} is compatible with this class and
	 * automatically typecast it.
	 */
	public static PaperToggleButtonElement as(Element elem) {
		assert elem.getTagName().equalsIgnoreCase(TAG);
		return (PaperToggleButtonElement) elem;
	}
	
	protected PaperToggleButtonElement(){}
	
	public boolean checked;// automatically treated as @JsProperty
	public boolean disabled;// automatically treated as @JsProperty

}
