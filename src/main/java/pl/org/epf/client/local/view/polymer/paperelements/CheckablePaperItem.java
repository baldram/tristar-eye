package pl.org.epf.client.local.view.polymer.paperelements;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasValue;

public abstract class CheckablePaperItem extends FocusWidget implements HasValue<Boolean> {
	
	private boolean valueChangeHandlerInitialized;

	protected CheckablePaperItem(Element elem) {
		super(elem);
	}

	public boolean isChecked() {
		return getPaperElement().isChecked();
	}

	public void seChecked(boolean status) {
		getPaperElement().setChecked(status);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
		// Is this the first value change handler? If so, time to add handlers
		if (!valueChangeHandlerInitialized) {
			ensureDomEventHandlers();
			valueChangeHandlerInitialized = true;
		}
		return addHandler(handler, ValueChangeEvent.getType());
	}

	protected void ensureDomEventHandlers() {
		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ValueChangeEvent.fire(CheckablePaperItem.this, getValue());
			}
		});
	}

	/**
	 * Determines whether this toggle button is currently checked.
	 * 
	 * @return <code>true</code> if the check box is checked, false otherwise.
	 *         Will not return null
	 */
	@Override
	public Boolean getValue() {
		if (isAttached()) {
			return getPaperElement().isChecked();
		} else {
			return getPaperElement().isDefaultChecked();
		}
	}

	/**
	 * Checks or unchecks the toggle button.
	 * 
	 * @param value
	 *            true to check, false to uncheck; null value implies false
	 */
	@Override
	public void setValue(Boolean value) {
		setValue(value, false);
	}

	/**
	 * Checks or unchecks the toggle button, firing {@link ValueChangeEvent} if
	 * appropriate.
	 * 
	 * @param value
	 *            true to check, false to uncheck; null value implies false
	 * @param fireEvents
	 *            If true, and value has changed, fire a
	 *            {@link ValueChangeEvent}
	 */
	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		if (value == null) {
			value = Boolean.FALSE;
		}

		Boolean oldValue = getValue();
		getPaperElement().setChecked(value);
		if (value.equals(oldValue)) {
			return;
		}
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}

	}

	protected CheckablePaperElement getPaperElement() {
		return getElement().cast();
	}

}
