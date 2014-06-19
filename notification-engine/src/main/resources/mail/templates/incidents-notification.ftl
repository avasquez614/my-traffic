<strong>Hello ${profile.attributes.firstName} ${profile.attributes.lastName}!</strong>

<p>We want to notify you that there have been incidents along some of the routes you have registered:</p>

<#list routesWithIncidents as routeWithIncident>
<h4>Traffic incidents for ${routeWithIncident.route.description}</h4>

<#list routeWithIncident.incidents as incident>
<strong>Type:</strong> ${incident.type}<br/>
<strong>Description:</strong> ${incident.description}<br/>
<strong>Severity:</strong> ${incident.severity}<br/>
<strong>Location (lat, lng):</strong> ${incident.location.latitude}, ${incident.location.longitude}<br/>
<strong>Start:</strong> ${formatter.format(incident.start)}<br/>
<strong>Last Change:</strong> ${formatter.format(incident.lastModified)}<br/>
<br/>
</#list>
</#list>

<p>You can use this information to plan your next trip carefully.</p>

<p>Bon Voyage!</p>
<p>The My Traffic Team</p>