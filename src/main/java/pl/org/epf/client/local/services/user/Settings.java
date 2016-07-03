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

    public Set<Integer> getUserFavaouriteObjects(TristarObjectType type) {
        Set<Integer> userObjects = userSettings.getAllUserObjectIds(type);
        if (userObjects.size() > 0) {
            return userObjects;
        }
        // TODO: to use default values based on the type
        // TODO: empty list here; default list would be a copy of default data copied to the user settings
        return new HashSet<>(Arrays.asList(getDefaultUserFavaouriteObjects(type)));
    }

    public Integer[] getDefaultUserFavaouriteObjects(TristarObjectType type) {
        if (TristarObjectType.CAMERA == type) {
            return new Integer[]{172,174,175,207,287,291,210,204,209,176,177,178,205,206,208,162,165,169,170,171,183,184,195,196};
        }
        return new Integer[]{};
    }

}
