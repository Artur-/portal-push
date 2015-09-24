# portal-push
Proof of Concept of using Vaadin push in a portal

# Using with any portal
1. Run ```mvn clean install```
2. Deploy the war file
3. Start the portal and add the portlet

# Using with Liferay
1. Define a Liferay profile in your ~/.m2/settings.xml as described in https://vaadin.com/book/-/page/portal.liferay.html
2. Run ```mvn install liferay:deploy``` (Remember to use ```-P liferay``` or similar to enable your Liferay profile if needed)
3. Start Liferay and add the portlet

# Potential issues
This PoC has been briefly tested with Liferay 6 and GateIn 3.

It might be that the portal session will time out when all messages are sent through a websocket connection. To deal with this you could force push to use long polling or update to 7.6, and use the WEBSOCKET_XHR transport mechanism.

Does it work or not with your portal? Discovered an issue? Please let me know.
