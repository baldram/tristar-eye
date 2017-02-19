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
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.HeadingSize;
import gwt.material.design.client.constants.ModalType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialModalContent;
import gwt.material.design.client.ui.MaterialModalFooter;
import gwt.material.design.client.ui.html.Heading;
import pl.itrack.client.local.view.helpers.Texts;

import javax.annotation.PostConstruct;

public class BaseDialog {

    private MaterialModal modal;

    private final MaterialModalFooter footer = new MaterialModalFooter();

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
        RootPanel.get().remove(modal);
    }

    MaterialButton createButton(String buttonLabel, ClickHandler action) {
        MaterialButton button = new MaterialButton();
        button.addClickHandler(action);
        button.setText(buttonLabel);
        button.setType(ButtonType.FLAT);
        return button;
    }

    protected void show() {
        RootPanel.get().add(modal);
        modal.open();
    }

    protected MaterialModal init(String title, Widget body, String cssClassName) {
        modal = new MaterialModal();
        modal.add(getModalContent(title, body));
        modal.add(footer);
        modal.getElement().addClassName(cssClassName);
        return modal;
    }

    private MaterialModalContent getModalContent(String title, Widget body) {
        MaterialModalContent content = new MaterialModalContent();

        content.add(getModalHeader(title));
        content.add(body);

        return content;
    }

    private Heading getModalHeader(String title) {
        Heading header = new Heading(HeadingSize.H5);
        // TODO: add this via CSS: header.setFontWeight(300);
        header.getElement().setInnerSafeHtml(SafeHtmlUtils.fromString(title));
        return header;
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
