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

package pl.org.epf.client.local;

import static pl.org.epf.client.local.TristarEyeApp.TEMPLATE_ROOT;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Panel;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.SyncBeanManager;
import org.jboss.errai.ui.shared.ServerTemplateProvider;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.shared.api.annotations.DataField;

import com.google.gwt.user.client.ui.Composite;

import pl.org.epf.client.local.view.ContentContainer;
import pl.org.epf.client.local.view.MapTabView;
import pl.org.epf.client.local.view.polymer.widget.PaperIconButtonWidget;
import pl.org.epf.client.local.view.widgets.Navigation;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@EntryPoint
@Templated(value = TEMPLATE_ROOT, provider = ServerTemplateProvider.class)
public class TristarEyeApp extends Composite {

    public static final String TEMPLATE_ROOT = "index.html#root";

    @Inject
    @DataField
    private PaperIconButtonWidget settingsButton;

    @Inject
    @DataField
    private PaperIconButtonWidget mapButton;

    @Inject
    @DataField
    private Navigation tristarNavigation;

    @Inject
    @DataField
    private ContentContainer content;

    @Inject
    private SyncBeanManager manager;

    private MapTabView mapView;

    @PostConstruct
    public void init() {
        mapView = getView(MapTabView.class);
        addView(content, mapView);
        mapView.setSearchBox(tristarNavigation.getSearchBox());
    }

    private <T extends Composite> T getView(Class<T> type) {
        T view = null;
        IOCBeanDef<T> bean = manager.lookupBean(type);
        if (bean != null) {
            view = bean.getInstance();
        }
        return view;
    }

    private void addView(Panel container, Composite view) {
        container.clear();
        container.add(view);
    }

    @EventHandler("settingsButton")
    public void doSomething(ClickEvent e) {
        removeView(content, mapView);
    }

    private void removeView(Panel container, Composite viewToRemove) {
        viewToRemove.removeFromParent();
        container.clear();
    }

    @EventHandler("mapButton")
    public void doSomething2(ClickEvent e) {
        addView(content, mapView);
    }
}
