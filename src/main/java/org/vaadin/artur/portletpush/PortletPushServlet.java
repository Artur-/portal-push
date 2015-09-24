package org.vaadin.artur.portletpush;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

@WebServlet(urlPatterns = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = MyPortletUI.class)
public class PortletPushServlet extends VaadinServlet {

    @Override
    protected VaadinServletService createServletService(
            DeploymentConfiguration deploymentConfiguration)
                    throws ServiceException {
        PortletPushServletService service = new PortletPushServletService(this,
                deploymentConfiguration);
        service.init();
        return service;
    }
}
