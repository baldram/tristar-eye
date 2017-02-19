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
import gwt.material.design.client.ui.html.Div;

import javax.inject.Inject;

public class SimpleDialog {

    private static final String DIALOG_CSS_CLASS_NAME = "simple-dialog";

    private final BaseDialog dialog;

    @Inject
    public SimpleDialog(BaseDialog dialog) {
        this.dialog = dialog;
    }

    public void show(final String title, final String content) {
        Div body = new Div();
        body.getElement().setInnerHTML(content);
        dialog.init(title, body, DIALOG_CSS_CLASS_NAME);
        dialog.setType(ModalType.FIXED_FOOTER);
        dialog.show();
    }
}
