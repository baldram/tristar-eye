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
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialSearch;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import pl.itrack.client.local.view.MapTabViewModel;

import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class SearchBarTest {

    @Mock
    private MaterialLink searchButton;

    @Mock
    private TransitionTo<MapTabViewModel> toMap;

    @Before
    public void setUp() throws Exception {
        SearchBar testedInstance = new SearchBar(toMap, searchButton, mock(MaterialSearch.class));
        when(searchButton.getElement()).thenReturn(mock(Element.class));
        testedInstance.init();
    }

    @Test
    public void hasSearchMaterialIcon() {
        verify(searchButton).setIconType(IconType.SEARCH);
    }

    // TODO: Implement Selenium tests to test UI events
}