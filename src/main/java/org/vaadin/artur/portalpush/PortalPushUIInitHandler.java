package org.vaadin.artur.portalpush;

import java.io.IOException;

import javax.portlet.PortletRequest;

import com.vaadin.server.VaadinPortletRequest;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.communication.PortletUIInitHandler;
import com.vaadin.ui.UI;

public class PortalPushUIInitHandler extends PortletUIInitHandler {

    @Override
    protected String getInitialUidl(VaadinRequest request, UI uI)
            throws IOException {
        // Hack as there is no event we can listen to when a UI is created

        PortletRequest pr = ((VaadinPortletRequest) request)
                .getPortletRequest();
        // Send push requests to our servlet
        uI.getPushConfiguration().setPushUrl(pr.getContextPath() + "/PUSH");
        return super.getInitialUidl(request, uI);
    }

}
