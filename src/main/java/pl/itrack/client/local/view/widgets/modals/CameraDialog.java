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
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialModalFooter;
import pl.itrack.client.local.event.CameraHighlight;
import pl.itrack.client.local.services.user.Settings;
import pl.itrack.client.local.services.utils.ResourcesRetriever;
import pl.itrack.client.local.view.helpers.Texts;
import pl.itrack.client.shared.model.TristarObjectType;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.Set;

public class CameraDialog {

    private static final String IMAGE_ID = "tristarModalImage";

    private static final String FAVOURITES_ADD_CSS = "btnFavouritesAdd";

    private BaseDialog dialog;

    private MaterialImage image;

    private Set<Integer> favouriteCameras;

    private Settings settings;

    private ResourcesRetriever retriever;

    @Inject
    private Event<CameraHighlight> cameraHighlightEvent;

    @Inject
    public CameraDialog(BaseDialog dialog, MaterialImage image, Settings settings, ResourcesRetriever retriever) {
        this.dialog = dialog;
        this.image = image;
        this.settings = settings;
        this.retriever = retriever;
    }

    public final void show(final String title, final Integer objectId) {
        image.setUrl(getImageUrl(objectId));
        image.setId(IMAGE_ID);
        image.setClass(IMAGE_ID);

        addFooterButons(objectId);

        dialog.show(title, image);
    }

    private String getImageUrl(Integer objectId) {
        return retriever.getImageUrl(TristarObjectType.CAMERA, objectId, true);
    }

    private void addFooterButons(Integer objectId) {
        favouriteCameras = settings.getUserFavaouriteCameras();

        MaterialModalFooter footer = dialog.getFooter();
        footer.clear();
        dialog.addCloseButton();
        footer.add(getFavAddButton(objectId));
    }

    private MaterialButton getFavAddButton(Integer objectId) {
        MaterialButton button = new MaterialButton();
        button.addClickHandler(clickEvent -> handleFavouriteSection(objectId, button));
        button.setType(ButtonType.FLAT);
        button.setIconType(IconType.FAVORITE);
        button.setTooltip(Texts.BTN_FAV_ADD);
        button.setClass(FAVOURITES_ADD_CSS);
        button.setBackgroundColor(Color.WHITE);
        button.setType(ButtonType.FLOATING);
        markFavouriteSelection(favouriteCameras.contains(objectId), button);
        return button;
    }

    private void handleFavouriteSection(Integer objectId, MaterialButton button) {
        cameraHighlightEvent.fire(new CameraHighlight(objectId));
        favouriteCameras = settings.getUserFavaouriteCameras();
        markFavouriteSelection(favouriteCameras.contains(objectId), button);
    }

    private void markFavouriteSelection(boolean isFavourite, MaterialButton button) {
        button.setIconColor(isFavourite ? Color.RED : Color.GREY_LIGHTEN_1);
        button.setTooltip(isFavourite ? Texts.BTN_FAV_REMOVE : Texts.BTN_FAV_ADD);
    }
}
