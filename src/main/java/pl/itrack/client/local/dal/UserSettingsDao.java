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

package pl.itrack.client.local.dal;

import org.apache.commons.lang3.StringUtils;
import pl.itrack.client.shared.model.TristarObjectType;
import pl.itrack.client.shared.model.UserSettings;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserSettingsDao {

    private static final String DATA_STRING_START_REGEXP = "\\[";
    private static final String DATA_STRING_END = "]";
    private static final String DATA_STRING_SEPARATOR = ", ";
    private static final String EMPTY_JAVA_SCRIPT_LIST = "[]";

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

        if (!isEmptyCamerasList(commaSeparatedIdsString)) {
            for (String cameraStringId : parseDigits(commaSeparatedIdsString)) {
                cameraIds.add(new Integer(cameraStringId));
            }
        }
        return cameraIds;
    }

    private boolean isEmptyCamerasList(String commaSeparatedIdsString) {
        return commaSeparatedIdsString.isEmpty() || commaSeparatedIdsString.equals(EMPTY_JAVA_SCRIPT_LIST);
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
        UserSettings settings = createOrUpdateCamerasSettings(cameraIdsString);
        entityManager.persist(settings);
        entityManager.flush(); // TODO: could be flushed once in different place on page transition or leaving...
    }

    public boolean isWelcomeHelpShown() {
        UserSettings settings = fetchUserSettings();
        return settings != null && settings.isWelcomeHelpShown();
    }

    public void setWelcomeHelpShown() {
        UserSettings settings = getOrCreateBareUserSettingsObject();
        settings.setWelcomeHelpShown(true);
        entityManager.persist(settings);
        entityManager.flush();
    }

    private UserSettings getOrCreateBareUserSettingsObject() {
        UserSettings settings = fetchUserSettings();
        if (settings == null) {
            settings = new UserSettings();
            settings.setId(UserSettings.FIRST_ROW);
            settings.setFavouriteCameraIds(EMPTY_JAVA_SCRIPT_LIST);
        }
        settings.setLastUpdated(new Date(System.currentTimeMillis()));
        return settings;
    }

    private UserSettings createOrUpdateCamerasSettings(String cameraIdsString) {
        UserSettings settings = getOrCreateBareUserSettingsObject();
        settings.setFavouriteCameraIds(cameraIdsString);
        return settings;
    }

}
