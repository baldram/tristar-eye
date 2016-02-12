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

package pl.org.epf.client.local.view.widgets;

import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Simple DIV panel. Because of some issues with FlowPanel, SimplePanel, etc.
 * this poor solution has been implemented and works very well.
 */
public class SimpleDivPanel extends HTMLPanel {
    private static final String PLACEHOLDER = "<div id=\"%s\"></div>";

    public SimpleDivPanel(String elementId) {
        super(PLACEHOLDER.replace("%s", elementId));
        this.getElement().setId(elementId);
    }
}
