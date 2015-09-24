package org.vaadin.artur.portletpush;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

public class PortletPushServletService extends VaadinServletService {

    public PortletPushServletService(VaadinServlet servlet,
            DeploymentConfiguration deploymentConfiguration)
                    throws ServiceException {
        super(servlet, deploymentConfiguration);
    }

    @Override
    public String getServiceName() {
        return "MyPortletService";
    }

    @Override
    protected boolean requestCanCreateSession(VaadinRequest request) {
        // Ensure a portlet session is created, not a servlet session
        return false;
    }
}
