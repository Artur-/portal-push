package org.vaadin.artur.portletpush;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinPortlet;
import com.vaadin.server.VaadinPortletService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;

/**
 *
 * @author ${user.name} This custom Vaadin portlet allows for serving Vaadin
 *         resources like theme or widgetset from its web context (instead of
 *         from ROOT). Usually it doesn't need any changes.
 *
 */
public class CustomVaadinPortlet extends VaadinPortlet {

    private class CustomVaadinPortletService extends VaadinPortletService {

        public CustomVaadinPortletService(final VaadinPortlet portlet,
                final DeploymentConfiguration config) throws ServiceException {
            super(portlet, config);
        }

        @Override
        public boolean ensurePushAvailable() {
            return true;
        }

        @Override
        public String getServiceName() {
            return "MyPortletService";
        }

        @Override
        protected VaadinSession createVaadinSession(VaadinRequest request)
                throws ServiceException {
            return new SharedVaadinPortletSession(this);
        }
    }

    @Override
    protected VaadinPortletService createPortletService(
            final DeploymentConfiguration deploymentConfiguration)
                    throws ServiceException {
        final CustomVaadinPortletService customVaadinPortletService = new CustomVaadinPortletService(
                this, deploymentConfiguration);
        customVaadinPortletService.init();
        return customVaadinPortletService;
    }

}
