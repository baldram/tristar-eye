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

package pl.itrack.client.local.view.helpers;

import com.google.gwt.core.client.Scheduler;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.js.JsSideNavOptions;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.jquery.client.api.JQueryElement;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class UiHelper {

    public final void initMaterialize() {
        Scheduler.get().scheduleDeferred(() -> {
            JQueryElement activator = $(".button-collapse");
            JsMaterialElement.$(activator).sideNav(new JsSideNavOptions());
        });
    }

    public final void buildFabButton(MaterialButton button, IconType iconType) {
        button.setType(ButtonType.FLOATING);
        button.setWaves(WavesType.LIGHT);
        button.setSize(ButtonSize.LARGE);
        button.setBackgroundColor(Color.RED);
        button.setIconType(iconType);
    }

    public static native void initUniteGallery()
    /*-{
        $wnd.jQuery("#favouritesPlaceholder").unitegallery({
            theme_enable_preloader: true,
            tile_enable_textpanel:true,
            tile_textpanel_title_text_align: "center",
            tile_textpanel_always_on:true,
            theme_appearance_order: "keep"
        });
    }-*/;
}
