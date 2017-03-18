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
import pl.itrack.client.local.services.user.Settings;
import pl.itrack.client.local.services.utils.ResourcesRetriever;
import pl.itrack.client.shared.model.TristarObjectType;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class CameraDialogTest {

    private static final String IMAGE_ID = "tristarModalImage";
    private static final int OBJECT_ID = 123;
    private static final String IMAGE_URL = "url";
    private static final String DIALOG_CSS_CLASS_NAME = "camera-dialog";

    @InjectMocks
    private CameraDialog cameraDialog;

    @Mock
    private BaseDialog dialog;

    @Mock
    private MaterialImage image;

    @Mock
    private ResourcesRetriever retriever;

    @Mock
    private Settings userSettings;

    @Mock
    private AppSettings appSettings;

    @Before
    public void setUp() throws Exception {
        when(retriever.getImageUrl(eq(TristarObjectType.CAMERA), eq(OBJECT_ID), eq(true))).thenReturn(IMAGE_URL);
        when(userSettings.getUserFavouriteCameras()).thenReturn(new HashSet<>(Arrays.asList(1, 2, OBJECT_ID, 4)));
    }

    @Test
    public void show() throws Exception {
        String dialogTitle = "title";

        cameraDialog.show(dialogTitle, OBJECT_ID);

        verify(image).setUrl(eq(IMAGE_URL));
        verify(image).setId(eq(IMAGE_ID));
        verify(image).setClass(eq(IMAGE_ID));
        verify(dialog).init(eq(dialogTitle), eq(image), eq(DIALOG_CSS_CLASS_NAME));
        verify(dialog).show();
    }

    // TODO: to implement Selenium tests to handle click event

}