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

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.overlays.Marker;
import pl.itrack.client.shared.model.TristarObject;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractMapService implements MapService {

    private static final String CSS_CLASS_MAP_WIDGET = "mapWidget";
    private static final String MAX_SIZE = "100%";

    private MapWidget mapWidget;

    private final Map<Integer, Marker> markers = new HashMap<>();

    protected abstract MapOptions getMapOptions();

    @Override
	public void initializeMap() {
		mapWidget = new MapWidget(getMapOptions());
		mapWidget.setSize(MAX_SIZE, MAX_SIZE);
		mapWidget.getElement().setClassName(CSS_CLASS_MAP_WIDGET);
	}

    @Override
	public MapWidget getMapWidget() {
		return mapWidget;
	}

    protected abstract void updateFavourites(Marker clickedMarker, Integer currentlyPressedMarker);

    void addCameraClickHandlers(Integer objectId, Marker marker) {
        marker.addClickHandler(event -> {
            final TristarObject cameraDetails = getCameraDetails(objectId);
            showCameraDialog(cameraDetails.getName(), objectId);
        });
    }

    protected abstract TristarObject getCameraDetails(final Integer objectId);

    protected abstract void showCameraDialog(String title, Integer objectId);

    void updateMarkersCache(Integer objectId, Marker marker) {
        markers.put(objectId, marker);
    }

    Marker getMarker(Integer objectId) {
        return markers.get(objectId);
    }

}
