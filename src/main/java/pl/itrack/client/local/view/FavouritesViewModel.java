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

package pl.itrack.client.local.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.*;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialButton;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import pl.itrack.client.local.services.user.Settings;
import pl.itrack.client.local.services.utils.GalleryCustomBehaviourService;
import pl.itrack.client.local.view.helpers.DomObjectHelper;
import pl.itrack.client.local.view.helpers.UiHelper;
import pl.itrack.client.shared.model.TristarObject;
import pl.itrack.client.shared.model.TristarObjectType;
import pl.itrack.client.local.services.utils.ResourcesRetriever;
import pl.itrack.client.local.view.widgets.DivContainer;
import pl.itrack.client.shared.services.TristarDataService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.util.List;
import java.util.Set;

import static pl.itrack.client.local.view.FavouritesViewModel.PAGE_NAME;

@Page(path = PAGE_NAME)
@Templated("FavouritesView.html#favouritesRoot")
public class FavouritesViewModel extends BasePage {

    public static final String PAGE_NAME = "favourites";

    @Inject @DataField private DivContainer favouritesPlaceholder;

    @Inject @DataField private MaterialButton refreshButton;

    @Inject private ResourcesRetriever retriever;

    @Inject private TransitionTo<FavouritesViewModel> toFavourites;

    @Inject private TransitionTo<HowToViewModel> toHowTo;

    @Inject private DomObjectHelper domObjectHelper;

    @Inject private TristarDataService dataService;

    @Inject private Settings userSettings;

    @Inject private UiHelper uiHelper;

    @Inject private GalleryCustomBehaviourService galleryCustomBehaviourService;

    private Set<Integer> favouritesCameraIds;

    @PostConstruct
    public void init() {
        loadFavourites();
        uiHelper.buildFabButton(refreshButton, IconType.REFRESH);
    }

    @Override
    protected void onPageShown() {
        if (favouritesCameraIds.isEmpty()) {
            toHowTo.go();
        }
        UiHelper.initUniteGallery();
        galleryCustomBehaviourService.addCameraDeleteButtonToGalleryTiles();
    }

    private void loadFavourites() {
        favouritesPlaceholder.clear();
        favouritesCameraIds = userSettings.getUserFavouriteCameras();

        List<TristarObject> cameraImages = dataService.getCameras(favouritesCameraIds);
        for (TristarObject image : cameraImages) {
            createAndAddImage(favouritesPlaceholder, image.getId(), image.getName());
        }
    }

    private void createAndAddImage(DivContainer imagesContainer, int resourceId, String name) {
        String url = retriever.getImageUrl(TristarObjectType.CAMERA, resourceId, true);
        final Image favouriteImage = domObjectHelper.createImageElement(resourceId, name, url);
        imagesContainer.add(favouriteImage);
    }

    @SuppressWarnings("unused")
    @EventHandler("refreshButton")
    public void refreshFavourites(ClickEvent e) {
        toFavourites.go();
    }

}
