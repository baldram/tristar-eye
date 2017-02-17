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
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerImage;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.user.client.Timer;
import pl.itrack.client.shared.model.TristarObject;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractMapService implements MapService {

    private static final String CSS_CLASS_MAP_WIDGET = "mapWidget";
    private static final String MAX_SIZE = "100%";
    private static final int LONG_PRESS_TIME = 500;
    static final String ICON_FILE_CAMERA = "camera.png";
    static final String ICON_FILE_CAMERA_SELECTED = "camera-selected.png";
    static final String IMAGES_PATH = "images/";

    private MapWidget mapWidget;

    private Integer clickedMarkerRelatedObjectId;
    private Marker clickedMarker;
    private boolean isMarkerLongPressed = false;

    private final Map<Integer, Marker> markers = new HashMap<>();

    abstract MapOptions getMapOptions();

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

    private final Timer longPressTimer = new Timer() {
        @Override
        public void run() {
            isMarkerLongPressed = true;
            updateFavourites(clickedMarker, clickedMarkerRelatedObjectId);
        }
    };

    protected abstract void updateFavourites(Marker clickedMarker, Integer currentlyPressedMarker);

    Marker createMarker(final Integer objectId, LatLng location, String iconFile) {
        MarkerOptions options = MarkerOptions.newInstance();
        options.setPosition(location);
        final MarkerImage markerImage = MarkerImage.newInstance(iconFile);
        options.setIcon(markerImage);
        final Marker marker = Marker.newInstance(options);
        marker.setMap(getMapWidget());

        marker.addClickHandler(event -> {
            if (!isMarkerLongPressed) {
                final TristarObject cameraDetails = getCameraDetails(objectId);
                showCameraDialog(cameraDetails.getName(), objectId);
            }
        });

        marker.addMouseDownHandler(event -> {
            isMarkerLongPressed = false;
            clickedMarkerRelatedObjectId = objectId;
            clickedMarker = marker;
            longPressTimer.schedule(LONG_PRESS_TIME);
        });

        marker.addMouseUpHandler(event -> longPressTimer.cancel());

        return marker;
    }

    abstract TristarObject getCameraDetails(final Integer objectId);

    abstract void showCameraDialog(String title, Integer objectId);

    void updateMarkersCache(Integer objectId, Marker marker) {
        markers.put(objectId, marker);
    }

    Marker getMarker(Integer objectId) {
        return markers.get(objectId);
    }

}
