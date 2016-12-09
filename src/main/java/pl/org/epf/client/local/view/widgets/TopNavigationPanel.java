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

package pl.org.epf.client.local.view.widgets;

import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.ui.nav.client.local.TransitionAnchor;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import pl.org.epf.client.local.event.PageLoaded;
import pl.org.epf.client.local.view.FavouritesViewModel;
import pl.org.epf.client.local.view.HowToViewModel;
import pl.org.epf.client.local.view.MapTabViewModel;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Templated("#topNavigationPanel")
public class TopNavigationPanel extends Composite {

    public static final String CLASS_TAB_ACTIVE = "is-active";

    @Inject
    @DataField
    private TransitionAnchor<FavouritesViewModel> favouritesButton;

    @Inject
    @DataField
    private TransitionAnchor<MapTabViewModel> mapButton;

    //@Inject
    //@DataField
    //private TransitionAnchor<ListViewModel> listButton;

    private void selectBookmarkedTab(@Observes PageLoaded event) {
        if (event.getPageName().equals(FavouritesViewModel.PAGE_NAME) || event.getPageName().equals(HowToViewModel.PAGE_NAME)) {
            switchActiveTab(favouritesButton, mapButton);
        } else {
            switchActiveTab(mapButton, favouritesButton);
        }
    }

    private void switchActiveTab(TransitionAnchor<?> activeButton, TransitionAnchor<?> inactiveButon) {
        activeButton.getElement().addClassName(CLASS_TAB_ACTIVE);
        inactiveButon.getElement().removeClassName(CLASS_TAB_ACTIVE);
    }

}
