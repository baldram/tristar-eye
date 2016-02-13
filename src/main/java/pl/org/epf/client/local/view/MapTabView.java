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
import com.google.gwt.user.client.ui.TextBox;

import pl.org.epf.client.local.datamock.StreetCamerasMock;
import pl.org.epf.client.local.services.maps.MapService;
import pl.org.epf.client.local.model.TristarObject;
import pl.org.epf.client.local.view.widgets.SimpleDivPanel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

@ApplicationScoped
public class MapTabView extends Composite {
    private static final String MAPS_CONTAINER = "maps-container";
    private static final String MAX_SIZE = "100%";
    private static final boolean SENSOR = true;

    private final SimpleDivPanel content;

    @Inject
    private MapService mapService;

    @Inject
    private StreetCamerasMock streetCamerasMock;

    private TextBox searchBox;

    public MapTabView() {
        content = new SimpleDivPanel(MAPS_CONTAINER);
        initWidget(content);
        content.setWidth(MAX_SIZE);
        content.setHeight(MAX_SIZE);
    }

    @PostConstruct
    private void loadMapApi() {
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

    private void initializeMapAndSetCenter() {
        mapService.initializeMap();
        LatLng initialLocation = LatLng.newInstance(mapService.getInitialLatitude(), mapService.getInitialLongitude());
        mapService.getMapWidget().setCenter(initialLocation);
    }

    private void bindMapWithView() {
        content.add(mapService.getMapWidget(), MAPS_CONTAINER);

        // TODO: to provide searchBox using IOC
        mapService.bindTextBoxWithAutoComplete(searchBox);
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
        return content;
    }
}
