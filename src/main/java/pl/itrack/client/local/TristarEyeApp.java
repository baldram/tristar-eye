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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.nav.client.local.Navigation;
import pl.itrack.client.local.view.helpers.LinkElementInjector;
import pl.itrack.client.local.view.helpers.UiHelper;
import pl.itrack.client.local.view.widgets.Header;
import pl.itrack.client.local.view.widgets.Main;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@EntryPoint
@SuppressWarnings("unused")
public class TristarEyeApp extends Composite {

    private static final String APP_CSS = "./css/tristar.css";
    private static final String SPLASH_SCREEN_ID = "splashscreen";

    @Inject private Navigation navigation;

    @Inject private Header header;

    @Inject private Main content;

    @Inject private UiHelper uiHelper;

    @Inject private LinkElementInjector cssInjector;

    @PostConstruct
    public void init() {
        cssInjector.injectStyleSheet(APP_CSS);

        content.getContainer().add(navigation.getContentPanel());

        RootPanel.get().add(header);
        RootPanel.get().add(content);

        uiHelper.initMaterialize();
    }

    @AfterInitialization
    private void postInit() {
        Scheduler.get().scheduleDeferred(() ->
                RootPanel.getBodyElement().removeChild(DOM.getElementById(SPLASH_SCREEN_ID)));
    }
}
