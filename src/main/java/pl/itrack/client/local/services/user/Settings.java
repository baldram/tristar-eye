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

package pl.itrack.client.local.services.user;

import gwt.material.design.client.ui.MaterialToast;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import pl.itrack.client.local.dal.UserSettingsDao;
import pl.itrack.client.local.event.FavouritesModify;
import pl.itrack.client.local.view.FavouritesViewModel;
import pl.itrack.client.local.view.helpers.Texts;
import pl.itrack.client.local.view.widgets.modals.SimpleDialog;
import pl.itrack.client.shared.model.TristarObjectType;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class Settings {

    private static final HashSet<Integer> EMPTY_CAMERAS_SET = new HashSet<>(Arrays.asList(new Integer[]{}));

    @Inject
    private TransitionTo<FavouritesViewModel> toFavourites;

    @Inject
    private UserSettingsDao userSettings;

    @Inject
    private SimpleDialog modal;

    public Set<Integer> getUserFavaouriteCameras() {
        Set<Integer> userObjects = userSettings.getAllUserObjectIds(TristarObjectType.CAMERA);

        if (!userObjects.isEmpty()) {
            return userObjects;
        }
        return new HashSet<>(Arrays.asList(new Integer[]{}));
    }

    public Set<Integer> addOrRemoveFavouriteCamera(Integer id) {
        Set<Integer> selectedCameras = getUserFavaouriteCameras();
        if (selectedCameras.contains(id)) {
            selectedCameras.remove(id);
            MaterialToast.fireToast(Texts.CAMERA_REMOVED);
        } else {
            selectedCameras.add(id);
            MaterialToast.fireToast(Texts.CAMERA_ADDED);
        }
        userSettings.storeFavouriteCameras(selectedCameras);
        return selectedCameras;
    }

    private Integer[] getDefaultUserFavaouriteObjects(TristarObjectType type) {
        if (TristarObjectType.CAMERA == type) {
            return new Integer[] { 165, 168, 170, 172, 173, 174, 175, 176, 177, 204, 205, 206, 207, 208, 209, 210, 287, 291 };
        }
        return new Integer[]{};
    }

    public void displayWelcomeHelpOnce() {
        if (!userSettings.isWelcomeHelpShown()) {
            modal.show(Texts.HELP_TITLE, Texts.HELP_DESCRIPTION);
            userSettings.setWelcomeHelpShown();
        }
    }

    @SuppressWarnings("unused")
    private void onMapTypeChange(@Observes FavouritesModify modifyEvent) {
        FavouritesModify.ModificationType modificationType = modifyEvent.getModificationType();
        if (modificationType == FavouritesModify.ModificationType.REMOVE_ALL) {
            removeAllFavourites();
            MaterialToast.fireToast(Texts.FAVOURITES_REMOVED);
        }
        if (modificationType == FavouritesModify.ModificationType.RESTORE_DEFAULT) {
            restoreDefaultFavourites();
            MaterialToast.fireToast(Texts.FAVOURITES_DEFAULTED);
        }
    }

    private void removeAllFavourites() {
        userSettings.storeFavouriteCameras(EMPTY_CAMERAS_SET);
        toFavourites.go();
    }

    private void restoreDefaultFavourites() {
        Set<Integer> selectedCameras = new HashSet<>(Arrays.asList(getDefaultUserFavaouriteObjects(TristarObjectType.CAMERA)));
        userSettings.storeFavouriteCameras(selectedCameras);
        toFavourites.go();
    }
}
