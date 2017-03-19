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

import gwt.material.design.client.constants.ModalType;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

public class SimpleAsyncDialog {

    private static final String DIALOG_CSS_CLASS_NAME = "simple-dialog";

    private final BaseDialog<SimpleDialogBody> dialog;

    private final SimpleDialogBody body;

    @Inject
    public SimpleAsyncDialog(BaseDialog<SimpleDialogBody> dialog, SimpleDialogBody body) {
        this.dialog = dialog;
        this.body = body;
    }

    public void show() {
        dialog.init(StringUtils.EMPTY, body, DIALOG_CSS_CLASS_NAME);
        dialog.setType(ModalType.FIXED_FOOTER);
        dialog.showWithLoader();
    }

    public void update(String title, String description) {
        dialog.setTitle(title);
        dialog.getDialogBody().setInnerHtml(description);
    }

    public void hideLoader() {
        dialog.hideLoader();
    }
}
