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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Composite;
import gwt.material.design.client.base.MaterialWidget;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Templated
public class Header extends Composite {

    @Inject @DataField private TopNavigationBar navDesktop;

    @Inject @DataField private SideNavigationBar navMobile;

    @Inject @DataField private SearchBar searchBar;

    @Inject private ContextMenu contextMenu;

    @PostConstruct
    public void init() {
        changeNav(navDesktop);
        Scheduler.get().scheduleDeferred(this::addButtons);
    }

    private void addButtons() {
        navDesktop.add(searchBar.getSearchButton());
        navDesktop.add(contextMenu.createNavBarOptions());

        searchBar.getSearchBox().addOpenHandler(openEvent -> changeNav(searchBar));
        searchBar.getSearchBox().addCloseHandler(closeEvent -> changeNav(navDesktop));
    }

    private void changeNav(MaterialWidget nav) {
        searchBar.setVisible(false);
        navDesktop.setVisible(false);
        nav.setVisible(true);
    }
}
