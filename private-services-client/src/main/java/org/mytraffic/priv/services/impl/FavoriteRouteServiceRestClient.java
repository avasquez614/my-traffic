package org.mytraffic.priv.services.impl;

import org.craftercms.commons.rest.RestClientUtils;
import org.mytraffic.FavoriteRoute;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.FavoriteRouteService;
import org.mytraffic.utils.datetime.DateTimeUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalTime;
import java.util.List;

import static org.mytraffic.priv.api.PrivateApiConstants.*;

/**
 * REST client implementation of {@link org.mytraffic.priv.api.services.FavoriteRouteService}.
 *
 * @author avasquez
 * @author mariobarque
 */
public class FavoriteRouteServiceRestClient extends AbstractPrivateServicesRestClientBase implements FavoriteRouteService {

    public static final ParameterizedTypeReference<List<FavoriteRoute>> favoriteRouteListTypeRef =
            new ParameterizedTypeReference<List<FavoriteRoute>>() {};


    @Override
    public List<FavoriteRoute> findFavoriteRoutesByUserId(String userId) throws PrivateApiException {
        String url = getAbsoluteUrl(URL_FAVORITE_ROUTES_FIND_BY_USER_ID);

        return doGetForObject(url, favoriteRouteListTypeRef, userId);
    }

    @Override
    public List<FavoriteRoute> findFavoriteRoutesByNotificationTimeRange(LocalTime from, LocalTime to)
            throws PrivateApiException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        RestClientUtils.addValue(PARAM_FROM, DateTimeUtils.formatTime(from), params);
        RestClientUtils.addValue(PARAM_TO, DateTimeUtils.formatTime(to), params);

        String url = getAbsoluteUrl(URL_FAVORITE_ROUTES_FIND_BY_NOTIFICATION_TIME_RANGE);
        url = RestClientUtils.addQueryParams(url, params, false);

        return doGetForObject(url, favoriteRouteListTypeRef);
    }

    @Override
    public FavoriteRoute addFavoriteRoute(FavoriteRoute route) throws PrivateApiException {
        String url = getAbsoluteUrl(URL_FAVORITE_ROUTES_REMOVE);

        return doPostForObject(url, route, FavoriteRoute.class);
    }

    @Override
    public FavoriteRoute updateFavoriteRoute(FavoriteRoute route) throws PrivateApiException {
        String url = getAbsoluteUrl(URL_FAVORITE_ROUTES_UPDATE);

        return doPostForObject(url, route, FavoriteRoute.class);
    }

    @Override
    public void removeFavoriteRoute(String id) throws PrivateApiException {
        String url = getAbsoluteUrl(URL_FAVORITE_ROUTES_REMOVE);

        doDelete(url, id);
    }

}
