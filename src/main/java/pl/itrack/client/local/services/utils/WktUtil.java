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
package pl.itrack.client.local.services.utils;

import pl.itrack.client.shared.model.Coordinates;

public class WktUtil {

    private static final String COORDINATES_SEPARATOR = " ";
    private static final String WKT_POINT_BLOCK_OPEN = "\\[POINT \\(";
    private static final String WKT_BLOCK_CLOSE = ")]";

    // TODO: Try to find jts-gwt artifact in Maven repo and then use here read() from WKTReader.
    // TODO: Guava-gwt Optional seems to not work properly - to try with the other Guava version.
    public Coordinates getPointAsPair(String input) {
        String cleanedUpInput = getPureDigits(input);
        String[] pair = cleanedUpInput.split(COORDINATES_SEPARATOR);
        if (pair.length != 2) {
            return null;
        }
        return new Coordinates(new Double(pair[0]), new Double(pair[1]));
    }

    private String getPureDigits(String input) {
        String cleanedUpInput = input.replaceFirst(WKT_POINT_BLOCK_OPEN, "");
        if (cleanedUpInput.endsWith(WKT_BLOCK_CLOSE)) {
            cleanedUpInput = cleanedUpInput.substring(0, cleanedUpInput.length() - 2);
        }
        return cleanedUpInput;
    }

    /**
     * Converts EPSG:3857 aka EPSG:900913 (Spherical Mercator) into EPSG:4326 (WGS84)
     *
     * @param point - Coordinates expressed in EPSG:3857
     *
     * @return Coordinates as latitude, longitude - EPSG:4326 standard point (World Geodetic System 84)
     */
    public Coordinates toLatitudeLongitude(Coordinates point) {
        double[] wgs84 = transformEPSG3857To4326(point.getX(), point.getY());

        Double longitude = wgs84[0];
        Double latitude = wgs84[1];
        return new Coordinates(latitude, longitude);
    }

    private static native double[] transformEPSG3857To4326(Double x, Double y)
    /*-{
        return $wnd.ol.proj.transform([x, y], 'EPSG:3857', 'EPSG:4326');
    }-*/;

}
