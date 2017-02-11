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

package pl.itrack.client.local;

import org.jboss.errai.enterprise.client.cdi.AbstractErraiCDITest;
import org.jboss.errai.ioc.client.container.IOC;
import org.jboss.errai.ioc.client.container.SyncBeanDef;

public abstract class AbstractTristarCDITest extends AbstractErraiCDITest {

    private static final String TESTS_MODULE_NAME = "pl.itrack.TristarEyeTest";

    @Override
    public String getModuleName() {
        return TESTS_MODULE_NAME;
    }

    protected <T> T lookupBeanInstance(Class<T> type) {
        T instance = null;
        SyncBeanDef<T> bean = IOC.getBeanManager().lookupBean(type);
        if (bean != null) {
            instance = bean.getInstance();
            assertNotNull(instance);
        }
        return instance;
    }
}
