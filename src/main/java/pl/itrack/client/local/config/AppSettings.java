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

package pl.itrack.client.local.config;

import com.google.gwt.i18n.client.Constants;

public interface AppSettings extends Constants {

    @Key("tristar.version")
    String getAppVersion(); // TODO: use the one from pom.xml

    @Key("tristar.stylesheet")
    String getAppStylesheet();

    @Key("tristar.images.path")
    String getImagesPath();

    @Key("tristar.cameras.image.url")
    String getCamerasImagePath();

    @Key("tristar.infoboards.image.url")
    String getInfoboardsImagePath();

    @Key("tristar.events.api.url")
    String getEventsApiUrl();

    @Key("tristar.camera.icon")
    String getCameraIcon();

    @Key("tristar.camera.icon.selected")
    String getCameraIconSelected();

    @Key("tristar.camera.dummy.image")
    String getCameraDummyImage();
}
