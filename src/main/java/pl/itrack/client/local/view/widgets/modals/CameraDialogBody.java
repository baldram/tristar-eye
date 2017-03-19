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

import gwt.material.design.client.ui.MaterialImage;
import pl.itrack.client.local.config.AppSettings;
import pl.itrack.client.local.services.utils.ResourcesRetriever;
import pl.itrack.client.shared.model.TristarObjectType;

import javax.inject.Inject;

public class CameraDialogBody extends DialogBodyBase {

    private static final String IMAGE_ID = "tristarModalImage";

    private final MaterialImage image;

    private final AppSettings appSettings;

    private final ResourcesRetriever retriever;

    @Inject
    public CameraDialogBody(MaterialImage image, AppSettings appSettings, ResourcesRetriever retriever) {
        super(image);

        this.image = image;
        this.appSettings = appSettings;
        this.retriever = retriever;

        setLoaderImage(image);
    }

    private void setLoaderImage(MaterialImage image) {
        image.setUrl(appSettings.getImagesPath() + appSettings.getCameraLoadingImage());
    }

    public void init(final Integer objectId) {
        image.setUrl(getImageUrl(objectId));
        image.setId(IMAGE_ID);
        image.setClass(IMAGE_ID);
    }

    private String getImageUrl(Integer objectId) {
        return retriever.getImageUrl(TristarObjectType.CAMERA, objectId, true);
    }

    @Override
    public void destroy() {
        setLoaderImage(image);
    }

    public MaterialImage getImage() {
        return image;
    }
}
