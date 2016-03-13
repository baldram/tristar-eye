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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import com.google.gwt.user.client.ui.Composite;
import pl.org.epf.client.local.event.MapViewTypeChange;
import pl.org.epf.client.local.services.maps.MapSearchInputProvider;
import pl.org.epf.client.local.view.polymer.widget.PaperToggleButtonWidget;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

@Templated("#drawerSidebar")
@Singleton
public class DrawerSidebar extends Composite {

    @Inject
    @DataField
    private TextBox searchBox;

    @Inject
    private Event<MapViewTypeChange> mapTypeChangeEvent;

    @Inject
    @DataField
    private PaperToggleButtonWidget mapTypeToggle;

    @Produces
    @SuppressWarnings("unused")
    public MapSearchInputProvider<TextBox> getMapSearch() {
        return new MapSearchInputProvider<TextBox>() {
            @Override
            public TextBox get() {
                return searchBox;
            }
        };
    }

    @EventHandler("mapTypeToggle")
    @SuppressWarnings("unused")
    private void onMapTypeChangeClicked(ClickEvent e) {
        mapTypeChangeEvent.fire(new MapViewTypeChange());
    }

}
