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
import pl.org.epf.client.local.view.polymer.widget.PaperToggleButtonWidget;

import javax.enterprise.event.Event;
import javax.inject.Inject;

@Templated("#sidebar")
public class Sidebar extends Composite {

    @Inject
    @DataField
    private TextBox searchBox;

    @Inject
    private Event<MapViewTypeChange> mapTypeChangeEvent;

    @Inject
    @DataField
    private PaperToggleButtonWidget mapTypeToggle;

    public TextBox getSearchBox() {
		return searchBox;
	}

    @SuppressWarnings("unused")
    @EventHandler("mapTypeToggle")
    private void onMapTypeChangeClicked(ClickEvent e) {
        mapTypeChangeEvent.fire(new MapViewTypeChange());
    }

}
