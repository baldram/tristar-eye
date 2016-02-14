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

package pl.org.epf.client.local.event;


import org.jboss.errai.bus.client.api.Local;
import org.jboss.errai.common.client.api.annotations.Portable;

@Portable @Local
public class MapViewTypeChange {

    /**
     * Flag informs whether to show classic map or city schema
     */
    private boolean classicMap = true;

    public MapViewTypeChange() {
    }

    public MapViewTypeChange(boolean classicMap) {
        this.classicMap = classicMap;
    }

    public boolean isClassicMap() {
        return classicMap;
    }
}
