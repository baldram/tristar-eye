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

import static com.google.gwt.maps.client.placeslib.AutocompleteType.ESTABLISHMENT;
import static com.google.gwt.maps.client.placeslib.AutocompleteType.GEOCODE;

import com.google.common.collect.ImmutableMap;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.events.bounds.BoundsChangeMapEvent;
import com.google.gwt.maps.client.events.bounds.BoundsChangeMapHandler;
import com.google.gwt.maps.client.events.place.PlaceChangeMapEvent;
import com.google.gwt.maps.client.events.place.PlaceChangeMapHandler;
import com.google.gwt.maps.client.layers.TrafficLayer;
import com.google.gwt.maps.client.placeslib.Autocomplete;
import com.google.gwt.maps.client.placeslib.AutocompleteOptions;
import com.google.gwt.maps.client.placeslib.PlaceGeometry;
import com.google.gwt.maps.client.placeslib.PlaceResult;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import pl.org.epf.client.local.model.TristarObject;

public class ClassicMapService extends AbstractMapService {

    private static final double INITIAL_LONGITUDE = 18.605209;
    private static final double INITIAL_LATTITUDE = 54.383967;
    private static final int ZOOM = 14;

    public void initializeMap() {
        super.initializeMap();
        initializeTrafficLayer(getMapWidget());
    }

    @Override
    protected MapOptions getMapOptions() {
        MapOptions opts = MapOptions.newInstance();
        opts.setZoom(ZOOM);
        opts.setMapTypeId(MapTypeId.ROADMAP);
        // ops.setCenter() can't be used here when service is injected since LatLng.newInstance() starts using "maps" component before it's initialized by AjaxLoader.loadApi()
        return opts;
    }

    private TrafficLayer initializeTrafficLayer(MapWidget assignedMapWidget) {
        final TrafficLayer trafficLayer = TrafficLayer.newInstance();
        trafficLayer.setMap(assignedMapWidget);
        return trafficLayer;
    }

    public void setCurrentLocationIfSupported() {
        Geolocation.getIfSupported().getCurrentPosition(
                new Callback<Position, PositionError>() {

                    @Override
                    public void onSuccess(Position result) {
                        Position.Coordinates coordinates = result.getCoordinates();
                        LatLng center = LatLng.newInstance(coordinates.getLatitude(), coordinates.getLongitude());
                        getMapWidget().setCenter(center);
                    }

                    @Override
                    public void onFailure(PositionError reason) {
                        Window.alert("Your browser or device does not support location!");
                    }
                });
    }

    public void bindTextBoxWithAutoComplete(TextBox searchBox) {
        final Autocomplete autoComplete = createAutoCompleteWithChangeListener(searchBox);

        getMapWidget().addBoundsChangeHandler(new BoundsChangeMapHandler() {
            public void onEvent(BoundsChangeMapEvent event) {
                LatLngBounds bounds = getMapWidget().getBounds();
                autoComplete.setBounds(bounds);
            }
        });
    }

    private Autocomplete createAutoCompleteWithChangeListener(TextBox searchBox) {
        Element element = searchBox.getElement();
        final Autocomplete autoComplete = Autocomplete.newInstance(element, getAutoCompleteOptions());

        autoComplete.addPlaceChangeHandler(new PlaceChangeMapHandler() {
            public void onEvent(PlaceChangeMapEvent event) {

                PlaceResult result = autoComplete.getPlace();

                PlaceGeometry geomtry = result.getGeometry();
                LatLng center = geomtry.getLocation();

                getMapWidget().panTo(center);
                getMapWidget().setZoom(ZOOM);

                GWT.log("place changed center=" + center);
            }
        });
        return autoComplete;
    }

    private AutocompleteOptions getAutoCompleteOptions() {
        AutocompleteOptions options = AutocompleteOptions.newInstance();
        options.setTypes(ESTABLISHMENT, GEOCODE);
        options.setBounds(getMapWidget().getBounds());
        return options;
    }

    @Override
    public void addMarkers(ImmutableMap<Integer, TristarObject> cameras) {
        for (TristarObject camera : cameras.values()) {
            createMarker(camera.getId(), LatLng.newInstance(camera.getLatitude(), camera.getLongitude()), ICON_FILE_CAMERA);
        }
    }

    @Override
    public double getInitialLatitude() {
        return INITIAL_LATTITUDE;
    }

    @Override
    public double getInitialLongitude() {
        return INITIAL_LONGITUDE;
    }
}