package pl.itrack.client.local.view.helpers;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadElement;
import com.google.gwt.dom.client.LinkElement;
import pl.itrack.client.local.config.AppSettings;

import javax.inject.Inject;

/**
 * Injects link tag of type text/css to the document's head section dynamically.
 *
 * This is an alternative to:
 *      StyleInjector.inject(AppClientBundle.INSTANCE.appCss().getText());
 * which adds CSS file content to the document instead of link only.
 */
public class LinkElementInjector {

    private static final String CSS_MIME_TYPE = "text/css";
    private static final String CSS = "stylesheet";
    private static final String HEAD_TAG_NAME = "head";
    private static final String ERROR_HEAD_NOT_FOUND = "The host HTML page does not have a <head> element which is required by LinkElementInjector";
    private static final String QUERY_STRING_SEPARATOR = "?";

    private HeadElement head;

    private AppSettings settings;

    @Inject
    public LinkElementInjector(AppSettings settings) {
        this.settings = settings;
    }

    public void injectStyleSheet(String fileWebPath) {
        LinkElement link = this.createElement(fileWebPath + QUERY_STRING_SEPARATOR + settings.getAppVersion());
        this.getHead().appendChild(link);
    }

    private LinkElement createElement(String filePath) {
        LinkElement style = Document.get().createLinkElement();
        style.setType(CSS_MIME_TYPE);
        style.setRel(CSS);
        style.setHref(filePath);
        return style;
    }

    private HeadElement getHead() {
        if(this.head == null) {
            Element element = Document.get().getElementsByTagName(HEAD_TAG_NAME).getItem(0);
            assert element != null : ERROR_HEAD_NOT_FOUND;
            this.head = HeadElement.as(element);
        }
        return this.head;
    }
}
