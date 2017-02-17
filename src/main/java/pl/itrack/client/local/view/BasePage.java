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

package pl.itrack.client.local.view;

import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.ui.nav.client.local.Navigation;
import org.jboss.errai.ui.nav.client.local.PageShown;
import pl.itrack.client.local.event.PageLoaded;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public abstract class BasePage extends Composite {

    @Inject
    private Navigation navigation;

    @Inject
    private Event<PageLoaded> pageLoadedEvent;

    @PageShown
    @SuppressWarnings("unused")
    private void setCurrentState() {
        onPageShown();
        if (navigation.getCurrentPage()!=null) {
            String currentPage = navigation.getCurrentPage().getURL();
            updateTabSelection(currentPage);
        }
    }

    private void updateTabSelection(String currentPage) {
        pageLoadedEvent.fire(new PageLoaded(currentPage));
    }

    protected abstract void onPageShown();
}
