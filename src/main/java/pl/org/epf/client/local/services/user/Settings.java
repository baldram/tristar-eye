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

package pl.org.epf.client.local.services.user;

import pl.org.epf.client.local.services.maps.ClassicMapService;
import pl.org.epf.client.local.services.maps.MapService;
import pl.org.epf.client.local.services.maps.TricitySchemaService;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class Settings {

    @Produces
    public MapService getMapService() {
        // TODO: to use settings stored by user here
        return (true) ? new ClassicMapService() : new TricitySchemaService();
    }

}
