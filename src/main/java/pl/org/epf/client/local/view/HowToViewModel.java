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
import com.google.gwt.user.client.ui.Button;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import pl.org.epf.client.local.services.user.Settings;
import pl.org.epf.client.shared.services.TristarDataService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.util.Set;

import static pl.org.epf.client.local.view.HowToViewModel.PAGE_NAME;

@Page(path = PAGE_NAME)
@Templated("HowToView.html#howToRoot")
public class HowToViewModel extends BasePage {

    public static final String PAGE_NAME = "howto";

    @Inject
    private TransitionTo<MapTabViewModel> toMap;

    @Inject
    private TransitionTo<FavouritesViewModel> toFavourites;

    @Inject
    @DataField
    private Button goToMapButton;

    @Inject
    private Settings userSettings;

    @Inject
    private TristarDataService dataService;

    @PostConstruct
    public void init() {
    }

    @Override
    protected void onPageShown() {
        Set<Integer> favouritesCameraIds = userSettings.getUserFavaouriteCameras();
        if (!favouritesCameraIds.isEmpty()) {
            toFavourites.go();
        }
    }

    @EventHandler("goToMapButton")
    public void refreshFavourites(ClickEvent e) {
        toMap.go();
    }

}
