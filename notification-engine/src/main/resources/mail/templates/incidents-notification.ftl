<strong>Hello!</strong>

<p>We want to notify you that there have been incidents along some of the routes you have registered:</p>

<#list routesWithIncidents?keys as routeName>
<h4>Traffic incidents for ${routeName}</h4>

<#list routesWithIncidents[key] as incident>
<strong>Type:</strong> ${incident.type}<br/>
<strong>Description:</strong> ${incident.description}<br/>
<strong>Severity:</strong> ${incident.severity}<br/>
<strong>Location (lat, lng):</strong> ${incident.location.latitude}, ${incident.location.longitude}<br/>
<strong>Start:</strong> ${dateFormatter.format(incident.start)}<br/>
<strong>Last Change:</strong> ${dateFormatter.format(incident.lastModified)}<br/>
<br/>
</#list>
</#list>

<p>You can use this information to plan your next trip carefully.</p>

<p>Bon Voyage!</p>
<p>The My Traffic Team</p>