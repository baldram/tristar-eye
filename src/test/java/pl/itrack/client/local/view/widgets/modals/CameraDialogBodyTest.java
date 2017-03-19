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

import com.google.gwtmockito.GwtMockitoTestRunner;
import gwt.material.design.client.ui.MaterialImage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.itrack.client.local.config.AppSettings;
import pl.itrack.client.local.services.utils.ResourcesRetriever;
import pl.itrack.client.shared.model.TristarObjectType;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class CameraDialogBodyTest {

    private static final String IMAGE_ID = "tristarModalImage";
    private static final String IMAGE_URL = "url";
    private static final int OBJECT_ID = 123;

    @InjectMocks
    private CameraDialogBody body;

    @Mock
    private MaterialImage image;

    @Mock
    private AppSettings appSettings;

    @Mock
    private ResourcesRetriever retriever;

    @Before
    public void setUp() throws Exception {
        when(retriever.getImageUrl(eq(TristarObjectType.CAMERA), eq(OBJECT_ID), eq(true))).thenReturn(IMAGE_URL);
    }

    @Test
    public void init() {
        body.init(OBJECT_ID);

        verify(image).setUrl(eq(IMAGE_URL));
        verify(image).setId(eq(IMAGE_ID));
        verify(image).setClass(eq(IMAGE_ID));
    }

}