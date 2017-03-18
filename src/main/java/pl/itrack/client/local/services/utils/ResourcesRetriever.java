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

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;
import pl.itrack.client.local.config.AppSettings;
import pl.itrack.client.shared.model.TristarObjectType;

import javax.inject.Inject;
import java.util.Date;

public class ResourcesRetriever {

    private static final String IMAGES_EXT = ".png";
    private static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmss";
    private static final String QUERY_STRING_SEPARATOR = "?";

    private AppSettings settings;

    @Inject
    public ResourcesRetriever(AppSettings settings) {
        this.settings = settings;
    }

    public String getImageUrl(TristarObjectType type, int resourceId, boolean preventCache) {
        String filePath = getResourcePath(type) + resourceId + IMAGES_EXT;
        if (preventCache) {
            filePath += QUERY_STRING_SEPARATOR + getTimeStamp();
        }
        return filePath;
    }

    private String getResourcePath(TristarObjectType type) {
        return (TristarObjectType.CAMERA == type) ? settings.getCamerasImagePath() : settings.getInfoboardsImagePath();
    }

    private String getTimeStamp() {
        Date date = new Date();
        DateTimeFormat dtf = DateTimeFormat.getFormat(TIMESTAMP_FORMAT);
        return dtf.format(date, TimeZone.createTimeZone(0));
    }
}
