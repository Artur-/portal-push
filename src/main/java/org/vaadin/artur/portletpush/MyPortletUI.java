package org.vaadin.artur.portletpush;

import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinPortletRequest;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedPortletSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@Widgetset("org.vaadin.artur.portletpush.PortletPushWidgetSet")
@Push
public class MyPortletUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        PortletRequest pr = ((VaadinPortletRequest) request)
                .getPortletRequest();
        final String portletContextName = getPortletContextName(request);
        // Send push requests to our servlet
        getPushConfiguration().setPushUrl(pr.getContextPath() + "/PUSH");

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        final Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label(
                        "Hello, World!<br>This is portlet "
                                + portletContextName, ContentMode.HTML));

            }
        });
        layout.addComponent(button);
        Thread t = new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {

                while (i++ < 50) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    access(new Runnable() {
                        @Override
                        public void run() {
                            layout.addComponent(new Label(
                                    "Hello from the server"));
                        }
                    });
                }
            }
        });
        t.start();
    }

    private String getPortletContextName(VaadinRequest request) {
        WrappedPortletSession wrappedPortletSession = (WrappedPortletSession) request
                .getWrappedSession();
        PortletSession portletSession = wrappedPortletSession
                .getPortletSession();

        final PortletContext context = portletSession.getPortletContext();
        final String portletContextName = context.getPortletContextName();
        return portletContextName;
    }

}
