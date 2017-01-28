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

package pl.itrack.client.shared.services;

import com.google.common.collect.ImmutableMap;
import pl.itrack.client.shared.fixture.TristarDataSet;
import pl.itrack.client.shared.model.TristarObjectType;
import pl.itrack.client.shared.model.TristarObject;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

// TODO: create REST client when API ready
// @Patch("/objects")
public class TristarDataService {

    @Inject
    private TristarDataSet camerasDataSet;

    // @GET @Path("/cameras")
    // @Produces("application/json")
    public List<TristarObject> getAllCameras() {
        ImmutableMap<Integer, TristarObject> camerasMap = camerasDataSet.fetchAll(TristarObjectType.CAMERA);
        return camerasMap.values().asList();
    }

    // @GET @Path("/cameras/{id}")
    // @Produces("application/json")
    public TristarObject getCamera(Integer id) {
        return camerasDataSet.fetch(TristarObjectType.CAMERA, id);
    }

    // @GET @Path("/cameras/{ids}")
    // @Produces("application/json")
    public List<TristarObject> getCameras(/*@PathParam("ids")*/ Set<Integer> ids) {
        return camerasDataSet.fetch(TristarObjectType.CAMERA, ids);
    }
}
