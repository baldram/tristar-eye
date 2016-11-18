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

package pl.org.epf.client.local.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;

import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import pl.org.epf.client.local.event.FavouritesModify;
import pl.org.epf.client.local.event.MapViewTypeChange;
import pl.org.epf.client.local.services.maps.ClassicMapService;
import pl.org.epf.client.local.services.maps.MapSearchInputProvider;
import pl.org.epf.client.local.services.maps.MapService;
import pl.org.epf.client.local.view.widgets.DivContainer;
import pl.org.epf.client.shared.services.TristarDataService;
import pl.org.epf.client.shared.model.TristarObject;
import pl.org.epf.client.local.services.maps.TricitySchemaService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static pl.org.epf.client.local.view.MapTabViewModel.PAGE_NAME;

@ApplicationScoped
@Page(role = DefaultPage.class, path = PAGE_NAME)
@Templated("MapView.html#mapViewRoot")
public class MapTabViewModel extends BasePage {
    public static final String PAGE_NAME = "map";

    private static final boolean SENSOR = true;

    @Inject
    @DataField
    private DivContainer mapContainer;

    @Inject
    @DataField
    private Button locationButton;

    private boolean classicMapType = true;

    @Inject
    private ClassicMapService classicMapService;

    @Inject
    private TricitySchemaService citySchemaService;

    @Inject
    private TristarDataService dataService;

    @Inject
    private MapSearchInputProvider<TextBox> searchInputProvider;

    @PostConstruct
    private void loadMapApi() {
        Runnable onLoad = new Runnable() {
            @Override
            public void run() {
                initializeMapAndSetCenter();
                bindMapWithView();
                addMarkers();
                refreshView();
            }
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
        getMapService().getMapWidget().triggerResize();
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
        loadLibraries.add(LoadApi.LoadLibrary.ADSENSE);
        loadLibraries.add(LoadApi.LoadLibrary.DRAWING);
        loadLibraries.add(LoadApi.LoadLibrary.GEOMETRY);
        loadLibraries.add(LoadApi.LoadLibrary.PANORAMIO);
        loadLibraries.add(LoadApi.LoadLibrary.PLACES);
        loadLibraries.add(LoadApi.LoadLibrary.WEATHER);
        loadLibraries.add(LoadApi.LoadLibrary.VISUALIZATION);
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

    @EventHandler("locationButton")
    public void setCurrentLocation(ClickEvent e) {
        classicMapService.setCurrentLocationIfSupported();
    }

    public MapService getMapService() {
        return classicMapType ? classicMapService : citySchemaService;
    }

}
