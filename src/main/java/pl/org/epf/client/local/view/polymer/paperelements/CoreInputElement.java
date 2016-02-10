package pl.org.epf.client.local.view.polymer.paperelements;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(CoreInputElement.TAG)
public class CoreInputElement extends Element {

	public static final String TAG = "core-input";

	/**
	 * Assert that the given {@link Element} is compatible with this class and
	 * automatically typecast it.
	 */
	public static CoreInputElement as(Element elem) {
		assert elem.getTagName().equalsIgnoreCase(TAG);
		return (CoreInputElement) elem;
	}

	protected CoreInputElement() {}

	public final native void commit() /*-{
		return this.commit();
	}-*/;

	public final native String getPlaceholder() /*-{
		return this.placeholder;
	}-*/;

	public final native void setPlaceholder(String pHolder) /*-{
		this.placeholder = pHolder;
	}-*/;

	public final native boolean isDisabled() /*-{
		return this.disabled;
	}-*/;

	public final native void setDisabled(boolean status) /*-{
		this.disabled = status;
	}-*/;

	public final native String getType() /*-{
		return this.type;
	}-*/;

	public final native boolean isReadonly() /*-{
		return this.readonly;
	}-*/;

	public final native void setReadonly(boolean status) /*-{
		this.readonly = status;
	}-*/;

	public final native boolean isRequired() /*-{
		return this.required;
	}-*/;

	public final native void setRequired(boolean status) /*-{
		this.required = status
	}-*/;

	public final native boolean isMultiline() /*-{
		return this.multiline;
	}-*/;

	public final native void setMultiline(boolean status) /*-{
		this.multiline = status
	}-*/;

	public final native String getRows() /*-{
		return this.rows;
	}-*/;

	public final native void setRows(String rws) /*-{
		if(this.multiline){
			rws = parseInt(rws);
			if(rws == NaN){
				this.rows = 'fit';
			}else{
				this.rows = rws;
			}
		}
	}-*/;

	public final native String getInputValue() /*-{
		return this.inputValue;
	}-*/;

	public final native String getValue() /*-{
		return this.value;
	}-*/;

	public final native void setValue(String val) /*-{
		this.value = (val == null ? "" : val);
	}-*/;

	public final native String getValidationExpr() /*-{
		return this.validate;
	}-*/;

	public final native void setValidationExpr(String regx) /*-{
		this.$.input.validate = regx;
	}-*/;

	public final native boolean isInvalid() /*-{
		return this.invalid;
	}-*/;

	public final native void setInvalid(boolean status) /*-{
		this.invalid = status;
	}-*/;

}
