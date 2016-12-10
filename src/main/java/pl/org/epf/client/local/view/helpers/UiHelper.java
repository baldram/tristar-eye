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

package pl.org.epf.client.local.view.helpers;

import org.jboss.errai.common.client.logging.util.StringFormat;

public class UiHelper {

    private static final String CLOSE_BTN_LABEL = "Zamknij";
    private static final String IMAGE_TAG = "<img src='%s' class='%s' id='%s' />";
    private static final String IMAGE_ID = "tristarModalImage";

    public final void showImageModalDialog(final String title, final String imageUrl) {
        final String imageTag = StringFormat.format(IMAGE_TAG, imageUrl, IMAGE_ID, IMAGE_ID);
        showModalDialog(title, imageTag, CLOSE_BTN_LABEL);
    }

    public final native void showModalDialog(final String title, final String content, final String buttonLabel) /*-{
        $wnd.showDialog({
            negative: {title: buttonLabel},
            positive: false,
            cancelable: true,
            title: title,
            text: content
        });
    }-*/;

    public final native void initMaterialDesignLite() /*-{
        // Needs to be called to finish MDL initialization
        $wnd.componentHandler.upgradeDom();
    }-*/;
}
