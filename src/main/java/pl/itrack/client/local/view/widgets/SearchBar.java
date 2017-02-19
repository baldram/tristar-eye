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

import com.google.gwt.user.client.ui.TextBox;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialSearch;
import gwt.material.design.client.ui.html.Nav;
import org.apache.commons.lang3.StringUtils;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import pl.itrack.client.local.services.maps.MapSearchInputProvider;
import pl.itrack.client.local.view.MapTabViewModel;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SearchBar extends Nav {

    static final String CSS_CLASS_SEARCH_BTN = "tristarNavSearchBtn";

    private final TransitionTo<MapTabViewModel> toMap;

    private final MaterialLink searchButton;

    private final MaterialSearch search;

    @Inject
    public SearchBar(TransitionTo<MapTabViewModel> toMap, MaterialLink searchButton, MaterialSearch search) {
        this.toMap = toMap;
        this.searchButton = searchButton;
        this.search = search;
    }

    @PostConstruct
    public void init() {
        search.addValueChangeHandler(valueChangeEvent -> toMap.go());
        search.addCloseHandler(closeEvent -> search.setText(StringUtils.EMPTY));
        searchButton.setIconType(IconType.SEARCH);
        searchButton.addClickHandler(clickEvent -> search.open());
        searchButton.getElement().addClassName(CSS_CLASS_SEARCH_BTN);
        this.add(search);
    }

    MaterialSearch getSearchBox() {
        return search;
    }

    MaterialLink getSearchButton() {
        return searchButton;
    }

    @Produces
    @SuppressWarnings("unused")
    private MapSearchInputProvider<TextBox> getMapSearch() {
        return () -> (TextBox) search.asValueBoxBase();
    }

}
