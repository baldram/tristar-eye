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

import com.google.gwt.user.client.ui.Image;

public class DomObjectHelper {

    private static final String ATTR_DATA_IMAGE = "data-image";
    private static final String ATTR_DATA_DESCRIPTION = "data-description";
    private static final String ATTR_ALT = "alt";
    private static final String ATTR_STYLE = "style";
    private static final String HIDDEN = "display:none";
    private final String IMG_ID_PREFIX = "fav-img";

    public Image createImageElement(int resourceId, String title, String url) {
        final Image favouriteImage = new Image();
        favouriteImage.getElement().setId(IMG_ID_PREFIX + resourceId);
        favouriteImage.setUrl(url);
        favouriteImage.getElement().setAttribute(ATTR_DATA_IMAGE, url);
        favouriteImage.getElement().setAttribute(ATTR_DATA_DESCRIPTION, title);
        favouriteImage.getElement().setAttribute(ATTR_ALT, title);
        favouriteImage.getElement().setAttribute(ATTR_STYLE, HIDDEN);
        return favouriteImage;
    }
}
