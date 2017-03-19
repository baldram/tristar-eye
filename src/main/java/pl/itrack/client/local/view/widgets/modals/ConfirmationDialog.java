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
import pl.itrack.client.local.view.helpers.Texts;

import javax.inject.Inject;

public class ConfirmationDialog {

    private static final String DIALOG_CSS_CLASS_NAME = "confirmation-dialog";

    private static final String LINE_SEPARATOR = "<br />";

    private final BaseDialog<SimpleDialogBody> dialog;

    private final SimpleDialogBody body;

    @Inject
    public ConfirmationDialog(BaseDialog<SimpleDialogBody> dialog, SimpleDialogBody body) {
        this.dialog = dialog;
        this.body = body;
    }

    public void show(final String title, final String question, ConfirmationAction action) {
        body.setContent(question + LINE_SEPARATOR + Texts.CONFIRMATION_QUESTION);
        replaceActionButtons(executeActionAndCloseModal(action));
        dialog.init(title, body, DIALOG_CSS_CLASS_NAME);
        dialog.show();
    }

    private ClickHandler executeActionAndCloseModal(ConfirmationAction confirmedAction) {
        return clickEvent -> {
            confirmedAction.execute();
            dialog.closeWindow();
        };
    }

    private void replaceActionButtons(ClickHandler confirmedAction) {
        dialog.clearFooter();
        dialog.addButton(dialog.createButton(Texts.NO, clickEvent -> dialog.closeWindow()));
        dialog.addButton(dialog.createButton(Texts.YES, confirmedAction));
    }
}
