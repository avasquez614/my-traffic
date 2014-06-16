package org.mytraffic.priv.api;

/**
 * Commons constants for private API.
 *
 * @author avasquez
 * @author mariobarque
 */
public class PrivateApiConstants {

    private PrivateApiConstants() {
    }

    public static final String BASE_URL_REST_API =          "/api/1/private";
    public static final String BASE_URL_TRAFFIC_INCIDENTS = BASE_URL_REST_API + "/traffic_incidents";
    public static final String BASE_URL_FAVORITE_ROUTES =   BASE_URL_REST_API + "/favorite_routes";
    public static final String BASE_URL_LOCATION =          BASE_URL_REST_API + "/location";

    public static final String PATH_VAR_ID = "id";

    public static final String URL_TRAFFIC_INCIDENTS_FIND_BY_ID =           "/{" + PATH_VAR_ID + "}";
    public static final String URL_TRAFFIC_INCIDENTS_FIND_BY_DATE_RANGE =   "/by_date_range";
    public static final String URL_TRAFFIC_INCIDENTS_ADD =                  "/add";
    public static final String URL_TRAFFIC_INCIDENTS_UPDATE =               "/update";
    public static final String URL_TRAFFIC_INCIDENTS_REMOVE =               "/{" + PATH_VAR_ID + "}/remove";

    public static final String URL_FAVORITE_ROUTES_FIND_BY_USER_ID =                    "/by_user_id";
    public static final String URL_FAVORITE_ROUTES_FIND_BY_NOTIFICATION_TIME_RANGE =    "/by_notification_time_range";
    public static final String URL_FAVORITE_ROUTES_ADD =                                "/add";
    public static final String URL_FAVORITE_ROUTES_UPDATE =                             "/update";
    public static final String URL_FAVORITE_ROUTES_REMOVE =                             "/{" + PATH_VAR_ID + "}/remove";

    public static final String URL_LOCATION_LOCATION_ON_ROUTE = "/location_on_route";

    public static final String PARAM_FROM =         "from";
    public static final String PARAM_TO =           "to";
    public static final String PARAM_USER_ID =      "userId";
    public static final String PARAM_LATITUDE =     "lat";
    public static final String PARAM_LONGITUDE =    "lng";
    public static final String PARAM_POLYLINE =     "poly";

    public static final String DATE_TIME_FORMAT =   "yyyy-MM-dd'T'HH:mm:ss";
    public static final String TIME_FORMAT =        "HH:mm:ss";

}
