/*
 * Copyright 2016 Marcin Szałomski
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

package pl.org.epf.client.local.core;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.SyncBeanManager;

import javax.inject.Inject;

public class BaseController {

    @Inject
    protected SyncBeanManager manager;

    protected <T extends Composite> T getView(Class<T> type) {
        T view = null;
        IOCBeanDef<T> bean = manager.lookupBean(type);
        if (bean != null) {
            view = bean.getInstance();
        }
        return view;
    }

    protected void addToPanel(Composite view, Panel container) {
        container.clear();
        container.add(view);
    }

    protected void removeFromPanel(Composite widgetToRemove, Panel container) {
        widgetToRemove.removeFromParent();
        container.clear();
    }

}
