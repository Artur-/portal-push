package org.vaadin.artur.portalpush;

import javax.portlet.PortletConfig;
import javax.portlet.PortletException;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinPortlet;
import com.vaadin.server.VaadinPortletService;

public class PushPortlet extends VaadinPortlet {

    @Override
    protected VaadinPortletService createPortletService(
            DeploymentConfiguration deploymentConfiguration)
                    throws ServiceException {
        PushPortletService service = new PushPortletService(this,
                deploymentConfiguration);
        service.init();
        return service;
    }

    @Override
    public void init(PortletConfig config) throws PortletException {
        super.init(config);

    }
}
