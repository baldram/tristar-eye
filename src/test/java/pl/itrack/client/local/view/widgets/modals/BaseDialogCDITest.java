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

import com.google.gwt.dom.client.BodyElement;
import org.apache.commons.lang3.StringUtils;
import pl.itrack.client.local.AbstractTristarCDITest;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class BaseDialogCDITest extends AbstractTristarCDITest {

    private static final String TEST_WIDGET_ID = "test-widget";
    private static final String MODAL_TITLE_TAG = "h5";
    private static final String GMD_OVERLAY_CSS_CLASS = CLASS_DOM_PREFIX + "lean-overlay";

    private BaseDialog<SimpleDialogBody> baseDialog;

    private SimpleDialogBody testWidget;

    @Override
    @SuppressWarnings("unchecked")
    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        setupJqueryAndInterfaceLibrary();
        baseDialog = lookupBeanInstance(BaseDialog.class);
        testWidget = getTestWidget();
    }

    private SimpleDialogBody getTestWidget() {
        testWidget = new SimpleDialogBody();
        testWidget.asWidget().getElement().setId(TEST_WIDGET_ID);
        return testWidget;
    }

    public void testModalOverlayIsInitialized() {
        assertNotNull($(GMD_OVERLAY_CSS_CLASS));
    }

    public void testShow() throws Exception {
        String dialogTitle = "modal-title";
        String cssClassName = "css-name";

        baseDialog.init(dialogTitle, testWidget, cssClassName);
        baseDialog.show();

        assertNotNull($(TEST_WIDGET_ID));
        assertEquals(dialogTitle, $(BodyElement.TAG).find(MODAL_TITLE_TAG).asElement().getInnerHTML());
        assertNotNull($(CLASS_DOM_PREFIX + cssClassName));
        assertTrue(rootPanelContains(baseDialog.getModal()));
        assertEquals(1, baseDialog.getFooter().getChildrenList().size());
    }

    public void testClose() throws Exception {
        baseDialog.init(StringUtils.EMPTY, testWidget, StringUtils.EMPTY);
        baseDialog.show();
        baseDialog.closeWindow();

        assertFalse(rootPanelContains(baseDialog.getModal()));
    }

}