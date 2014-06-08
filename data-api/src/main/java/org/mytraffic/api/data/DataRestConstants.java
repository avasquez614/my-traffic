package org.mytraffic.api.data;

/**
 * Commons constants for data services.
 *
 * @author avasquez
 * @author mariobarque
 */
public class DataRestConstants {

    private DataRestConstants() {
    }

    public static final String BASE_URL_REST_API =          "/api/1/data";
    public static final String BASE_URL_TRAFFIC_INCIDENTS = BASE_URL_REST_API + "/traffic_incidents";
    public static final String BASE_URL_FAVORITE_ROUTES =   BASE_URL_REST_API + "/favorite_routes";

    public static final String PATH_VAR_ID = "id";

    public static final String URL_TRAFFIC_INCIDENTS_FIND_BY_ID =           "/" + PATH_VAR_ID;
    public static final String URL_TRAFFIC_INCIDENTS_FIND_BY_DATE_RANGE =   "/by_date_range";
    public static final String URL_TRAFFIC_INCIDENTS_ADD =                  "/add";
    public static final String URL_TRAFFIC_INCIDENTS_UPDATE =               "/update";
    public static final String URL_TRAFFIC_INCIDENTS_REMOVE =               "/" + PATH_VAR_ID + "/remove";

    public static final String URL_FAVORITE_ROUTES_FIND_BY_USER_ID =                    "/by_user_id";
    public static final String URL_FAVORITE_ROUTES_FIND_BY_NOTIFICATION_TIME_RANGE =    "/by_notification_time_range";
    public static final String URL_FAVORITE_ROUTES_ADD =                                "/add";
    public static final String URL_FAVORITE_ROUTES_UPDATE =                             "/update";
    public static final String URL_FAVORITE_ROUTES_REMOVE =                             "/" + PATH_VAR_ID + "/remove";

    public static final String PARAM_FROM =     "from";
    public static final String PARAM_TO =       "to";
    public static final String PARAM_USER_ID =  "userId";

    public static final String DATE_TIME_FORMAT =   "yyyy-MM-dd'T'HH:mm:ss";
    public static final String TIME_FORMAT =        "HH:mm:ss";

}
