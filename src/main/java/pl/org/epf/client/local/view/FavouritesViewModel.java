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

package pl.org.epf.client.local.view;

import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import static pl.org.epf.client.local.view.FavouritesViewModel.PAGE_NAME;

@Page(path = PAGE_NAME)
@Templated("Favourites.html#favouritesRoot")
public class FavouritesViewModel extends Composite {

    public static final String PAGE_NAME = "favourites";

}
