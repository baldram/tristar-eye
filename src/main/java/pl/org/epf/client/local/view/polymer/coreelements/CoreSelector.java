package pl.org.epf.client.local.view.polymer.coreelements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SimpleKeyProvider;

public class CoreSelector<T> extends FocusWidget implements
		HasConstrainedValue<T> {

	protected ProvidesKey<T> keyProvider;
	protected CoreSelectorItemRenderer<T> renderer;
	
	protected List<T> values = new ArrayList<T>();
	protected Map<Object, Integer> valueKeyToIndex = new HashMap<Object, Integer>();

	private T value;

	private boolean valueChangeHandlerInitialized;

	public CoreSelector() {
		this(new CoreSelectorItemRenderer<T>() {
			@Override
			public Element render(T item) {
				Element el = DOM.createDiv();
				String label = (item == null ? "" : item.toString());
				el.setAttribute("label", label);
				el.setInnerHTML(label);
				return el;
			}
		}, new SimpleKeyProvider<T>());
	}

	public CoreSelector(CoreSelectorItemRenderer<T> renderer,
			ProvidesKey<T> keyProvider) {
		this( Document.get().createElement(CoreSelectorElement.TAG), renderer, keyProvider );
	}

	public CoreSelector(Element elem, CoreSelectorItemRenderer<T> renderer,
			ProvidesKey<T> keyProvider) {
		super(elem);
		this.keyProvider = keyProvider;
		this.renderer = renderer;
	}

	protected void ensureDomEventHandlers() {
		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ValueChangeEvent.fire(CoreSelector.this, getValue());
			}
		});
	}

	public T getSelected() {
		return getValue();
	}

	public void setSelected(T selected) {
		setValue(selected, true);
	}

	public boolean isMulti() {
		return getPaperElement().isMulti();
	}

	public void setMulti(boolean status) {
		getPaperElement().setMulti(status);
	}

	public String getValueAttribute() {
		return getPaperElement().getValueAttribute();
	}

	public void setValueAttribute(String attr) {
		getPaperElement().setValueAttribute(attr);
	}

	public String getSelectedClass() {
		return getPaperElement().getSelectedClass();
	}

	public void setSelectedClass(String cls) {
		getPaperElement().setSelectedClass(cls);
	}

	public String getSelectedProperty() {
		return getPaperElement().getSelectedProperty();
	}

	public void setSelectedProperty(String prop) {
		getPaperElement().setSelectedProperty(prop);
	}

	public String getSelectedAttribute() {
		return getPaperElement().getSelectedAttribute();
	}

	public void setSelectedAttribute(String attr) {
		getPaperElement().setSelectedAttribute(attr);
	}

	public int getSelectedIndex() {
		return values.indexOf(getValue());
	}

	public void setSelectedIndex(int index) {
		T selected = values.get(index);
		setSelected(selected);
	}

	public Element getTarget() {
		return getPaperElement().getTarget();
	}

	public void setTarget(Element target) {
		getPaperElement().setTarget(target);
	}

	public String getItemSelector() {
		return getPaperElement().getItemSelector();
	}

	public void setItemSelector(String selector) {
		getPaperElement().setItemSelector(selector);
	}

	public String getActivateEvent() {
		return getPaperElement().getActivateEvent();
	}

	public void setActivateEvent(String evt) {
		getPaperElement().setActivateEvent(evt);
	}

	public boolean isNotap() {
		return getPaperElement().isNotap();
	}

	public void setNotap(boolean status) {
		getPaperElement().setNotap(status);
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {
		setValue(value, false);
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		if (value == this.value
				|| (this.value != null && this.value.equals(value))) {
			return;
		}

		T before = this.value;
		this.value = value;
		updateSelection();

		if (fireEvents) {
			ValueChangeEvent.fireIfNotEqual(this, before, value);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<T> handler) {
		if (!valueChangeHandlerInitialized) {
			ensureDomEventHandlers();
			valueChangeHandlerInitialized = true;
		}
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public void setAcceptableValues(Collection<T> newValues) {
		values.clear();
		valueKeyToIndex.clear();
		
		clearItems();

		for (T nextNewValue : newValues) {
			addValue(nextNewValue);
		}

		updateSelection();
	}

	protected CoreSelectorElement getPaperElement() {
		return getElement().cast();
	}

	public void addValue(T value) {
		Object key = keyProvider.getKey(value);
		if (valueKeyToIndex.containsKey(key)) {
			throw new IllegalArgumentException("Duplicate value: " + value);
		}

		valueKeyToIndex.put(key, values.size());
		values.add(value);
		Element itemEl = renderer.render(value);

		itemEl = itemEl.getFirstChildElement();
		
		//Window.alert(value.toString());
		//Window.alert(itemEl.toString());
		
		getPaperElement().appendChild(itemEl);
	}
	
	protected void updateSelection(){}
	
	public void clearItems(){
		NodeList<Node> nodes = getPaperElement().getChildNodes();
		for(int i = 0, len = nodes.getLength(); i < len; i++){
			nodes.getItem(i).removeFromParent();
		}
	}

}
