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

package pl.itrack.client.local.view.widgets;

import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.HeadingSize;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialModalContent;
import gwt.material.design.client.ui.MaterialModalFooter;
import gwt.material.design.client.ui.html.Div;
import gwt.material.design.client.ui.html.Heading;
import org.jboss.errai.common.client.logging.util.StringFormat;
import pl.itrack.client.local.view.helpers.Texts;

public class ModalWindow {

    private static final String IMAGE_TAG = "<img src='%s' class='%s' id='%s' />";

    private static final String IMAGE_ID = "tristarModalImage";

    private MaterialModal modal;

    public final void showImageModalDialog(final String title, final String imageUrl) {
        final String imageTag = StringFormat.format(IMAGE_TAG, imageUrl, IMAGE_ID, IMAGE_ID);
        // TODO: use Image widget here
        showModalDialog(title, imageTag, Texts.BTN_CLOSE_LABEL);
    }

    public final void showModalDialog(final String title, final String content) {
        showModalDialog(title, content, Texts.BTN_CLOSE_LABEL);
    }

    private void showModalDialog(final String title, final String content, final String buttonLabel) {
        modal = createModal(title, content, buttonLabel);
        RootPanel.get().add(modal);
        modal.open();
    }

    private MaterialModal createModal(String title, String content, String buttonLabel) {
        modal = new MaterialModal();
        modal.add(getModalContent(title, content));
        modal.add(getModalFooter(buttonLabel));
        return modal;
    }

    private MaterialModalFooter getModalFooter(String buttonLabel) {
        MaterialModalFooter footer = new MaterialModalFooter();
        MaterialButton btnClose = new MaterialButton();
        btnClose.addClickHandler(clickEvent -> {
            modal.close();
            RootPanel.get().remove(modal); // TODO: execute with small delay
        });
        btnClose.setText(buttonLabel);
        btnClose.setType(ButtonType.FLAT);
        footer.add(btnClose);
        return footer;
    }

    private MaterialModalContent getModalContent(String title, String description) {
        MaterialModalContent content = new MaterialModalContent();

        content.add(getModalHeader(title));
        content.add(getModalBody(description));

        return content;
    }

    private Heading getModalHeader(String title) {
        Heading header = new Heading(HeadingSize.H5);
        // TODO: add this via CSS: header.setFontWeight(300);
        header.getElement().setInnerSafeHtml(SafeHtmlUtils.fromString(title));
        return header;
    }

    private Div getModalBody(String description) {
        Div body = new Div();
        body.getElement().setInnerHTML(description);
        return body;
    }

}
