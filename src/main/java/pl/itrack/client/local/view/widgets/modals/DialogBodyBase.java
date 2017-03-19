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

import com.google.gwt.user.client.ui.Widget;

abstract class DialogBodyBase implements DialogBody {

    final private Widget mainBodyWidget;

    protected DialogBodyBase(Widget mainBodyWidget) {
        this.mainBodyWidget = mainBodyWidget;
    }

    /**
     * An optional method to be overridden to clean up the resources
     */
    public void destroy() {}

    @Override
    public void setInnerHtml(String content) {
        mainBodyWidget.getElement().setInnerHTML(content);
    }

    @Override
    public Widget asWidget() {
        return mainBodyWidget;
    }
}
