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

import pl.org.epf.client.local.dal.UserSettingsDao;
import pl.org.epf.client.shared.model.TristarObjectType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class Settings {

    @Inject
    private UserSettingsDao userSettings;

    public Set<Integer> getUserFavaouriteCameras() {
        Set<Integer> userObjects = userSettings.getAllUserObjectIds(TristarObjectType.CAMERA);

        if (userObjects.size() > 0) {
            return userObjects;
        }
        return new HashSet<>(Arrays.asList(getDefaultUserFavaouriteObjects(TristarObjectType.CAMERA)));
    }

    public Set<Integer> addOrRemoveFavouriteCamera(Integer id) {
        Set<Integer> selectedCameras = getUserFavaouriteCameras();
        if (selectedCameras.contains(id)) {
            selectedCameras.remove(id);
        } else {
            selectedCameras.add(id);
        }
        userSettings.storeFavouriteCameras(selectedCameras);
        return selectedCameras;
    }

    public Integer[] getDefaultUserFavaouriteObjects(TristarObjectType type) {
        if (TristarObjectType.CAMERA == type) {
            return new Integer[] { 165, 168, 170, 172, 173, 174, 175, 176, 177, 204, 205, 206, 207, 208, 209, 210, 287, 291 };
        }
        return new Integer[]{};
    }

}
