package org.vaadin.artur.portalpush;

import java.util.List;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.RequestHandler;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinPortletService;
import com.vaadin.server.communication.PortletUIInitHandler;

public class PushPortletService extends VaadinPortletService {

    public PushPortletService(PushPortlet portlet,
            DeploymentConfiguration deploymentConfiguration)
            throws ServiceException {
        super(portlet, deploymentConfiguration);
        PushServletService.setServiceName(getServiceName());
    }

    @Override
    protected List<RequestHandler> createRequestHandlers()
            throws ServiceException {
        List<RequestHandler> list = super.createRequestHandlers();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof PortletUIInitHandler) {
                list.set(i, new PortalPushUIInitHandler());
            }
        }
        return list;
    }

}
