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

package pl.org.epf.client.local.local.dal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import pl.org.epf.client.local.dal.UserSettingsDao;
import pl.org.epf.client.shared.model.TristarObjectType;
import pl.org.epf.client.shared.model.UserSettings;

import javax.persistence.EntityManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserSettingsDaoTest {

    private static final String USER_FAVOURITES_DATA_STRING = "[162, 165, 169, 170, 171, 172, 175, 176, 177, 178, 183, 184, 195, 196, 204, 205, 206, 207, 208, 209, 210, 287, 291]";
    private static final Set<Integer> EXPECTED_CAMERA_IDS = new HashSet<>(Arrays.asList(162, 165, 169, 170, 171, 172, 175, 176, 177, 178, 183, 184, 195, 196, 204, 205, 206, 207, 208, 209, 210, 287, 291));

    @Mock
    private UserSettings userSettings;

    @Mock
    private EntityManager entityManager;

    @Captor
    private ArgumentCaptor<UserSettings> settingsArgumentCaptor;

    @InjectMocks
    private UserSettingsDao userSettingsDao = new UserSettingsDao();

    @Test
    public void storedUserSettingsRetrieval() {
        mockUserSettingsEntityManager(userSettings);
        when(userSettings.getFavouriteCameraIds()).thenReturn(USER_FAVOURITES_DATA_STRING);

        Set<Integer> userObjectIds = userSettingsDao.getAllUserObjectIds(TristarObjectType.CAMERA);

        assertThat(userObjectIds.size(), is(EXPECTED_CAMERA_IDS.size()));
        assertTrue(userObjectIds.containsAll(EXPECTED_CAMERA_IDS));
        assertFalse(userObjectIds.contains(666));
    }

    @SuppressWarnings("unchecked")
    private void mockUserSettingsEntityManager(UserSettings userSettings) {
        when(entityManager.find((Class<Object>) Matchers.anyObject(), eq(UserSettings.FIRST_ROW))).thenReturn(userSettings);
    }

    @Test
    public void retrievalWhenNoUserSettingsStoredYet() {
        mockUserSettingsEntityManager(null);

        Set<Integer> userObjectIds = userSettingsDao.getAllUserObjectIds(TristarObjectType.CAMERA);

        assertThat(userObjectIds.size(), is(0));
    }

    @Test
    public void storeUserSettings() {
        userSettingsDao.storeFavouriteCameras(EXPECTED_CAMERA_IDS);

        verify(entityManager).persist(settingsArgumentCaptor.capture());

        UserSettings storedSettings = settingsArgumentCaptor.getValue();

        assertThat(storedSettings.getId(), is(UserSettings.FIRST_ROW));
        //assertThat(storedSettings.getFavouriteCameraIds(), is(USER_FAVOURITES_DATA_STRING));
        // TODO: it would be nice to keep the original order
        assertThat(storedSettings.getFavouriteCameraIds(), is("[162, 195, 291, 196, 165, 169, 170, 171, 172, 204, 205, 206, 175, 207, 176, 208, 177, 209, 178, 210, 183, 184, 287]"));
        assertNotNull(storedSettings.getLastUpdated());
    }

}
