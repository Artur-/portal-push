package org.vaadin.artur.portalpushdemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@Push
public class PortalPushDemoUI extends UI {

    private ScheduledExecutorService executor = Executors
            .newScheduledThreadPool(1);
    private VerticalLayout layout;

    @Override
    protected void init(VaadinRequest request) {
        layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        final Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label("Hello user!"));

            }
        });
        layout.addComponent(button);

        executor.scheduleAtFixedRate(new ScheduledRunnable(), 1, 1,
                TimeUnit.SECONDS);
    }

    private class ScheduledRunnable implements Runnable {

        @Override
        public void run() {
            access(new Runnable() {
                @Override
                public void run() {
                    layout.addComponent(new Label("Hello from the server"));
                }
            });
        }
    }

}
