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

package pl.org.epf.client.local.services.maps;

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.events.mousedown.MouseDownMapEvent;
import com.google.gwt.maps.client.events.mousedown.MouseDownMapHandler;
import com.google.gwt.maps.client.events.mouseup.MouseUpMapEvent;
import com.google.gwt.maps.client.events.mouseup.MouseUpMapHandler;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerImage;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.user.client.Timer;
import pl.org.epf.client.shared.model.TristarObject;

abstract class AbstractMapService implements MapService {

    protected static final String ICON_FILE_CAMERA = "camera.png";
    protected static final String ICON_FILE_CAMERA_SELECTED = "camera-selected.png";
    protected static final String IMAGES_PATH = "images/";
    private static final String CSS_CLASS_MAP_WIDGET = "mapWidget";
    private static final String MAX_SIZE = "100%";
    private static final int LONG_PRESS_TIME = 500;

    private MapWidget mapWidget;

    private Integer clickedMarkerRelatedObjectId;
    private Marker clickedMarker;
    private boolean isMarkerLongPressed = false;

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

    final Timer longPressTimer = new Timer() {
        @Override
        public void run() {
            isMarkerLongPressed = true;
            updateFavourites(clickedMarker, clickedMarkerRelatedObjectId);
        }
    };

    protected abstract void updateFavourites(Marker clickedMarker, Integer currentlyPressedMarker);

    protected Marker createMarker(final Integer objectId, LatLng location, String iconFile) {
        MarkerOptions options = MarkerOptions.newInstance();
        options.setPosition(location);
        final MarkerImage markerImage = MarkerImage.newInstance(iconFile);
        options.setIcon(markerImage);
        final Marker marker = Marker.newInstance(options);
        marker.setMap(getMapWidget());

        marker.addClickHandler(new ClickMapHandler() {
            @Override
            public void onEvent(ClickMapEvent event) {
                if (!isMarkerLongPressed) {
                    final TristarObject cameraDetails = getCameraDetails(objectId);
                    showModalDialog(cameraDetails.getName(), getImageUrl(objectId));
                }
            }
        });

        marker.addMouseDownHandler(new MouseDownMapHandler() {
            @Override
            public void onEvent(MouseDownMapEvent event) {
                isMarkerLongPressed = false;
                clickedMarkerRelatedObjectId = objectId;
                clickedMarker = marker;
                longPressTimer.schedule(LONG_PRESS_TIME);
            }
        });

        marker.addMouseUpHandler(new MouseUpMapHandler() {
            @Override
            public void onEvent(MouseUpMapEvent event) {
                longPressTimer.cancel();
            }
        });

        return marker;
    }

    abstract String getImageUrl(final Integer objectId);

    abstract TristarObject getCameraDetails(final Integer objectId);

    public final native void showModalDialog(String objName, String imageUrl) /*-{
        $wnd.showDialog({
            negative: {title: 'Zamknij'},
            positive: false,
            cancelable: true,
            title: objName,
            text: '<img src="' + imageUrl + '" class="modalCamView" id="modalCamView" />'
        });
    }-*/;

}
