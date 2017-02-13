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

package pl.itrack.client.local;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.MaterialDesign;
import gwt.material.design.client.resources.MaterialResources;
import gwt.material.design.client.resources.WithJQueryResources;
import org.jboss.errai.enterprise.client.cdi.AbstractErraiCDITest;
import org.jboss.errai.ioc.client.container.IOC;
import org.jboss.errai.ioc.client.container.SyncBeanDef;

import static gwt.material.design.jquery.client.api.JQuery.$;

public abstract class AbstractTristarCDITest extends AbstractErraiCDITest {

    private static final String TESTS_MODULE_NAME = "pl.itrack.TristarEyeTest";

    @Override
    public String getModuleName() {
        return TESTS_MODULE_NAME;
    }

    protected <T> T lookupBeanInstance(Class<T> type) {
        T instance = null;
        SyncBeanDef<T> bean = IOC.getBeanManager().lookupBean(type);
        if (bean != null) {
            instance = bean.getInstance();
            assertNotNull(instance);
        }
        return instance;
    }

    protected void setupJqueryAndInterfaceLibrary() {
        WithJQueryResources jquery = GWT.create(WithJQueryResources.class);
        // Test JQuery
        MaterialDesign.injectJs(jquery.jQuery());
        assertTrue(MaterialDesign.isjQueryLoaded());
        // Test Materialize
        MaterialDesign.injectJs(MaterialResources.INSTANCE.materializeJs());
        assertTrue(MaterialDesign.isMaterializeLoaded());
        // gwt-material-jquery Test
        assertNotNull($("body"));
    }

    protected boolean rootPanelContains(Widget widget) {
        boolean missingElement = DOM.getParent(widget.getElement()) == null;
        return !missingElement && DOM.getParent(widget.getElement()).isOrHasChild(widget.getElement());
    }

}
