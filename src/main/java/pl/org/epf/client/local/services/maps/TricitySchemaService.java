/*
 * Copyright 2016 Marek Wojtuszkiewicz
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

import com.google.common.collect.ImmutableMap;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.Point;
import com.google.gwt.maps.client.base.Size;
import com.google.gwt.maps.client.controls.MapTypeControlOptions;
import com.google.gwt.maps.client.maptypes.ImageMapType;
import com.google.gwt.maps.client.maptypes.ImageMapTypeOptions;
import com.google.gwt.maps.client.maptypes.TileUrlCallBack;
import com.google.gwt.user.client.ui.TextBox;
import pl.org.epf.client.local.model.TristarObject;

public class TricitySchemaService extends AbstractMapService {

    private static final double INITIAL_LATITUDE = -90d;
    private static final double INITIAL_LONGITUDE = 180d;

    private static final double TILE_SIZE = 256d;
    private static final int BASE_TILE_RANGE = 4;

    private static final int ZOOM_MIN = 2;
    private static final int ZOOM_MAX = 5;
    private static final int ZOOM_INITIAL = 2;

    private final static String BACKGROUND_COLOR = "#ffffff";
    private final static String MAP_TYPE_ID = "Tristar";

    @Override
    public void initializeMap() {
        super.initializeMap();
        initialize(getMapWidget());
    }

    @Override
    public MapOptions getMapOptions() {
        MapTypeControlOptions controlOpts = MapTypeControlOptions.newInstance();
        controlOpts.setMapTypeIds(new String[] { MAP_TYPE_ID });

        MapOptions opts = MapOptions.newInstance();
        opts.setZoom(ZOOM_INITIAL);
        opts.setMapTypeControlOptions(controlOpts);
        opts.setStreetViewControl(false);
        opts.setBackgroundColor(BACKGROUND_COLOR);
        opts.setCenter(LatLng.newInstance(INITIAL_LATITUDE, INITIAL_LONGITUDE));
        return opts;
    }

    private void initialize(MapWidget assignedMapWidget) {
        assignedMapWidget.getMapTypeRegistry().set(MAP_TYPE_ID, getCitySchemaMapType());
        assignedMapWidget.setMapTypeId(MAP_TYPE_ID);
    }

    private ImageMapType getCitySchemaMapType() {
        ImageMapTypeOptions opts = ImageMapTypeOptions.newInstance();
        opts.setMinZoom(ZOOM_MIN);
        opts.setMaxZoom(ZOOM_MAX);
        opts.setName(MAP_TYPE_ID);
        opts.setTileSize(Size.newInstance(TILE_SIZE, TILE_SIZE));
        opts.setTileUrl(new TileUrlCallBack() {
            @Override
            public String getTileUrl(Point point, int zoomLevel) {
                Point normalizedCoord = getNormalizedCoords(point, zoomLevel);

                if (normalizedCoord == null) {
                    return null;
                }

                return "images/cityschema/" + (zoomLevel-2) + "/"
                        + (zoomLevel-2) + "-" + (int)normalizedCoord.getY() + "-" + (int)(normalizedCoord.getX()) + ".png";
            }
        });

        return ImageMapType.newInstance(opts);
    }

    public static Point getNormalizedCoords(Point coords, int zoom) {
        double y = coords.getY();
        double x = coords.getX();

        double currentTileRange = BASE_TILE_RANGE << (zoom-2);

        if (x < 0 || x >= currentTileRange) {
            //x = (x % currentTileRange + currentTileRange) % currentTileRange;     // repeat horizontal
            return null;                                                            // don't repeat horizontal
        }

        if (y < 0 || y >= currentTileRange) {
            //y = (y % currentTileRange + currentTileRange) % currentTileRange;     // repeat vertical
            return null;                                                            // don't repeat vertical
        }

        return Point.newInstance(x, y);
    }

    @Override
    public void bindTextBoxWithAutoComplete(TextBox searchBox) {
        // TODO: optional binding for future searching through the schema markers
    }

    @Override
    public void addMarkers(ImmutableMap<Integer, TristarObject> cameras) {
        // TODO: to add markers using proper lat&lang values for this kind of map
    }

    @Override
    public double getInitialLatitude() {
        return INITIAL_LATITUDE;
    }

    @Override
    public double getInitialLongitude() {
        return INITIAL_LONGITUDE;
    }
}
