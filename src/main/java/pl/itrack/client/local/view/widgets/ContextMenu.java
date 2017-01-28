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

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.html.ListItem;
import pl.itrack.client.local.event.MapViewTypeChange;
import pl.itrack.client.local.event.MenuActionEvent;
import pl.itrack.client.local.event.FavouritesModify;
import pl.itrack.client.local.view.helpers.Texts;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;

// TODO: Implement page dependent menu content
// TODO: Implement "change order" option

@Singleton
public class ContextMenu {

    private static final String DIVIDER_CSS_CLASS_NAME = "divider";

    private static final String NAV_OPTIONS_CSS_CLASS = "tristarNavOptionsBtn";

    private static final String OPTIONS_BUTTON_CSS_CLASS = "context-menu";

    @Inject
    private Event<MapViewTypeChange> mapTypeChangeEvent;

    @Inject
    private Event<FavouritesModify> favouritesModifyEvent;

    @Inject
    private ModalWindow modal;

    MaterialButton createNavBarOptions() {
        MaterialButton moreButton = new MaterialButton();

        moreButton.add(createDropDown());

        moreButton.setType(ButtonType.FLAT);
        moreButton.setIconColor(Color.WHITE);
        moreButton.setIconPosition(IconPosition.LEFT);
        moreButton.setIconType(IconType.MORE_VERT);
        moreButton.getElement().addClassName(NAV_OPTIONS_CSS_CLASS);

        return moreButton;
    }

    private MaterialDropDown createDropDown() {
        MaterialDropDown dropDown = new MaterialDropDown();

        dropDown.setTextColor(Color.WHITE);
        dropDown.getElement().setClassName(OPTIONS_BUTTON_CSS_CLASS);

        dropDown.add(createSimpleMenuItem(Texts.OPTIONS_DELETE_ALL, favouritesModifyEvent, new FavouritesModify(FavouritesModify.ModificationType.REMOVE_ALL)));
        dropDown.add(createSimpleMenuItem(Texts.OPTIONS_IMPORT_DEMO, favouritesModifyEvent, new FavouritesModify(FavouritesModify.ModificationType.RESTORE_DEFAULT)));
        dropDown.add(createSimpleMenuItem(Texts.OPTIONS_SWITCH_MAP_SCHEMA, mapTypeChangeEvent, new MapViewTypeChange()));
        dropDown.add(createDivider());

        MaterialLink helpLink = new MaterialLink(Texts.HELP_TITLE);
        helpLink.addClickHandler(clickEvent -> modal.showModalDialog(Texts.HELP_TITLE, Texts.HELP_DESCRIPTION));
        dropDown.add(helpLink);

        dropDown.addSelectionHandler(selectionEvent -> MaterialToast.fireToast(((MaterialLink) selectionEvent.getSelectedItem()).getText()));
        return dropDown;
    }

    @SuppressWarnings("unchecked")
    private MaterialLink createSimpleMenuItem(String label, Event eventProducer, MenuActionEvent action) {
        MaterialLink link = new MaterialLink(label);
        link.addClickHandler(clickEvent -> eventProducer.fire(action));
        return link;
    }

    private ListItem createDivider() {
        ListItem listItem = new ListItem();
        listItem.getElement().setClassName(DIVIDER_CSS_CLASS_NAME);
        return listItem;
    }
}
