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

package pl.itrack.client.local.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.user.client.ui.TextBox;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialButton;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import pl.itrack.client.local.services.maps.MapSearchInputProvider;
import pl.itrack.client.local.event.FavouritesModify;
import pl.itrack.client.local.event.MapViewTypeChange;
import pl.itrack.client.local.services.maps.ClassicMapService;
import pl.itrack.client.local.services.maps.MapService;
import pl.itrack.client.local.services.maps.TricitySchemaService;
import pl.itrack.client.local.view.helpers.UiHelper;
import pl.itrack.client.local.view.widgets.DivContainer;
import pl.itrack.client.shared.model.TristarObject;
import pl.itrack.client.shared.services.TristarDataService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.gwt.maps.client.LoadApi.LoadLibrary.*;
import static pl.itrack.client.local.view.MapTabViewModel.PAGE_NAME;

@ApplicationScoped
@Page(role = DefaultPage.class, path = PAGE_NAME)
@Templated("MapView.html#mapViewRoot")
public class MapTabViewModel extends BasePage {
    public static final String PAGE_NAME = "map";

    private static final boolean SENSOR = true;

    @Inject @DataField private DivContainer mapContainer;

    @Inject @DataField private MaterialButton locationButton;

    private boolean classicMapType = true;

    @Inject private ClassicMapService classicMapService;

    @Inject private TricitySchemaService citySchemaService;

    @Inject private TristarDataService dataService;

    @Inject private MapSearchInputProvider<TextBox> searchInputProvider;

    @Inject private UiHelper uiHelper;

    @PostConstruct
    private void init() {
        loadMapApi();
        uiHelper.buildFabButton(locationButton, IconType.MY_LOCATION);
    }

    private void loadMapApi() {
        Runnable onLoad = () -> {
            initializeMapAndSetCenter();
            bindMapWithView();
            addMarkers();
            refreshView();
        };

        LoadApi.go(onLoad, getLoadLibraries(), SENSOR);
    }

    @Override
    protected void onPageShown() {
        refreshView();
    }

    private void refreshView() {
        // Due to refresh issues according to the MapAPI here is some workaround.
        // http://stackoverflow.com/questions/5454535/fire-resizeevent-in-gwt-google-web-toolkit
        if (getMapService().getMapWidget() != null) {
            getMapService().getMapWidget().triggerResize();
        }
    }

    private void initializeMapAndSetCenter() {
        getMapService().initializeMap();
        LatLng initialLocation = LatLng.newInstance(getMapService().getInitialLatitude(), getMapService().getInitialLongitude());
        getMapService().getMapWidget().setCenter(initialLocation);
    }

    private void bindMapWithView() {
        mapContainer.clear();
        mapContainer.add(getMapService().getMapWidget());

        getMapService().bindTextBoxWithAutoComplete(searchInputProvider.get());
    }

    private void addMarkers() {
        List<TristarObject> markers = dataService.getAllCameras();
        getMapService().addMarkers(markers);
    }

    private ArrayList<LoadApi.LoadLibrary> getLoadLibraries() {
        ArrayList<LoadApi.LoadLibrary> loadLibraries = new ArrayList<>();
        loadLibraries.addAll(Arrays.asList(ADSENSE, DRAWING, GEOMETRY, PANORAMIO, PLACES, WEATHER, VISUALIZATION));
        return loadLibraries;
    }

    @SuppressWarnings("unused")
    private void onMapTypeChange(@Observes MapViewTypeChange event) {
        classicMapType = !classicMapType;
        // TODO: to optimize and avoid loading whole the API each time
        loadMapApi();
    }

    @SuppressWarnings("unused")
    private void onFavouritesModified(@Observes FavouritesModify event) {
        loadMapApi();
    }

    @SuppressWarnings("unused")
    @EventHandler("locationButton")
    public void setCurrentLocation(ClickEvent e) {
        classicMapService.setCurrentLocationIfSupported();
    }

    private MapService getMapService() {
        return classicMapType ? classicMapService : citySchemaService;
    }

}
