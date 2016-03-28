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

package pl.org.epf.client.local.view.widgets;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.SinkNative;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import pl.org.epf.client.local.event.PageChange;
import pl.org.epf.client.local.view.FavouritesViewModel;
import pl.org.epf.client.local.view.MapTabViewModel;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Templated("#topNavigationPanel")
public class TopNavigationPanel extends Composite {

    public static final String BUTTON_SUFFIX = "Button";

    @Inject
    private javax.enterprise.event.Event<PageChange> pageChangeEvent;

    @PostConstruct
    private void setCurrentState() {
        // TODO: set current tab based on Navigation when page opened with anchor
    }

    @SuppressWarnings("unused")
    @EventHandler(FavouritesViewModel.PAGE_NAME + BUTTON_SUFFIX)
    @SinkNative(Event.ONCLICK)
    protected void switchToFavourites(Event e) {
        pageChangeEvent.fire(new PageChange(FavouritesViewModel.PAGE_NAME));
    }

    @SuppressWarnings("unused")
    @EventHandler(MapTabViewModel.PAGE_NAME + BUTTON_SUFFIX)
    @SinkNative(Event.ONCLICK)
    protected void switchToMap(Event e) {
        pageChangeEvent.fire(new PageChange(MapTabViewModel.PAGE_NAME));
    }
}
