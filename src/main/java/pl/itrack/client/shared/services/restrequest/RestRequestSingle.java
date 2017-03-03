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

package pl.itrack.client.shared.services.restrequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;
import org.apache.commons.lang3.StringUtils;
import org.jboss.errai.enterprise.client.jaxrs.MarshallingWrapper;

// TODO: To use JAX-RS instead
public abstract class RestRequestSingle<T> implements RestRequest {

    private final Class<T> typeParameterClass;

    private String apiUrl;

    protected RestRequestSingle(Class<T> typeParameterClass, String apiUrl) {
        this.typeParameterClass = typeParameterClass;
        this.apiUrl = apiUrl;
    }

    public void call() throws RequestException {
        call(StringUtils.EMPTY);
    }

    public void call(String path) throws RequestException {
        RequestBuilder request = new RequestBuilder(RequestBuilder.GET, apiUrl + path);
        request.sendRequest(null, new RequestSingleCallback<T>(typeParameterClass) {
            @Override
            void execute(T occurrences) {
                apply(occurrences);
            }
        });
    }

    public abstract void apply(T result);

    abstract class RequestSingleCallback<R> implements RequestCallback {

        private final Class<T> typeParameterClass;

        protected RequestSingleCallback(Class<T> typeParameterClass) {
            this.typeParameterClass = typeParameterClass;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onResponseReceived(Request request, Response response) {
            String result = response.getText();

            R responseObject = (R) MarshallingWrapper.fromJSON(result, typeParameterClass);
            if (responseObject != null) {
                execute(responseObject);
            }
        }

        abstract void execute(R result);

        @Override
        public void onError(Request request, Throwable exception) {
            GWT.log(FAILURE_MESSAGE);
        }
    }

}