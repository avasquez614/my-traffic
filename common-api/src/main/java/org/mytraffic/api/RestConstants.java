package org.mytraffic.api;

/**
 * Commons constants.
 *
 * @author avasquez
 * @author mariobarque
 */
public class RestConstants {

    private RestConstants() {
    }

    public static final String BASE_URL_REST_API =                  "/api/1";
    public static final String BASE_URL_DATA_API =                  BASE_URL_REST_API + "/data";
    public static final String BASE_URL_DATA_TRAFFIC_INCIDENTS =    BASE_URL_DATA_API + "/traffic_incidents";
    public static final String BASE_URL_DATA_FAVORITE_ROUTES =      BASE_URL_DATA_API + "/favorite_routes";

    public static final String PATH_VAR_ID =    "id";

    public static final String URL_DATA_TRAFFIC_INCIDENTS_FIND_BY_ID =          "/" + PATH_VAR_ID;
    public static final String URL_DATA_TRAFFIC_INCIDENTS_FIND_BY_DATE_RANGE =  "/by_date_range";
    public static final String URL_DATA_TRAFFIC_INCIDENTS_ADD =                 "/add";
    public static final String URL_DATA_TRAFFIC_INCIDENTS_UPDATE =              "/update";
    public static final String URL_DATA_TRAFFIC_INCIDENTS_REMOVE =              "/" + PATH_VAR_ID + "/remove";

    public static final String PARAM_FROM = "from";
    public static final String PARAM_TO =   "to";

}
