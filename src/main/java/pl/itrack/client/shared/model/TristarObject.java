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

public class TristarObject {
    private Integer id;
    private TristarObjectType type;
    private String wkt; // TODO: to use here Map<WktDataType, String> or List<Pair<WktDataType, String>>
    private String name;

    public TristarObject() {
    }

    public TristarObject(Integer id, String wkt, String name, TristarObjectType type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.wkt = wkt;
    }

    public TristarObjectType getType() {
        return type;
    }

    public void setType(TristarObjectType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }
}
