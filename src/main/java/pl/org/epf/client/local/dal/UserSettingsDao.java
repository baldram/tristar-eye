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

package pl.org.epf.client.local.dal;

import org.apache.commons.lang3.StringUtils;
import pl.org.epf.client.shared.model.TristarObjectType;
import pl.org.epf.client.shared.model.UserSettings;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserSettingsDao {

    @Inject
    private EntityManager entityManager;

    public Set<Integer> getAllUserObjectIds(TristarObjectType type) {
        // TODO: to handle object type

        // as Errai's implementation of JPA doesn't offer @ElementCollection, this manual conversion is needed
        return parseIdsString(getFavouriteCamersIdsString());
    }

    private String getFavouriteCamersIdsString() {
        UserSettings settings = fetchUserSettings();
        if (settings != null) {
            return settings.getFavouriteCameraIds();
        }
        return StringUtils.EMPTY;
    }

    public UserSettings fetchUserSettings() {
        return entityManager.find(UserSettings.class, UserSettings.FIRST_ROW);
    }

    private Set<Integer> parseIdsString(String commaSeparatedIdsString) {
        Set<Integer> cameraIds = new HashSet<>();
        if (!commaSeparatedIdsString.isEmpty()) {
            String[] cameraIdsArray = commaSeparatedIdsString.split(",");
            for (String cameraStringId : cameraIdsArray) {
                cameraIds.add(new Integer(cameraStringId));
            }
        }
        return cameraIds;
    }

    public void storeFavouriteCameras(Set<Long> cameraIds) {
        String readyToStoreString = cameraIds.toString();

        UserSettings settings = fetchUserSettings();
        if (settings == null) {
            settings = new UserSettings();
            settings.setId(UserSettings.FIRST_ROW);
        }
        settings.setFavouriteCameraIds(readyToStoreString);
        settings.setLastUpdated(new Date(System.currentTimeMillis()));
        entityManager.persist(settings);
    }

}
