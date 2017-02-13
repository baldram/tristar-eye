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

import javax.inject.Inject;

public class ImageDialog {

    private static final String IMAGE_ID = "tristarModalImage";

    private BaseDialog dialog;

    private MaterialImage image;

    @Inject
    public ImageDialog(BaseDialog dialog, MaterialImage image) {
        this.dialog = dialog;
        this.image = image;
    }

    public final void show(final String title, final String imageUrl) {
        image.setUrl(imageUrl);
        image.setId(IMAGE_ID);
        image.setClass(IMAGE_ID);
        dialog.show(title, image);
    }
}
