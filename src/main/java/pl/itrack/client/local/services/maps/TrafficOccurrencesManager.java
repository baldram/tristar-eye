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

import com.google.common.collect.ImmutableMap;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlays.Marker;
import pl.itrack.client.local.config.AppSettings;
import pl.itrack.client.local.event.UpdateTrafficOccurrencesLayer;
import pl.itrack.client.local.services.utils.WktUtil;
import pl.itrack.client.local.view.widgets.modals.SimpleAsyncDialog;
import pl.itrack.client.shared.model.Coordinates;
import pl.itrack.client.shared.model.TrafficOccurrenceDto;
import pl.itrack.client.shared.services.restrequest.RestRequestList;
import pl.itrack.client.shared.services.restrequest.RestRequestSingle;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class TrafficOccurrencesManager {

    private static final String ACCIDENT_ICON = "event.1.accident.png";
    private static final String BREAKDOWN_ICON = "event.2.breakdown.png";
    private static final String EXCLUDED_ROAD_ICON = "event.3.excluded_road.png";
    private static final String ROADWORKS_ICON = "event.4.roadworks.png";
    private static final String MASS_EVENT_ICON = "event.5.mass_event.png";
    private static final String TRAFFIC_DATA_DEFAULT_ICON = "event.0.traffic_data.png";
    private static final String ERROR_MESSAGE = "ERROR - please try again";

    private final SimpleAsyncDialog simpleDialog;

    private final WktUtil wktUtil;

    private final GMapManager mapManager;

    private final AppSettings settings;

    private final Event<UpdateTrafficOccurrencesLayer> addTrafficOccurrence;

    @Inject
    public TrafficOccurrencesManager(SimpleAsyncDialog simpleDialog, WktUtil wktUtil, GMapManager mapManager,
                                     Event<UpdateTrafficOccurrencesLayer> addTrafficOccurrence, AppSettings settings) {
        this.simpleDialog = simpleDialog;
        this.wktUtil = wktUtil;
        this.mapManager = mapManager;
        this.addTrafficOccurrence = addTrafficOccurrence;
        this.settings = settings;
    }

    /**
     * Retrieves REST data and fires add marker event to receivers
     */
    public void retrieveAndAddMarkers() throws RequestException {
        new RestRequestList<TrafficOccurrenceDto>(TrafficOccurrenceDto.class, settings.getEventsApiUrl()) {
            @Override
            public void apply(List<TrafficOccurrenceDto> occurrences) {
                addMarkers(occurrences);
            }
        }.call();
    }

    private void addMarkers(List<TrafficOccurrenceDto> occurrences) {
        if (occurrences != null && !occurrences.isEmpty()) {
            addTrafficOccurrence.fire(new UpdateTrafficOccurrencesLayer(occurrences));
        }
    }

    void addMarkers(final MapWidget mapWidget, List<TrafficOccurrenceDto> trafficOccurrences) {
        // TODO: remove existing before add
        for (TrafficOccurrenceDto occurrence : trafficOccurrences) {
            Coordinates coordinates = wktUtil.getPointAsPair(occurrence.getLocation());
            if (coordinates != null) {
                addMarker(mapWidget, occurrence, coordinates);
            }
        }
    }

    private void addMarker(MapWidget mapWidget, TrafficOccurrenceDto occurrence, Coordinates coordinates) {
        Coordinates latLngCoordinates = wktUtil.toLatitudeLongitude(coordinates);
        String cameraIconFile = getTrafficOccurrenceIcon(Integer.valueOf(occurrence.getIconType()));
        Marker marker = mapManager.createMarker(mapWidget, LatLng.newInstance(latLngCoordinates.getX(), latLngCoordinates.getY()), cameraIconFile);
        marker.setTitle(occurrence.getEventName());
        if (occurrence.getEventName() != null) {
            addOccurrenceClickHandler(occurrence, marker);
        }
    }

    private String getTrafficOccurrenceIcon(Integer iconType) {

        Map<Integer, String> iconsMapping = ImmutableMap.of(
                1, ACCIDENT_ICON,
                2, BREAKDOWN_ICON,
                3, EXCLUDED_ROAD_ICON,
                4, ROADWORKS_ICON,
                5, MASS_EVENT_ICON
        );

        String iconFile = iconsMapping.get(iconType);
        if (iconFile.isEmpty()) {
            iconFile = TRAFFIC_DATA_DEFAULT_ICON;
        }
        return settings.getImagesPath() + iconFile;
    }

    private void addOccurrenceClickHandler(TrafficOccurrenceDto occurrence, Marker marker) {
        marker.addClickHandler(clickEvent -> {
            simpleDialog.show();
            showOccurrenceDetails(Integer.valueOf(occurrence.getId()));
        });
    }

    private void showOccurrenceDetails(int id) {
        try {
            new RestRequestSingle<TrafficOccurrenceDto>(TrafficOccurrenceDto.class, settings.getEventsApiUrl()) {
                @Override
                public void apply(TrafficOccurrenceDto occurrence) {
                    String title = occurrence.getTitle() + " / " + occurrence.getEventName();
                    String description = "<b>" + occurrence.getPeriod() + "</b><br />" + occurrence.getDescription();
                    simpleDialog.initContent(title, simpleDialog.getBodyContainer(description));
                }
            }.call("/" + id + "/resources/data");
        } catch (RequestException e) {
            simpleDialog.initContent(ERROR_MESSAGE, simpleDialog.getBodyContainer(ERROR_MESSAGE));
        }
    }

}
