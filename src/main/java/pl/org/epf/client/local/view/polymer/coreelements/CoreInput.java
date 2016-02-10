package pl.org.epf.client.local.view.polymer.coreelements;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.TextBoxBase;

public class CoreInput extends TextBoxBase {
	
	public static final String STYLE = "errai-core-input"; 
	
	protected CoreInput(Element elem){
		super(elem);
	}

	public CoreInput() {
		this(Document.get().createElement(CoreInputElement.TAG), STYLE);
	}

	public CoreInput(Element element, String styleName) {
		this(element);
		if (styleName != null && !styleName.equalsIgnoreCase(STYLE)) {
			styleName = STYLE + " " + styleName;
		}
		setStyleName(styleName);
	}

	@Override
	public String getText() {
		return DOM.getElementProperty(getElement(), "inputValue");
	}

	protected void commit() {
		getPaperElement().commit();
	}

	public String getPlaceholder() {
		return getPaperElement().getPlaceholder();
	}

	public void setPlaceholder(String pHolder) {
		getPaperElement().setPlaceholder(pHolder);
	}

	public boolean isDisabled() {
		return getPaperElement().isDisabled();
	}

	public void setDisabled(boolean status) {
		getPaperElement().setDisabled(status);
	}

	public String getType() {
		return getPaperElement().getType();
	}

	public boolean isReadonly() {
		return getPaperElement().isReadonly();
	}

	public void setReadonly(boolean status) {
		getPaperElement().setReadonly(status);
	}

	public boolean isRequired() {
		return getPaperElement().isRequired();
	}

	public void setRequired(boolean status) {
		getPaperElement().setRequired(status);
	}

	public boolean isMultiline() {
		return getPaperElement().isMultiline();
	}

	public void setMultiline(boolean status) {
		getPaperElement().setMultiline(status);
	}

	public String getRows() {
		return getPaperElement().getRows();
	}

	public void setRows(String rws) {
		getPaperElement().setRows(rws);
	}

	public String getInputValue() {
		return getPaperElement().getInputValue();
	}

	public String getValue() {
		return getPaperElement().getValue();
	}

	public void setValue(String val) {
		getPaperElement().setValue(val);
	}

	public String getValidationExpr() {
		return getPaperElement().getValidationExpr();
	}

	public void setValidationExpr(String regx){
		getPaperElement().setValidationExpr(regx);
	}

	public boolean isInvalid(){
		return getPaperElement().isInvalid();
	}

	public void setInvalid(boolean status) {
		getPaperElement().setInvalid(status);
	}

	private CoreInputElement getPaperElement() {
		return getElement().cast();
	}

}
