/*
 * Copyright 2000-2014 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.vaadin.artur.portalpush;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.vaadin.server.ServiceException;
import com.vaadin.server.ServletPortletHelper;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.VaadinServletService;
import com.vaadin.server.VaadinSession;

public class PushRequestHandler
        extends com.vaadin.server.communication.PushRequestHandler {

    public PushRequestHandler(VaadinServletService service)
            throws ServiceException {
        super(service);
    }

    @Override
    public boolean handleRequest(VaadinSession session, VaadinRequest request,
            VaadinResponse response) throws IOException {

        if (!ServletPortletHelper.isPushRequest(request)) {
            return false;
        }

        if (request instanceof VaadinServletRequest) {
            if (((VaadinServletRequest) request).getSession(false) == null) {
                // No HTTP session create, do not forward to Atmosphere as it
                // might create one with the wrong cookie URL
                response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,
                        "No HTTP session available. Retry in a second.");
                return true;

            }
        }
        return super.handleRequest(session, request, response);
    }
}
