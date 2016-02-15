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

import com.google.common.collect.ImmutableMap;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;

import org.apache.commons.lang3.StringUtils;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageShown;
import pl.org.epf.client.local.fixture.StreetCamerasDataSet;
import pl.org.epf.client.local.services.maps.MapService;
import pl.org.epf.client.local.model.TristarObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

import static pl.org.epf.client.local.view.MapTabViewModel.PAGE_NAME;

@ApplicationScoped
@Page(role = DefaultPage.class, path = PAGE_NAME)
public class MapTabViewModel extends Composite {
    public static final String PAGE_NAME = "map";

    private static final String MAP_CONTAINER_ID = "mapContainer";
    private static final boolean SENSOR = true;

    private final HTMLPanel mapContainer;

    @Inject
    private MapService mapService;

    @Inject
    private StreetCamerasDataSet streetCamerasMock;

    private TextBox searchBox;

    public MapTabViewModel() {
        mapContainer = createContentPanel();
    }

    private HTMLPanel createContentPanel() {
        HTMLPanel content = new HTMLPanel(StringUtils.EMPTY);
        content.getElement().setId(MAP_CONTAINER_ID);
        return content;
    }

    @PostConstruct
    private void loadMapApi() {
        initWidget(mapContainer);
        Runnable onLoad = new Runnable() {
            @Override
            public void run() {
                initializeMapAndSetCenter();
                bindMapWithView();
                addMarkers();
            }
        };

        LoadApi.go(onLoad, getLoadLibraries(), SENSOR);
    }

    @PageShown
    private void refreshView() {
        // Due to refresh issues according to the MapAPI here is some workaround.
        // http://stackoverflow.com/questions/5454535/fire-resizeevent-in-gwt-google-web-toolkit
        mapService.getMapWidget().triggerResize();
    }

    private void initializeMapAndSetCenter() {
        mapService.initializeMap();
        LatLng initialLocation = LatLng.newInstance(mapService.getInitialLatitude(), mapService.getInitialLongitude());
        mapService.getMapWidget().setCenter(initialLocation);
    }

    private void bindMapWithView() {
        mapContainer.clear();
        mapContainer.add(mapService.getMapWidget());

        // TODO: to provide searchBox using IOC or bind using events
        //mapService.bindTextBoxWithAutoComplete(searchBox);
    }

    private void addMarkers() {
        ImmutableMap<Integer, TristarObject> cameras = streetCamerasMock.getCameras();
        mapService.addMarkers(cameras);
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

    public void setSearchBox(TextBox searchBox) {
        this.searchBox = searchBox;
    }

    public HTMLPanel getMainPanel() {
        return mapContainer;
    }
}
