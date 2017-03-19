/*
 * Copyright 2017 Marcin Szałomski
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package pl.itrack.client.local.view.widgets.modals;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.*;
import gwt.material.design.client.ui.html.Heading;
import org.apache.commons.lang3.StringUtils;
import pl.itrack.client.local.view.helpers.Texts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class BaseDialog<T extends DialogBody> {

    private MaterialModal modal;

    private final MaterialModalFooter footer = new MaterialModalFooter();

    @Inject
    private MaterialProgress loader;

    private Heading header;
    private T dialogBody ;

    @PostConstruct
    private void basicInitialization() {
        addCloseButton();
    }

    void addCloseButton() {
        addButton(createButton(Texts.BTN_CLOSE, clickEvent -> closeWindow()));
    }

    void addButton(Widget button) {
        footer.add(button);
    }

    void clearFooter() {
        footer.clear();
    }

    void closeWindow() {
        modal.close();
        modal.setTitle(StringUtils.EMPTY);
        dialogBody.destroy();
        RootPanel.get().remove(modal);
    }

    MaterialButton createButton(String buttonLabel, ClickHandler action) {
        MaterialButton button = new MaterialButton();
        button.addClickHandler(action);
        button.setText(buttonLabel);
        button.setType(ButtonType.FLAT);
        return button;
    }

    protected void showWithLoader() {
        loader.setType(ProgressType.INDETERMINATE);
        modal.add(loader);
        show();
    }

    protected void show() {
        RootPanel.get().add(modal);
        modal.open();
    }

    protected MaterialModal init(String title, T dialogBody, String cssClassName) {
        this.dialogBody = dialogBody;
        modal = new MaterialModal();
        modal.getElement().addClassName(cssClassName);
        modal.add(getModalContent(dialogBody.asWidget()));
        modal.add(footer);

        setTitle(title);

        return modal;
    }

    public void setTitle(String title) {
        header.getElement().setInnerSafeHtml(SafeHtmlUtils.fromString(title));
    }

    public void hideLoader() {
        modal.remove(loader);
    }

    public T getDialogBody() {
        return dialogBody;
    }

    private MaterialModalContent getModalContent(Widget body) {
        MaterialModalContent content = new MaterialModalContent();
        header = new Heading(HeadingSize.H5);
        // TODO: add this via CSS: header.setFontWeight(300);

        content.add(header);
        content.add(body);

        return content;
    }

    public void setType(ModalType type) {
        modal.setType(type);
    }

    public MaterialModal getModal() {
        return modal;
    }

    public MaterialModalFooter getFooter() {
        return footer;
    }
}
