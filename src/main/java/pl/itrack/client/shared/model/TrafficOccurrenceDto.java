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

package pl.itrack.client.shared.model;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class TrafficOccurrenceDto {

    private String id; // objectId
    private String title; // type
    private String period;
    private String eventName; // localization
    private String detailsUri; // s: resources.data.uri
    private String lastEdit;
    private String description;
    private String iconType; // tristarType // s: tristar_type
    private String timestamp;
    private String roadworks; // s: wkt.blocked_roads
    private String location; // s: wkt.location

    public TrafficOccurrenceDto() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPeriod() {
        return period;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDetailsUri() {
        return detailsUri;
    }

    public String getDescription() {
        return description;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public String getIconType() {
        return iconType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getRoadworks() {
        return roadworks;
    }

    public String getLocation() {
        return location;
    }
}
