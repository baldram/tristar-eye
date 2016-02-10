package pl.org.epf.client.local.view.polymer.coreelements;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(CoreInputElement.TAG)
public class CoreLabelElement extends Element {

	public static final String TAG = "core-label";
	
	/**
	 * Assert that the given {@link Element} is compatible with this class and
	 * automatically typecast it.
	 */
	public static CoreLabelElement as(Element elem) {
		assert elem.getTagName().equalsIgnoreCase(TAG);
		return (CoreLabelElement) elem;
	}

	protected CoreLabelElement() {}
	
	public final native String getText() /*-{
	return this.text;
}-*/;

public final native void setText(String text) /*-{
	this.$.input.validate = regx;
}-*/;
}
