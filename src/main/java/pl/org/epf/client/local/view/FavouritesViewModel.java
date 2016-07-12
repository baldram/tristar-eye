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

package pl.org.epf.client.local.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.*;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import pl.org.epf.client.local.services.user.Settings;
import pl.org.epf.client.local.view.helpers.DomObjectHelper;
import pl.org.epf.client.shared.model.TristarObject;
import pl.org.epf.client.shared.model.TristarObjectType;
import pl.org.epf.client.local.services.utils.ResourcesRetriever;
import pl.org.epf.client.local.view.widgets.ContentContainer;
import pl.org.epf.client.local.view.widgets.DivContainer;
import pl.org.epf.client.shared.services.TristarDataService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.util.List;
import java.util.Set;

import static pl.org.epf.client.local.view.FavouritesViewModel.PAGE_NAME;

@Page(path = PAGE_NAME)
@Templated("Favourites.html#favouritesRoot")
public class FavouritesViewModel extends BasePage {

    public static final String PAGE_NAME = "favourites";

    @Inject
    @DataField
    private DivContainer favouritesPlaceholder;

    @Inject
    @DataField
    private Button refreshButton;

    @Inject
    private ContentContainer contentContainer;

    @Inject
    private ResourcesRetriever retriever;

    @Inject
    private TransitionTo<FavouritesViewModel> toFavourites;

    @Inject
    private DomObjectHelper domObjectHelper;

    @Inject
    private TristarDataService dataService;

    @Inject
    private Settings userSettings;

    @PostConstruct
    public void init() {
        loadFavourites();
    }

    @Override
    protected void onPageShown() {
        initJSLibs();
    }

    public static native void initJSLibs()
    /*-{
        $wnd.jQuery("#favouritesPlaceholder").unitegallery({
            theme_enable_preloader: true,
            tile_enable_textpanel:true,
            tile_textpanel_title_text_align: "center",
            tile_textpanel_always_on:true,
            theme_appearance_order: "keep"
        });
        $wnd.componentHandler.upgradeDom('MaterialMenu', 'mdl-menu');
    }-*/;

    private void loadFavourites() {
        favouritesPlaceholder.clear();
        Set<Integer> favouritesCameraIds = userSettings.getUserFavaouriteCameras();

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

    @EventHandler("refreshButton")
    public void refreshFavourites(ClickEvent e) {
        toFavourites.go();
    }

}
