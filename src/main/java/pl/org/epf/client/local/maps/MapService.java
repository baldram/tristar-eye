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

package pl.org.epf.client.local.maps;

import com.google.common.collect.ImmutableMap;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.ui.TextBox;
import pl.org.epf.client.local.model.TristarObject;

public interface MapService {

    double getInitialLatitude();

    double getInitialLongitude();

    void bindTextBoxWithAutoComplete(TextBox searchBox);

    void initializeMap();

    MapWidget getMapWidget();

    void addMarkers(ImmutableMap<Integer, TristarObject> cameras);
}
