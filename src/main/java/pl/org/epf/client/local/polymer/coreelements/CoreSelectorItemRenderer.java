package pl.org.epf.client.local.polymer.coreelements;

import com.google.gwt.dom.client.Element;

public abstract class CoreSelectorItemRenderer<T> {
	
	public abstract Element render(T item);

}
