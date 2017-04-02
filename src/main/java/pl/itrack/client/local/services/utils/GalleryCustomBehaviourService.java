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

package pl.itrack.client.local.services.utils;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import gwt.material.design.jquery.client.api.JQueryElement;
import gwt.material.design.jscore.client.api.core.Node;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import pl.itrack.client.local.event.CameraHighlight;
import pl.itrack.client.local.view.FavouritesViewModel;
import pl.itrack.client.local.view.helpers.Texts;
import pl.itrack.client.local.view.widgets.modals.ConfirmationDialog;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.Optional;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * Adds a custom behaviour to already rendered UniteGallery's tiles
 */
public class GalleryCustomBehaviourService {

    public static final String FAV_DELETE_CSS_CLASS = "fav-delete-trigger";

    private static final String FAVOURITE_IMAGES_SELECTOR = "#favouritesPlaceholder > .ug-tiles-wrapper";
    private static final String TAG_IMG = "IMG";
    private static final String ATTR_SRC = "src";
    private static final String IMAGE_FILENAME_REGEX_PATTERN = ".*/(.*).png.*";
    private static final String GLOBAL_SEARCH_FLAG = "g";
    private static final String DELETE_MATERIAL_ICON_HTML = "<i class=\"" + FAV_DELETE_CSS_CLASS + " white-text material-icons\">delete</i>";
    private static final String EMPTY_FILTER = "";

    @Inject
    private Event<CameraHighlight> cameraHighlightEvent;

    @Inject
    private TransitionTo<FavouritesViewModel> toFavourites;

    @Inject
    private ConfirmationDialog confirmation;

    public void addCameraDeleteButtonToGalleryTiles() {
        if ($(FAVOURITE_IMAGES_SELECTOR).length() != 0) {
            JQueryElement tilesWrapper = $(FAVOURITE_IMAGES_SELECTOR);
            JQueryElement deleteIconElement = $(DELETE_MATERIAL_ICON_HTML);
            deleteIconElement.click(event -> {
                event.preventDefault();
                event.stopPropagation();
                JQueryElement imageElement = $(event.getCurrentTarget()).parents().first();

                Optional<Integer> cameraId = extractCameraId(imageElement);
                cameraId.ifPresent(selectedCameraId -> confirmation.show(Texts.CAMERA_REMOVE, Texts.REMOVE_SINGLE_CAMERA, () -> removeCameraAndRefreshView(selectedCameraId)));
                return true;
            });
            tilesWrapper.children(EMPTY_FILTER).append(deleteIconElement);
        }
    }

    private void removeCameraAndRefreshView(Integer cameraId) {
        cameraHighlightEvent.fire(new CameraHighlight(cameraId));
        toFavourites.go();
    }

    private Optional<Integer> extractCameraId(Node currentTarget) {
        String currentElementUrl = $(currentTarget).children(TAG_IMG).first().attr(ATTR_SRC).toString();
        MatchResult result = RegExp.compile(IMAGE_FILENAME_REGEX_PATTERN, GLOBAL_SEARCH_FLAG).exec(currentElementUrl);
        if (isCameraIdExtracted(result)) {
            Integer cameraId = new Integer(result.getGroup(1));
            return Optional.of(cameraId);
        }
        return Optional.empty();
    }

    private boolean isCameraIdExtracted(MatchResult result) {
        return result != null && result.getGroupCount() > 0 && !isNullOrEmpty(result.getGroup(1));
    }

    // TODO: why can't I use Guava here?
    // No source code is available for type com.google.gwt.thirdparty.guava.common.base.Strings; did you forget to inherit a required module?
    // temporary implemented locally
    private boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0;
    }
}
