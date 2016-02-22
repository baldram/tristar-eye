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

import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import pl.org.epf.client.AbstractPowerMockUnitTest;
import pl.org.epf.client.local.event.PageChange;
import pl.org.epf.client.local.view.FavouritesViewModel;
import pl.org.epf.client.local.view.MapTabViewModel;

import static org.mockito.Mockito.verify;

@PrepareForTest({TransitionTo.class }) // TransitionTo is final - so Powermock necessary
public class AppControllerUnitTest extends AbstractPowerMockUnitTest {

    private static final String UNEXISTING_PAGE = "unexisting";

    @InjectMocks
    private AppController appController;

    @Mock
    private TransitionTo<MapTabViewModel> toMap;

    @Mock
    private TransitionTo<FavouritesViewModel> toFavourites;

    @Test
    public void swtichToMap() {
        appController.onPageChange(new PageChange(MapTabViewModel.PAGE_NAME));

        verify(toMap).go();
    }

    @Test
    public void swtichToFavourites() {
        appController.onPageChange(new PageChange(FavouritesViewModel.PAGE_NAME));

        verify(toFavourites).go();
    }

    @Test
    public void swtichToMapByDefault() {
        appController.onPageChange(new PageChange(UNEXISTING_PAGE));

        verify(toMap).go();
    }
}
