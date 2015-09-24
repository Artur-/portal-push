package org.vaadin.artur.portletpush;

import javax.portlet.PortletSession;

import com.vaadin.server.VaadinPortletService;
import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedPortletSession;
import com.vaadin.server.WrappedSession;

public class SharedVaadinPortletSession extends VaadinPortletSession {
    public SharedVaadinPortletSession(VaadinPortletService service) {
        super(service);
    }

    @Override
    public void storeInSession(VaadinService service, WrappedSession session) {
        super.storeInSession(service, session);
        ((WrappedPortletSession) session).getPortletSession().setAttribute(
                getSessionAttributeName(service), this,
                PortletSession.APPLICATION_SCOPE);

    }

    private static String getSessionAttributeName(VaadinService service) {
        return VaadinSession.class.getName() + "." + service.getServiceName();
    }
}
