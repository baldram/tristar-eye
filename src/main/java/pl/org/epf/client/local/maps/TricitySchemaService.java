/*
 * Copyright 2016 Marek Wojtuszkiewicz
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
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.user.client.ui.TextBox;
import pl.org.epf.client.local.model.TristarObject;

public class TricitySchemaService extends AbstractMapService {

    @Override
    MapOptions getMapOptions() {
        return null;
    }

    @Override
    public double getInitialLatitude() {
        return 0;
    }

    @Override
    public double getInitialLongitude() {
        return 0;
    }

    @Override
    public void bindTextBoxWithAutoComplete(TextBox searchBox) {
        // TODO: optional binding for future searching through the schema markers
    }

    @Override
    public void addMarkers(ImmutableMap<Integer, TristarObject> cameras) {
        // TODO: to add markers using proper lat&lang values for this kind of map
    }
}
