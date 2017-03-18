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

package pl.itrack.client.local.services.maps;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerImage;
import com.google.gwt.maps.client.overlays.MarkerOptions;

public class GMapManager {

    Marker createMarker(final MapWidget mapWidget, LatLng location, String iconFile) {
        MarkerOptions options = MarkerOptions.newInstance();
        options.setPosition(location);
        final MarkerImage markerImage = MarkerImage.newInstance(iconFile);
        options.setIcon(markerImage);
        final Marker marker = Marker.newInstance(options);
        marker.setMap(mapWidget);

        return marker;
    }
}
