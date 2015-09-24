# portal-push
Proof of Concept of using Vaadin push in a Liferay portal

# Building
1. Define a Liferay profile in your ~/.m2/settings.xml as described in https://vaadin.com/book/-/page/portal.liferay.html
2. Run ```mvn install liferay:deploy``` (Remember to use ```-P liferay``` or similar to enable your Liferay profile if needed)
3. Start Liferay and add the portlet

# Issues
1. Probably the Liferay session will time out when all messages are sent through a websocket connection. To deal with this you could force push to use long polling or update to 7.6, and use the WEBSOCKET_XHR transport mechanism.
2. Discovered anything else? Create an issue
