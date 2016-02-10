package pl.org.epf.client.local.view.polymer.paperelements;

import com.google.gwt.core.client.js.JsType;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(PaperFocusableElement.TAG)
@JsType
public class PaperFocusableElement extends Element {

	public static final String TAG = "paper-focusable";

	/**
	 * Assert that the given {@link Element} is compatible with this class and
	 * automatically typecast it.
	 */
	public static PaperFocusableElement as(Element elem) {
		assert elem.getTagName().equalsIgnoreCase(TAG);
		return (PaperFocusableElement) elem;
	}

	protected PaperFocusableElement() {
	}

	public boolean active;
	public boolean focused;
	public boolean pressed;
	public boolean disabled;
	public boolean toggle;
	
	/*
	 * Use JSNI to call Polymer's click() method to click this PaperButton
	 * programmatically
	 */
	public final native void click() /*-{
										this.click();
										}-*/;
}