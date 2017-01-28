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

import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.ui.MaterialSplashScreen;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.nav.client.local.Navigation;
import pl.itrack.client.local.view.helpers.UiHelper;
import pl.itrack.client.local.view.resources.AppClientBundle;
import pl.itrack.client.local.view.widgets.Header;
import pl.itrack.client.local.view.widgets.Main;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@EntryPoint
@SuppressWarnings("unused")
public class TristarEyeApp extends Composite {

    @Inject private Navigation navigation;

    @Inject private Header header;

    @Inject private Main content;

    @Inject private UiHelper uiHelper;

    @Inject private MaterialSplashScreen splash;

    @PostConstruct
    public void init() {
        StyleInjector.inject(AppClientBundle.INSTANCE.appCss().getText());
        content.getContainer().add(navigation.getContentPanel());
        splash.show();

        RootPanel.get().add(splash);
        RootPanel.get().add(header);
        RootPanel.get().add(content);

        uiHelper.initMaterialize();
    }

    @AfterInitialization
    void postInit() {
        splash.hide();
    }
}
