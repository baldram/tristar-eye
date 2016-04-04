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

import com.google.gwt.user.client.ui.RootPanel;
import org.jboss.errai.ioc.client.api.EntryPoint;

import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.ui.nav.client.local.Navigation;
import org.jboss.errai.ui.shared.ServerTemplateProvider;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import pl.org.epf.client.local.view.widgets.ContentContainer;
import pl.org.epf.client.local.view.widgets.HeaderPanel;
import pl.org.epf.client.local.view.widgets.Sidebar;
import pl.org.epf.client.local.view.widgets.TopNavigationPanel;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@EntryPoint
@SuppressWarnings("unused")
@Templated("#root")
public class TristarEyeApp extends Composite {

    @Inject
    private Navigation navigation;

    @Inject
    @DataField
    private Sidebar sidebar;

    @Inject
    @DataField
    private HeaderPanel headerPanel;

    @Inject
    @DataField
    private TopNavigationPanel topNavigationPanel;

    @Inject
    @DataField
    private ContentContainer content;

    @PostConstruct
    public void init() {
        content.add(navigation.getContentPanel());
        RootPanel.get().add(this);
    }

    @Override
    protected void onLoad() {
        initMaterialDesignLite();
        super.onLoad();
    }

    public final native void initMaterialDesignLite() /*-{
        // Needs to be called to finish MDL initialization
        $wnd.componentHandler.upgradeDom();
    }-*/;
}
