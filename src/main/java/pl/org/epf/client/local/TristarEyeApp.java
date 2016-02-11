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

package pl.org.epf.client.local;

import static pl.org.epf.client.local.TristarEyeApp.TEMPLATE_ROOT;
import org.jboss.errai.ui.shared.ServerTemplateProvider;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.shared.api.annotations.DataField;

import com.google.gwt.user.client.ui.Composite;

import pl.org.epf.client.local.view.MapTabView;
import pl.org.epf.client.local.view.widgets.Navigation;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@EntryPoint
@Templated(value=TEMPLATE_ROOT, provider=ServerTemplateProvider.class)
public class TristarEyeApp extends Composite {

    public static final String TEMPLATE_ROOT = "index.html#root";

    public static final String CONTENT_CONTAINER = "content";

    @Inject
    @DataField
    private Navigation tristarNavigation;

    @Inject
    @DataField(value = CONTENT_CONTAINER)
    private MapTabView mapPanel; // tab opened as default

    @PostConstruct
    public void init() {
    	mapPanel.setSearchBox(tristarNavigation.getSearchBox());
    }
}
