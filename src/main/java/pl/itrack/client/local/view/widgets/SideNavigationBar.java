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

package pl.itrack.client.local.view.widgets;

import pl.itrack.client.local.event.PageChange;
import pl.itrack.client.local.view.helpers.UiHelper;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class SideNavigationBar extends NavigationBar {

    @Inject UiHelper uiHelper;

    @Override
    protected boolean isHiddenOnMobile() {
        return false;
    }

    @SuppressWarnings("unused")
    private void hideNavbarIfOpened(@Observes PageChange event) {
        uiHelper.hideNavBar();
    }
}
