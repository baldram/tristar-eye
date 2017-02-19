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

package pl.itrack.client.local.view.widgets;

import com.google.gwt.user.client.Element;
import com.google.gwtmockito.GwtMockitoTestRunner;
import gwt.material.design.client.ui.MaterialLink;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.itrack.client.local.event.PageLoaded;
import pl.itrack.client.local.view.FavouritesViewModel;
import pl.itrack.client.local.view.MapTabViewModel;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class NavigationBarTest {

    private static final String CSS_CLASS_TAB_ACTIVE = "active";

    @InjectMocks
    private TopNavigationBar testedInstance;

    @Mock private MaterialLink buttonFav;

    @Mock private MaterialLink buttonMap;

    @Mock private Element favElement;

    @Mock private Element mapElement;

    @Before
    public void setUp() throws Exception {
        when(buttonFav.getElement()).thenReturn(favElement);
        when(buttonMap.getElement()).thenReturn(mapElement);
    }

    @Test
    public void selectMapButtonAsActive() {
        testedInstance.selectActiveNavButton(new PageLoaded(MapTabViewModel.PAGE_NAME));

        verify(mapElement).addClassName(CSS_CLASS_TAB_ACTIVE);
        verify(favElement).removeClassName(CSS_CLASS_TAB_ACTIVE);
    }

    @Test
    public void selectFavouritesButtonAsActive() {
        testedInstance.selectActiveNavButton(new PageLoaded(FavouritesViewModel.PAGE_NAME));

        verify(favElement).addClassName(CSS_CLASS_TAB_ACTIVE);
        verify(mapElement).removeClassName(CSS_CLASS_TAB_ACTIVE);
    }
}