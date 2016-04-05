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
import pl.org.epf.client.local.event.MapViewTypeChange;

import javax.inject.Inject;
import javax.inject.Singleton;

@Templated("#sidebar")
@Singleton
public class Sidebar extends Composite {

    @Inject
    private javax.enterprise.event.Event<MapViewTypeChange> mapTypeChangeEvent;

    @SuppressWarnings("unused")
    @EventHandler("mapTypeToggle")
    @SinkNative(Event.ONCLICK)
    private void onMapTypeChangeClicked(Event e) {
        mapTypeChangeEvent.fire(new MapViewTypeChange());
    }

}
