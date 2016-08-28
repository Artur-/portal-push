package org.vaadin.artur.portalpush;

import java.util.List;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.RequestHandler;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletService;

public class PushServletService extends VaadinServletService {

    private static String serviceName = null;

    public PushServletService(PushServlet servlet,
            DeploymentConfiguration deploymentConfiguration)
            throws ServiceException {
        super(servlet, deploymentConfiguration);
    }

    @Override
    public String getServiceName() {
        // Must match portlet to use same VaadinSession
        return serviceName;
    }

    @Override
    protected List<RequestHandler> createRequestHandlers()
            throws ServiceException {
        List<RequestHandler> requestHandlers = super.createRequestHandlers();
        requestHandlers.add(new PushRequestHandler(this));
        return requestHandlers;
    }

    @Override
    protected boolean requestCanCreateSession(VaadinRequest request) {
        // Ensure a portlet session is created, not a servlet session.
        // This is just a precaution.
        return false;
    }

    public static void setServiceName(String serviceName) {
        PushServletService.serviceName = serviceName;
    }
}
