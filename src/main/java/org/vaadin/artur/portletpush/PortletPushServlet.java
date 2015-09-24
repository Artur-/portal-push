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
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession(false);
        if (s == null) {
            System.out.println("Session null in portlet handleRequest");
        } else {
            System.out.println(
                    "ServletSession.portlet: " + s.getAttribute("portlet"));
            System.out.println(
                    "ServletSession.servlet: " + s.getAttribute("servlet"));
            s.setAttribute("servlet", "Set in servlet service");
        }

        super.service(request, response);
    }

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
