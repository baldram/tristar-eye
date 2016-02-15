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

package pl.org.epf.client.local;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.errai.ui.nav.client.local.TransitionTo;
import pl.org.epf.client.local.event.PageChange;
import pl.org.epf.client.local.view.FavouritesViewModel;
import pl.org.epf.client.local.view.MapTabViewModel;

@ApplicationScoped
public class AppController {

    @Inject
    private TransitionTo<MapTabViewModel> toMap;

    @Inject
    private TransitionTo<FavouritesViewModel> toFavourites;

    private void onPageChange(@Observes PageChange event) {
        switch (event.getPageName()) {
            case FavouritesViewModel.PAGE_NAME:
                toFavourites.go();
                break;
            case MapTabViewModel.PAGE_NAME:
                toMap.go();
                break;
        }

    }

}
