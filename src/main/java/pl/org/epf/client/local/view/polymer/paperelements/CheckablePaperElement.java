package pl.org.epf.client.local.view.polymer.paperelements;

import com.google.gwt.dom.client.Element;

public class CheckablePaperElement extends Element {
	
	/**
	 * Assert that the given {@link Element} is compatible with this class and
	 * automatically typecast it.
	 */
	public static CheckablePaperElement as(Element elem) {
		return (CheckablePaperElement) elem;
	}
	
	protected CheckablePaperElement(){}

	public final native boolean isChecked() /*-{
		return !this.checked;
	}-*/;

	public final native void setChecked(boolean status) /*-{
		this.checked = status;
	}-*/;

	public final native boolean isDefaultChecked() /*-{
		return this.hasAttribute("checked");
	}-*/;

}
