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

package pl.itrack.client.local.services.utils;

import pl.itrack.client.local.AbstractTristarCDITest;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class GalleryCustomBehaviourServiceTest extends AbstractTristarCDITest {

    private GalleryCustomBehaviourService behaviourService;

    @Override
    @SuppressWarnings("unchecked")
    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        setupJqueryAndInterfaceLibrary();
        behaviourService = lookupBeanInstance(GalleryCustomBehaviourService.class);
    }

    public void testDoNothingIfUniteGalleryNotInitialized() throws Exception {
        behaviourService.addCameraDeleteButtonToGalleryTiles();

        assertNull($(GalleryCustomBehaviourService.FAV_DELETE_CSS_CLASS).html());
    }

    // TODO: introduce HtmlUnit and implement integration tests in a different way
    //    @Ignore
    //    public void testAddCameraDeleteButtonToGalleryTiles() throws Exception {
    //        SimplePanel testPanel = new SimplePanel();
    //        testPanel.getElement().setId("favouritesPlaceholder");
    //        RootPanel.get().add(testPanel);
    //        $("favouritesPlaceholder").append($("<span class=\"ug-tiles-wrapper\"><div>fake child</div></span>"));
    //
    //        behaviourService.addCameraDeleteButtonToGalleryTiles();
    //
    //        assertNotNull($(GalleryCustomBehaviourService.FAV_DELETE_CSS_CLASS).html());
    //    }

}