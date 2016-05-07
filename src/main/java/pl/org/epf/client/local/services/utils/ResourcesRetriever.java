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

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;
import pl.org.epf.client.shared.model.TristarObjectType;

import java.util.Date;

public class ResourcesRetriever {

    // TODO: move this to ErraiApp.properties and then inject here
    private static final String IMAGES_PATH = "http://tristareye.kodujdlapolski.pl/tristar-fixture/cams/";
    private static final String INFO_BOARDS_PATH = "http://tristareye.kodujdlapolski.pl/tristar-fixture/info-boards/";

    private static final String IMAGES_EXT = ".png";
    private static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmss";

    public String getImageUrl(TristarObjectType type, int resourceId, boolean preventCache) {
        String filePath = getResourcePath(type) + resourceId + IMAGES_EXT;
        if (preventCache) {
            filePath += "?" + getTimeStamp();
        }
        return filePath;
    }

    private String getResourcePath(TristarObjectType type) {
        return (TristarObjectType.CAMERA == type) ? IMAGES_PATH : INFO_BOARDS_PATH;
    }

    private String getTimeStamp() {
        Date date = new Date();
        DateTimeFormat dtf = DateTimeFormat.getFormat(TIMESTAMP_FORMAT);
        return dtf.format(date, TimeZone.createTimeZone(0));
    }
}
