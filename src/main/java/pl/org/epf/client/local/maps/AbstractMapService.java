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

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerImage;
import com.google.gwt.maps.client.overlays.MarkerOptions;

abstract class AbstractMapService implements MapService {

    protected static final String ICON_FILE_CAMERA = "camera.png";
	private static final String CSS_CLASS_MAP_WIDGET = "mapWidget";
    private static final String MAX_SIZE = "100%";
    
	private MapWidget mapWidget;

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
    
    Marker createMarker(final Integer id, LatLng location, String iconFile) {
        MarkerOptions options = MarkerOptions.newInstance();
        options.setPosition(location);
        MarkerImage markerImage = MarkerImage.newInstance("images/" + iconFile);
        options.setIcon(markerImage);
        Marker marker = Marker.newInstance(options);
        marker.setMap(getMapWidget());

        marker.addClickHandler(new ClickMapHandler() {
            @Override
            public void onEvent(ClickMapEvent event) {
            	// TODO: open details dialog
            }
        });

        return marker;
    }
	
}
