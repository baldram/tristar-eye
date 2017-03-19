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

import com.google.gwtmockito.GwtMockitoTestRunner;
import gwt.material.design.client.constants.ModalType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class SimpleDialogTest {

    private static final String DIALOG_CSS_CLASS_NAME = "simple-dialog";

    @InjectMocks
    private SimpleDialog dialog;

    @Mock
    private BaseDialog<SimpleDialogBody> baseDialog;

    @Mock
    private SimpleDialogBody body;

    @Test
    public void show() throws Exception {
        String dialogTitle = "title";
        String dialogContent = "content";

        dialog.show(dialogTitle, dialogContent);

        verify(body).setContent(dialogContent);
        verify(baseDialog).init(eq(dialogTitle), eq(body), eq(DIALOG_CSS_CLASS_NAME));
        verify(baseDialog).setType(ModalType.FIXED_FOOTER);
        verify(baseDialog).show();
    }

}