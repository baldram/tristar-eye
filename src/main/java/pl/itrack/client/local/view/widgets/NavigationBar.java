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
import gwt.material.design.client.constants.HideOn;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.html.UnorderedList;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import pl.itrack.client.local.event.PageLoaded;
import pl.itrack.client.local.view.FavouritesViewModel;
import pl.itrack.client.local.view.MapTabViewModel;
import pl.itrack.client.local.view.BasePage;
import pl.itrack.client.local.view.HowToViewModel;
import pl.itrack.client.local.view.helpers.Texts;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

public abstract class NavigationBar extends UnorderedList {

    static final String CSS_CLASS_TAB_ACTIVE = "active";

    @Inject MaterialLink buttonMap;

    @Inject MaterialLink buttonFav;

    @Inject private TransitionTo<FavouritesViewModel> toFavourites;

    @Inject private TransitionTo<MapTabViewModel> toMap;

    @PostConstruct
    public void init() {
        configureSimpleButtonLink(buttonMap, Texts.TITLE_MAPS, toMap, isHiddenOnMobile());
        configureSimpleButtonLink(buttonFav, Texts.TITLE_FAVOURITES, toFavourites, isHiddenOnMobile());
        Scheduler.get().scheduleDeferred(this::addButtons);
    }

    private void configureSimpleButtonLink(MaterialLink buttonLink, String label, TransitionTo<? extends BasePage> transitTo, boolean hideOnMobile) {
        buttonLink.setText(label);
        if (hideOnMobile) {
            buttonLink.setHideOn(HideOn.HIDE_ON_MED_DOWN);
            buttonLink.setWaves(WavesType.LIGHT);
            buttonLink.setWaves(WavesType.DEFAULT);
        }
        buttonLink.addClickHandler(clickEvent -> transitTo.go());
    }

    private void addButtons() {
        this.add(buttonMap);
        this.add(buttonFav);
    }

    private void updateNavButtons(@Observes PageLoaded event) {
        Scheduler.get().scheduleDeferred(() -> selectActiveNavButton(event));
    }

    void selectActiveNavButton(PageLoaded event) {
        if (event.getPageName().equals(FavouritesViewModel.PAGE_NAME) || event.getPageName().equals(HowToViewModel.PAGE_NAME)) {
            switchActiveNavButton(buttonFav, buttonMap);
        } else {
            switchActiveNavButton(buttonMap, buttonFav);
        }
    }

    private void switchActiveNavButton(MaterialLink activeButton, MaterialLink inactiveButton) {
        activeButton.getElement().addClassName(CSS_CLASS_TAB_ACTIVE);
        inactiveButton.getElement().removeClassName(CSS_CLASS_TAB_ACTIVE);
    }

    abstract boolean isHiddenOnMobile();
}
