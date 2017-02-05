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
import com.google.gwt.maps.client.layers.TrafficLayer;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.placeslib.Autocomplete;
import com.google.gwt.maps.client.placeslib.AutocompleteOptions;
import com.google.gwt.maps.client.placeslib.PlaceGeometry;
import com.google.gwt.maps.client.placeslib.PlaceResult;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import pl.itrack.client.local.services.user.Settings;
import pl.itrack.client.local.services.utils.ResourcesRetriever;
import pl.itrack.client.local.services.utils.WktUtil;
import pl.itrack.client.local.view.helpers.Texts;
import pl.itrack.client.local.view.widgets.ModalWindow;
import pl.itrack.client.shared.model.Coordinates;
import pl.itrack.client.shared.model.TristarObject;
import pl.itrack.client.shared.model.TristarObjectType;
import pl.itrack.client.shared.services.TristarDataService;

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
    private ResourcesRetriever retriever;

    @Inject
    private WktUtil wktUtil;

    @Inject
    private Settings settings;

    @Inject
    private ModalWindow modal;

    private Set<Integer> favouriteCameras;

    public void initializeMap() {
        super.initializeMap();
        favouriteCameras = settings.getUserFavaouriteCameras();
        settings.displayWelcomeHelpOnce();
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

            PlaceGeometry geomtry = result.getGeometry();
            LatLng center = geomtry.getLocation();

            getMapWidget().panTo(center);
            getMapWidget().setZoom(ZOOM);

            GWT.log("place changed center=" + center);
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
                Coordinates latLngCoordinates = wktUtil.toLatitudeLongitude(coordinates);
                String cameraIconFile = getCameraIcon(camera.getId());
                createMarker(camera.getId(), LatLng.newInstance(latLngCoordinates.getX(), latLngCoordinates.getY()), cameraIconFile);
            }
        }
    }

    private String getCameraIcon(Integer cameraId) {
        return IMAGES_PATH + ((favouriteCameras.contains(cameraId)) ? ICON_FILE_CAMERA_SELECTED : ICON_FILE_CAMERA);
    }

    // TODO: try to implement it in the parent abstract class and inject services there
    @Override
    TristarObject getCameraDetails(Integer objectId) {
        return dataService.getCamera(objectId);
    }

    @Override
    void showCameraDialog(String title, String imageUrl) {
        modal.showImageModalDialog(title, imageUrl);
    }

    @Override
    String getImageUrl(Integer objectId) {
        return retriever.getImageUrl(TristarObjectType.CAMERA, objectId, true);
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
        favouriteCameras = settings.addOrRemoveFavouriteCamera(objectId);
        clickedMarker.setIcon(getCameraIcon(objectId));
    }
}