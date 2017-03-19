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

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwtmockito.GwtMockitoTestRunner;
import gwt.material.design.client.ui.MaterialModalFooter;
import gwt.material.design.client.ui.html.Div;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.itrack.client.local.view.helpers.Texts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class ConfirmationDialogTest {

    private static final String DIALOG_CSS_CLASS_NAME = "confirmation-dialog";

    private static final String LINE_SEPARATOR = "<br />";

    @InjectMocks
    private ConfirmationDialog confirmationDialog;

    @Mock
    private BaseDialog<SimpleDialogBody> baseDialog;

    @Mock
    private SimpleDialogBody body;

    @Captor ArgumentCaptor<String> dialogContentCaptor;

    @Test
    public void replaceDefaultFooter() {
        confirmationDialog.show(StringUtils.EMPTY, StringUtils.EMPTY, () -> {});

        verify(baseDialog).clearFooter();
        verify(baseDialog, times(2)).addButton(any(Widget.class));
    }

    @Test
    public void showModal() {
        String title = "expected title";
        String question = "the question";

        confirmationDialog.show(title, question, () -> {});

        verify(baseDialog).init(eq(title), eq(body), eq(DIALOG_CSS_CLASS_NAME));
        verify(body).setContent(dialogContentCaptor.capture());
        assertThat(dialogContentCaptor.getValue(), is(question + LINE_SEPARATOR + Texts.CONFIRMATION_QUESTION));
        verify(baseDialog).show();
    }
}