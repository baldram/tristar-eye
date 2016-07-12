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

    private static final String DATA_STRING_START_REGEXP = "\\[";
    private static final String DATA_STRING_END = "]";
    private static final String DATA_STRING_SEPARATOR = ", ";

    @Inject
    private EntityManager entityManager;

    public Set<Integer> getAllUserObjectIds(TristarObjectType type) {
        // TODO: to handle object type

        // as Errai's implementation of JPA doesn't offer @ElementCollection, this manual conversion is needed
        return getParsedIdSet(getUserSelectedCameraIdsString());
    }

    private String getUserSelectedCameraIdsString() {
        UserSettings settings = fetchUserSettings();
        if (settings != null) {
            return settings.getFavouriteCameraIds();
        }
        return StringUtils.EMPTY;
    }

    private UserSettings fetchUserSettings() {
        // single record (for current local user only) is stored
        return entityManager.find(UserSettings.class, UserSettings.FIRST_ROW);
    }

    private Set<Integer> getParsedIdSet(String commaSeparatedIdsString) {
        Set<Integer> cameraIds = new HashSet<>();
        if (!commaSeparatedIdsString.isEmpty()) {
            for (String cameraStringId : parseDigits(commaSeparatedIdsString)) {
                cameraIds.add(new Integer(cameraStringId));
            }
        }
        return cameraIds;
    }

    private String[] parseDigits(String commaSeparatedIdsString) {
        String cleanedUpInput = commaSeparatedIdsString.replaceFirst(DATA_STRING_START_REGEXP, "");
        if (cleanedUpInput.endsWith(DATA_STRING_END)) {
            cleanedUpInput = cleanedUpInput.substring(0, cleanedUpInput.length() - 1);
        }
        return cleanedUpInput.split(DATA_STRING_SEPARATOR);
    }

    public void storeFavouriteCameras(Set<Integer> cameraIds) {
        String cameraIdsString = cameraIds.toString();
        UserSettings settings = createOrUpdateSettingsObject(cameraIdsString);
        entityManager.persist(settings);
        entityManager.flush(); // TODO: could be flushed once in different place on page transition or leaving...
    }

    private UserSettings createOrUpdateSettingsObject(String cameraIdsString) {
        UserSettings settings = fetchUserSettings();
        if (settings == null) {
            settings = new UserSettings();
            settings.setId(UserSettings.FIRST_ROW);
        }
        settings.setFavouriteCameraIds(cameraIdsString);
        settings.setLastUpdated(new Date(System.currentTimeMillis()));
        return settings;
    }

}
