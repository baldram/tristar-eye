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

package pl.itrack.client.local.view.widgets.modals;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialButton;
import pl.itrack.client.local.event.CameraHighlight;
import pl.itrack.client.local.services.user.Settings;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.Set;

public class CameraDialog {

    private static final String FAVOURITES_ADD_CSS = "btnFavouritesAdd";

    private static final String DIALOG_CSS_CLASS_NAME = "camera-dialog";

    private final BaseDialog<CameraDialogBody> dialog;

    private final Settings settings;

    private final CameraDialogBody dialogBody;

    private Set<Integer> favouriteCameras;

    @Inject
    private Event<CameraHighlight> cameraHighlightEvent;

    @Inject
    public CameraDialog(BaseDialog<CameraDialogBody> dialog, CameraDialogBody dialogBody, Settings userSettings) {
        this.dialog = dialog;
        this.dialogBody = dialogBody;
        this.settings = userSettings;
    }

    public final void show(final String title, final Integer objectId) {
        dialogBody.init(objectId);

        addFooterButtons(objectId);

        dialog.init(title, dialogBody, DIALOG_CSS_CLASS_NAME);
        dialog.show();
    }

    private void addFooterButtons(Integer objectId) {
        favouriteCameras = settings.getUserFavouriteCameras();
        dialog.clearFooter();
        dialog.addCloseButton();
        dialog.addButton(getFavAddButton(objectId));
    }

    private MaterialButton getFavAddButton(Integer objectId) {
        MaterialButton button = new MaterialButton();
        button.addClickHandler(clickEvent -> handleFavouriteSection(objectId, button));
        button.setType(ButtonType.FLAT);
        button.setIconType(IconType.FAVORITE);
        button.setClass(FAVOURITES_ADD_CSS);
        button.setBackgroundColor(Color.WHITE);
        button.setType(ButtonType.FLOATING);
        markFavouriteSelection(favouriteCameras.contains(objectId), button);
        return button;
    }

    private void handleFavouriteSection(Integer objectId, MaterialButton button) {
        cameraHighlightEvent.fire(new CameraHighlight(objectId));
        favouriteCameras = settings.getUserFavouriteCameras();
        markFavouriteSelection(favouriteCameras.contains(objectId), button);
    }

    private void markFavouriteSelection(boolean isFavourite, MaterialButton button) {
        button.setIconColor(isFavourite ? Color.RED : Color.GREY_LIGHTEN_1);
    }
}
