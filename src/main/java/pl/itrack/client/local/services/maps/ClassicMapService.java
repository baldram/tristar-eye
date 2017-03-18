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

import com.google.gwt.core.client.Callback;
import com.google.gwt.dom.client.Element;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.layers.TrafficLayer;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.placeslib.Autocomplete;
import com.google.gwt.maps.client.placeslib.AutocompleteOptions;
import com.google.gwt.maps.client.placeslib.PlaceGeometry;
import com.google.gwt.maps.client.placeslib.PlaceResult;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import pl.itrack.client.local.config.AppSettings;
import pl.itrack.client.local.event.CameraHighlight;
import pl.itrack.client.local.event.UpdateTrafficOccurrencesLayer;
import pl.itrack.client.local.services.user.Settings;
import pl.itrack.client.local.services.utils.WktUtil;
import pl.itrack.client.local.view.helpers.Texts;
import pl.itrack.client.local.view.widgets.modals.CameraDialog;
import pl.itrack.client.shared.model.Coordinates;
import pl.itrack.client.shared.model.TristarObject;
import pl.itrack.client.shared.services.TristarDataService;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

import static com.google.gwt.maps.client.placeslib.AutocompleteType.ESTABLISHMENT;
import static com.google.gwt.maps.client.placeslib.AutocompleteType.GEOCODE;

public class ClassicMapService extends AbstractMapService {

    private static final double INITIAL_LONGITUDE = 18.605209;
    private static final double INITIAL_LATITUDE = 54.383967;
    private static final int ZOOM = 14;

    @Inject
    private TristarDataService dataService;

    @Inject
    private WktUtil wktUtil;

    @Inject
    private Settings userSettings;

    @Inject
    private AppSettings appSettings;

    @Inject
    private CameraDialog modal;

    @Inject
    private GMapManager mapManager;

    @Inject
    private TrafficOccurrencesManager trafficOccurrencesManager;

    private Set<Integer> favouriteCameras;

    public void initializeMap() {
        super.initializeMap();
        favouriteCameras = userSettings.getUserFavouriteCameras();
        userSettings.displayWelcomeHelpOnce();
        initializeTrafficLayer(getMapWidget());
    }

    private void initializeTrafficLayer(MapWidget assignedMapWidget) {
        final TrafficLayer trafficLayer = TrafficLayer.newInstance();
        trafficLayer.setMap(assignedMapWidget);
    }

    @Override
    protected MapOptions getMapOptions() {
        MapOptions opts = MapOptions.newInstance();
        opts.setZoom(ZOOM);
        opts.setMapTypeId(MapTypeId.ROADMAP);
        // ops.setCenter() can't be used here when service is injected since LatLng.newInstance() starts using "maps" component before it's initialized by AjaxLoader.loadApi()
        return opts;
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
                    Window.alert(Texts.MSG_LOCATION_NOT_SUPPORTED);
                }
            });
    }

    public void bindTextBoxWithAutoComplete(TextBox searchBox) {
        final Autocomplete autoComplete = createAutoCompleteWithChangeListener(searchBox);

        getMapWidget().addBoundsChangeHandler(event -> {
            LatLngBounds bounds = getMapWidget().getBounds();
            autoComplete.setBounds(bounds);
        });
    }

    private Autocomplete createAutoCompleteWithChangeListener(TextBox searchBox) {
        Element element = searchBox.getElement();
        final Autocomplete autoComplete = Autocomplete.newInstance(element, getAutoCompleteOptions());

        autoComplete.addPlaceChangeHandler(event -> {

            PlaceResult result = autoComplete.getPlace();

            PlaceGeometry geometry = result.getGeometry();
            LatLng center = geometry.getLocation();

            getMapWidget().panTo(center);
            getMapWidget().setZoom(ZOOM);
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
    public void addMarkers(List<TristarObject> cameras) {
        for (TristarObject camera : cameras) {
            Coordinates coordinates = wktUtil.getPointAsPair(camera.getWkt());
            if (coordinates != null) {
                addMarker(camera, coordinates);
            }
        }
    }

    private void addMarker(TristarObject camera, Coordinates coordinates) {
        Coordinates latLngCoordinates = wktUtil.toLatitudeLongitude(coordinates);
        String cameraIconFile = getCameraIcon(camera.getId());
        Marker marker = mapManager.createMarker(getMapWidget(), LatLng.newInstance(latLngCoordinates.getX(), latLngCoordinates.getY()), cameraIconFile);
        addCameraClickHandlers(camera.getId(), marker);
        updateMarkersCache(camera.getId(), marker);
    }

    private void addMarkers(@Observes UpdateTrafficOccurrencesLayer event) {
        trafficOccurrencesManager.addMarkers(getMapWidget(), event.getTrafficOccurrences());
    }

    private String getCameraIcon(Integer cameraId) {
        return appSettings.getImagesPath() + ((favouriteCameras.contains(cameraId)) ? appSettings.getCameraIconSelected() : appSettings.getCameraIcon());
    }

    // TODO: try to implement it in the parent abstract class and inject services there
    @Override
    protected TristarObject getCameraDetails(Integer objectId) {
        return dataService.getCamera(objectId);
    }

    @Override
    protected void showCameraDialog(String title, Integer objectId) {
        modal.show(title, objectId);
    }

    @Override
    public double getInitialLatitude() {
        return INITIAL_LATITUDE;
    }

    @Override
    public double getInitialLongitude() {
        return INITIAL_LONGITUDE;
    }

    @Override
    protected void updateFavourites(Marker clickedMarker, Integer objectId) {
        favouriteCameras = userSettings.addOrRemoveFavouriteCamera(objectId);
        clickedMarker.setIcon(getCameraIcon(objectId));
    }

    @SuppressWarnings("unused")
    private void updateFavourites(@Observes CameraHighlight event) {
        if (event.getId() != null) {
            updateFavourites(getMarker(event.getId()), event.getId());
        }
    }
}