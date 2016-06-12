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
package pl.org.epf.client.local.services.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import pl.org.epf.client.shared.model.Coordinates;

public class WktUtilTest {

    public static final double COORDINATE_X = 2065593.15287473;
    public static final double COORDINATE_Y = 7257176.57009129;

    WktUtil wktUtil = new WktUtil();

    @Test
    public void parsePoint() throws Exception {
        String input = "[POINT (" + COORDINATE_X + " " + COORDINATE_Y + ")]";

        Coordinates point = wktUtil.getPointAsPair(input);

        assertThat(point.getX(), is(COORDINATE_X));
        assertThat(point.getY(), is(COORDINATE_Y));
    }

    @Test
    public void parserIgnoresInvalidDataData() throws Exception {
        String input = "[? (? ?)]";

        Coordinates point = wktUtil.getPointAsPair(input);

        assertNull(point);
    }
}
